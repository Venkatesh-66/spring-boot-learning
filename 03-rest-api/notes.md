📘 03 - REST API

🔹 1. What is REST API?

REST = Representational State Transfer

👉 It is a way for applications to communicate over HTTP

Client → sends request
Server → sends response (mostly JSON)

🧠 Simple Example

GET /users/1

👉 Response:

{
  "id": 1,
  "name": "John"
}

🔹 2. HTTP Methods (VERY IMPORTANT)

| Method | Use            | Example       |
|--------|----------------|--------------|
| GET    | Read data      | Get user     |
| POST   | Create data    | Add user     |
| PUT    | Update full data | Update user |
| PATCH  | Partial update | Update email |
| DELETE | Remove data    | Delete user  |

# 🔷 Spring Boot REST API Notes

---

## 🔹 3. Basic Annotations

### ✅ @RestController

```java
@RestController
public class UserController {
}

👉 Returns JSON directly

✅ @RequestMapping
@RequestMapping("/users")

👉 Base URL for all APIs in this controller

🔹 4. GET API

@GetMapping("/users/{id}")
public String getUser(@PathVariable int id) {
    return "User id: " + id;
}

🔹 5. POST API

@PostMapping("/users")
public String createUser(@RequestBody User user) {
    return "User created: " + user.getName();
}

🔹 6. PUT API (FULL UPDATE)

@PutMapping("/users/{id}")
public String updateUser(@PathVariable int id, @RequestBody User user) {
    return "User updated";
}

👉 Replaces full data

🔹 7. PATCH API (PARTIAL UPDATE)

@PatchMapping("/users/{id}")
public String updateUserEmail(@PathVariable int id, @RequestBody User user) {
    return "User email updated";
}

👉 Updates only some fields

🔹 8. DELETE API

@DeleteMapping("/users/{id}")
public String deleteUser(@PathVariable int id) {
    return "User deleted";
}

🔹 9. PathVariable vs RequestParam

✅ PathVariable
@GetMapping("/users/{id}")
public String getUser(@PathVariable int id) {
}

👉 URL: /users/10

✅ RequestParam
@GetMapping("/users")
public String getUser(@RequestParam String name) {
}

👉 URL: /users?name=John

🔹 10. RequestBody (IMPORTANT)

@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return user;
}

👉 Converts JSON → Java Object

🔹 11. ResponseEntity (Best Practice)

@GetMapping("/users")
public ResponseEntity<String> getUser() {
    return ResponseEntity.ok("Success");
}


🔹 12. HTTP Status Codes

| Code | Meaning        |
|------|----------------|
| 200  | OK             |
| 201  | Created        |
| 204  | No Content     |
| 400  | Bad Request    |
| 404  | Not Found      |
| 500  | Server Error   |

🔹 13. Exception Handling

❌ Problem

App crashes on error

✅ Solution

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "Error: " + ex.getMessage();
    }
}

🔹 16. Project Flow

Client → Controller → Service → Database → Response

🔹 17. Interview Important Questions

🔸 Difference: PUT vs PATCH

PUT → full update
PATCH → partial update

🔸 Difference: POST vs PUT

POST → create new
PUT → update existing

🔸 What is @RequestBody?

👉 Converts JSON → Java object

🔸 What is REST?

👉 Communication using HTTP

🔸 What is idempotent?

👉 Same request → same result

🧠 Final Summary
REST API = HTTP communication
Use GET, POST, PUT, DELETE
JSON is default format
Use proper status codes
Handle errors properly
Follow clean URL design