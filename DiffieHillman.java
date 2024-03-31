public class DiffieHillman

{

    public static long side_A(long n,long g,long prA)

    {

        long puA= ((long)Math.pow(g, prA))%n;

        return puA;

    }

    public static long side_B(long n,long g, long prB)

    {

        long puB= ((long)Math.pow(g, prB))%n;

        return puB;

    }

    public static long sec_key(long n,long pr,long pu)

    {

        long k=((long)Math.pow(pu, pr))%n;

        return k;

    }

    public static void main(String [] args)

    {

        long n = 11, g=3, prA=5, prB=8, puA, puB, k;

        puA=side_A(n,g,prA);

        puB=side_B(n,g,prB);

        k=sec_key(n,prA,puB);

        System.out.println("The public key of A is:" +puA);

        System.out.println("The public key of B is:" +puB);

        System.out.println("The shared secret key is:" +k);

    }



}