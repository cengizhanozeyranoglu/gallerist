package com.cengizhanozeyranoglu.controller;

import com.cengizhanozeyranoglu.dto.DtoCustomer;
import com.cengizhanozeyranoglu.dto.DtoCustomerIU;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

}
