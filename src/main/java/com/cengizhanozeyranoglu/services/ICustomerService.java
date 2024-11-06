package com.cengizhanozeyranoglu.services;

import com.cengizhanozeyranoglu.dto.DtoCustomer;
import com.cengizhanozeyranoglu.dto.DtoCustomerIU;

import java.util.List;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

    public List<DtoCustomer> getCustomerList();

    public DtoCustomer updateCustomer(Long id,DtoCustomerIU dtoCustomerIU);

    public void deleteCustomer(Long id);

    public DtoCustomer getCustomerById(Long id);
}
