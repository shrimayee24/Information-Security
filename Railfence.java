import java.util.Scanner;

public class Railfence {
    public static void main(String[] args) {
        String input=acceptInput();
        int key=acceptKey();
        String encrypted=encrypt(input,key);
        System.out.println("ENCRYPTED TEXT = "+encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("DECRYPTED TEXT = "+decrypted);
    }

    private static String decrypt(String input, int key) {
        StringBuilder ans=new StringBuilder();
        int rows=key;
        int col=input.length();
        char[][] m= new char[rows][col];

        for(int i=0;i<rows;i++){
            for(int j=0;j<col;j++){
                m[i][j]='.';
            }
        }
        int c=0;
        int i=0;
        int n=input.length();
        boolean goUp=false;
        while(i<n){
            m[c][i]='+';
            if(c==0){goUp=false;}
            else if(c==key-1){goUp=true;}
            if(goUp){
                c--;
            }
            else{
                c++;
            }
            i++;
        }

        int w=0;
        for(int x=0; x<rows;x++){
            for(int y=0;y<col;y++){
                if(m[x][y]=='+'){
                    m[x][y]=input.charAt(w);
                    w++;
                }
            }
        }

        for(int y=0;y<col;y++){
            for(int x=0; x<rows;x++){
                if(m[x][y]!='.'){
                    ans.append(m[x][y]);

                }
            }
        }
        return ans.toString();

    }

    private static String encrypt(String input, int key) {
        StringBuilder ans=new StringBuilder();
        int rows=key;
        int col=input.length();
        char[][] m= new char[rows][col];

        for(int i=0;i<rows;i++){
            for(int j=0;j<col;j++){
                m[i][j]='.';
            }
        }
        int c=0;
        int i=0;
        int n=input.length();
        boolean goUp=false;
        while(i<n){
            m[c][i]=input.charAt(i);
            if(c==0){goUp=false;}
            else if(c==key-1){goUp=true;}
            if(goUp){
                c--;
            }
            else{
                c++;
            }
            i++;
        }
        printCharArray(m);
        for(int x=0; x<rows;x++){
            for(int y=0;y<col;y++){
                if(m[x][y]!='.'){
                    ans.append(m[x][y]);
                }
            }
        }
        return ans.toString();

    }
    public static void printCharArray(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
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
