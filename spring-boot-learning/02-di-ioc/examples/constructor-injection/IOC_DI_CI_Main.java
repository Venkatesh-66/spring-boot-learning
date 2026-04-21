package com.springboot.iocdi.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class IOC_DI_CI_Main {

	
	public static void main(String[] args) {
		
		
		/**
		 * Before Spring IoC and Dependency Injection (DI), we had to create objects manually using the `new` keyword. 
		 * For example:
		 * IOC_DI_CI_Car car = new IOC_DI_CI_Car();
		 * 
		 * With Spring Boot, by using the @SpringBootApplication annotation, we tell Spring that this is a Spring Boot application.
		 * 
		 * The Spring IoC container is then responsible for creating beans for all classes annotated with @Component, 
		 * including Car and Engine. Spring automatically scans, creates, and stores these beans in the container.
		 * 
		 * In the example below, we are fetching the Car bean from the container and using it. 
		 * Spring takes care of injecting the Engine dependency into the Car automatically.
		 */
		    
		
		
		// Start Spring Boot application and get ApplicationContext
        ApplicationContext container = SpringApplication.run(IOC_DI_CI_Main.class, args);

        // Fetch Car bean from the container
        IOC_DI_CI_Car car = container.getBean(IOC_DI_CI_Car.class);

        // Call start method
        car.start();
		
		
	}
	
}
