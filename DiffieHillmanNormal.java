import java.util.Scanner;

public class DiffieHillmanNormal {
    public static void main(String[] args) {
        long n=13;
        long g=6;
        long prA=5;
        long prB=4;
        long pubA=2;
        long pubB=9;
        long sharedA=modPow(pubB,prA,n);
        long sharedB=modPow(pubA, prB,n);
        System.out.println(sharedB+", "+sharedA);

    }

    private static long acceptLong() {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter Long:\n");
        return sc.nextLong();
    }

    private static long modPow(long base, long exp, long modulus) {
        long result = 1;
        while (exp > 0) {
            if(exp%2==1){
                result=(result*base)%modulus;
                exp--;
            }
            else{
                base=(base*base)%modulus;
                exp/=2;

            }

        }
        return result;
    }
}
