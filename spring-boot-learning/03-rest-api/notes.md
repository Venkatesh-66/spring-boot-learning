# 📘 03 - REST API

---

## 🔹 1. What is REST API?

**REST = Representational State Transfer**

👉 A way for applications to communicate over HTTP

* **Client** → sends request
* **Server** → sends response (mostly JSON)

### 🧠 Simple Example

```http
GET /users/1
```

👉 Response:

```json
{
  "id": 1,
  "name": "John"
}
```

---

## 🔹 2. HTTP Methods (VERY IMPORTANT)

| Method | Use            | Example      |
| ------ | -------------- | ------------ |
| GET    | Read data      | Get user     |
| POST   | Create data    | Add user     |
| PUT    | Full update    | Update user  |
| PATCH  | Partial update | Update email |
| DELETE | Remove data    | Delete user  |

---

# 🔷 Spring Boot REST API Notes

---

## 🔹 3. Basic Annotations

### ✅ `@RestController`

```java
@RestController
public class UserController {
}
```

👉 Returns JSON directly

---

### ✅ `@RequestMapping`

```java
@RequestMapping("/users")
```

👉 Base URL for all APIs in this controller

---

## 🔹 4. GET API

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable int id) {
    return "User id: " + id;
}
```

---

## 🔹 5. POST API

```java
@PostMapping("/users")
public String createUser(@RequestBody User user) {
    return "User created: " + user.getName();
}
```

---

## 🔹 6. PUT API (FULL UPDATE)

```java
@PutMapping("/users/{id}")
public String updateUser(@PathVariable int id, @RequestBody User user) {
    return "User updated";
}
```

👉 Replaces full data

---

## 🔹 7. PATCH API (PARTIAL UPDATE)

```java
@PatchMapping("/users/{id}")
public String updateUserEmail(@PathVariable int id, @RequestBody User user) {
    return "User email updated";
}
```

👉 Updates only specific fields

---

## 🔹 8. DELETE API

```java
@DeleteMapping("/users/{id}")
public String deleteUser(@PathVariable int id) {
    return "User deleted";
}
```

---

## 🔹 9. PathVariable vs RequestParam

### ✅ PathVariable

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable int id) {
}
```

👉 URL: `/users/10`

---

### ✅ RequestParam

```java
@GetMapping("/users")
public String getUser(@RequestParam String name) {
}
```

👉 URL: `/users?name=John`

---

## 🔹 10. RequestBody (IMPORTANT)

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return user;
}
```

👉 Converts JSON → Java Object

---

## 🔹 11. ResponseEntity (Best Practice)

```java
@GetMapping("/users")
public ResponseEntity<String> getUser() {
    return ResponseEntity.ok("Success");
}
```

---

## 🔹 12. HTTP Status Codes

| Code | Meaning      |
| ---- | ------------ |
| 200  | OK           |
| 201  | Created      |
| 204  | No Content   |
| 400  | Bad Request  |
| 404  | Not Found    |
| 500  | Server Error |

---

## 🔹 13. Exception Handling

### ❌ Problem

* Application crashes on error

### ✅ Solution

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "Error: " + ex.getMessage();
    }
}
```

---

## 🔹 16. Project Flow

```text
Client → Controller → Service → Database → Response
```

---

## 🔹 17. Interview Important Questions

### 🔸 Difference: PUT vs PATCH

* **PUT** → Full update
* **PATCH** → Partial update

### 🔸 Difference: POST vs PUT

* **POST** → Create new
* **PUT** → Update existing

### 🔸 What is `@RequestBody`?

👉 Converts JSON → Java object

### 🔸 What is REST?

👉 Communication using HTTP

### 🔸 What is idempotent?

👉 Same request → same result

---

## 🧠 Final Summary

* REST API = HTTP communication
* Use GET, POST, PUT, DELETE
* JSON is the default format
* Use proper status codes
* Handle errors properly
* Follow clean URL design
