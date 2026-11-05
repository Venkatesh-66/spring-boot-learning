# JPA – Handy Notes (English + தமிழ்)

## 1. What is JPA?

**JPA stands for Java Persistence API.**

JPA is a **Java specification (standard rules)** that defines how Java objects are mapped to database tables and how database operations are performed.

It provides annotations and interfaces such as `@Entity`, `@Id`, and `EntityManager`.

**Hibernate is one implementation of JPA.**

**தமிழில்:**
JPA என்பது Java-வில் உள்ள ஒரு standard/rule book மாதிரி. Java object-ஐ database table-உடன் connect செய்வது மற்றும் database operations செய்வது எப்படி என்பதை இது define செய்கிறது. Hibernate என்பது அந்த rules-ஐ follow செய்து வேலை செய்யும் ஒரு implementation.

**Simple-ஆ சொல்லணும்னா:**

JPA என்பது Java object-ல இருக்கிற data-வை Database-ல் save பண்ணவும், Database-ல இருக்கிற data-வை Java object-ஆக எடுக்கவும் உதவும் ஒரு standard.

> Easy to remember: **JPA = Rules; Hibernate = Implements those rules.**

## 2. Why do we need JPA?

Without JPA (using JDBC), we often write SQL and handle the result manually.

With JPA, we can work with Java objects. JPA provider (for example, Hibernate) handles the SQL generation.

**தமிழில்:** JDBC-ல் SQL query-ஐ நாமே எழுத வேண்டும். JPA பயன்படுத்தினால் Java object மூலம் database-ல் save, fetch, update, delete செய்யலாம்.

## 3. JPA vs Hibernate vs Spring Data JPA

| Name | Simple meaning |
|---|---|
| JPA | Standard rules / specification |
| Hibernate | JPA implementation; performs the actual ORM work |
| Spring Data JPA | Makes repository/database code easier in Spring |

**Flow:**

`Your code → Spring Data JPA → Hibernate → Database`

## 4. Important JPA annotations

| Annotation | Purpose |
|---|---|
| `@Entity` | Marks a Java class as a database entity |
| `@Table` | Specifies the database table name (optional if default is fine) |
| `@Id` | Marks the primary key |
| `@GeneratedValue` | Configures automatic ID generation |
| `@Column` | Customizes a column mapping |
| `@OneToOne` | One-to-one relationship |
| `@OneToMany` | One-to-many relationship |
| `@ManyToOne` | Many-to-one relationship |
| `@ManyToMany` | Many-to-many relationship |

**தமிழில்:** `@Entity` class-ஐ table-உடன் map செய்கிறது. `@Id` primary key-ஐ குறிப்பிடுகிறது. Relationship annotations tables இடையிலான தொடர்பைக் காட்டுகின்றன.

## 5. Small example

```java
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String name;

    private String email;

    // Getters and setters
}
```

**Understand it:**
- `Customer` class → `customers` table
- `id` → primary key
- `name` → `customer_name` column
- `email` → `email` column (default mapping)

**தமிழில்:** இந்த Java class database-ல் உள்ள `customers` table-ஐ represent செய்கிறது.

## 6. EntityManager – basic operations

JPA provides `EntityManager` to manage entities.

| Method | Meaning |
|---|---|
| `persist()` | Insert a new entity |
| `find()` | Find an entity by primary key |
| `merge()` | Copy changes into a managed entity |
| `remove()` | Delete an entity |
| `flush()` | Synchronize pending changes with the database |

**தமிழில்:** `EntityManager` என்பது entity objects-ஐ manage செய்யும் JPA interface.

## 7. What is Persistence Context?

Persistence Context is a place where JPA tracks the entities currently being managed.

If you change a managed entity inside a transaction, JPA can detect the change. This is called **Dirty Checking**. The change is synchronized with the database during flush/commit.

**தமிழில்:** Persistence Context என்பது JPA கவனித்துக் கொண்டிருக்கும் entity objects-க்கான tracking area. Managed object-ல் value மாற்றினால், JPA அதை கண்டுபிடித்து transaction முடியும் போது database-ல் update செய்யும். இதுதான் Dirty Checking.

## 8. JPA and Spring Data JPA repository

```java
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {
}
```

Spring Data JPA provides common methods such as:

- `save(customer)` – save or update an entity
- `findById(id)` – find by ID
- `findAll()` – get all records
- `deleteById(id)` – delete by ID

**Important:** `save()` is a Spring Data JPA repository method, not a method defined by the JPA specification.

## 9. Quick interview revision

**Q1. What is JPA?**  
JPA is a Java specification that defines standard rules and APIs for mapping Java objects to relational database tables and performing persistence operations.

**Q2. Is JPA a tool?**  
No. JPA is a specification. Hibernate is a popular implementation.

**Q3. What is ORM?**  
ORM means Object-Relational Mapping. It maps Java objects to database tables.

**Q4. What is `@Entity`?**  
It marks a Java class as an entity that JPA can persist.

**Q5. What is `@Id`?**  
It identifies the primary key of an entity. Every entity must have a primary key.

**Q6. What is Dirty Checking?**  
JPA detects changes made to managed entities and synchronizes them with the database during flush/commit.

---

## Remember these five points

1. **JPA** – specification / standard rules.
2. **Hibernate** – JPA implementation.
3. **ORM** – maps Java objects to database tables.
4. **`@Entity` and `@Id`** – mark an entity and its primary key.
5. **Spring Data JPA** – reduces repository boilerplate code.



## Question: What is JPA and why is it called a specification?

You can answer:

"JPA stands for Java Persistence API. It is a Java specification that defines standard rules for mapping Java objects to database tables and performing database operations. It provides annotations and interfaces such as @Entity, @Id and EntityManager. Hibernate is one of the implementations of JPA."