package com.cengizhanozeyranoglu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoGalleristCar extends DtoBase{

    private DtoGallerist dtoGallerist;

    private DtoCar dtoCar;
}
