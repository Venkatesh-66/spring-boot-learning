package com.springboot.iocdi.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IOC_DI_SI_Car {

    /**
     * Problems with setter injection:
     * 1. Dependency is not mandatory. Object can exist without it.
     * 2. Can lead to NullPointerException if setter not called.
     * 3. Object can be in incomplete state until setter runs.
     * 4. Dependency can be changed anytime.
     * 5. Not immutable.
     * 6. Harder to see required dependencies.
     * 7. Slightly harder to test than constructor injection.
     */
    private IOC_DI_SI_Engine engine;

    
    // Setter injection = Spring calls it automatically after object creation.
    // You never manually call the setter.
    // The field is assigned by Spring before you use the object.
    
    
    // Spring will inject engine via this setter
    @Autowired
    public void setEngine(IOC_DI_SI_Engine engine) {
        this.engine = engine;
    }

    public void start() {
        System.out.println("Starting the car...");
        engine.startEngine();
    }
}