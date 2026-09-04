import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

import  java.util.Objects;

public class Main {
    public static void main(String[] args) {
//        LocalDate today = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = today.format(formatter);
//        System.out.println("Formatted date: " + formattedDate);

//        String phoneNumber = null;
//        Optional<String> optionalPhone = Optional.ofNullable(phoneNumber);
//        String result = optionalPhone.orElse("Phone number not available");
//        System.out.println(result);

        String username = null; // Use a String to avoid the exception.
        Objects.requireNonNull(username, "username cannot be null");


    }
}