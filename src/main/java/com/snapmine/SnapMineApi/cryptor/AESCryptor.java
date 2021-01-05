package com.snapmine.SnapMineApi.cryptor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AESCryptor
    implements Cryptor{

    Key aesKey = new SecretKeySpec("31f89ee208fa4219".getBytes(), "AES");
    Cipher cipher = Cipher.getInstance("AES");

    public AESCryptor() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    public String encrypt(String text, String key) {
        try {

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            System.err.println(new String(encrypted));
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

    private byte[] encrypt(String text, Key aesKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        System.err.println(new String(encrypted));
        return encrypted;
    }

    private void decrypt( byte[] encrypted,Key aesKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));
        System.err.println(decrypted);
    }

    @Override
    public String decrypt(String text, String key) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(text.getBytes()));
        System.err.println(decrypted);

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