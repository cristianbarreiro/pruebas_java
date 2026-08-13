import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, String> fruitMap = new HashMap<>();

        fruitMap.push(1, "Apple");
        fruitMap.push(2, "Banana");
        fruitMap.push(3, "Mango");
        System.out.println(fruitMap);
    }
}