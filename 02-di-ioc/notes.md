# 📘 IoC (Inversion of Control) & DI (Dependency Injection)

---

## 🔷 What is IoC (Inversion of Control)?

Instead of creating and managing objects ourselves, we give that responsibility to the **Spring Framework**.

### 🔸 Traditional Java

* We create objects using `new`
* We control everything

### 🔸 In Spring Boot

* Spring creates objects for us
* Spring manages their lifecycle

👉 Control is **inverted (reversed)** from developer → Spring

---

### 📌 Example

#### ❌ Without IoC

```java
class Car {
    Engine engine = new Engine(); // tightly coupled
}
```

* `Car` is directly dependent on `Engine`
* Hard to change or test

---

#### ✅ With IoC

```java
class Car {
    Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }
}
```

* `Car` does NOT create `Engine`
* Spring provides it

👉 IoC is a **design principle**
👉 Spring uses an **IoC Container** to implement it

---

## 🔷 What is DI (Dependency Injection)?

Spring provides required dependencies (objects) to a class instead of the class creating them.

* A class needs another class → **dependency**
* Instead of creating it → **Spring injects it**

---

### 📌 Example

```java
@Component
class Engine {
}

@Component
class Car {

    private Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

👉 Spring automatically:

* Creates `Engine`
* Injects it into `Car`

---

### 🧩 Key Interview Point

👉 **DI is a technique to achieve IoC**

---

## 🔷 IoC vs DI (Very Important)

| Feature | IoC                     | DI                    |
| ------- | ----------------------- | --------------------- |
| Meaning | Control given to Spring | Dependencies injected |
| Type    | Principle               | Implementation        |
| Role    | Big concept             | Part of IoC           |

👉 IoC = Spring controls object creation
👉 DI = How dependencies are injected

---

## 🔷 What is Spring IoC Container?

The **IoC Container** is responsible for creating, managing, and injecting beans.

### 📦 Types

* **BeanFactory** (basic)
* **ApplicationContext** (advanced, commonly used)

---

### 🔄 What the Container Does

* Scans classes (`@Component`)
* Creates objects (**Beans**)
* Stores them
* Injects where needed

---

## 🔷 What is a Bean?

A **Bean** is an object created and managed by the Spring container.

### 📌 Example

```java
@Component
class Engine {
}
```

👉 `Engine` becomes a **Spring Bean**

---

## 🔷 Types of Dependency Injection

### ✅ 1. Constructor Injection (Best Practice)

```java
@Component
class Car {

    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

✔ Recommended
✔ Immutable
✔ Easy to test

---

### ✅ 2. Setter Injection

```java
@Component
class Car {

    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```

✔ Used for optional dependencies

---

### ❌ 3. Field Injection (Not Recommended)

```java
@Component
class Car {

    @Autowired
    private Engine engine;
}
```

❌ Hard to test
❌ Breaks encapsulation

---

## 🔷 Important Annotations

| Annotation  | Purpose              |
| ----------- | -------------------- |
| @Component  | Marks class as bean  |
| @Service    | Business logic layer |
| @Repository | Database layer       |
| @Controller | Web layer            |
| @Autowired  | Inject dependency    |

---

## 🔷 Advantages of DI & IoC

### 🚀 Benefits

* Loose coupling
* Easy testing (mocking)
* Better code readability
* Easy maintenance
* Reusability

---

## 🔷 Real-Time Example

### ❌ Without DI

```java
Engine engine = new Engine();
Car car = new Car(engine);
```

### ✅ With DI

```java
@Autowired
Car car;
```

👉 Spring handles everything

---

## 🔷 Common Interview Questions

### ❓ What is IoC?

👉 Control of object creation is given to Spring

### ❓ What is DI?

👉 Spring injects dependencies into classes

### ❓ Difference between IoC and DI?

👉 IoC = concept, DI = implementation

### ❓ Which injection is best?

👉 Constructor Injection ✅

### ❓ Why not Field Injection?

👉 Hard to test and not recommended

---

## 🔷 Final Interview Summary (Must Remember)

* IoC = Spring controls objects
* DI = Spring injects dependencies
* Bean = Object managed by Spring
* Container = Manages beans
* Best Practice = Constructor Injection

---

## 🧠 10-Second Interview Answer

In Spring Boot, **IoC** means the framework controls object creation and lifecycle.
**Dependency Injection (DI)** is the process where Spring injects required dependencies into a class.

👉 This results in:

* Loose coupling
* Better testing
* Maintainable code

✅ **Constructor injection is the recommended approach**
