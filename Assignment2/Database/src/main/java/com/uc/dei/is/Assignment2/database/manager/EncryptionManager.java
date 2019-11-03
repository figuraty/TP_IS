package com.uc.dei.is.Assignment2.database.manager;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionManager {

    private static Cipher ecipher;
    private static Cipher dcipher;
    private static SecretKey key;

    public static String[] encprypt(String password){
        try {
            // generate secret key using DES algorithm
            key = KeyGenerator.getInstance("DES").generateKey();
            ecipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key
            ecipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] utf8 = password.getBytes(StandardCharsets.UTF_8);
            byte[] enc = ecipher.doFinal(utf8);

            // encode to base64
            enc = BASE64EncoderStream.encode(enc);
            return new String[]{Base64.getEncoder().encodeToString(key.getEncoded()), new String(enc)};
        }
        catch (Exception e) {
            System.out.println("Encrypted exception:" + e.getMessage());
            return null;
        }
    }

    public static String decprypt(String key, String password){
        try {
            // decode the base64 encoded string
            byte[] decodedKey = Base64.getDecoder().decode(key);
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");

            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key
            dcipher.init(Cipher.DECRYPT_MODE, originalKey);

            // decode with base64 to get bytes
            byte[] dec = BASE64DecoderStream.decode(password.getBytes());
            byte[] utf8 = dcipher.doFinal(dec);

            // create new string based on the specified charset
            return new String(utf8, StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            System.out.println("Decrypted exception:" + e.getMessage());
            return null;
        }
    }
}
