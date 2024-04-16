import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSA {

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();


        String originalMessage = "Hello, RSA!";
        byte[] encryptedBytes = encrypt(originalMessage, publicKey);


        String decryptedMessage = decrypt(encryptedBytes, privateKey);


        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedBytes));
        System.out.println("Decrypted Message: " + decryptedMessage);
    }


    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }


    public static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        // Perform encryption
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());
    }


    public static String decrypt(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        // Perform decryption
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(encryptedMessage));
    }
}
