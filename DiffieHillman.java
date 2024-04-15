import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DiffieHillman {

    public static void main(String[] args) throws Exception {
        // Generate Alice's key pair
        KeyPairGenerator aliceKeyGen = KeyPairGenerator.getInstance("DH");
        aliceKeyGen.initialize(2048);
        KeyPair aliceKeyPair = aliceKeyGen.generateKeyPair();
        PrivateKey alicePrivateKey = aliceKeyPair.getPrivate();
        PublicKey alicePublicKey = aliceKeyPair.getPublic();

        // Generate Bob's key pair using the same parameters
        DHParameterSpec dhParams = ((javax.crypto.interfaces.DHPublicKey) alicePublicKey).getParams();
        KeyPairGenerator bobKeyGen = KeyPairGenerator.getInstance("DH");
        bobKeyGen.initialize(dhParams);
        KeyPair bobKeyPair = bobKeyGen.generateKeyPair();
        PrivateKey bobPrivateKey = bobKeyPair.getPrivate();
        PublicKey bobPublicKey = bobKeyPair.getPublic();

        // Alice and Bob exchange public keys
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
        aliceKeyAgree.init(alicePrivateKey);
        aliceKeyAgree.doPhase(bobPublicKey, true);

        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
        bobKeyAgree.init(bobPrivateKey);
        bobKeyAgree.doPhase(alicePublicKey, true);

        // Generate the shared secret
        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
        byte[] bobSharedSecret = bobKeyAgree.generateSecret();

        // Display the shared secrets
        System.out.println("Alice's shared secret: " + bytesToHex(aliceSharedSecret));
        System.out.println("Bob's shared secret: " + bytesToHex(bobSharedSecret));
    }

    // Convert bytes to hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
