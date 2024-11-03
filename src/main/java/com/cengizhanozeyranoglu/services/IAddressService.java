package com.cengizhanozeyranoglu.services;

import com.cengizhanozeyranoglu.dto.DtoAddress;
import com.cengizhanozeyranoglu.dto.DtoAddressIU;

import java.util.List;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

    public List<DtoAddress> getAddressList();

    public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddresIU);

    public void deleteAddress(Long id);

    public DtoAddress getAddressById(Long id);

}
