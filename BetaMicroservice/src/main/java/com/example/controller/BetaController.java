package com.example.controller;


import com.example.model.Device;
import com.example.model.Devices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BetaController {
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    List<Device> devices;
    @GetMapping("/beta")
    public Devices getDevices() {
        devices=new ArrayList<>();

        devices.add(new Device("Beta note", "Smart phone with good camera"));
        devices.add(new Device("Beta Fold", "Smart phone which folds"));
        Devices myDeviceList = new Devices("Samsung", devices);
        return myDeviceList;
    }
}
