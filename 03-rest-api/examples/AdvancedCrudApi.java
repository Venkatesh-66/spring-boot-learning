package com.springboot.restapi.learning;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advanced")
public class AdvancedCrudApi {

    /**
     * GET API with @PathVariable
     * URL: GET http://localhost:8086/api/advanced/get/101
     * Output: "Returning user with ID: 101"
     */
    @GetMapping("/get/{id}")
    public String getUserById(@PathVariable int id) {
        return "Returning user with ID: " + id;
    }
    
    /**
     * DELETE API with @PathVariable
     * URL: DELETE http://localhost:8086/api/advanced/delete/101
     * Output: "User with ID 101 deleted successfully"
     */
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return "User with ID " + id + " deleted successfully";
    }

    /**
     * GET API with @RequestParam
     * URL: GET http://localhost:8086/api/advanced/search?name=Venkatesh
     * Output: "Searching user with name: Venkatesh"
     */
    @GetMapping("/search")
    public String getUserByName(@RequestParam String name) {
        return "Searching user with name: " + name;
    }
    
    // with 2 Params
    @GetMapping("/login")
    public String loginUser(
            @RequestParam String name,
            @RequestParam String password) {

        // Simple check (for demo purposes)
        if ("admin".equals(name) && "1234".equals(password)) {
            return "Login successful for user: " + name;
        } else {
            return "Login failed for user: " + name;
        }
    }

    /**
     * POST API with @RequestBody
     * URL: POST http://localhost:8086/api/advanced/create
     * Body: { "name": "Venkatesh" }
     * Output: "User created with name: Venkatesh"
     */
    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        return "User created with name: " + user.getName();
    }

    /**
     * PUT API with @RequestBody
     * URL: PUT http://localhost:8086/api/advanced/update
     * Body: { "name": "New Name" }
     * Output: "User updated to name: New Name"
     */
    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        return "User updated to name: " + user.getName();
    }

    /**
     * PATCH API with @RequestBody
     * URL: PATCH http://localhost:8086/api/advanced/patch
     * Body: { "name": "Partial Name" }
     * Output: "User partially updated with name: Partial Name"
     */
    @PatchMapping("/patch")
    public String patchUser(@RequestBody User user) {
        return "User partially updated with name: " + user.getName();
    }


    // -------------------
    // User class for RequestBody
    // -------------------
	public static class User {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}