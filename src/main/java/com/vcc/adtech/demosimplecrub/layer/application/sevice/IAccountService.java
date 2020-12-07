package com.vcc.adtech.demosimplecrub.layer.application.sevice;

import com.vcc.adtech.demosimplecrub.layer.application.domain.entity.Account;
import com.vcc.adtech.demosimplecrub.layer.application.model.LoginPayload;

public interface IAccountService {
    boolean logIn(LoginPayload loginPayload) throws Exception;
    boolean logOut(LoginPayload loginPayload) throws Exception;
    Account findAccountById(String id) throws Exception;
}
