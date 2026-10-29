# Spring Boot Annotations — Simple English, Deep Understanding

A practical guide to the annotations you should know for Java and Spring Boot interviews.

> **Big idea:** An annotation is a label that gives instructions or metadata to Java, Spring, or Spring MVC. The framework reads those labels and behaves accordingly.
>
> Example: `@RestController` tells Spring, “Treat this class as a web controller whose methods usually return response data.”

---

## 1. First, understand how Spring works

Imagine you are building a banking application. You have classes for accounts, customers, payments, and APIs.

Without Spring, you might create objects manually:

```java
AccountRepository repository = new AccountRepository();
AccountService service = new AccountService(repository);
AccountController controller = new AccountController(service);
```

As the application grows, manually creating and connecting every object becomes difficult.

Spring can create and manage objects for you. An object managed by Spring is called a **Spring bean**.

```text
Application starts
       |
       v
@SpringBootApplication
       |
       v
Spring scans for components and configuration
       |
       v
Spring creates and configures beans
       |
       v
Dependencies are injected into other beans
       |
       v
Application is ready to handle requests
```

This is a simplified view; bean creation and dependency injection can happen in a more detailed lifecycle.

### What is dependency injection (DI)?

Suppose `AccountService` needs an `AccountRepository`.

Instead of `AccountService` creating its own repository, Spring provides the repository object to it. This is **dependency injection**.

```text
AccountController
       |
       v
AccountService
       |
       v
AccountRepository
```

**Remember:**
- **IoC (Inversion of Control):** Spring takes responsibility for creating and managing objects.
- **DI (Dependency Injection):** Spring supplies the objects a class depends on.
- **Bean:** An object managed by Spring.
- **ApplicationContext:** The Spring container that manages beans and other application configuration.

---

# 2. Bean and dependency annotations

## `@Component`

**Simple meaning:** “Spring, discover this class and manage an object of it.”

```java
import org.springframework.stereotype.Component;

@Component
public class NotificationHelper {
    public void send() {
        System.out.println("Notification sent");
    }
}
```

When component scanning finds this class, Spring can register it as a bean.

**When to use:** For a general-purpose helper or class that does not specifically represent a service, repository, or controller.

**Remember:** `@Component` is a general-purpose stereotype annotation.

## `@Autowired`

**Simple meaning:** “Spring, inject the dependency I need.”

Constructor injection is generally the recommended approach:

```java
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final NotificationHelper notificationHelper;

    public AccountService(NotificationHelper notificationHelper) {
        this.notificationHelper = notificationHelper;
    }
}
```

Because the class has only one constructor, Spring can use it for injection without `@Autowired`.

You can also write:

```java
@Autowired
public AccountService(NotificationHelper notificationHelper) {
    this.notificationHelper = notificationHelper;
}
```

**Prefer constructor injection** because required dependencies are explicit, fields can be `final`, and the class is easier to test.

**Important:** `@Autowired` does not create a missing implementation magically. Spring must be able to find a suitable bean to inject.

## `@Qualifier`

**Simple meaning:** “If multiple beans match, inject the one with this name/qualifier.”

Imagine you have two payment implementations:

```java
@Component("upiPayment")
class UpiPayment implements Payment {
}

@Component("cardPayment")
class CardPayment implements Payment {
}
```

Inject the required one:

```java
@Service
class CheckoutService {
    private final Payment payment;

    CheckoutService(@Qualifier("upiPayment") Payment payment) {
        this.payment = payment;
    }
}
```

**When to use:** When more than one bean matches the same dependency type and you need to specify which one to inject.

## `@Primary`

**Simple meaning:** “Use this bean by default when several candidates match.”

```java
@Component
@Primary
class UpiPayment implements Payment {
}

@Component
class CardPayment implements Payment {
}
```

Now an injection of `Payment` without a qualifier normally chooses `UpiPayment`.

**`@Primary` vs `@Qualifier`:**
- `@Primary`: Establishes the preferred default.
- `@Qualifier`: Narrows the selection at an injection point.
- A matching `@Qualifier` can select a specific bean instead of the primary default.

