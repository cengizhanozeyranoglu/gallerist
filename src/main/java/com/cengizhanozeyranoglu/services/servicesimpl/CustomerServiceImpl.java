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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Account ve addres bilgilerini gözden geçiriniz."));
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

    @Override
    public List<DtoCustomer> getCustomerList() {
        List<Customer> customerList = customerRepository.findAll();
        List<DtoCustomer> dtoCustomerList;
        if (customerList.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Empty list"));
        } else {
            dtoCustomerList = new ArrayList<>();
            for (Customer customer : customerList) {
                DtoCustomer dtoCustomer = new DtoCustomer();
                DtoAddress dtoAddress = new DtoAddress();
                DtoAccount dtoAccount = new DtoAccount();

                BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
                BeanUtils.copyProperties(customer.getAddress(), dtoAddress);

                BeanUtils.copyProperties(customer, dtoCustomer);
                dtoCustomer.setAddress(dtoAddress);
                dtoCustomer.setAccount(dtoAccount);
                dtoCustomerList.add(dtoCustomer);

            }
        }
        return dtoCustomerList;

    }

    @Override
    public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU) {

        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Customer customer = optCustomer.get();
        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
        }
        if (optAccount.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
        }
        customer.setAccount(optAccount.get());
        customer.setAddress(optAddress.get());
        BeanUtils.copyProperties(dtoCustomerIU, customer);
        customerRepository.save(customer);

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
        BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoAddress);
        BeanUtils.copyProperties(customer, dtoCustomer);
        return dtoCustomer;

    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isPresent()) {
            customerRepository.delete(optCustomer.get());
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
    }

    @Override
    public DtoCustomer getCustomerById(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        } else {
            Customer customer = optCustomer.get();
            DtoCustomer dtoCustomer = new DtoCustomer();
            DtoAddress dtoAddress = new DtoAddress();
            DtoAccount dtoAccount = new DtoAccount();
            BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
            BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
            dtoCustomer.setAccount(dtoAccount);
            dtoCustomer.setAddress(dtoAddress);
            BeanUtils.copyProperties(customer, dtoCustomer);
            return dtoCustomer;
        }


    }
}
