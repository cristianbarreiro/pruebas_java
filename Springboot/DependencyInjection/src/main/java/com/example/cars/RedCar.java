package com.example.cars;

import com.example.engines.Engine;
import com.example.interfaces.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedCar implements Car {

    @Value("${message}")
    private String message;

    @Autowired
    @Qualifier("V")
    Engine engine;

    @Override
    public void getSpec() {
        System.out.println("this is a SUV in Red color with " + engine.getType() + " engine");
        System.out.println("Message from properties: " + message);

    }
}
