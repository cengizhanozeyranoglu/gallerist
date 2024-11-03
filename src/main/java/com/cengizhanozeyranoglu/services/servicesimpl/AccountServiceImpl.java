package com.cengizhanozeyranoglu.services.servicesimpl;

import com.cengizhanozeyranoglu.controller.RootEntity;
import com.cengizhanozeyranoglu.dto.DtoAccount;
import com.cengizhanozeyranoglu.dto.DtoAccountIU;
import com.cengizhanozeyranoglu.exception.BaseException;
import com.cengizhanozeyranoglu.exception.ErrorMessage;
import com.cengizhanozeyranoglu.exception.MessageType;
import com.cengizhanozeyranoglu.model.Account;
import com.cengizhanozeyranoglu.repository.AccountRepository;
import com.cengizhanozeyranoglu.services.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU) {
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU, account);
        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        DtoAccount dtoAccount = new DtoAccount();
        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
        BeanUtils.copyProperties(savedAccount, dtoAccount);
        return dtoAccount;
    }

    @Override
    public List<DtoAccount> getAccountList() {
        List<DtoAccount> dtoAccountList = new ArrayList<>();
        List<Account> accountList = accountRepository.findAll();
        for (Account account : accountList) {
            DtoAccount dtoAccount = new DtoAccount();
            BeanUtils.copyProperties(account, dtoAccount);
            dtoAccountList.add(dtoAccount);
        }
        return dtoAccountList;
    }

    @Override
    public DtoAccount updateDtoAccount(Long id, DtoAccountIU dtoAccountIU) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            DtoAccount dtoAccount = new DtoAccount();
            BeanUtils.copyProperties(dtoAccountIU, account, "id");
            accountRepository.save(account);
            BeanUtils.copyProperties(account, dtoAccount);
            return dtoAccount;
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }

    }

    @Override
    public void deleteAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            accountRepository.delete(optionalAccount.get());
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
    }

    @Override
    public DtoAccount getAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            DtoAccount dtoAccount = new DtoAccount();
            BeanUtils.copyProperties(optionalAccount.get(), dtoAccount);
            return dtoAccount;
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
    }
}