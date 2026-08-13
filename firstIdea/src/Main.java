import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();

        names.add("Joe");
        names.add("Ricky");
        names.add("Tim");

        for (String name : names) {
            System.out.println(name);
        }

        names.forEach(name -> {
            System.out.println("Name: " + name);
            System.out.println("Length: " + name.length());
        });
    }
}