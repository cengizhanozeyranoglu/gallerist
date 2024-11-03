package com.cengizhanozeyranoglu.controller;

import com.cengizhanozeyranoglu.dto.DtoAccount;
import com.cengizhanozeyranoglu.dto.DtoAccountIU;

import java.util.List;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

    public RootEntity<List<DtoAccount>> getAccountList();

    public RootEntity<DtoAccount> updateDtoAccount(Long id, DtoAccountIU dtoAccountIU);

    public void deleteAccount(Long id);

    public RootEntity<DtoAccount> getAccountById(Long id);
}
