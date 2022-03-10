package sst.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class SecurityEncryption {

    private String encryptionKey;

    public SecurityEncryption(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }


    public String decrypt(String text) {
        String decryptedString = null;
        try {
            byte[] textInBytes = text.getBytes("UTF-8");
            byte[] firstHash = Base64.getDecoder().decode(textInBytes);
            byte[] secondHash = Base64.getDecoder().decode(firstHash);
            decryptedString = new String(secondHash);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decryptedString;
    }
    public String encrypt(String text) {
        String encryptedString = null;
        try {
            byte[] textInBytes = text.getBytes("UTF-8");
            byte[] firstHash = Base64.getEncoder().encode(textInBytes);
            byte[] secondHash = Base64.getEncoder().encode(firstHash);
            encryptedString = new String(secondHash);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptedString;                                                 
    }
}
