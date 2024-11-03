package com.cengizhanozeyranoglu.services.servicesimpl;

import com.cengizhanozeyranoglu.dto.DtoAccount;
import com.cengizhanozeyranoglu.dto.DtoAddress;
import com.cengizhanozeyranoglu.dto.DtoCustomer;
import com.cengizhanozeyranoglu.dto.DtoCustomerIU;
import com.cengizhanozeyranoglu.exception.BaseException;
import com.cengizhanozeyranoglu.exception.ErrorMessage;
import com.cengizhanozeyranoglu.exception.MessageType;
import com.cengizhanozeyranoglu.model.Account;
import com.cengizhanozeyranoglu.model.Address;
import com.cengizhanozeyranoglu.model.Customer;
import com.cengizhanozeyranoglu.repository.AccountRepository;
import com.cengizhanozeyranoglu.repository.AddressRepository;
import com.cengizhanozeyranoglu.repository.CustomerRepository;
import com.cengizhanozeyranoglu.services.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optAddress.isPresent() && optAccount.isPresent()) {

            Customer customer = new Customer();
            customer.setCreateTime(new Date());
            customer.setAddress(optAddress.get());
            customer.setAccount(optAccount.get());

            BeanUtils.copyProperties(dtoCustomerIU, customer);

            return customer;
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"Account ve addres bilgilerini gözden geçiriniz."));
        }
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);

        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoAddress);

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);

        return dtoCustomer;

    }
}
