package com.account.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableConfigurationProperties({DatabasePrimary.class})
public class DataSourceBean {

    @Autowired
    private DatabasePrimary primary;

    @Bean
    @Primary
    public DataSource primaryDataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(primary.getUrl());
        config.setUsername(primary.getUsername());
        config.setPassword(primary.getPassword());
        config.setConnectionTimeout(primary.getTimeout());
        config.setMaximumPoolSize(primary.getMaximumPoolSize());

        return new HikariDataSource(config);
    }

}
