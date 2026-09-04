import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        String email = "null"; // you can change to null to see how it's used or use any string
        System.out.println(email);

        Optional<String> optionalEmail = Optional.ofNullable(email);

        if (optionalEmail.isPresent()) {
            System.out.println(optionalEmail.get());
        } else {
            System.out.println("Email not available");
        }

        String result = optionalEmail.orElse("No email provided");
        System.out.println(result);
    }
}