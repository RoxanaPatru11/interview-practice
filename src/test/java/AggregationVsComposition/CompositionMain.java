package AggregationVsComposition;

public class CompositionMain {

    public static void main(String[] args) {

        Car car= new Car("Tesla", 2025, "V8");

        System.out.println(car.engine.engineType);
        System.out.println(car.model);
        System.out.println(car.year);

        car.start();
    }
}
