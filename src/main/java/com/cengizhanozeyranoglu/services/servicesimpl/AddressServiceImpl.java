package com.cengizhanozeyranoglu.services.servicesimpl;

import com.cengizhanozeyranoglu.dto.DtoAddress;
import com.cengizhanozeyranoglu.dto.DtoAddressIU;
import com.cengizhanozeyranoglu.exception.BaseException;
import com.cengizhanozeyranoglu.exception.ErrorMessage;
import com.cengizhanozeyranoglu.exception.MessageType;
import com.cengizhanozeyranoglu.model.Address;
import com.cengizhanozeyranoglu.repository.AddressRepository;
import com.cengizhanozeyranoglu.services.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<DtoAddress> getAddressList() {
        List<Address> addressList = addressRepository.findAll();
        List<DtoAddress> dtoAddressList = new ArrayList<>();
        for (Address address : addressList) {
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(address, dtoAddress);
            dtoAddressList.add(dtoAddress);
        }
        return dtoAddressList;
    }

    @Override
    public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddresIU) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            BeanUtils.copyProperties(dtoAddresIU, address, "id");
            addressRepository.save(address);
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(address, dtoAddress);
            return dtoAddress;
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
    }

    @Override
    public void deleteAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            addressRepository.delete(optionalAddress.get());
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
    }

    @Override
    public DtoAddress getAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(optionalAddress.get(), dtoAddress);
            return dtoAddress;
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
    }


}