## `@Bean`

**Simple meaning:** “Spring, register the object returned by this method as a bean.”

```java
@Configuration
public class AppConfig {
    @Bean
    public java.time.Clock applicationClock() {
        return java.time.Clock.systemUTC();
    }
}
```

Spring calls the method when creating the bean and manages the returned object.

**When to use:** Especially useful when you need to configure a class you did not write, such as a third-party library class.

### `@Component` vs `@Bean`

| `@Component` | `@Bean` |
|---|---|
| Usually placed on a class | Placed on a method |
| Spring discovers the class through component scanning | A configuration method explicitly provides the object |
| Useful for classes you control | Useful for third-party objects or explicit setup |


Here is the Markdown content in the same format, ready to add to your existing `.md` file.

### 1. `@Component` என்றால் என்ன?

ஒரு class-ஐ Spring தானாகக் கண்டுபிடித்து object create செய்து manage செய்யச் சொல்வதற்கு `@Component` பயன்படுத்துகிறோம்.


@Component
public class EmailService {

}


இங்கே என்ன நடக்கும்?

- Spring இந்த class-ஐ கண்டுபிடிக்கும்.
- Object create செய்யும்.
- அந்த object-ஐ manage செய்யும்.

**நினைவில் வைத்துக்கொள்ளுங்கள்:** `@Component` → Class-க்கு போடுவது.

### 2. `@Bean` என்றால் என்ன?

ஒரு method return செய்யும் object-ஐ Spring manage செய்ய

## `@Scope`

**Simple meaning:** “Define how Spring creates and reuses this bean.”

Common scopes:

| Scope | Meaning |
|---|---|
| `singleton` | One bean instance per Spring container; this is the default |
| `prototype` | Spring creates a new instance each time that bean is requested from the container |
| `request` | One instance per HTTP request in a web application |
| `session` | One instance per HTTP session in a web application |

Example:

```java
@Component
@Scope("prototype")
public class ReportBuilder {
}
```

**Important:** `singleton` does not mean one instance for the entire universe or necessarily one per JVM; it means one instance per bean definition in that Spring container. Also, injecting a prototype bean into a singleton does not automatically create a fresh prototype for every method call.

## `@Lazy`

**Simple meaning:** “Delay creating this bean until it is first needed,” in common use.

```java
@Component
@Lazy
public class HeavyReportService {
    public HeavyReportService() {
        System.out.println("HeavyReportService created");
    }
}
```

A lazy bean is normally created when first requested rather than eagerly at startup. Exact behavior can depend on how it is requested and the surrounding configuration.

**When to use:** For expensive initialization when delaying it is useful.

**Caution:** It can delay the moment an initialization problem appears. It is not a general performance fix.

## `@Value`

**Simple meaning:** “Read a value from configuration and put it here.”

`application.properties`:

```properties
app.name=BankingApp
app.max-retries=3
```

Java:

```java
@Component
public class AppInfo {
    @Value("${app.name}")
    private String appName;

    @Value("${app.max-retries:5}")
    private int maxRetries;
}
```

- `${app.name}` reads the configured property.
- `${app.max-retries:5}` uses `5` if the property is not available.

For larger groups of related configuration properties, `@ConfigurationProperties` is often easier to maintain than many `@Value` fields.

## `@PropertySource`

**Simple meaning:** “Load properties from this additional properties file.”

```java
@Configuration
@PropertySource("classpath:custom.properties")
public class CustomPropertiesConfig {
}
```

If `custom.properties` is in `src/main/resources`, Spring can load its properties into the Environment.

**When to use:** When you need to load an additional properties file. Spring Boot's `application.properties` or `application.yml` is usually the main application configuration mechanism.

## `@PropertySources`

**Simple meaning:** “Declare multiple `@PropertySource` annotations together.”

```java
@Configuration
@PropertySources({
    @PropertySource("classpath:database.properties"),
    @PropertySource("classpath:feature.properties")
})
public class ExtraPropertiesConfig {
}
```

This is a container annotation. In modern Java, repeatable annotations may let you write repeated `@PropertySource` annotations directly, but `@PropertySources` is still valid.

