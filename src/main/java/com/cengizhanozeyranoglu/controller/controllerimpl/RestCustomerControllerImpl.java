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
}
