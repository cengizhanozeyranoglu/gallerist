package com.cengizhanozeyranoglu.controller;

import com.cengizhanozeyranoglu.dto.DtoAddress;
import com.cengizhanozeyranoglu.dto.DtoAddressIU;

import java.util.List;


public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);

    public RootEntity<List<DtoAddress>> getAddressList();

    public RootEntity<DtoAddress> updateAddress(Long id, DtoAddressIU dtoAddressIU);

    public void deleteAddress(Long id);

    public RootEntity<DtoAddress> getAddressById(Long id);

}
