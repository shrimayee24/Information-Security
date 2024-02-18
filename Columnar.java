import java.util.Scanner;

public class Columnar {
    public static void main(String[] args) {
        String input=acceptInput();
        int[] key=acceptKey();
        columnar(input,key);
    }
    public  static void columnar(String input, int[] key){
        String encrypted= encrypt(key, input);
        String decrypted=decrypt(key, encrypted);
    }

    private static String decrypt(int[] key, String input) {
        char[][] m= new char[input.length()/key.length][key.length];
        for (int i=0;i<input.length();i++){
            int col=findCol(i+1,key);
            for(int j=0;j<key.length;j++){

            }
        }

    }

    public static String encrypt(int[] key, String input){
        StringBuilder ans= new StringBuilder();
        int n=key.length;
        int rows=0;
        if(input.length() % n==0){
            rows=input.length()/n;
        }
        else{
            rows=input.length()/n +1;
        }
        int x=0;
        if(input.length()%n!=0){
            x= (n*((input.length()/n) +1))-input.length();

        }
        String input2=input;
        for(int i=0;i<x;i++){
            input2=input2+"X";
        }
//        System.out.println("Input becomes = "+input2);
//        System.out.println("new input length = "+input2.length());

        char[][] m= new char[rows][n];

        for(int i=0; i<input2.length();i++){
            m[i/n][i%n]=input2.charAt(i);
        }
//
        for(int i=0;i< key.length;i++){
            int col=findCol(i+1,key);
            for(int j=0; j<rows;j++){
                ans.append(m[j][col]);
            }
        }
        System.out.println("Encrypted text = "+ans.toString());
        return ans.toString();
    }

    private static int findCol(int j, int[] key) {
        int col=-1;
        for(int i=0;i< key.length;i++){
            if(key[i]==j){
                col=i;
                break;
            }
        }
        return col;
    }


    public static void printCharArray(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }
    public static int[] acceptKey(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter number of columns");
        int n=sc.nextInt();
        int[] key=new int[n];
        System.out.println("Enter column numbers between 1 to "+n);
        for(int i=0; i<n;i++){
            key[i]=sc.nextInt();
        }
        return key;
    }
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
}
