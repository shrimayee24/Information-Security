import java.util.Scanner;

public class Vigenere {
    public static void main(String[] args) {
        char[] capitalLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        vigenere(capitalLetters);
    }

    private static void vigenere(char[] capitalLetters) {
        String input = acceptInput();
        String key = acceptKey();
        String encrypted = encrypt(input, key, capitalLetters);
        System.out.println("ENCRYPTED TEXT = " + encrypted);
        String decrypted = decrypt(encrypted, key, capitalLetters);
        System.out.println("DECRYPTED TEXT = " + decrypted);
    }

    private static String decrypt(String input, String key, char[] capitalLetters) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char p = input.charAt(i);
            char k = key.charAt(i % key.length());
            int j = ((int) p - (int) k - 130) % 26;
            if (j < 0) {
                j += 26;
            }
            ans.append(capitalLetters[j]);

        }
        return ans.toString();
    }

    private static String encrypt(String input, String key, char[] capitalLetters) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char p = input.charAt(i);
            char k = key.charAt(i % key.length());
            int j = ((int) p + (int) k - 130) % 26;
            ans.append(capitalLetters[j]);

        }
        return ans.toString();
    }

    public static String acceptKey() {
        Scanner sc = new Scanner(System.in);
        boolean correctInput = false;
        String input = "";
        do {
            System.out.println("Enter String key (capital letters)  :");
            input = sc.next();
            if (checkInputCorrect(input)) {
                correctInput = true;
            }
        }
        while (!correctInput);
        System.out.println("Key = " + input);
        return input;

    }

    public static String acceptInput() {
        Scanner sc = new Scanner(System.in);
        boolean correctInput = false;
        String input = "";
        do {
            System.out.println("Enter String input (capital letters)  :");
            input = sc.next();
            if (checkInputCorrect(input)) {
                correctInput = true;
            }
        }
        while (!correctInput);
        System.out.println("Input = " + input);
        return input;

    }

    public static boolean checkInputCorrect(String input) {
        boolean correctInput = true;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isUpperCase(input.charAt(i))) {
                correctInput = false;
                break;
            }
        }
        return correctInput;
    }
}

