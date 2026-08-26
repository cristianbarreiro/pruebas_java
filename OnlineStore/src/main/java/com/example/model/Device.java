package com.example.model;

public class Device {
    String name;
    String description;
    String type;

    public Device(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Device() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
