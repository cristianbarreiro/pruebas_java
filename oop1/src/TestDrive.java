import oop1.Car;
import oop1.Engine;

public class TestDrive {

    public static void main(String[] args) {

        Engine e = new Engine();
        e.setManufacturer("home-built");
        e.setDisplacement(1100);

        Car c = new Car(e);
        c.setBrand("Ford");
        c.setModel("F01");

        // RPM
        c.goFaster();
        System.out.println(c.getCurrentSpeed());

        c.goFaster();
        System.out.println(c.getCurrentSpeed());

        c.goFaster();
        System.out.println(c.getCurrentSpeed());

        c.goFaster();
        System.out.println(c.getCurrentSpeed());

        c.goFaster();
        System.out.println(c.getCurrentSpeed());
    }
}