---

# 3. Common class-role annotations

## `@Controller`

**Simple meaning:** “This class handles web requests.”

```java
@Controller
public class PageController {
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
```

In a typical Spring MVC application, returning `"welcome"` from a controller like this identifies a view name, such as a template. It does not automatically mean the response body is the literal word `welcome`.

**When to use:** Traditional MVC pages/views, although a controller can also use `@ResponseBody` on selected methods.

## `@RestController`

**Simple meaning:** “This class handles web requests and writes return values to the response body by default.”

```java
@RestController
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting() {
        return "Hello";
    }
}
```

The response body is `Hello`. For an object return value, Spring commonly serializes it as JSON when an appropriate message converter is configured.

**Key fact:** `@RestController` is effectively `@Controller` + `@ResponseBody`.

## `@Service`

**Simple meaning:** “This class contains application/business logic.”

```java
@Service
public class TransferService {
    public String transfer() {
        return "Transfer processed";
    }
}
```

`@Service` is a specialized form of `@Component`. It communicates the class's role clearly.

**Typical flow:** Controller → Service → Repository.

## `@Repository`

**Simple meaning:** “This class is responsible for data access.”

```java
@Repository
public class AccountRepository {
    public String findAccount() {
        return "Account data";
    }
}
```

`@Repository` is a specialized component annotation for persistence/data-access classes. In Spring, it can also participate in translating certain persistence exceptions into Spring's data-access exception hierarchy.

In Spring Data JPA, repository interfaces are commonly detected and implemented by Spring, so you often do not write a concrete repository class yourself.

### Quick comparison

| Annotation | Main role | Easy way to remember |
|---|---|---|
| `@Component` | General Spring-managed component | Any general bean |
| `@Controller` | Web/MVC request handling | Usually views/pages |
| `@RestController` | REST/API request handling | Response body/data |
| `@Service` | Business logic | Rules and decisions |
| `@Repository` | Data access | Database operations |

---

# 4. Web/API annotations

## `@RequestMapping`

**Simple meaning:** “Map web requests to this class or method.”

```java
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status() {
        return "Account API is running";
    }
}
```

The class-level mapping provides a common path. The method adds `/status`.

`@RequestMapping` can define a path, HTTP method, request parameters, headers, and media types.

For everyday code, the shortcut annotations below are often clearer.

## `@GetMapping`

**Simple meaning:** “Handle HTTP GET requests.”

```java
@GetMapping("/{id}")
public String getAccount(@PathVariable long id) {
    return "Account ID: " + id;
}
```

Typically used to retrieve data.

## `@PostMapping`

**Simple meaning:** “Handle HTTP POST requests.”

```java
@PostMapping
public String createAccount(@RequestBody AccountRequest request) {
    return "Account creation requested for " + request.name();
}
```

Typically used to create a resource or submit data for processing.

## `@PutMapping`

**Simple meaning:** “Handle HTTP PUT requests.”

```java
@PutMapping("/{id}")
public String updateAccount(@PathVariable long id,
                            @RequestBody AccountRequest request) {
    return "Update requested for account " + id;
}
```

Typically used to replace/update a resource. By HTTP convention, PUT is generally idempotent: repeating the same request should have the same intended effect as making it once.

## `@DeleteMapping`

**Simple meaning:** “Handle HTTP DELETE requests.”

```java
@DeleteMapping("/{id}")
public String deleteAccount(@PathVariable long id) {
    return "Delete requested for account " + id;
}
```

Typically used to delete a resource.

### Mapping cheat sheet

| Annotation | HTTP method | Common purpose |
|---|---|---|
| `@GetMapping` | GET | Read data |
| `@PostMapping` | POST | Create/submit data |
| `@PutMapping` | PUT | Replace/update a resource |
| `@DeleteMapping` | DELETE | Delete a resource |
| `@RequestMapping` | Configurable | General-purpose mapping |

These are common conventions, not strict rules that every API must follow.

## `@PathVariable`

**Simple meaning:** “Read a value from the URL path.”

