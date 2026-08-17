package com.example.cars;

import com.example.engines.Engine;
import com.example.interfaces.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class RedCar implements Car {
    @Autowired
    @Qualifier("V")
    Engine engine;

    @Override
    public void getSpec() {
        System.out.println("this is a SUV in Red color with " + engine.getType() + " engine");

    }
}
