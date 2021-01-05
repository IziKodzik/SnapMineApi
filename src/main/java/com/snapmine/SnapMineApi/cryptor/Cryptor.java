package com.snapmine.SnapMineApi.cryptor;

public interface Cryptor {

    String encrypt(String text,String key);
    String decrypt(String text,String key);

}
