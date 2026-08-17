package com.example.cars;

import com.example.engines.Engine;
import com.example.interfaces.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class YellowCar implements Car {

    @Autowired
            @Qualifier("W")
    Engine engine;

    //Contructor injection
    @Autowired
    public YellowCar(@Qualifier("W") Engine engine) {
        this.engine = engine;
    }

    //Setter injection

    @Autowired
    public void setEngine(@Qualifier("W") Engine engine) {
        this.engine = engine;
    }

    @Override
    public void getSpec() {
        System.out.println("this is a SUV in Yellow color with " + engine.getType() + " engine");

    }


}
