package com.cengizhanozeyranoglu.services.servicesimpl;

import com.cengizhanozeyranoglu.dto.DtoAddress;
import com.cengizhanozeyranoglu.dto.DtoGallerist;
import com.cengizhanozeyranoglu.dto.DtoGalleristIU;
import com.cengizhanozeyranoglu.exception.BaseException;
import com.cengizhanozeyranoglu.exception.ErrorMessage;
import com.cengizhanozeyranoglu.exception.MessageType;
import com.cengizhanozeyranoglu.model.Address;
import com.cengizhanozeyranoglu.model.Gallerist;
import com.cengizhanozeyranoglu.repository.AddressRepository;
import com.cengizhanozeyranoglu.repository.GalleristRepository;
import com.cengizhanozeyranoglu.services.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
        } else {
            Address address = optAddress.get();
            Gallerist gallerist = new Gallerist();
            gallerist.setCreateTime(new Date());
            gallerist.setAddress(address);
            BeanUtils.copyProperties(dtoGalleristIU, gallerist);
            return gallerist;
        }
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
        DtoAddress dtoAddress = new DtoAddress();
        DtoGallerist dtoGallerist = new DtoGallerist();
        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }

    @Override
    public List<DtoGallerist> getGalleristList() {
        List<Gallerist> galleristList = galleristRepository.findAll();
        if (galleristList.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "List is empty"));
        } else {
            List<DtoGallerist> dtoGalleristList = new ArrayList<>();
            for (Gallerist gallerist : galleristList) {
                DtoGallerist dtoGallerist = new DtoGallerist();
                BeanUtils.copyProperties(gallerist, dtoGallerist);
                DtoAddress dtoAddress = new DtoAddress();
                BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
                dtoGallerist.setAddress(dtoAddress);
                dtoGalleristList.add(dtoGallerist);

            }
            return dtoGalleristList;
        }

    }

    @Override
    public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU) {
        Optional<Gallerist> optGallerist = galleristRepository.findById(id);
        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        } else {
            Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
            if (optAddress.isEmpty()) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
            } else {
                DtoAddress dtoAddress = new DtoAddress();
                DtoGallerist dtoGallerist = new DtoGallerist();

                Address address = optAddress.get();
                Gallerist gallerist = optGallerist.get();

                BeanUtils.copyProperties(dtoGalleristIU, gallerist);
                gallerist.setAddress(address);
                galleristRepository.save(gallerist);

                BeanUtils.copyProperties(address,dtoAddress);
                BeanUtils.copyProperties(gallerist,dtoGallerist);

                dtoGallerist.setAddress(dtoAddress);
                return dtoGallerist;

            }
        }
    }

    @Override
    public void deleteGallerist(Long id) {
        Optional<Gallerist> optGallerist = galleristRepository.findById(id);
        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Gallerist gallerist = optGallerist.get();
        galleristRepository.delete(gallerist);

    }

    public DtoGallerist getGalleristById(Long id) {
        Optional<Gallerist> optGallerist = galleristRepository.findById(id);
        if(optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Gallerist gallerist = optGallerist.get();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        BeanUtils.copyProperties(gallerist,dtoGallerist);
        BeanUtils.copyProperties(gallerist.getAddress(),dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;

    }

}
