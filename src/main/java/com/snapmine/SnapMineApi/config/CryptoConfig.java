package com.snapmine.SnapMineApi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class CryptoConfig {

    @Bean
    public Function<String,String> encrypt(){
        return this::encryptD;
    }

    private String encryptD(String string){
        return string.toUpperCase();
    }

}
