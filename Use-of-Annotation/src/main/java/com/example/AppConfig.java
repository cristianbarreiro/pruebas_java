package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = {"com.example", "org.learnquest"})

public class AppConfig {
}
