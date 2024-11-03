package com.cengizhanozeyranoglu.services;

import com.cengizhanozeyranoglu.controller.RootEntity;
import com.cengizhanozeyranoglu.dto.DtoAccount;
import com.cengizhanozeyranoglu.dto.DtoAccountIU;

import java.util.List;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

    public List<DtoAccount> getAccountList();

    public DtoAccount updateDtoAccount(Long id, DtoAccountIU dtoAccountIU);

    public void deleteAccount(Long id);

    public DtoAccount getAccountById(Long id);
}
