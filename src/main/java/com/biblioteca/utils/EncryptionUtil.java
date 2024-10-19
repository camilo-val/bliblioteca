package com.biblioteca.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Properties;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String PROPERTIES_FILE = "secret.properties";
    private static final String KEY_PROPERTY = "secret.key";

    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedData);
    }
    
    public static SecretKey loadKey() throws Exception {
        Properties properties = new Properties();
        try (InputStream input = EncryptionUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new FileNotFoundException("El archivo " + PROPERTIES_FILE + " no se encontró en el classpath");
            }
            properties.load(input);
            String keyString = properties.getProperty(KEY_PROPERTY);
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            return new SecretKeySpec(keyBytes, "AES");
        }
    }
}
