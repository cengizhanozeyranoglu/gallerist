package com.cengizhanozeyranoglu.services;

import com.cengizhanozeyranoglu.dto.DtoCar;
import com.cengizhanozeyranoglu.dto.DtoCarIU;

import java.util.List;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);

    public List<DtoCar> getCarList();

    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU);

    public void deleteCar(Long id);

    public DtoCar getCarById(Long id);
}
