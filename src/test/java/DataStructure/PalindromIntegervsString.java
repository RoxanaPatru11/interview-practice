package DataStructure;

public class PalindromIntegervsString {

    public static void main(String[] args) {

        String sb2="cojoc";

        //StringBuilder sb2=new StringBuilder("cojoc");

        StringBuilder reverseString=new StringBuilder();

        for(int i=sb2.length()-1; i>=0; i--) {
            char cc=sb2.charAt(i);
            reverseString.append(cc);
        }

        if(sb2.equals(reverseString.toString())){
            System.out.println("Palindrom String");
        }else {
            System.out.println("Not Palindrom");
        }

        System.out.println(reverseString);

        int number=101;
        int copyNumber=number;
        int palindrom=0;

        while(number>0) {
            palindrom=palindrom*10+ number%10;
            number=number/10;
        }

        if(copyNumber==palindrom){
            System.out.println("Palindrom String");

        }else {
            System.out.println("Not Palindrom");
        }

        System.out.println(palindrom);

    }
}
