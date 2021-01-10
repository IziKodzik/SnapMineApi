package com.snapmine.SnapMineApi.cryptor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESCryptor
    implements Cryptor{

    Key aesKey ;
    Cipher cipher = Cipher.getInstance("AES");


    public AESCryptor() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    @Override
    public byte[] encrypt(byte[] text) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            return cipher.doFinal(text);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String encrypt(String text){
        return Base64
                .getEncoder()
                .encodeToString
                        (this.encrypt(text.getBytes(StandardCharsets.UTF_8)))
                .replace("/","-")
                .replace("+","_");
    }
    public String decrypt(String text){
        return new String
                (this.decrypt(Base64.getDecoder()
                .decode(text
                        .replace("-","/")
                        .replace("_","+"))
        ));
    }

    @Override
    public byte[] decrypt(byte[] text) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            return cipher.doFinal(text);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] encrypt(byte[] bytes, String key) {
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] bytes, String key) {
        return new byte[0];
    }

    public void setKey(String key){
        this.aesKey  = new SecretKeySpec(key.getBytes(), "AES");
    }


}