package com.springboot.iocdi.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IOC_DI_SI_Main {

    public static void main(String[] args) {
        // Start Spring Boot and get the context
        ApplicationContext container = SpringApplication.run(IOC_DI_SI_Main.class, args);

        // Get the Car bean from Spring
        IOC_DI_SI_Car car = container.getBean(IOC_DI_SI_Car.class);

        // Start the car
        car.start();
    }
}