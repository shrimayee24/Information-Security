import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class DigitalSignature {
    public static KeyPair generateKeyPair() {
        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static boolean verifyDigitalSignature(byte[] input, byte[] hashedSignedMessage, KeyPair keyPair) {
        try {

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(keyPair.getPublic());
            signature.update(input);
            return signature.verify(hashedSignedMessage);

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static byte[] hashSignMessage(byte[] input, KeyPair keyPair) {
        try {

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(keyPair.getPrivate());
            signature.update(input);
            return signature.sign();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] acceptInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message:");
        String inputString = sc.next();
        try {
            return inputString.getBytes("UTF-8");
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        KeyPair keyPair = generateKeyPair();
        byte[] input = null;
        byte[] hashed_signed_message = null;

        int ch = 0;
        do {
            System.out.println("Enter:\n1.Sender's Side: for sending message\n2. Receiver;s Side: for verifying digital signature\n3. Exit");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    input = acceptInput();
                    hashed_signed_message = hashSignMessage(input, keyPair);
                    System.out.println("Sent message: " + Base64.getEncoder().encodeToString(hashed_signed_message));
                    break;
                case 2:
                    if (input == null) {
                        System.out.println("No message sent yet. Please send a message first.");
                        break;
                    }

                    System.out.println("Enter received text:");
                    String received = sc.next();
                    byte[] receivedBytes = Base64.getDecoder().decode(received);
                    boolean result = verifyDigitalSignature(input, receivedBytes, keyPair);
                    System.out.println("Digital signature is valid: " + result);
                    break;

                case 3:
                    System.out.println("Exiting");
                    break;
            }
        } while (ch != 3);
    }


}
