package DataStructure;

import java.util.Arrays;
import java.util.List;

public class StringvsStringBuffervsStringBuilder {

    public static void main(String[] args) {
        String name="Roxana";
        System.out.println("name hash code: " + name.hashCode());


        StringBuffer sb=new StringBuffer("my new string buffer");
        System.out.println("name hash code: " + sb.hashCode());
        sb=sb.insert(4, "NEW text");
        System.out.println("name hash code: " + sb.hashCode());
        sb=sb.append("append");
        System.out.println("name hash code: " + sb.hashCode());
        System.out.println(sb);

        StringBuilder sb2=new StringBuilder("my new string buffer");

        //iterate through an String builder
        System.out.println("iterate through an String builder");
        for(int i=0; i<sb.length(); i++) {
            char c = sb.charAt(i);
            System.out.println(c);
        }

        //split
        System.out.println("Split");
        List<String> splittedElements= Arrays.asList(sb2.toString().split(" "));

        for(String s : splittedElements) {
            System.out.println(s);
        }

        //substring
        System.out.println("Substring");
        String str = "Hello, World!";
        System.out.println(str);
        String substrings= str.substring(7);
        System.out.println(substrings);
        String subs= str.substring(1,str.length()-2);
        System.out.println(subs);


        StringBuilder sb3=new StringBuilder();
        for(int i=0; i<sb2.length(); i++){
            char cc=sb2.charAt(i);
            sb3.append(cc);
        }


        System.out.println("");




    }



}
