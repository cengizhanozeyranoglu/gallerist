package com.cengizhanozeyranoglu.controller;

import com.cengizhanozeyranoglu.dto.DtoGallerist;
import com.cengizhanozeyranoglu.dto.DtoGalleristIU;

import java.util.List;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

    public RootEntity<List<DtoGallerist>> getGalleristList();

    public RootEntity<DtoGallerist> updateGallerist (Long id, DtoGalleristIU dtoGalleristIU);

    public void deleteGallerist(Long id);

    public RootEntity<DtoGallerist> getGalleristById(Long id);


}
