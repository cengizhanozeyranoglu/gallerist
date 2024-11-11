package com.cengizhanozeyranoglu.controller;


import com.cengizhanozeyranoglu.dto.DtoGalleristCar;
import com.cengizhanozeyranoglu.dto.DtoGalleristCarIU;

import java.util.List;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

    public RootEntity<List<DtoGalleristCar>> getGalleristCarList();

    public RootEntity<DtoGalleristCar> updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);

    public void deleteGalleristCar(Long id);

    public RootEntity<DtoGalleristCar> getGalleristCarById(Long id);


}
