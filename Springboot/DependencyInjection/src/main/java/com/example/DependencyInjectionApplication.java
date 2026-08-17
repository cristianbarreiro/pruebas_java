package com.example;

import com.example.cars.RedCar;
import com.example.cars.YellowCar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {

    public static void main(String[] args) {

        //SpringApplication.run(DependencyInjectionApplication.class, args);

        ApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class);
        YellowCar yCar = context.getBean(YellowCar.class);
        yCar.getSpec();
        RedCar rCar = context.getBean(RedCar.class);
        rCar.getSpec();
    }

}
