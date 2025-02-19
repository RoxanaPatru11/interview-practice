package DataStructure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArraysANDArrayLists {

    @Test
    public void MyArrays(){
        String[] colors= new String[5];

        colors[0]="purple";
        colors[1] ="blue";

        System.out.println(colors[0]);
        System.out.println(colors[1]);

        System.out.println(Arrays.toString(colors));
    }


    @Test
    public void MyFisrtFor(){

        String[] animals= {"dog", "cat", "lizard", "bird", "snake", "cat", "mouse", "cat"};

        int index=0;
        for(int i=0; i<animals.length;i++){
            if(animals[i]=="cat"){
                index=index+1;
            }
        }

        System.out.println("the cat is duplicated "+ index +" times");

        for(String animal:animals){
            System.out.println(animal);
        }

        System.out.println(Arrays.toString(animals));


        int[] numbers= {4,9,1,3};
        System.out.println(Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }


    @Test
    public void ArrayLists(){
        List<String> mylists= new ArrayList<>();
        mylists.add("my first texts");
        mylists.add("my second texts");


        System.out.println(mylists.size());
        System.out.println(mylists.getFirst());
        System.out.println(mylists.getLast());
        System.out.println(mylists.get(0));

    }

    @Test
    public void HasMapExample(){
        HashMap<String, Integer> maps= new HashMap<>();

        maps.put("Maria",22);
        maps.put("Vlad",35);
        maps.put("John",45);

        System.out.println(maps.size());
        System.out.println("Maria's age is " + maps.get("Maria"));

        maps.remove(22);
        System.out.println(maps);
    }









}
