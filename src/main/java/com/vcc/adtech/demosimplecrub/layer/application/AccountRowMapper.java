package com.vcc.adtech.demosimplecrub.layer.application;

import com.vcc.adtech.demosimplecrub.layer.application.domain.entity.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setEmail(resultSet.getString("email"));
        account.setUserID(resultSet.getString("id"));
        account.setPassword(resultSet.getString("password"));
        account.setRoleID(resultSet.getString("role"));
        account.setLogin(resultSet.getBoolean("is_login"));
        account.setLogin(resultSet.getBoolean("is_active"));




        return account;
    }
}
