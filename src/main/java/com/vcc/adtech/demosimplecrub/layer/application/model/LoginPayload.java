package com.vcc.adtech.demosimplecrub.layer.application.model;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vcc.adtech.demosimplecrub.layer.application.domain.entity.Account;

public class LoginPayload {
    private Account accountInfo = new Account();
    private Long lastLoginTime;

    public Account getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Account accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
