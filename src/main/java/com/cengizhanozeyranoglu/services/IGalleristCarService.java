package com.cengizhanozeyranoglu.services;

import com.cengizhanozeyranoglu.dto.DtoGalleristCar;
import com.cengizhanozeyranoglu.dto.DtoGalleristCarIU;

import java.util.List;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

    public List<DtoGalleristCar> getGalleristCarList();

    public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);

    public void deleteGalleristCar(Long id);

    public DtoGalleristCar getGalleristCarById(Long id);


}
