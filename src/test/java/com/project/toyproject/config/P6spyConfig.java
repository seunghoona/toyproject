package com.project.toyproject.config;

import com.p6spy.engine.spy.P6SpyOptions;
import com.querydsl.core.annotations.Config;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@TestConfiguration
public class P6spyConfig {

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettySqlFormatter.class.getName());
    }

}