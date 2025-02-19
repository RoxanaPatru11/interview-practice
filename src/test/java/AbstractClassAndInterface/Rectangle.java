package AbstractClassAndInterface;

public class Rectangle extends Shape implements Drawable, Movable{
    int length, width;

    Rectangle(int length, int width, String name) {
        super(name);
        this.length = length;
        this.width = width;
    }

    @Override
    double area(){
        return this.length * this.width;
    }

    @Override
    void draw(){
        System.out.println("Rectangle has been drawn ");
    }

    @Override
    public void drawing(){
        System.out.println("Rectangle has been drawn from the interface");
    }

    @Override
    public void moving(){
        System.out.println("Rectangle has been moved from the interface");
    }
}
