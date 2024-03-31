import java.io.*;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class DigiSign_File {
    public static KeyPair generateKeyPair() {
        try {
            return KeyPairGenerator.getInstance("RSA").generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean verifyDigitalSignature(byte[] input, byte[] hashedSignedMessage, KeyPair keyPair) {
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(keyPair.getPublic());
            signature.update(input);
            return signature.verify(hashedSignedMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static byte[] hashSignMessage(byte[] input, KeyPair keyPair) {
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(keyPair.getPrivate());
            signature.update(input);
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] readFile(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        KeyPair keyPair = generateKeyPair();
        byte[] input = null;
        byte[] hashedSignedMessage;

        int ch;
        do {
            System.out.println("Enter:\n1. Sender's Side: for sending message\n2. Receiver's Side: for verifying digital signature\n3. Exit");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter file path:");
                    input = readFile(sc.next());
                    if (input != null) {
                        hashedSignedMessage = hashSignMessage(input, keyPair);
                        System.out.println("Sent message: " + Base64.getEncoder().encodeToString(hashedSignedMessage));
                    } else {
                        System.out.println("File not found or could not be read.");
                    }
                    break;
                case 2:
                    if (input == null) {
                        System.out.println("No message sent yet. Please send a message first.");
                        break;
                    }
                    System.out.println("Enter received text:");
                    byte[] receivedBytes = Base64.getDecoder().decode(sc.next());
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
