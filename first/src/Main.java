//interfaces de java
import java.util.function.*;

@FunctionalInterface
interface Greeting {

   void sayHello();
}

@FunctionalInterface
interface Addition {
    int add(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        // Greeting greeting = () -> System.out.println("Welcome to Interfaces!");
        // greeting.sayHello();

        // Addition addition = (a, b) -> a + b;
        // int result = addition.add(10, 20);
        // System.out.println(result);

        // Predicate<Integer> isEven = number -> number % 2 == 0;
        // System.out.println(isEven.test(4));

        Function<Integer, Integer> square = number -> number * number;
        System.out.println(square.apply(10));

    }
}