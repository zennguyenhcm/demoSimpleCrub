package com.vcc.adtech.demosimplecrub.layer.application.sevice.impl;

import com.vcc.adtech.demosimplecrub.layer.application.dao.IAccountDao;
import com.vcc.adtech.demosimplecrub.layer.application.domain.entity.Account;
import com.vcc.adtech.demosimplecrub.layer.application.model.LoginPayload;
import com.vcc.adtech.demosimplecrub.layer.application.sevice.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    @Override
    public boolean logIn(LoginPayload loginPayload) throws Exception {
        accountDao.updateLoginStatus(loginPayload.getAccountInfo().getEmail(), loginPayload.getAccountInfo().getPassword(), true);
        return false;
    }

    @Override
    public boolean logOut(LoginPayload loginPayload) throws Exception {
        accountDao.updateLogoutStatus(loginPayload.getAccountInfo().getEmail(),loginPayload.getAccountInfo().getPassword(),false);

        return false;
    }

    @Override
    public Account findAccountById(String id) throws Exception {
        Account acc =accountDao.findAccountById(id);
        return acc ;
    }

}

