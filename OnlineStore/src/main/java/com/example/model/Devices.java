package com.example.model;

import java.util.List;

public class Devices {
    String brand;
    List<Device> devices;

    public Devices() {

    }

    public Devices(String brand, List<Device> devices) {
        this.brand = brand;
        this.devices = devices;
    }

    public String getBrand() {
        return brand;
    }

    public List<Device> getDevices() {
        return devices;
    }
}
