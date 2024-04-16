import java.security.*;
import java.util.Scanner;

public class DigitalSignature {
    public static void main(String[] args) throws SignatureException {
        KeyPair key = generateKeys();
        byte[] plaintext = acceptPlaintext();
        byte[] hash = hash(plaintext, "MD5");
        Signature signedDoc = sign(hash, key);
        System.out.println("Signed Document (in hexadecimal): " + bytesToHex(signedDoc.sign()).toString());
        String signedDocFromSender = acceptSignedDoc();
        boolean signIsValid = signVerify(signedDocFromSender, hash, key);
        System.out.println("Digital signature is valid: " + signIsValid);
    }

    private static String acceptSignedDoc() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter signed document (in hexadecimal):");
        return sc.next();
    }

    private static KeyPair generateKeys() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] acceptPlaintext() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plaintext:");
        String input = sc.next();
        return input.getBytes();
    }

    private static byte[] hash(byte[] plaintext, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            return digest.digest(plaintext);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Signature sign(byte[] hash, KeyPair key) {
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(key.getPrivate());
            signature.update(hash);
            return signature;
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean signVerify(String signedDocHex, byte[] hash, KeyPair key) {
        try {
            Signature signedDoc = Signature.getInstance("MD5withRSA");
            signedDoc.initVerify(key.getPublic());
            signedDoc.update(hash);
            byte[] signedDocBytes = hexStringToByteArray(signedDocHex);
            return signedDoc.verify(signedDocBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }
}
