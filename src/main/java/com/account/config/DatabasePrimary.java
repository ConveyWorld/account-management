package com.account.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "database.primary")
@Data
public class DatabasePrimary {
    private String url;
    private String username;
    private String password;
    private Integer timeout;
    private Integer maximumPoolSize;
}