import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class DES2 {
private static final String algorithm = "DES";
private static final byte[] keyvalue= new byte[] { 'q','d','t','r','e','v','h','f'};
     public static String encrypt(String data){
         try{
             SecretKeySpec key= new SecretKeySpec(keyvalue, algorithm);
             Cipher cipher = Cipher.getInstance(algorithm);
             cipher.init(Cipher.ENCRYPT_MODE, key);
             byte[] encryptedBytes= cipher.doFinal(data.getBytes());
             return Base64.getEncoder().encodeToString(encryptedBytes);




         }
         catch(Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }
    public static String decrypt(String encrypted){
        try{

            SecretKeySpec key= new SecretKeySpec(keyvalue, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedBytes= Base64.getDecoder().decode(encrypted.getBytes());
            byte[] decrypted=cipher.doFinal(decodedBytes);
            return new String(decrypted);


        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String data = "attack at 12 pm";
        String encryptedData = DES.encrypt(data);
        String decryptedData = DES.decrypt(encryptedData);
        System.out.println("Original Data: " + data);
        System.out.println("Encrypted Data: " + encryptedData);
        System.out.println("Decrypted Data: " + decryptedData);
    }
}
