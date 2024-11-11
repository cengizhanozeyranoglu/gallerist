package com.cengizhanozeyranoglu.services.servicesimpl;

import com.cengizhanozeyranoglu.dto.*;
import com.cengizhanozeyranoglu.exception.BaseException;
import com.cengizhanozeyranoglu.exception.ErrorMessage;
import com.cengizhanozeyranoglu.exception.MessageType;
import com.cengizhanozeyranoglu.model.Car;
import com.cengizhanozeyranoglu.model.Gallerist;
import com.cengizhanozeyranoglu.model.GalleristCar;
import com.cengizhanozeyranoglu.repository.CarRepository;
import com.cengizhanozeyranoglu.repository.GalleristCarRepository;
import com.cengizhanozeyranoglu.repository.GalleristRepository;
import com.cengizhanozeyranoglu.services.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GalleristCarImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    public GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
        }
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
        }
        Gallerist gallerist = optGallerist.get();
        Car car = optCar.get();

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setGallerist(gallerist);
        galleristCar.setCar(car);
        galleristCar.setCreateTime(new Date());
        return galleristCar;
    }


    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar savedGallerist = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoAddress dtoAddress = new DtoAddress();

        BeanUtils.copyProperties(savedGallerist, dtoGalleristCar);
        BeanUtils.copyProperties(savedGallerist.getGallerist().getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedGallerist.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getCar(), dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setDtoCar(dtoCar);
        dtoGalleristCar.setDtoGallerist(dtoGallerist);

        return dtoGalleristCar;

    }

    @Override
    public List<DtoGalleristCar> getGalleristCarList() {
        List<GalleristCar> galleristCarList = galleristCarRepository.findAll();
        List<DtoGalleristCar> dtoGalleristCarList = new ArrayList<>();

        if (galleristCarList.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "List is empty "));
        } else {

            for (GalleristCar galleristCar : galleristCarList) {

                DtoGallerist dtoGallerist = new DtoGallerist();
                DtoCar dtoCar = new DtoCar();


                BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
                BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);


                DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
                DtoAddress dtoAddress = new DtoAddress();

                BeanUtils.copyProperties(galleristCar, dtoGalleristCar);
                BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), dtoAddress);
                dtoGallerist.setAddress(dtoAddress);


                dtoGalleristCar.setDtoGallerist(dtoGallerist);
                dtoGalleristCar.setDtoCar(dtoCar);
                dtoGalleristCarList.add(dtoGalleristCar);
            }
        }
        return dtoGalleristCarList;

    }

    @Override
    public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU) {

        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);

        if (optGalleristCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        } else {
            Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
            Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
            if (optGallerist.isEmpty()) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
            }
            if (optCar.isEmpty()) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
            }
            GalleristCar galleristCar = optGalleristCar.get();
            Car car = optCar.get();
            Gallerist gallerist = optGallerist.get();

            galleristCar.setCar(car);
            galleristCar.setGallerist(gallerist);
            galleristCarRepository.save(galleristCar);

            DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
            DtoCar dtoCar = new DtoCar();
            DtoAddress dtoAddress = new DtoAddress();
            DtoGallerist dtoGallerist = new DtoGallerist();

            BeanUtils.copyProperties(gallerist, dtoGallerist);
            BeanUtils.copyProperties(galleristCar, dtoGalleristCar);
            BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
            BeanUtils.copyProperties(car, dtoCar);

            dtoGalleristCar.setDtoGallerist(dtoGallerist);
            dtoGalleristCar.setDtoCar(dtoCar);

            return dtoGalleristCar;
        }
    }

    @Override
    public void deleteGalleristCar(Long id) {
        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
        if (optGalleristCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        } else {
            GalleristCar galleristCar = optGalleristCar.get();
            galleristCarRepository.delete(galleristCar);
        }


    }

    @Override
    public DtoGalleristCar getGalleristCarById(Long id) {
        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
        if (optGalleristCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        } else {
            GalleristCar galleristCar = optGalleristCar.get();

            DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
            DtoGallerist dtoGallerist = new DtoGallerist();
            DtoCar dtoCar = new DtoCar();
            DtoAddress dtoAddress = new DtoAddress();

            BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);
            BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(),dtoAddress);
            BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);

            dtoGallerist.setAddress(dtoAddress);
            dtoGalleristCar.setDtoGallerist(dtoGallerist);
            dtoGalleristCar.setDtoCar(dtoCar);
            BeanUtils.copyProperties(galleristCar, dtoGalleristCar);

            return dtoGalleristCar;

        }

    }


}