```java
@GetMapping("/accounts/{id}")
public String getAccount(@PathVariable("id") long accountId) {
    return "Account ID: " + accountId;
}
```

Request: `GET /accounts/42`

Here, `id` is `42`.

**Clue:** If the value is part of the URL structure, such as `/accounts/42`, think `@PathVariable`.

## `@RequestParam`

**Simple meaning:** “Read a value from a query parameter, or another configured request parameter.”

```java
@GetMapping("/accounts")
public String searchAccounts(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String status) {
    return "Page: " + page + ", status: " + status;
}
```

Request: `GET /accounts?page=2&status=ACTIVE`

- `page` receives `2`.
- `status` receives `ACTIVE`.
- `required = false` makes `status` optional.
- `defaultValue = "0"` supplies a default when `page` is missing (and also applies when it is empty).

**Clue:** If the value appears after `?`, usually think `@RequestParam`.

## `@RequestBody`

**Simple meaning:** “Read the HTTP request body and convert it into a Java object.”

Example JSON:

```json
{
  "name": "Venkatesh",
  "accountType": "SAVINGS"
}
```

Java DTO using a Java record:

```java
public record AccountRequest(String name, String accountType) {
}
```

Controller:

```java
@PostMapping("/accounts")
public String createAccount(@RequestBody AccountRequest request) {
    return "Creating account for " + request.name();
}
```

Spring uses an HTTP message converter (commonly Jackson for JSON in Spring Boot web applications) to deserialize the request body into the DTO.

**Remember:** `@RequestBody` is for the request body, not for a URL path value or query parameter.

## `@ResponseBody`

**Simple meaning:** “Write the method's return value directly into the HTTP response body.”

```java
@Controller
public class StatusController {
    @GetMapping("/status")
    @ResponseBody
    public String status() {
        return "UP";
    }
}
```

Without `@ResponseBody`, a typical `@Controller` method returning a string treats it as a view name. With `@ResponseBody`, it writes `UP` as response content.

`@RestController` applies this behavior to all handler methods by default.

---

# 5. Application startup annotation

## `@SpringBootApplication`

**Simple meaning:** “This is the main Spring Boot application configuration and startup point.”

```java
@SpringBootApplication
public class BankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }
}
```

It combines three important annotations:

1. `@SpringBootConfiguration` — indicates a Spring Boot configuration class.
2. `@EnableAutoConfiguration` — asks Spring Boot to configure beans based on the dependencies and configuration it detects.
3. `@ComponentScan` — scans for components in the package of this class and its subpackages by default.

**Important:** Put the main application class in a suitable top-level package so component scanning can find your controllers, services, and other components.

**What happens at startup, simplified?**

```text
main()
  |
  v
SpringApplication.run(...)
  |
  v
Create/refresh Spring ApplicationContext
  |
  +--> Read configuration and auto-configuration
  |
  +--> Scan/register bean definitions
  |
  +--> Create eager singleton beans and inject dependencies
  |
  v
Application ready
  |
  v
Handle incoming HTTP requests
```

This is a simplified diagram, not a complete internal lifecycle sequence.

---

# 6. Full mini-example: Account API

The example connects the annotations into one simple flow. In a real application, the repository would usually access a database.

### DTO

```java
public record AccountRequest(String name, String accountType) {
}
```

### Repository

```java
@Repository
public class AccountRepository {
    public String save(String name) {
        // Replace with real database persistence.
        return "Saved account for " + name;
    }
}
```

### Service

```java
@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public String create(String name) {
        return repository.save(name);
    }
}
```

### Controller

```java
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@RequestBody AccountRequest request) {
        return service.create(request.name());
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id) {
        return "Requested account ID: " + id;
    }

    @GetMapping
    public String search(@RequestParam(defaultValue = "0") int page) {
        return "Requested page: " + page;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        return "Delete requested for account: " + id;
    }
}
```

### Request flow

```text
HTTP request
    |
    v
AccountController
    |
    v
AccountService
    |
    v
AccountRepository
    |
    v
Response returned to the client
```

Spring creates and wires the beans. Spring MVC routes HTTP requests to the matching controller method and converts request/response data.

---

