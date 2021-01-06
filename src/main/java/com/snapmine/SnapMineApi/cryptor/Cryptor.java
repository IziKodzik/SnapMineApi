package com.snapmine.SnapMineApi.cryptor;

public interface Cryptor {

    byte[] encrypt(byte[] text);
    byte[] decrypt(byte[]  text);

}
