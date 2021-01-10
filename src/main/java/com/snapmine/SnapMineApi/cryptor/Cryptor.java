package com.snapmine.SnapMineApi.cryptor;

import java.security.Key;

public interface Cryptor {

    byte[] encrypt(byte[] text);
    byte[] decrypt(byte[]  text);

    byte[] encrypt(byte[] bytes,Key key);
    byte[] decrypt(byte[] bytes, Key key);

}
