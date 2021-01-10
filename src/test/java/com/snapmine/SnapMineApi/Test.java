package com.snapmine.SnapMineApi;

import com.snapmine.SnapMineApi.cryptor.AESCryptor;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Test {


    public static void main(String[] args) throws Exception{

        AESCryptor aesCryptor = new AESCryptor();
        String s = aesCryptor.encrypt("test","testtesttesttest");
        System.out.println(aesCryptor.decrypt(s,"tssttesttesttest"));

    }

}
