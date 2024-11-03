package com.cengizhanozeyranoglu.controller.controllerimpl;

import com.cengizhanozeyranoglu.controller.IRestAddressController;
import com.cengizhanozeyranoglu.controller.RestBaseController;
import com.cengizhanozeyranoglu.dto.DtoAddress;
import com.cengizhanozeyranoglu.dto.DtoAddressIU;
import com.cengizhanozeyranoglu.controller.RootEntity;
import com.cengizhanozeyranoglu.services.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU address) {
        return ok(addressService.saveAddress(address));
    }

    @GetMapping(path = "/getList")
    @Override
    public RootEntity<List<DtoAddress>> getAddressList() {
        return ok(addressService.getAddressList());
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public RootEntity<DtoAddress> updateAddress(@PathVariable @Valid Long id, @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.updateAddress(id, dtoAddressIU));
    }

    @DeleteMapping(path = "delete/{id}")
    @Override
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }

    @GetMapping(path = "/get/{id}")
    @Override
    public RootEntity<DtoAddress> getAddressById(@PathVariable Long id) {
        return ok(addressService.getAddressById(id));
    }
}
