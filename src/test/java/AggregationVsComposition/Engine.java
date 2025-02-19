package AggregationVsComposition;

public class Engine {

    String engineType;

    Engine(String engineType) {
        this.engineType = engineType;
    }

    void start(){
        System.out.println("you start the "+ this.engineType+" engine");
    }

}
