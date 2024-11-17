package com.cengizhanozeyranoglu.services;

import com.cengizhanozeyranoglu.dto.DtoSaledCar;
import com.cengizhanozeyranoglu.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
