package Polymorphism;

public class Main {

    public static void main(String[] args) {

        Boat boat = new Boat();
        Car car = new Car();
        Bike bike = new Bike();

        Vehicle[] vehicle= {boat, car, bike};

        for(Vehicle v: vehicle){
            v.go();
        }

        Vehicle[] vehicle1= new Vehicle[3];
        vehicle1[0]=boat;
        vehicle1[1]=car;
        vehicle1[2]=bike;

        for(int i=0; i<vehicle1.length; i++){
            vehicle[i].go();
        }
    }
}
