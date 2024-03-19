import java.util.HashMap;
import java.util.Scanner;
public class HillCipher2 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map= inversemap();
        char[] letters = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        int[][] key={
                {3,10,20},
                {20,9,17},
                {9,4,17}
        };
        String input=acceptInput();
        int[][] inputmatrix= createinputmatrix(input);
        int[][] keymatrix=inputMatrix(map);
        System.out.println("\nInput matrix");
        printMatrix(inputmatrix);
        System.out.println("\nKey matrix");
        printMatrix(keymatrix);
        int[][] ciphertext=multiply(inputmatrix, keymatrix);
        System.out.println("\nCipher matrix");
        printMatrix(ciphertext);
        System.out.println("\nCipher Text");
        printText(ciphertext, letters);
        int[][] decryptedmatrix= decrypt(ciphertext, keymatrix,map);
        System.out.println("\nDecrypted text");
        printText(decryptedmatrix, letters);




    }

    private static int[][] decrypt(int[][] ciphertext, int[][] keymatrix, HashMap<Integer, Integer> map) {
        int[][] inverse= inverse(ciphertext, keymatrix, map);
        int[][] ans=multiply(ciphertext, inverse);
        return ans;
    }

    private static void printText(int[][] matrix, char[] letters) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(letters[matrix[i][j]]+ " ");
            }

        }

    }

    private static int[][] inverse(int[][] c,int[][] keymatrix, HashMap<Integer, Integer> map) {
        int k = map.get(calculateDet(keymatrix));
        int[][] t= transposeMatrix(keymatrix);
        int [][] adj=adjoint(t);
        for(int i=0;i<3;i++){
            for(int j=0; j<3;j++){
                adj[i][j]*=k;
                adj[i][j]=adj[i][j]%26;
                if(adj[i][j]<0){adj[i][j]+=26;}

            }
        }
        return adj;


    }

    private static int[][] adjoint(int[][] t) {
        int[][] a= new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0; j<3;j++){
                a[i][j]=minor(t,i,j);
                if((i+j)%2==1){a[i][j]*=(-1);}
            }
        }
        return a;
    }

    private static int minor(int[][] t, int row, int col) {
        int[][] minorMatrix = new int[2][2];
        int a=0,b=0;
        for(int i=0;i<3;i++){
            if(i==row){continue;}
            for(int j=0; j<3;j++){
                if(j==col){continue;}
                minorMatrix[a][b]=t[i][j];
                if(a==0 && b==0){ a=0; b=1;}
                else if(a==0 && b==1){a=1;b=0;}
                else if(a==1 && b==0){a=1;b=1;}

            }

        }
        return minorMatrix[0][0] * minorMatrix[1][1] - minorMatrix[0][1] * minorMatrix[1][0];
        }



    private static int[][] transposeMatrix(int[][] keymatrix) {
        int[][] t= new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0; j<3;j++){
                t[j][i]=keymatrix[i][j];
            }
        }
        return t;
    }




    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] ans= new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    ans[i][j]+=a[i][k]*b[k][j];
                    ans[i][j]=ans[i][j]%26;

                }

            }        }
        return ans;

    }

    public static int[][] createinputmatrix(String input) {
        int[][] m = new int[3][3];
        for (int i = 0; i < 9; i++) {
            m[i/3][i%3]= (int)input.charAt(i)-65;
        }
        return m;
    }

    private static HashMap<Integer, Integer> inversemap() {
        HashMap<Integer, Integer> map= new HashMap();
        map.put(1,1);
        map.put(3,9);
        map.put(5,21);
        map.put(9,3);
        map.put(21,5);
        map.put(7,15);
        map.put(15,7);
        map.put(11,19);
        map.put(19,11);
        map.put(17,23);
        map.put(23,17);
        map.put(25,25);
        return map;

    }

    public static int[][] inputMatrix(HashMap<Integer, Integer> map) {
        int[][] matrix = new int[3][3];
        System.out.println("Enter 3x3 matrix such that det!=0 and inverse of determinant mod 26 exists");
        boolean validmatrix=false;
        while(validmatrix==false){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the elements of the 3x3 matrix:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            int det=calculateDet(matrix);
            if(det!=0 && inversemap().containsKey(det)){
                validmatrix=true;
            }
        }

        return matrix;
    }

    private static int calculateDet(int[][] m){
        int ans=0;
        ans = (m[0][0]*(m[1][1]*m[2][2]-m[2][1]*m[1][2]))-(m[0][1]*(m[1][0]*m[2][2]
                -m[2][0]*m[1][2]))+(m[0][2]*(m[1][0]*m[2][1]-m[2][0]*m[1][1]));
        ans=ans%26;
        if(ans<0){ans+=26;}
        //
        return ans;
    }
    private static String acceptInput() {
        String input="";
        boolean correctinput=false;
        while(correctinput==false){
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter input string (9 length and capital letters)");
            input=sc.next();
            boolean uppercase=true;
            for(int i=0;i<input.length();i++){
                if(!Character.isUpperCase(input.charAt(i))){
                    uppercase=false;
                    break;
                }
            }
            if(uppercase=true && input.length()==9){
                correctinput=true;
            }
        }
        return input;
    }
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
