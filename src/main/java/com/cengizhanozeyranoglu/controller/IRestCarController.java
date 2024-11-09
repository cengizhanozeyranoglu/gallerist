package com.cengizhanozeyranoglu.controller;

import com.cengizhanozeyranoglu.dto.DtoCar;
import com.cengizhanozeyranoglu.dto.DtoCarIU;

import java.util.List;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

    public RootEntity<List<DtoCar>> getCarList();

    public RootEntity<DtoCar> updateCar(Long id, DtoCarIU dtoCarIU);

    public void deleteCar(Long id);

    public RootEntity<DtoCar> getCarById(Long id);


}
