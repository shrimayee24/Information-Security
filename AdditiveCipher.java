import java.util.Scanner;

public class AdditiveCipher {

    public static String acceptInput() {
        boolean isNotCapital = false;
        String input;
        do {
            System.out.println("Enter string to be encrypted/decrypted:");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            int n = input.length();
            for (int i = 0; i < n; i++) {
                char curr = input.charAt(i);
                int currAscii = (int) curr;
                if (currAscii >= 65 && currAscii <= 90) {
                    isNotCapital = true;
                    break;
                }
            }
        } while (!isNotCapital);
        return input;
    }

    public static boolean checkKeyCorrect(String key) {
        boolean isInt = true;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(key.charAt(i))) {
                isInt = false;
                break;
            }
        }
        return isInt;
    }

    public static int acceptKey() {
        boolean isInt = true;
        int key;
        do {
            System.out.println("Enter key:");
            Scanner scanner = new Scanner(System.in);
            key = scanner.nextInt();
            if (key < 0) {
                key = (key + 26) % 26;
            }
        } while (!isInt);
        return key;
    }

    public static String encrypt(String input, int key) {
        StringBuilder ans = new StringBuilder();
        int n = input.length();
        for (int i = 0; i < n; i++) {
            char curr = input.charAt(i);
            int currAscii = (int) curr;
            currAscii -= 65;
            currAscii = (currAscii + key) % 26;
            currAscii += 65;
            curr = (char) currAscii;
            ans.append(curr);
        }
        return ans.toString();
    }

    public static String decrypt(String input, int key) {
        StringBuilder ans = new StringBuilder();
        int n = input.length();
        for (int i = 0; i < n; i++) {
            char curr = input.charAt(i);
            int currAscii = (int) curr;
            currAscii -= 65;
            currAscii = (currAscii - key);
            if (currAscii >= 0) {
                currAscii = currAscii % 26;
                currAscii += 65;
            } else {
                currAscii = currAscii + 26;
                currAscii += 65;
            }
            curr = (char) currAscii;
            ans.append(curr);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String input = acceptInput();
        int key = acceptKey();
        key = key % 26;
        String encryptedCode = encrypt(input, key);
        System.out.println("-----------------------\nEncrypted code for given input: " + encryptedCode);
        String decryptedCode = decrypt(encryptedCode, key);
        String decryptedCode2 = decrypt(input, key);
        System.out.println("-----------------------\nDecrypted code for encrypted code: " + decryptedCode);
        System.out.println("-----------------------\nDecrypted code for given input: " + decryptedCode2);
    }
}
