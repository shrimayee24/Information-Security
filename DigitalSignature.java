import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class DigitalSignature {
    public static KeyPair generateKeyPair() {
        try {
            // Initialize KeyPairGenerator with the desired algorithm (e.g., RSA)
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            // Set the key size (adjust as needed)
            keyPairGenerator.initialize(2048);

            // Generate the key pair
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            // Handle exception (e.g., algorithm not supported)
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
            System.out.println("Enter:\n1. for sending message\n2. for verifying digital signature\n3. Exit");
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

    private static boolean verifyDigitalSignature(byte[] input, byte[] hashedSignedMessage, KeyPair keyPair) {
        try {
            // Get a Signature instance using the SHA256withRSA algorithm
            Signature signature = Signature.getInstance("SHA256withRSA");

            // Initialize the signature with the public key of the sender
            signature.initVerify(keyPair.getPublic());

            // Update the signature with the input data
            signature.update(input);

            // Verify the digital signature
            return signature.verify(hashedSignedMessage);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            // Handle exceptions
            e.printStackTrace();
            return false;
        }
    }

    private static byte[] hashSignMessage(byte[] input, KeyPair keyPair) {
        try {
            // Get a Signature instance using the SHA256withRSA algorithm
            Signature signature = Signature.getInstance("SHA256withRSA");

            // Initialize the signature with the private key of the sender
            signature.initSign(keyPair.getPrivate());

            // Update the signature with the input data
            signature.update(input);

            // Generate the digital signature
            return signature.sign();
        } catch (Exception e) {
            // Handle exceptions
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
        } catch (UnsupportedEncodingException e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }
}
