package com.example.engines;

import org.springframework.stereotype.Component;

@Component
public class WEngine extends Engine {
    public String getType() {
        return "WEngine";
    }
}
