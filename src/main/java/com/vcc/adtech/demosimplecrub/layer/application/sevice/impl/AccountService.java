package com.vcc.adtech.demosimplecrub.layer.application.sevice.impl;

import com.vcc.adtech.demosimplecrub.layer.application.dao.IAccountDao;
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
}

