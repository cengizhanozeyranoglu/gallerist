package com.cengizhanozeyranoglu.controller;

import com.cengizhanozeyranoglu.dto.DtoCustomer;
import com.cengizhanozeyranoglu.dto.DtoCustomerIU;

import java.util.List;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

    public RootEntity<List<DtoCustomer>> getCustomerList();

    public RootEntity<DtoCustomer> updateCustomer(Long id, DtoCustomerIU dtoCustomerIU);

    public void deleteCustomer(Long id);

    public RootEntity<DtoCustomer> getCustomerById(Long id);

}
