package com.cengizhanozeyranoglu.controller.controllerimpl;

import com.cengizhanozeyranoglu.controller.IRestCustomerController;
import com.cengizhanozeyranoglu.controller.RestBaseController;
import com.cengizhanozeyranoglu.controller.RootEntity;
import com.cengizhanozeyranoglu.dto.DtoCustomer;
import com.cengizhanozeyranoglu.dto.DtoCustomerIU;
import com.cengizhanozeyranoglu.services.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.saveCustomer(dtoCustomerIU));
    }

    @GetMapping(path = "/getList")
    @Override
    public RootEntity<List<DtoCustomer>> getCustomerList() {
        return ok(customerService.getCustomerList());
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public RootEntity<DtoCustomer> updateCustomer(@PathVariable @Valid Long id, @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.updateCustomer(id, dtoCustomerIU));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

    }

    @GetMapping(path = "/get/{id}")
    @Override
    public RootEntity<DtoCustomer> getCustomerById(@PathVariable Long id) {
        return ok(customerService.getCustomerById(id));
    }
}
