import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
public class AES2 {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue =
            new byte[] {  'T', 'h', '6', 's', 'I', 's', 'h', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    public static String encrypt(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String data = "attack at 10 pm";
        String encryptedData = AES2.encrypt(data);
        String decryptedData = AES2.decrypt(encryptedData);
        System.out.println("Original Data: " + data);
        System.out.println("Encrypted Data: " + encryptedData);
        System.out.println("Decrypted Data: " + decryptedData);
    }
}
