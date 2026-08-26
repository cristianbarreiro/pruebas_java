package com.example.model;

import java.util.List;

public class Devices {
    private String brand;
    private List<Device> devices;

    public Devices(String brand, List<Device> devices) {
        this.brand = brand;
        this.devices = devices;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "brand='" + brand + '\'' +
                ", devices=" + devices +
                '}';
    }
}
