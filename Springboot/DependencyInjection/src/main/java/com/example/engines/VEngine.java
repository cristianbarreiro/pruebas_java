package com.example.engines;

import org.springframework.stereotype.Component;

@Component("V")
public class VEngine extends Engine {
    public String getType() {
        return "VEngine";
    }
}
