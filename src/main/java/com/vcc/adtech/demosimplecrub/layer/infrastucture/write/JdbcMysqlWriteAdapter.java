package com.vcc.adtech.demosimplecrub.layer.infrastucture.write;

import com.mysql.cj.jdbc.Driver;
import com.vcc.adtech.demosimplecrub.config.Settings;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcMysqlWriteAdapter {

    private final Settings setting = Settings.getInstance();

    @Bean(name = "mysqlWriteDataSource")
    public DataSource mysqlLocalDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        String connectionString = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", setting.MYSQL_HOST,
                setting.MYSQL_PORT, setting.MYSQL_DB_NAME);
        dataSource.setUrl(connectionString);
        dataSource.setUsername(setting.MYSQL_USER);
        dataSource.setPassword(setting.MYSQL_PASSWORD);

        dataSource.setInitialSize(0);
        dataSource.setMaxTotal(setting.MYSQL_POOL_SIZE);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQueryTimeout(3);
        dataSource.setValidationQuery("SELECT 1");

        dataSource.setMaxConnLifetimeMillis(900000);
        dataSource.setTimeBetweenEvictionRunsMillis(300000);
        dataSource.setLogExpiredConnections(false);

        return dataSource;
    }

    @Bean(name = "mysqlJdbcWriteTemplate")
    @Autowired
    public JdbcTemplate mysqlLocalJdbcReadTemplate(@Qualifier("mysqlWriteDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(false);
        return jdbcTemplate;
    }
}
