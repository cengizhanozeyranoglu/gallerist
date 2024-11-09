package com.cengizhanozeyranoglu.services;

import com.cengizhanozeyranoglu.dto.DtoGallerist;
import com.cengizhanozeyranoglu.dto.DtoGalleristIU;

import java.util.List;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

    public List<DtoGallerist> getGalleristList();

    public DtoGallerist updateGallerist(Long id,DtoGalleristIU dtoGalleristIU);

    public void deleteGallerist(Long id);

    public DtoGallerist getGalleristById(Long id);
}
