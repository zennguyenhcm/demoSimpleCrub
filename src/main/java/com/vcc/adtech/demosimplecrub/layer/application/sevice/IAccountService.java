package com.vcc.adtech.demosimplecrub.layer.application.sevice;

import com.vcc.adtech.demosimplecrub.layer.application.model.LoginPayload;

public interface IAccountService {
    boolean logIn(LoginPayload loginPayload) throws Exception;
}
