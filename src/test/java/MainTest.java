import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void testShallowMemory(){
        // creating an object of the class ShallowCopyExample
        ShallowAndDeepCopyExample el1 = new ShallowAndDeepCopyExample();

        // it will copy the reference, not value
        ShallowAndDeepCopyExample el2 = el1;

        // updating the value to 6
        // using the reference variable obj2
        el2.x=6;

        // printing the value of x using reference variable obj1
        System.out.println("The value of x is: " + el1.x); //6
    }


    @Test
    public void testDeepMemory(){
        // creating an object of the class ShallowCopyExample
        ShallowAndDeepCopyExample obj1 = new ShallowAndDeepCopyExample();

        // it will copy the reference, not value
        ShallowAndDeepCopyExample obj2 = new ShallowAndDeepCopyExample();

        // updating the value to 6
        // using the reference variable obj2
        obj2.x=6;

        // printing the value of x using reference variable obj1
        System.out.println("The value of x from obj1 is: " + obj1.x);  //30
        System.out.println("The value of x from obj2 is: " + obj2.x); //6
    }

    @Test
    public void stringCOPYMemory(){
        //there is no difference between deep and shallow copy when we are dealing with strings in Java... strings in Java are always immutable.
        // an object of the String class is created
        String obj1 = new String("JavaTpoint is a very good site.");

        // copying obj1 to obj2
        String obj2 = obj1;

        // printing the hash code using the reference variable obj1.
        System.out.println("The hash code is: " + obj1.hashCode());
        // printing the string
        System.out.println("The string is: " + obj1 + "\n");

        // printing the hash code using the reference variable obj2.
        System.out.println("The hash code for obj2 before updating is:" + obj2.hashCode());

        // printing the string
        System.out.println("The hash code for obj2 before updating is:" + obj2);


        // we have updated the string for reference variable obj2
        obj2 = "JavaTpoint is very good.";

        // printing the hash code using the reference variable obj2.
        System.out.println("The hash code for obj2 after updating is:" + obj2.hashCode());

        // printing the string
        System.out.println("The hash code for obj2 after updating is:" + obj2);
    }

    @Test
    public void StringsAreImmutable(){
        //Example1
        String s1 = "knowledge";
        System.out.println("The hash code is: " + s1.hashCode());

        String s2 = s1;            // s2 points to the same "knowledge"
        System.out.println("The hash code is: " + s2.hashCode());

        s1 = s1.concat(" base");   // creates a new String "knowledge base"
        System.out.println("The new hash code is: " + s1.hashCode());
        System.out.println(s1);


        //Example2
        String s3 = "java";
        System.out.println("The hash code is: " + s3.hashCode());

        // creates a new String "java rules",
        // but does not change s1
        s3.concat(" rules");

        // s3 still refers to "java"
        System.out.println("The hash code is: " + s3.hashCode());
        System.out.println("s3 refers to " + s3);
    }

    @Test
    public void DataTypeCopyExample(){
        int x=9;
        int y=x;

        System.out.println("x: "+ x);
    }


}
