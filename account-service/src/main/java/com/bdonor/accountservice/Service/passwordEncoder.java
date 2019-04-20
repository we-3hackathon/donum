package com.bdonor.accountservice.Service;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class passwordEncoder {

        private static final String ALGORITHM       = "AES";
        private static final String myEncryptionKey = "ThisIsFoundation";
        private static final String UNICODE_FORMAT  = "UTF8";

        public static String encrypt(String valueToEnc) throws Exception {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = c.doFinal(valueToEnc.getBytes(UNICODE_FORMAT));
            //String encryptedValue = new Base64Encoder().encode(encValue);
            String encryptedValue = new Base64Encoder().encode(encValue);
            return encryptedValue;
        }

        public static String decrypt(String encryptedValue) throws Exception {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new Base64Encoder().decode(encryptedValue);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        }

        private static Key generateKey() throws Exception {
            byte[] keyAsBytes;
            keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            Key key = new SecretKeySpec(keyAsBytes, ALGORITHM);
            return key;
        }
}
