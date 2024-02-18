import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MultiplicativeCipher {
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

    public static int acceptKey(HashMap <Integer, Integer>  map) {

        boolean correctkey=false;
        int key=-1;
        do{
            System.out.println("Enter Key:");
            try{
                Scanner sc= new Scanner(System.in);
                key=sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Key should be an integer with a multiplication inverse in mode 26. Try again.");

            }
            if(map.containsKey(key)||map.containsValue(key)){
                correctkey=true;

            }
        }
        while(!correctkey);
        System.out.println("Key = "+key);
        return key;


    }

    public static String encrypt(String input, int key, char[] capitalLetters) {
        StringBuilder ans = new StringBuilder();
        int n = input.length();
        for(int i=0; i<n; i++){
            int p = (int)input.charAt(i);
            int c =  ((p-65)*key)%26;
            ans.append(capitalLetters[c]);
        }
        String encrypted = ans.toString();
        System.out.println("Encrypted String = "+encrypted);
        return encrypted;

    }

    public static String decrypt(String input, int key, char[] capitalLetters) {
        StringBuilder ans = new StringBuilder();
        int n = input.length();
        for(int i=0; i<n; i++){
            int p = (int)input.charAt(i);
            int c =  ((p-65)*key)%26;
            if(c<0){
                c+=26;
            }
            ans.append(capitalLetters[c]);
        }
        String decrypted = ans.toString();
        System.out.println("Decrypted String = "+decrypted);
        return decrypted;
    }
    public static void multiplicativeCipher(HashMap <Integer, Integer>  map, char[] capitalLetters) {
        int key = acceptKey(map);
        String input= acceptInput();
        String encrypted = encrypt(input, key, capitalLetters);
        int dkey=-1;
        if(map.containsKey(key)){
            dkey=map.get(key);
        }
        else {
            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                if(entry.getValue() == key){
                    dkey=entry.getKey();
                    break;
                }
            }
            if(dkey==-1){}
        }
        System.out.println("Decryption Key = "+dkey);
        String decrypted = decrypt(encrypted, dkey, capitalLetters);
    }

    public static void main(String[] args) {
        HashMap <Integer, Integer>  map = new HashMap<>();
        map.put(1,1);
        map.put(3,9);
        map.put(5,21);
        map.put(7,15);
        map.put(11,19);
        map.put(17,23);
        map.put(25,25);
        char[] capitalLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        multiplicativeCipher(map, capitalLetters);



    }
}
