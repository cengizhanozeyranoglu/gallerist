package com.cengizhanozeyranoglu.services.servicesimpl;

import com.cengizhanozeyranoglu.dto.DtoCar;
import com.cengizhanozeyranoglu.dto.DtoCarIU;
import com.cengizhanozeyranoglu.exception.BaseException;
import com.cengizhanozeyranoglu.exception.ErrorMessage;
import com.cengizhanozeyranoglu.exception.MessageType;
import com.cengizhanozeyranoglu.model.Car;
import com.cengizhanozeyranoglu.repository.CarRepository;
import com.cengizhanozeyranoglu.services.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    public Car createCar(DtoCarIU dtoCarIU) {
        Car car = new Car();
        car.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCarIU, car);
        return car;
    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        Car savedCar = carRepository.save(createCar(dtoCarIU));
        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;

    }

    @Override
    public List<DtoCar> getCarList() {
        List<Car> carList = carRepository.findAll();
        List<DtoCar> dtoCarList = new ArrayList<>();
        if (carList.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Araba listesi boş"));
        }
        for (Car car : carList) {
            DtoCar dtoCar = new DtoCar();
            BeanUtils.copyProperties(car, dtoCar);
            dtoCarList.add(dtoCar);
        }

        return dtoCarList;
    }

    @Override
    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Car car = optCar.get();
        BeanUtils.copyProperties(dtoCarIU, car);
        Car savedCar = carRepository.save(car);
        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;
    }

    @Override
    public void deleteCar(Long id) {
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Car car = optCar.get();
        carRepository.delete(car);
    }

    @Override
    public DtoCar getCarById(Long id) {
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Car car = optCar.get();
        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(car, dtoCar);
        return dtoCar;
    }

}
