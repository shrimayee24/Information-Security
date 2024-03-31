import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class AES2 {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue =
            new byte[] {  'T', 'h', '6', 's', 'I', 's', 'h', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    public static String encrypt(String data) {
        try{
            SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
            Cipher cipher= Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedbytes=cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedbytes);
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }

    }

    public static String decrypt(String encryptedData) {
        try{
            SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
            Cipher cipher= Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded= Base64.getDecoder().decode(encryptedData);
            byte[] decrypted=cipher.doFinal(decoded);
            return new String(decrypted);

        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Enter:\n1. Sender's Side\n2. Receiver's Side\n3. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter data to encrypt:");
                    String data = scanner.nextLine();
                    String encryptedData = AES.encrypt(data);
                    System.out.println("Encrypted Data: " + encryptedData);
                    break;
                case 2:
                    System.out.println("Enter encrypted data to decrypt:");
                    String encrypted = scanner.next();
                    String decryptedData = AES.decrypt(encrypted);
                    System.out.println("Decrypted Data: " + decryptedData);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }
}
