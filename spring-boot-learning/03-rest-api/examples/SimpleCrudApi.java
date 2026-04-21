package com.springboot.restapi.learning;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simple") // Base URL for all simple operations
public class SimpleCrudApi {

    /**
     * GET API
     * Returns a simple message
     * Example: GET http://localhost:8086/api/simple/get
     */
    @GetMapping("/get")
    public String getUser() {
        return "Returning user details";
    }

    /**
     * POST API
     * Creates a new user (simple example)
     * Example: POST http://localhost:8086/api/simple/create
     */
    
    // A POST API is generally used to create new data or add a user
  	// Example endpoint: http://localhost:8086/createUser
 	// Note: We cannot directly make this POST request from a browser URL bar because browsers default to GET requests.
 	// You can test it using curl:
 	// curl -X POST http://localhost:8086/createUser
 	// else use post man
    
    @PostMapping("/create")
    public String createUser() {
        return "User created successfully";
    }

    /**
     * PUT API
     * Updates user details (simple example)
     * Example: PUT http://localhost:8086/api/simple/update
     */
    @PutMapping("/update")
    public String updateUser() {
        return "User updated successfully";
    }

    /**
     * DELETE API
     * Deletes a user (simple example)
     * Example: DELETE http://localhost:8086/api/simple/delete
     */
    @DeleteMapping("/delete")
    public String deleteUser() {
        return "User deleted successfully";
    }
}