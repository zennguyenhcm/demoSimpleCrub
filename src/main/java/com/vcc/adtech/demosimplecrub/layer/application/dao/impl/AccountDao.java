package com.vcc.adtech.demosimplecrub.layer.application.dao.impl;

import com.vcc.adtech.demosimplecrub.config.Settings;
import com.vcc.adtech.demosimplecrub.layer.application.dao.IAccountDao;
import com.vcc.adtech.demosimplecrub.layer.application.domain.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountDao implements IAccountDao {
    @Autowired
    @Qualifier("mysqlJdbcWriteTemplate")
    private JdbcTemplate mysqlJdbcWriteTemplate;

    @Override
    public int updateLoginStatus(String email, String password, boolean isLogin) throws Exception {
        String sql = String.format("UPDATE %s SET is_login=? WHERE email=? AND password=?",
                Settings.getInstance().MYSQL_ACCOUNT_TABLE);
        return mysqlJdbcWriteTemplate.update(sql, new Object[]{isLogin,email, password});
    }
}
