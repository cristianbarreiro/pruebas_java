//interfaces de java
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

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

        // Function<Integer, Integer> square = number -> number * number;
        // System.out.println(square.apply(10));

        // Stream Operations
        List<Integer> numbers = Arrays.asList(10, 30, 25, 45, 50);
        System.out.println(numbers);

        // numbers.stream().filter(n -> n > 30).forEach(System.out::println);

        // Utilizando map
        // numbers.stream().map(n -> n * n).forEach(System.out::println);

        // numbers.stream().sorted().forEach( n -> System.out.println(n));

        // numbers.stream().filter(n -> n > 20).map(n -> n * n).forEach(System.out::println);

        List<Integer> result = numbers.stream().filter(n -> n > 20).collect(Collectors.toList());

        System.out.println(result);
    }
}