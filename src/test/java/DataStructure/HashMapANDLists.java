package DataStructure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapANDLists {

    @Test
    public void hashMap(){

        HashMap<String, Integer> hashMap=new HashMap<>();

        hashMap.put("John",25);
        hashMap.put("Alice",30);
        hashMap.put("Bob",35);

        System.out.println("Age of John " + hashMap.get("John"));
        System.out.println("Age of Alice "+ hashMap.get("Alice"));

        for(String key: hashMap.keySet()){
            System.out.println("Age of "+ key+ " is " +hashMap.get(key));
        }

        hashMap.forEach((key, value) -> {
            System.out.println("Age of " + value + " is " + key);
        });
    }

    @Test
    public void listOfHashMap(){

        HashMap<String,Object> firstHashMap= new HashMap<>();

        firstHashMap.put("traceName", "my first trace");
        firstHashMap.put("autoMerge", true);

        HashMap<String, Object> secondHashMap= new HashMap<>();

        secondHashMap.put("traceName", "my second trace");
        secondHashMap.put("autoMerge", false);

        List<HashMap<String,Object>> listOfHashMap= new ArrayList<>();

        listOfHashMap.add(firstHashMap);
        listOfHashMap.add(secondHashMap);

        for(HashMap<String,Object> map: listOfHashMap){
            System.out.println(map.get("traceName"));
            System.out.println(map.get("autoMerge").toString());
        }
    }
}
