import java.util.Scanner;

public class HillCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of columns (m) for the matrices:");
        int m = scanner.nextInt();

        System.out.println("Enter the input String");
        String input= scanner.next();
        int n=input.length();

        int x=0;
        if(n%m!=0){x= (m*((n/m) + 1))-n;}

        for(int i=0;i<x;i++){
            input+="Z";
        }
        int l=input.length()/m;
        System.out.println(input);
        int[][] plaintext = new int[l][m];

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < m; j++) {
                char x1 = input.charAt(i * m + j);
                System.out.println(x1);
                plaintext[i][j] = ((int) x1)-65;
            }
        }

        printCharArray(plaintext);
        int[][] ciphertext = new int[m][m];
        System.out.println("Enter cipher matrix");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                ciphertext[i][j]= scanner.nextInt();
            }
        }


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
