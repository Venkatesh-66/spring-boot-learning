package com.springboot.iocdi.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IOC_DI_FI_Main {
	
	
	public static void main(String[] args) {

        ApplicationContext container = SpringApplication.run(IOC_DI_FI_Main.class, args);

        IOC_DI_FI_Car car = container.getBean(IOC_DI_FI_Car.class);

        car.start();
    }

}
