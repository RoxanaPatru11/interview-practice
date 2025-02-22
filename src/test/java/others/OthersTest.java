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
        List<Integer> input= new ArrayList<>(Arrays.asList(0,1,0,3,12));
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
        List<Integer> input = new ArrayList<>(Arrays.asList(0,1,0,3,12));

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

    @Test
    public void mergeSortedArray(){
        int m=3;
        int n=3;
        int[] nums1= {1,2,3,0,0,0};
        int[] nums2= {2,5,7};

        merge(nums1, m, nums2,n);

        int[] a = {1};
        m = 1;
        int[] b = {};
        n = 0;
        merge(a, m, b,n);

        int c[]= {0};
        m = 0;
        int d[] = {1};
        n = 1;
        merge(c, m, d,n);

        int[] e = {0,0,0,0,0};
        m = 0;
        n = 5;
        int[] f={1,2,3,4,5};
        merge(e, m, f,n);

        int[] g = {4,0,0,0,0,0};
        m = 1;
        n = 5;
        int[] h={1,2,3,5,6};
        merge(g, m, h,n);

    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n!=0) {
            int i = n - 1;
            int j = m + n - 1;

            while (i >= 0) {
                nums1[j] = nums2[i];
                i--;
                j--;
            }
        }


        System.out.println(Arrays.toString(nums1));


        for(int i=0; i<m+n-1;i++){
            for(int j=i+1;j<m+n;j++){
                if(nums1[i]>nums1[j]){
                    int temp= nums1[i];
                    nums1[i]=nums1[j];
                    nums1[j]=temp;
                }
            }
        }

        System.out.println(Arrays.toString(nums1));
    }

    @Test
    public void removeElement() {
        int[] nums = {0,3,2,2,3,0,4,2};
        int val=3;

        int k= nums.length;
        System.out.println(Arrays.toString(nums));

        int i=0;
        while(i<nums.length){
            if(nums[i]==val){

                for(int j=i; j<k-1;j++){
                    nums[j]=nums[j+1];
                }

                nums[k-1]= -1;
                k--;
                System.out.println(Arrays.toString(nums));
            }else i++;
        }

        System.out.println(Arrays.toString(nums));
        System.out.println("no of different values:"+k);
    }

    @Test
    public void removeElement2() {
        int[] nums = {0,3,2,2,3,0,4,2};
        int val=3;
        System.out.println(Arrays.toString(nums));

        int index=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[index]=nums[i];
                index++;
            }
        }

        System.out.println(Arrays.toString(nums));
        System.out.println("no of different values:"+index);
    }

    @Test
    public void removeDuplicatedFromSortedArray() {
        int[] nums= {0,0,1,1,1,2,2,3,3,4};

        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }

        System.out.print(Arrays.toString(nums));
        System.out.println(j);
    }

    @Test
    public void bestTime(){
        //int[] prices = {7,8,1,3,6,4};
        //int[] prices = {7,6,4,3,1};
        int[] prices = {3,2,6,5,0,3};
        int min = Integer.MAX_VALUE, max=0;
        int minpozition=0, maxpozition=0;
        int profit=0;

        if(prices.length>2) {
            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i] < min) {
                    min = prices[i];
                    minpozition = i;
                }

                for (int j = minpozition; j < prices.length; j++) {
                    if (prices[j] > min && prices[j] > max) {
                        max = prices[j];
                        maxpozition = j;
                    }
                }
            }
        }else if (prices.length==2 && prices[1] > min){
            max = prices[1];
            maxpozition = 1;
        }


        if(max!=0){
            profit= prices[maxpozition]-prices[minpozition];
        }

        System.out.println(profit);
    }

    @Test
    public void test(){
        StringBuilder sb1 = new StringBuilder("test");
        StringBuilder sb2 = new StringBuilder("test");
        System.out.println(sb1.toString().equals(sb2.toString()));
        System.out.println(sb1.equals(sb2));
        System.out.println("");
    }









}
