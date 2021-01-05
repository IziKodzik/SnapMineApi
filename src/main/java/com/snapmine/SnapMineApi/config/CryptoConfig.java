package com.snapmine.SnapMineApi.config;


import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoConfig {

    AESCryptor aesCryptor = new AESCryptor();

    @Bean("aesCryptor")
    public AESCryptor getAESCryptor(){
        return this.aesCryptor;
    }


}
