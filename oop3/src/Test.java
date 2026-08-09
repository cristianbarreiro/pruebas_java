import oop3.Car;

public class Test {

    public static void main(String[] args) {
        Car c = new Car();
        c.accelerate();
        c.accelerate(40);
        int limit = 70;
        c.accelerate(100,limit);
    }
}
