package com.example.engines;

import org.springframework.stereotype.Component;

@Component("W")
public class WEngine extends Engine {
    public String getType() {
        return "WEngine";
    }
}
