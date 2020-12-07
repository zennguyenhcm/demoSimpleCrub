package com.vcc.adtech.demosimplecrub.layer.application.dao;

import com.vcc.adtech.demosimplecrub.layer.application.domain.entity.Account;

public interface IAccountDao {
    int updateLoginStatus(String email, String password, boolean isLogin) throws Exception;
}
