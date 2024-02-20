import java.util.Scanner;

public class HillCipher {
    public static String acceptInput() {
        Scanner sc= new Scanner(System.in);
        boolean correctInput=false;
        String input="";
        do{
            System.out.println("Enter String input (capital letters)  :");
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
        boolean correctInput=true;
        for(int i=0; i<input.length(); i++){
            if(!Character.isUpperCase(input.charAt(i))){
                correctInput=false;
                break;
            }
        }
        return correctInput;

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of columns (m) for the matrices:");
        int m = scanner.nextInt();

        String input= acceptInput();
        input=processInput(input,m);

        int l=input.length()/m; //no rows

        int[][] plaintext = createPlainTextMatrix(input,l,m);
//        int[][] ciphertext = acceptCipherMatrix(m);
//        int[][] invcipher = acceptCipherMatrix(m);


        int[][] mat = {
                {3, 4, 2, 1},
                {1, 2, 3, 4},
                {4, 3, 1, 2},
                {2, 1, 4, 3}
        };
        int[][] invmat = {
                {2, 3, 4, 1},
                {1, 4, 2, 3},
                {3, 2, 1, 4},
                {4, 1, 3, 2}
        };

        String encrypted= encrypt(plaintext, mat);
        int[][] encryptedmatrix=createEncryptedMatrix(encrypted,l,m);
        String decrypted=decrypt(encryptedmatrix, invmat);


    }

    private static int[][] createEncryptedMatrix(String encrypted, int l, int m) {
        int[][] encryptedmatrix=new int[l][m];
        return encryptedmatrix;
    }

    private static String encrypt(int[][] plaintext, int[][] mat) {
        StringBuilder ans= new StringBuilder();
        return ans.toString();
    }
    private static String decrypt(int[][] encryptedMatrix, int[][] mat) {
        StringBuilder ans= new StringBuilder();
        return ans.toString();
    }

    private static int[][] acceptCipherMatrix(int m) {
        Scanner scanner = new Scanner(System.in);
        int[][] ciphertext = new int[m][m];
        System.out.println("Enter cipher matrix");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                ciphertext[i][j]= scanner.nextInt();
            }
        }
        return ciphertext;
    }

    private static int[][] createPlainTextMatrix(String input, int l, int m) {
        int[][] plaintext = new int[l][m];

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < m; j++) {
                char x1 = input.charAt(i * m + j);
                System.out.println(x1);
                plaintext[i][j] = ((int) x1)-65;
            }
        }
        return plaintext;
    }

    private static String processInput(String input, int m) {
        int n=input.length();

        int x=0;
        if(n%m!=0){x= (m*((n/m) + 1))-n;}

        for(int i=0;i<x;i++){
            input+="Z";
        }
        return input;
    }

    public static void printCharArray(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }
}
