package com.sky.config;

import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AliOssConfiguration {
    @Bean
    public AliOssUtil aliOssUtil(){
        return new AliOssUtil();
    };
}
