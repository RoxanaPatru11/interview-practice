package others;

import org.junit.jupiter.api.Test;

import java.util.*;

public class OthersTest {
    static List<Integer> list = new ArrayList<>(Arrays.asList(5,2,1,6,3,9));

    @Test
    public void findTheSmallestAndLargestElement() {
        int min= list.get(0);
        int max= list.get(0);

        for(int i=0; i<list.size();i++){
            if(list.get(i)<min){
                min= list.get(i);
            }

            if(list.get(i)>max){
                max= list.get(i);
            }
        }

        System.out.println("min: "+min);
        System.out.println("max: "+max);
    }

    @Test
    public void sortArray(){
        List<Integer> copyList= new ArrayList<>(list);
        int i=0;
        int j;

        while(i<copyList.size()-1){
            j=i+1;
            while(j<copyList.size()){
                if(copyList.get(i)>copyList.get(j)){
                    int temp= copyList.get(i);
                    copyList.set(i, copyList.get(j));
                    copyList.set(j, temp);
                }

                j++;
            }
            i++;
        }

        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(copyList.toArray()));
    }

    @Test
    public void frequencyOfElements() {
        List<Integer> elements= new ArrayList<>(Arrays.asList(1,2,2,3,4,4,4));

        System.out.println("frequency using hash map");
        Map<Integer,Integer> freqMap= new HashMap<>();
        freqMap.put(elements.get(0),1);

        for(int i=1; i<elements.size(); i++){
            Integer temp= elements.get(i);
            boolean exists = false;

            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
                Integer key = entry.getKey();
                Integer count = entry.getValue();

                if (key == temp) {
                    freqMap.put(key,count+1);
                    exists = true;
                }
            }

            if(!exists){
                freqMap.put(temp,1);
            }

        }

        for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()){
            Integer key= entry.getKey();
            Integer value= entry.getValue();

            System.out.println(key+ " appears "+ value+ " times in the array.");
        }
    }

    public void reverseBigWords(){


    }

    @Test
    public void moveAllZeroToTheEnd(){
        List<Integer> input= new ArrayList<>(Arrays.asList(1,4,0,5,0,0,1,2,9));
        System.out.println(Arrays.toString(input.toArray()));

        int i=0;
        int lastPosition= input.size()-1;

        while(i<input.size()){

            if(input.get(i)==0){
                int j= lastPosition;
                while(j>i){
                    if(input.get(j)==0){
                        j--;
                    }else {
                        int temp= input.get(i);
                        input.set(i, input.get(j));
                        input.set(j, temp);
                        lastPosition= j;
                        break;
                    }
                }
            }
            i++;
        }
        System.out.println(Arrays.toString(input.toArray()));
    }

    @Test
    public void getMoveAllZeroToTheEndMaintainingTheOrderOfNonZeroElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 4, 0, 5, 0, 0, 1, 2, 9));

        int i = 0;

        while (i < input.size() - 1) {
            if (input.get(i) == 0) {
                int j = i + 1;
                while (j < input.size()) {
                    if (input.get(j) != 0) {
                        int temp = input.get(i);
                        input.set(i, input.get(j));
                        input.set(j, temp);
                        break;
                    } else {
                        j++;
                    }
                }
            }
            i++;

        }
        System.out.println(Arrays.toString(input.toArray()));
    }


    @Test
    public void fibonacci(){
        int a= 0;
        int b= 1;
        int count=2;
        int howMany= 12;

        System.out.print(a+" "+b+" ");

        while(count<howMany){
            int c=a+b;
            a=b;
            b=c;

            System.out.print(c+" ");
            count++;
        }
    }

    @Test
    public void factorial(){
        int n=4;
        int fact=1;

        for(int i=1;i<=n;i++){
            fact*=i;
        }

        System.out.println("factorial of "+n+" is "+fact);
    }







}
