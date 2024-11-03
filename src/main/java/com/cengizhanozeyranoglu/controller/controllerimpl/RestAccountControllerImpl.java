package com.cengizhanozeyranoglu.controller.controllerimpl;

import com.cengizhanozeyranoglu.controller.IRestAccountController;
import com.cengizhanozeyranoglu.controller.RestBaseController;
import com.cengizhanozeyranoglu.controller.RootEntity;
import com.cengizhanozeyranoglu.dto.DtoAccount;
import com.cengizhanozeyranoglu.dto.DtoAccountIU;
import com.cengizhanozeyranoglu.services.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping(path = "/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.saveAccount(dtoAccountIU));
    }

    @GetMapping(path = "/getList")
    @Override
    public RootEntity<List<DtoAccount>> getAccountList() {
        return ok(accountService.getAccountList());
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public RootEntity<DtoAccount> updateDtoAccount(@PathVariable(name = "id") Long id, @Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.updateDtoAccount(id, dtoAccountIU));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteAccount(@Valid @PathVariable(name = "id") Long id) {
        accountService.deleteAccount(id);

    }

    @GetMapping(path = "/get/{id}")
    @Override
    public RootEntity<DtoAccount> getAccountById(@PathVariable @Valid Long id) {
        return ok(accountService.getAccountById(id));
    }
}
