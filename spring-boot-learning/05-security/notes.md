# 🔐 Spring Boot Security

## 📌 What is Spring Security?
Spring Security is a framework used to secure Spring Boot applications.  
It handles authentication (who you are) and authorization (what you can access).

👉 Simple:
Spring Security acts like a security guard that protects your application.

---

## 🔑 Core Concepts

### 1. Authentication
Authentication means verifying the identity of a user.

Example:
User enters username and password → system checks → valid or not

👉 Answer:
"Authentication is the process of verifying who the user is."

---

### 2. Authorization
Authorization means checking what the user is allowed to do.

Example:
Admin → can delete data  
User → cannot delete data  

👉 Answer:
"Authorization determines what actions a user can perform after authentication."

---

### 🔁 Difference

| Authentication | Authorization |
|---------------|--------------|
| Who are you?  | What can you do? |

---

## 🔄 Spring Security Flow

Request flow:

Client → Security Filter → Controller → Response

Steps:
1. User sends request
2. Spring Security intercepts it
3. Authentication is checked
4. Authorization is checked
5. If valid → request goes to controller
6. Else → error (401 / 403)

---

## 🧱 Security Filter Chain

Spring Security uses a chain of filters to process requests.

Each filter performs a task:
- Authentication check
- Token validation
- Authorization check

👉 Important:
All requests pass through the Security Filter Chain.

---

## 🔐 Default Security

When you add:
spring-boot-starter-security

Spring Boot:
- Secures all endpoints
- Provides default login
- Uses Basic Authentication

---

## 🔑 Basic Authentication

- Sends username and password in every request
- Encoded in Base64

👉 Not recommended for production

---

## 🔐 JWT (JSON Web Token)

JWT is a token-based authentication method.

### Flow:
1. User logs in with username and password
2. Server generates a token
3. Client stores the token
4. Client sends token in every request

👉 No need to send password again

---

### JWT Structure

HEADER.PAYLOAD.SIGNATURE

- Header → algorithm info
- Payload → user data (username, roles)
- Signature → security verification

---

## 🔁 Session vs JWT

| Feature | Session | JWT |
|--------|--------|-----|
| Storage | Server | Client |
| Scalable | No | Yes |
| Performance | Medium | High |

---

## 🔐 Password Encoding

Passwords should never be stored in plain text.

Use:
BCryptPasswordEncoder

Example:
1234 → encrypted value

---

## 👥 Roles

Users are assigned roles:

- ROLE_USER
- ROLE_ADMIN

Used for authorization.

---

## 🚫 Common Errors

- 401 → Unauthorized (not logged in)
- 403 → Forbidden (no permission)

---

## 🔐 CSRF

CSRF is an attack where a user is tricked into sending unwanted requests.

Solution:
- CSRF tokens
- Often disabled for REST APIs

---

## 🌐 CORS

CORS allows communication between frontend and backend on different origins.

Example:
React → Spring Boot

Solution:
@CrossOrigin

---

## 🧠 Summary

- Authentication → Who are you
- Authorization → What you can do
- Spring Security protects APIs
- JWT is used for secure authentication
- Filters handle request validation



# 🎯 Interview Perspective (Spring Security)

## 🔹 1. What is Spring Security?

👉 **Answer:**

Spring Security is a framework used to secure Spring Boot applications.  
It provides authentication and authorization mechanisms to protect APIs and control access.

---

## 🔹 2. Authentication vs Authorization?

👉 **Answer:**

Authentication is the process of verifying the user's identity,  
while authorization determines what actions the authenticated user is allowed to perform.

---

## 🔹 3. How does Spring Security work internally?

👉 **Answer:**

Spring Security uses a filter chain.  
Every request passes through multiple security filters where authentication and authorization checks are performed before reaching the controller.

---

## 🔹 4. What is JWT?

👉 **Answer:**

JWT (JSON Web Token) is a token-based authentication mechanism where the server generates a token after login,  
and the client uses this token for subsequent requests instead of sending credentials again.

---

## 🔹 5. Why JWT over Session?

👉 **Answer:**

JWT is stateless and scalable since it does not require server-side session storage,  
making it suitable for distributed systems.

---

## 🔹 6. What is 401 vs 403?

👉 **Answer:**

- **401** → User is not authenticated  
- **403** → User is authenticated but not authorized  

---

## 🔹 7. What is Password Encoding?

👉 **Answer:**

Password encoding converts plain text passwords into a secure hashed format  
using algorithms like BCrypt to prevent exposure.

---

## 🔹 8. What is Security Filter Chain?

👉 **Answer:**

It is a sequence of filters in Spring Security that intercepts incoming requests  
and applies authentication and authorization checks.

---

## 🧠 Pro Tip (What Interviewer Expects)

Don’t just define — explain the flow.

👉 **Best short explanation:**

When a request comes in, it goes through the Spring Security filter chain  
where authentication and authorization are validated before reaching the controller.