package com.springboot.iocdi.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IOC_DI_FI_Car {

	
	/**
	 * Problems with field injection:
	 *
	 * 1. When Spring creates the object (IOC_DI_FI_Car), it injects the dependency
	 *    (IOC_DI_FI_Engine) automatically, so everything works fine.
	 *
	 *    However, if you create the object manually using the 'new' keyword,
	 *    Spring is not involved, so the dependency will NOT be injected.
	 *
	 *    In that case, 'engine' will be null, and calling engine.startEngine()
	 *    will throw a NullPointerException.
	 *
	 * 2. Dependencies are hidden.
	 *    You cannot see what this class needs just by looking at the constructor.
	 *
	 * 3. Hard to test.
	 *    You cannot easily pass a mock object for testing.
	 *
	 * 4. Cannot make dependency final.
	 *    So it can be changed later, which is not safe.
	 *
	 * 5. Injection happens after object creation.
	 *    So the object can exist in an incomplete (invalid) state for some time.
	 *
	 * 6. Harder to debug.
	 *    NullPointerException may come at runtime instead of early detection.
	 */
	 @Autowired
	 private IOC_DI_FI_Engine engine;

	    public void start() {
	        System.out.println("Starting the car...");
	        engine.startEngine();
	    }
}
