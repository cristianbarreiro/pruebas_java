package com.example.controller;

import com.example.model.Devices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private Devices result;

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/alpha")
    public ModelAndView alpha() {
        ModelAndView modelAndView = new ModelAndView("alpha");
        RestTemplate restTemplate = new RestTemplate();
        result = restTemplate.getForObject("http://localhost:8086/alpha", Devices.class);
        modelAndView.addObject("mylist", result);

        return modelAndView;
    }

    @RequestMapping("/beta")
    public ModelAndView beta() {
        ModelAndView modelAndView = new ModelAndView("beta");
        RestTemplate restTemplate = new RestTemplate();
        result = restTemplate.getForObject("http://localhost:8087/beta", Devices.class);
        modelAndView.addObject("mylist", result);

        return modelAndView;
    }



}
