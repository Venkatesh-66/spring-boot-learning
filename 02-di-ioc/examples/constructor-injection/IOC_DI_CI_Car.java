package com.springboot.iocdi.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IOC_DI_CI_Car {

    private final IOC_DI_CI_Engine engine;  // Make final for constructor injection

    @Autowired  // Optional in recent Spring versions if only one constructor
    public IOC_DI_CI_Car(IOC_DI_CI_Engine engine) {
        this.engine = engine;
    }

    public void start() {
        System.out.println("Starting the car...");
        engine.startEngine();
    }
}