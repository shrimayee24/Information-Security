import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class AdditiveCipher {
    public static String acceptInput() {
        Scanner sc= new Scanner(System.in);
        boolean correctInput=false;
        String input="";
        do{
            System.out.println("Enter String input (capital letters):");
            input=sc.next();
            if(checkInputCorrect(input)){
                correctInput=true;
            }
        }
        while(!correctInput);
        System.out.println("Input = "+input);
        return input;
    }

    public static boolean checkInputCorrect(String input) {
        return input.matches("[A-Z]+");
    }

    public static int acceptKey() {
        int key = 0;
        boolean correctKey = false;
        do {
            System.out.println("Enter Key (an integer):");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                key = sc.nextInt();
                correctKey = true;
            } else {
                System.out.println("Invalid input. Key should be an integer.");
            }
        } while (!correctKey);
        System.out.println("Key = " + key);
        return key;
    }

    public static String encrypt(String input, int key, char[] capitalLetters) {
        StringBuilder encryptedString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int index = ch - 'A'; // Convert character to 0-based index
            int encryptedIndex = (index + key) % 26; // Apply the additive cipher formula
            if (encryptedIndex < 0) {
                encryptedIndex += 26; // Handle negative indices
            }
            encryptedString.append(capitalLetters[encryptedIndex]);
        }
        System.out.println("Encrypted String = " + encryptedString);
        return encryptedString.toString();
    }

    public static String decrypt(String input, int key, char[] capitalLetters) {
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int index = ch - 'A'; // Convert character to 0-based index
            int decryptedIndex = (index - key) % 26; // Apply the additive cipher formula
            if (decryptedIndex < 0) {
                decryptedIndex += 26; // Handle negative indices
            }
            decryptedString.append(capitalLetters[decryptedIndex]);
        }
        System.out.println("Decrypted String = " + decryptedString);
        return decryptedString.toString();
    }

    public static void main(String[] args) {
        char[] capitalLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int key = acceptKey();
        String input = acceptInput();
        String encrypted = encrypt(input, key, capitalLetters);
        decrypt(encrypted, key, capitalLetters);
    }
}
