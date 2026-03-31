# 01 - SPRING BOOT FUNDAMENTALS (Deep & Simple)

---

# 🚀 1. What is Spring Boot?

Spring Boot is a Java framework used to build backend applications quickly with minimal configuration.

### Key Features:

- Auto Configuration (Spring configures things automatically)
- Embedded Server (Tomcat included, no need to install)
- No XML configuration
- Production-ready

---

# 🔥 2. Why Spring Boot?

### Problems in old Spring:

- Too much manual configuration
- XML-based setup
- Time-consuming

### With Spring Boot:

- Less setup
- Faster development
- Focus on business logic instead of configuration

👉 Main goal:
Reduce boilerplate code (repetitive code)

---

# ⚙️ 3. Spring vs Spring Boot

| Spring                | Spring Boot        |
| --------------------- | ------------------ |
| Manual configuration  | Auto configuration |
| Needs external server | Built-in server    |
| Complex setup         | Easy setup         |

---

# 🧠 4. Core Concepts (Very Important)

---

## 🔹 Dependency Injection (DI)

- Spring creates and manages objects
- You don’t use `new` keyword everywhere
- Reduces tight coupling

❌ Without DI:

```java id="b1"
UserService service = new UserService();
```

✅ With DI:

```java id="b2"
@Autowired
UserService service;
```

---

## 🔹 Inversion of Control (IoC)

- Control is given to Spring
- Spring manages object creation and lifecycle

👉 Simple idea:
"Don’t call objects — let Spring provide them"

---

## 🔹 Bean

- Any object managed by Spring is called a Bean
- Example: Controller, Service, Repository

---

## Common Packages:

- controller → handles API requests
- service → business logic
- repository → database access
- model/entity → data objects

---

# 📦 5. pom.xml (Dependencies)

- Managed by Maven
- Used to add external libraries

Example:

```xml id="b4"
<dependency>
    <groupId>org.springframework.boot</groupId>
</dependency>
```

👉 Without dependencies, nothing works in Spring Boot

---

# 🔧 6. application.properties

- Used to configure application

Example:

```properties id="b5"
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/db
```

---

## Common Uses:

- Change port
- Database connection
- Logging configuration

---

# ▶️ 7. Main Class (Entry Point)

```java id="b6"
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

---

## What happens internally:

- Starts Spring Boot
- Scans all components
- Creates beans
- Starts server

---

# 🌐 8. REST API Basics

---

## 🔹 @RestController

- Used to create API endpoints

```java id="b7"
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
```

---

## 🔹 HTTP Methods

- GET → Fetch data
- POST → Create data
- PUT → Update data
- DELETE → Remove data

---

## 🔹 API Endpoint

- URL that client calls

Example:

```id="b8"
http://localhost:8080/hello
```

---

# 🔗 9. How Spring Boot Works (Flow)

1. Application starts
2. Spring scans classes
3. Finds annotations
4. Creates beans
5. Injects dependencies
6. Starts embedded server
7. APIs become available

---

# 🔌 10. Embedded Server

- Default: Tomcat
- No need to install separately

👉 Advantage:

- Run application directly using main method

---

# 🧩 11. Important Annotations

- @SpringBootApplication → main class
- @RestController → API class
- @Autowired → inject dependency
- @Component → generic bean
- @Service → business layer
- @Repository → database layer

---

# 🧠 12. Internal Working (Deep Understanding)

- Spring scans your project
- Identifies classes with annotations
- Creates objects (beans)
- Stores them in container
- Injects them where needed
- Maps URLs to methods

---

# ⚠️ 13. Common Mistakes

- Forgetting @RestController
- Wrong URL mapping
- Not adding dependencies
- Using `new` instead of DI
- Wrong package structure

---

# 🧠 FINAL SUMMARY

- Spring Boot = Fast backend framework
- DI = No manual object creation
- IoC = Spring controls objects
- REST = API creation
- Embedded server = No setup needed

---

# ✅ MINI TASK

1. Create Spring Boot project
2. Add controller
3. Create `/hello` API
4. Run and test in browser

---

# 🔁 REVISION METHOD

- Read in 5 minutes
- Explain without seeing
- Rebuild project from memory

---

# Important Annotations Basics

## @SpringBootApplication

- Entry point annotation
- Combines:
  - @Configuration
  - @EnableAutoConfiguration
  - @ComponentScan

## @RestController

- Marks class as REST API controller
- Combines:
  - @Controller
  - @ResponseBody

## @Autowired

- Used for dependency injection
