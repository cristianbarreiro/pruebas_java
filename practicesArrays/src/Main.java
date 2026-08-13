import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("");
        fruits.add("Mango");
        fruits.add("Apple");
        System.out.println(fruits.get(0));
        fruits.remove("Banana");
        System.out.println(fruits);

    }
}