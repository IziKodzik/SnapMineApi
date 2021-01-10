package com.snapmine.SnapMineApi.cryptor;

public interface Cryptor {

    byte[] encrypt(byte[] text);
    byte[] decrypt(byte[]  text);

    byte[] encrypt(byte[] bytes,String key);
    byte[] decrypt(byte[] bytes,String key);

}
