package com.springboot.iocdi.learning;

import org.springframework.stereotype.Component;

@Component
public class IOC_DI_FI_Engine {

	 public void startEngine() {
	        System.out.println("Started the engine");
	        System.out.println("Car is ready to race");
	    }
}