# 7. How to identify the correct annotation in an interview

| If the question says… | Think of… |
|---|---|
| “Make this class managed by Spring” | `@Component`, `@Service`, `@Repository`, etc. |
| “Inject a dependency” | Constructor injection; optionally `@Autowired` |
| “There are two implementations; choose one” | `@Qualifier` or `@Primary` |
| “Create a bean using a method” | `@Bean` |
| “Delay initialization” | `@Lazy` |
| “Choose bean instance lifecycle” | `@Scope` |
| “Read a configuration property” | `@Value` or `@ConfigurationProperties` |
| “Load another properties file” | `@PropertySource` / `@PropertySources` |
| “Handle a web page/view” | `@Controller` |
| “Build a REST API” | `@RestController` |
| “Map a general route” | `@RequestMapping` |
| “Retrieve data using GET” | `@GetMapping` |
| “Submit/create using POST” | `@PostMapping` |
| “Update using PUT” | `@PutMapping` |
| “Delete using DELETE” | `@DeleteMapping` |
| “Get an ID from `/accounts/42`” | `@PathVariable` |
| “Get `page=2` from `?page=2`” | `@RequestParam` |
| “Convert JSON body to a DTO” | `@RequestBody` |
| “Return data instead of a view name” | `@ResponseBody` or `@RestController` |
| “Main Spring Boot startup class” | `@SpringBootApplication` |

---

# 8. Common interview traps

1. **`@Autowired` is not mandatory on a single constructor.** Spring can use the only constructor automatically.
2. **`@Component`, `@Service`, and `@Repository` all register components**, but the specialized annotations communicate the class's role.
3. **`@Controller` and `@RestController` are not identical.** A normal controller often returns view names; a REST controller writes response bodies by default.
4. **`@Bean` is placed on a method; `@Component` is usually placed on a class.**
5. **`@Primary` and `@Qualifier` solve bean-selection problems in different ways.**
6. **`@PathVariable` and `@RequestParam` read values from different places.** `/accounts/42` versus `/accounts?id=42`.
7. **`@RequestBody` reads the body; it does not read a query parameter.**
8. **A Spring singleton is one instance per Spring container/bean definition**, not automatically one instance per user or HTTP request.
9. **`@Lazy` delays initialization; it does not guarantee that the bean will never be created.**
10. **`@SpringBootApplication` is a combination of configuration, auto-configuration, and component scanning.**

---

# 9. One-minute revision

- **Spring-managed object:** bean
- **Spring creates/manages objects:** IoC
- **Spring supplies dependencies:** DI
- **General component:** `@Component`
- **Business logic:** `@Service`
- **Data access:** `@Repository`
- **Web page/controller:** `@Controller`
- **REST API:** `@RestController`
- **Method creates a bean:** `@Bean`
- **Inject dependency:** constructor injection / `@Autowired`
- **Choose one of several beans:** `@Qualifier` / `@Primary`
- **Bean creation timing:** `@Lazy`
- **Bean lifecycle scope:** `@Scope`
- **Read a property:** `@Value`
- **Load extra property files:** `@PropertySource` / `@PropertySources`
- **General route:** `@RequestMapping`
- **GET / POST / PUT / DELETE:** matching mapping annotation
- **URL path value:** `@PathVariable`
- **Query parameter:** `@RequestParam`
- **Request JSON/body:** `@RequestBody`
- **Write return value to response body:** `@ResponseBody`
- **Spring Boot entry point:** `@SpringBootApplication`

## Small correction to your list

You wrote `@Laz`; the annotation name is **`@Lazy`**.

---

## How to practise

1. Create a small Spring Boot project.
2. Add one controller, one service, and one repository.
3. Use constructor injection to connect them.
4. Test a GET endpoint with `@PathVariable` and `@RequestParam`.
5. Test a POST endpoint with `@RequestBody`.
6. Add a property to `application.properties` and read it with `@Value`.
7. Practise explaining what each annotation does and *why* you chose it.

**Golden rule:** Don't memorise only the annotation name. For each one, know three things: **what it tells Spring to do, where you use it, and what problem it solves.**
