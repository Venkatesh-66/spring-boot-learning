🔷 What is IoC (Inversion of Control)?

Instead of creating and managing objects ourselves, we give that responsibility to the Spring framework.

Normally in Java:

We create objects using new
We control everything

But in Spring Boot:

Spring creates objects for us
Spring manages their lifecycle

👉 So control is inverted (reversed) from developer → to Spring

📌 Example

❌ Without IoC

class Car {
    Engine engine = new Engine(); // tightly coupled
}

Car is dependent on Engine directly
Hard to change or test

✅ With IoC

class Car {
    Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }
}

Car does NOT create Engine

Spring will provide it

👉 IoC is a design principle
👉 Spring uses IoC Container to implement it


🔷 What is DI (Dependency Injection)?

Spring provides the required dependencies (objects) to a class instead of the class creating them.

A class needs another class → called dependency
Instead of creating it → Spring injects it

📌 Example

@Component
class Engine {}
@Component
class Car {

    private Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}

👉 Spring automatically:

Creates Engine
Injects into Car
🧩 Key Point for Interview

👉 DI is a technique to achieve IoC

🔷 IoC vs DI (Very Important)

## 🔷 IoC vs DI (Very Important)

| Feature  | IoC                          | DI                          |
|----------|------------------------------|------------------------------|
| Meaning  | Control is given to Spring   | Dependencies are injected    |
| Type     | Principle                    | Implementation               |
| Role     | Big concept                  | Part of IoC                 |

IoC means Spring controls object creation, and DI is the way Spring injects required dependencies.

🔷 What is Spring IoC Container?

IoC Container is responsible for creating, managing, and injecting beans.

📦 Types
BeanFactory (basic)
ApplicationContext (advanced, mostly used)

🔄 What Container Does
Scans classes (@Component)
Creates objects (Beans)
Stores them
Injects where needed

🔷 What is a Bean?

A Bean is an object that is created and managed by the Spring container.

📌 Example
@Component
class Engine {}

👉 This Engine becomes a Spring Bean

🔷 Types of Dependency Injection

✅ 1. Constructor Injection (Best Practice)

@Component
class Car {

    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}

✔ Recommended
✔ Immutable
✔ Easy to test

✅ 2. Setter Injection

@Component
class Car {

    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}

✔ Used for optional dependencies

❌ 3. Field Injection (Not Recommended)

@Component
class Car {

    @Autowired
    private Engine engine;
}

❌ Hard to test
❌ Breaks encapsulation

🔷 Important Annotations

Annotation	Purpose
@Component	Marks class as bean
@Service	Business logic layer
@Repository	Database layer
@Controller	Web layer
@Autowired	Inject dependency

🔷 Advantages of DI & IoC

🚀 Benefits
Loose coupling
Easy testing (mocking)
Better code readability
Easy maintenance
Reusability

🔷 Real-Time Example

Without DI
Engine engine = new Engine();
Car car = new Car(engine);
With DI
@Autowired
Car car;

👉 Spring handles everything

🔷 Common Interview Questions
❓ What is IoC?

👉 Control of object creation is given to Spring.

❓ What is DI?

👉 Spring injects dependencies into classes.

❓ Difference between IoC and DI?

👉 IoC is concept, DI is implementation.

❓ Which injection is best?

👉 Constructor Injection ✅

❓ Why not Field Injection?

👉 Hard to test and not recommended.

🔷 Final Interview Summary (Must Remember)

👉 IoC = Spring controls objects
👉 DI = Spring injects dependencies
👉 Bean = Object managed by Spring
👉 Container = Manages beans
👉 Best Practice = Constructor Injection

🧠 10-Second Interview Answer

In Spring Boot, IoC means the framework takes control of object creation and lifecycle. Dependency Injection is the process where Spring injects required dependencies into a class. This helps in loose coupling, better testing, and maintainable code. Constructor injection is the recommended approach.