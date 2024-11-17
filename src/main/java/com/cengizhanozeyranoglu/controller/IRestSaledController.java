package com.cengizhanozeyranoglu.controller;

import com.cengizhanozeyranoglu.dto.DtoSaledCar;
import com.cengizhanozeyranoglu.dto.DtoSaledCarIU;

public interface IRestSaledController {

    public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
