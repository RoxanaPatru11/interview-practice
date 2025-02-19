package AbstractClassAndInterface;

public class MainTest {

    //static methods can be called without initiating any object inside of a class.
    // If we don't use static, the JVM will not call the main method and you are not able to run from command line.
    public static void main(String[] args) {
        ///Abstract methods
        Rectangle rectangle= new Rectangle(4,6, "Rectangle");

        System.out.println(rectangle.area());
        rectangle.draw();

        rectangle.drawing();
        rectangle.moving();

        Shape circle1= new Circle( "Circle", 2);
        System.out.println(circle1.area());
        circle1.moveTo(2, 4);
        circle1.draw();

        //interface
    }


}
