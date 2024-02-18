
import java.util.*;

public class Playfair {
    public static void main(String[] args) {
        char[] capitalLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        playfair(capitalLetters);

    }

    public static void playfair(char[] capitalLetters) {
        String input = acceptInput();
        String finalinput = processInput(input);
        System.out.println("Processed input = " + finalinput);
        String key = acceptKey();
        char[][] m = createMatrix(key, capitalLetters);
        String encrypted = encrypt(finalinput, m);
        System.out.println("ENCRYPTED TEXT = "+encrypted);
        String decrypted = decrypt(encrypted, m);
        System.out.println("DECRYPTED TEXT = "+decrypted);

    }

    public static String decrypt(String input, char[][] m){
        StringBuilder ans= new StringBuilder();
        for(int i=0; i<=input.length()-2; i=i+2){
            int c1_row= findRow(input.charAt(i),m);
            int c1_col=findCol(input.charAt(i),m);
            int c2_row= findRow(input.charAt(i+1),m);
            int c2_col=findCol(input.charAt(i+1),m);

            int newc1col = (c1_col-1)%5;
            if(newc1col<0){newc1col+=5;}
            int newc2col = (c2_col-1)%5;
            if(newc2col<0){newc2col+=5;}
            int newc1row=(c1_row-1)%5;
            if(newc1row<0){newc1row+=5;}
            int newc2row=(c2_row-1)%5;
            if(newc2row<0){newc2row+=5;}

            if(c1_row==c2_row){

                ans.append(m[c1_row][newc1col]);
                ans.append(m[c2_row][newc2col]);
            }
            else if(c1_col==c2_col){
                ans.append(m[newc1row][c1_col]);
                ans.append(m[newc2row][c2_col]);

            }
            else{
                ans.append(m[c1_row][c2_col]);
                ans.append(m[c2_row][c1_col]);

            }

//            System.out.println(input.charAt(i)+" "+input.charAt(i+1));
//            System.out.println(ans.toString());
        }
        return ans.toString();

    }

    public static String encrypt(String input, char[][] m){
        StringBuilder ans= new StringBuilder();
        for(int i=0; i<=input.length()-2; i=i+2){
            int c1_row= findRow(input.charAt(i),m);
            int c1_col=findCol(input.charAt(i),m);
            int c2_row= findRow(input.charAt(i+1),m);
            int c2_col=findCol(input.charAt(i+1),m);
            if(c1_row==c2_row){
               ans.append(m[c1_row][(c1_col+1)%5]);
               ans.append(m[c2_row][(c2_col+1)%5]);
            }
            else if(c1_col==c2_col){
                ans.append(m[(c1_row+1)%5][c1_col]);
                ans.append(m[(c2_row+1)%5][c2_col]);

            }
            else{
                ans.append(m[c1_row][c2_col]);
                ans.append(m[c2_row][c1_col]);

            }
//            System.out.println(input.charAt(i)+" "+input.charAt(i+1));
//            System.out.println(ans.toString());
        }
        return ans.toString();

    }

    private static boolean sameRow(int i, int i2, char[][] m, String input) {
        boolean ans=false;
        if(findRow(input.charAt(i),m)==findRow(input.charAt(i2),m)){
            ans=true;
        }
        return  ans;
    }

    private static boolean sameColumn(int i, int i2, char[][] m,  String input) {
        boolean ans=false;
        if(findCol(input.charAt(i),m)==findCol(input.charAt(i2),m)){
            ans=true;
        }
        return  ans;
    }

    private static int findRow(char c, char[][] m){
        int row=-1;
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){
                if(m[i][j]==c){
                    row=i;
                }
            }
        }
        return row;

    }
    private static int findCol(char c, char[][] m){
        int col=-1;
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){
                if(m[i][j]==c){
                    col=j;
                }

            }
        }
        return col;

    }

    private static char[][] createMatrix(String key, char[] capitalLetters) {
        char[][] m= new char[5][5];
        Set<Character> s = new LinkedHashSet<>();
        //adding key char to set
        for (int i=0; i<key.length();i++){
            s.add(key.charAt(i));
        }

        //adding rest chars to set
        for (int i=0; i< capitalLetters.length;i++){
            s.add(capitalLetters[i]);
        }

        int i=0; int j=0;
        for(char c: s){
            m[i/5][j%5]=c;
            i++;
            j++;
        }
        printCharArray(m);
        return m;

    }

    private static String processInput(String input) {
       StringBuilder ans= new StringBuilder();
       List<Character> ls= new LinkedList<>();
       int[] visited= new int[input.length()];
       int i=0;
       while(i<input.length()-1){
           if(input.charAt(i)!=input.charAt(i+1)){
               ans.append(input.charAt(i));
               visited[i]=1;
               ans.append(input.charAt(i+1));
               visited[i+1]=1;
               i=i+2;

           }
           else{
               ans.append(input.charAt(i));
               visited[i]=1;
               ans.append('X');
               i=i+1;
           }
       }
       if(visited[input.length()-1]==0){
           if(ans.length()%2==0){
               ans.append(input.charAt(input.length()-1));
               visited[input.length()-1]=1;
               ans.append('X');
           }
           else{
               ans.append('X');
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

    public static String acceptKey() {
        Scanner sc= new Scanner(System.in);
        boolean correctInput=false;
        String input="";
        do{
            System.out.println("Enter String key (capital letters)  :");
            input=sc.next();
            if(checkInputCorrect(input)){
                correctInput=true;
            }
        }
        while(!correctInput);
        System.out.println("Key = "+input);
        return input;

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
