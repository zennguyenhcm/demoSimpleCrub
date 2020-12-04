package com.vcc.adtech.demosimplecrub.layer.application.model;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vcc.adtech.demosimplecrub.layer.application.entity.Account;

public class LoginPayload {
    private ObjectNode accountInfo;
    private Long lastLoginTime;

    public ObjectNode getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(ObjectNode accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
