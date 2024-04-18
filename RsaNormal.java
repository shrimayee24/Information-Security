public class RsaNormal {
    public static void main(String[] args) {
        int p = 5, q = 17;
        int n = p * q;
        int m = (p - 1) * (q - 1);
        System.out.println("m=" + m);
        int e = 0;
        for (int i = 2; i < m; i++) {
            if (gcd(i, m) == 1) {
                e = i;
                break;
            }
        }
        e = 5;
        int d = modInverse(e, m);
        System.out.println("e=" + e);
        System.out.println("d=" + d);
        int input = 7;
        int encrypted = modPow(input, e, n);
        System.out.println("encrypted=" + encrypted);
        int decrypted = modPow(encrypted, d, n);
        System.out.println("decrypted=" + decrypted);
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static int modInverse(int b, int m) {
        int k=m;
        //find b inverse mod m
        int q=0,t1=0,t2=1,t3=0,r=0;
        while(b>0){
            q=m/b;
            r=m%b;
            t3=t1-(q*t2);
            t1=t2;
            t2=t3;
            m=b;
            b=r;

        }
        if(t1<0){
            t1+=k;
        }
        return t1;
    }

    private static int modPow(int base, int exp, int modulus) {
        int result = 1;
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
