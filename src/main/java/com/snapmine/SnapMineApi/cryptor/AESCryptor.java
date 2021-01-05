package com.snapmine.SnapMineApi.cryptor;

import org.aspectj.weaver.World;

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

    Key aesKey = new SecretKeySpec("31f89ee208fa4219".getBytes(),"AES");
    Cipher cipher = Cipher.getInstance("AES");

    public AESCryptor() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    public String encrypt(String text, String key) {
        try {


            // Create key and cipher
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            System.err.println(new String(encrypted));
            // decrypt the text

            return new String(encrypted);


        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "XD";
    }

    @Override
    public String decrypt(String text, String key) {
        try {
            cipher.init(Cipher.DECRYPT_MODE,aesKey);
            byte[] de = cipher.doFinal(text.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "dupa";
    }
}