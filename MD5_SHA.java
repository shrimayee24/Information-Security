import java.security.*;

public class MD5_SHA{

    public static void main(String[] args) {
        String original = "Hello";
        String hash = getMD5(original);
        System.out.println("Hash: " + hash);
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
