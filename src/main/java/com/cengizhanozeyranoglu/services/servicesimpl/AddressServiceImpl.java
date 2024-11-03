package com.cengizhanozeyranoglu.services.servicesimpl;

import com.cengizhanozeyranoglu.dto.DtoAddress;
import com.cengizhanozeyranoglu.dto.DtoAddressIU;
import com.cengizhanozeyranoglu.model.Address;
import com.cengizhanozeyranoglu.repository.AddressRepository;
import com.cengizhanozeyranoglu.services.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU) {
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU, address);
        return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        Address saveAddress = addressRepository.save(createAddress(dtoAddressIU));
        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(saveAddress, dtoAddress);
        return dtoAddress;


    }
}
