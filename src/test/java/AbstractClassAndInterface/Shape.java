package AbstractClassAndInterface;

public abstract class Shape {
    String objectName=" ";


    Shape(String object){
        this.objectName=object;
    }

    public void moveTo(int x, int y) {
        System.out.println(this.objectName + " has been moved to x = " + x + " and y = " + y);
    }

    abstract double area();
    abstract void draw();

}
