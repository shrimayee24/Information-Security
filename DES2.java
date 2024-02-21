import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class DES2 {
private static final String algorithm = "DES";
private static final byte[] keyvalue= new byte[] { '7','3','w','r','e','v','h','f'};
     public static String encrypt(String data){
         try{
             SecretKeySpec key= new SecretKeySpec(keyvalue,algorithm);
             Cipher cipher = Cipher.getInstance(algorithm);
             cipher.init(Cipher.ENCRYPT_MODE, key);
             byte[] encrypted=cipher.doFinal(data.getBytes());
             return Base64.getEncoder().encodeToString(encrypted);
         }
         catch(Exception e){
             System.out.println(e.getMessage());

             return null;
         }
     }
    public static String decrypt(String encrypted){
        try{
            SecretKeySpec key= new SecretKeySpec(keyvalue,algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded= Base64.getDecoder().decode(encrypted);
            byte[] decrypted= cipher.doFinal(decoded);
            return new String(decrypted);

        }
        catch(Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }
    public static byte[] generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
            // Generate a DES key with a keysize of 56 bits (DES uses 64-bit keys, but only 56 bits are used for encryption)
            keyGen.init(56);
            // Generate the key
            SecretKey secretKey = keyGen.generateKey();
            // Get the encoded form of the key
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String data = "attack at 12 pm";
        String encryptedData = DES2.encrypt(data);
        String decryptedData = DES2.decrypt(encryptedData);
        System.out.println("Original Data: " + data);
        System.out.println("Encrypted Data: " + encryptedData);
        System.out.println("Decrypted Data: " + decryptedData);
    }
}
