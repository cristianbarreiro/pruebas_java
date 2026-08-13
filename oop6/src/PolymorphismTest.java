import oop6.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolymorphismTest {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static int input() {
        System.out.println("Select a flyer: 1=Airplane, 2=Superhero, 3=UFO, 0=Exit");
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Flyer f = null;
        boolean keep = true;
        while (keep) {
            int option = input();
            switch (option) {
                case 1:
                    f = new Airplane();
                    break;
                case 2:
                    f = new Superhero();
                    break;
                case 3:
                    f = new UFO();
                    break;
                case 0:
                    keep = false;
                    break;
                default:
                    break;
            }
            if (f != null) {
                System.out.println("ABOUT TO EXECUTE FLIGHT MANOUVERS...");
                System.out.println(f.takeOff());
                System.out.println(f.fly());
                System.out.println(f.land());
                System.out.println("");
            }
        }
    }
}
