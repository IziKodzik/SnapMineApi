package com.snapmine.SnapMineApi.config;


import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class CryptoConfig {

    AESCryptor aesCryptor = new AESCryptor();

    public CryptoConfig() throws NoSuchAlgorithmException, NoSuchPaddingException {
    }

    @Bean("aesCryptor")
    public AESCryptor getAESCryptor(){
        return this.aesCryptor;
    }


}
