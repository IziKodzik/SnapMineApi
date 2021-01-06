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
            byte[] encrypted = cipher.doFinal(text);
            return encrypted;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
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
    public void setKey(String key){
        this.aesKey  = new SecretKeySpec(key.getBytes(), "AES");
    }

    private void wtf(byte[] text){
        try{

            // Create key and cipher
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text);
            System.err.println(new String(encrypted));
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encrypted));
            System.err.println(decrypted);
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}