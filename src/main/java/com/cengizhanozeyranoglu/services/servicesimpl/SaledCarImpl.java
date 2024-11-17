package com.cengizhanozeyranoglu.services.servicesimpl;

import com.cengizhanozeyranoglu.dto.*;
import com.cengizhanozeyranoglu.enums.CarStatusType;
import com.cengizhanozeyranoglu.exception.BaseException;
import com.cengizhanozeyranoglu.exception.ErrorMessage;
import com.cengizhanozeyranoglu.exception.MessageType;
import com.cengizhanozeyranoglu.model.*;
import com.cengizhanozeyranoglu.repository.CarRepository;
import com.cengizhanozeyranoglu.repository.CustomerRepository;
import com.cengizhanozeyranoglu.repository.GalleristRepository;
import com.cengizhanozeyranoglu.repository.SaledCarRepository;
import com.cengizhanozeyranoglu.services.ICurrencyRatesService;
import com.cengizhanozeyranoglu.services.ISaledCarService;
import com.cengizhanozeyranoglu.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarImpl implements ISaledCarService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @Autowired
    private SaledCarRepository saledCarRepository;


    public BigDecimal convertCustomerAmountToUsd(Customer customer) {
        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates("15-11-2024", "15-11-2024");
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        return customerUSDAmount;

    }


    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
        }
        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
        }
        BigDecimal customerUSDAmount = convertCustomerAmountToUsd(optCustomer.get());

        if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0 || customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
            return true;
        }
        return false;

    }

    public boolean checkCarStatus(DtoSaledCarIU dtoSaledCarIU) {
        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
        } else if (optCar.get().getCarStatusType() == (CarStatusType.SALABLE)) {
            return true;
        }
        return false;

    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());
        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
        return saledCar;

    }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car) {
        BigDecimal customerUSDAmount = convertCustomerAmountToUsd(customer);
        BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());


        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates("15-11-2024", "15-11-2024");

        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        return remaningCustomerUSDAmount.multiply(usd);


    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
        if (!checkAmount(dtoSaledCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
        }
        if (checkCarStatus(dtoSaledCarIU)) {
            SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

            Car car = savedSaledCar.getCar();
            car.setCarStatusType(CarStatusType.SALED);
            carRepository.save(car);
            Customer customer = savedSaledCar.getCustomer();
            customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
            customerRepository.save(customer);
            return toDTO(savedSaledCar);
        }
        throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, dtoSaledCarIU.getCustomerId().toString()));

    }

    public DtoSaledCar toDTO(SaledCar saledCar) {
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);

        Address customerAddress = saledCar.getCustomer().getAddress();
        Address galleristAddress = saledCar.getGallerist().getAddress();

        DtoAddress dtoCustomerAddress = new DtoAddress();
        DtoAddress dtoGalleristAddress = new DtoAddress();

        BeanUtils.copyProperties(customerAddress, dtoCustomerAddress);
        BeanUtils.copyProperties(galleristAddress, dtoGalleristAddress);


        DtoAccount dtoAccount = new DtoAccount();
        Account account = saledCar.getCustomer().getAccount();
        BeanUtils.copyProperties(account, dtoAccount);

        BeanUtils.copyProperties(saledCar, dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoCustomerAddress);
        dtoGallerist.setAddress(dtoGalleristAddress);
        dtoSaledCar.setDtoCar(dtoCar);
        dtoSaledCar.setDtoGallerist(dtoGallerist);
        dtoSaledCar.setDtoCustomer(dtoCustomer);
        return dtoSaledCar;
    }
}
