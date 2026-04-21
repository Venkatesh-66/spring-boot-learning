# 📘 JPA (Java Persistence API)

---

## 🔹 1. What is JPA?

**JPA (Java Persistence API)** is a Java specification used to manage relational data in Java applications.

* Maps Java objects → database tables
* It is **not an implementation**
* Popular implementations:

  * Hibernate (most used)
  * EclipseLink

---

## 🔹 2. ORM (Object Relational Mapping)

ORM is a technique used to map Java objects to database tables.

| Java   | Database |
| ------ | -------- |
| Class  | Table    |
| Object | Row      |
| Field  | Column   |

---

## 🔹 3. JPA Architecture

```
Application (your service code)
   ↓
Repository (Spring Data JPA)
   ↓
EntityManager (hidden)
   ↓
Persistence Context
   ↓
Hibernate
   ↓
Database
```

---

## 🔹 4. Persistence Context

* A cache of managed entities
* Managed by `EntityManager`

**Ensures:**

* No duplicate objects
* Automatic synchronization

---

## 🔹 5. Entity Lifecycle

**States:**

* **Transient** → New object, not in DB
* **Managed** → Tracked by JPA
* **Detached** → Exists but not tracked
* **Removed** → Marked for deletion

---

## 🔹 6. Entity Class

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
```

---

## 🔹 7. Annotations (Core)

### Entity Mapping

* `@Entity`
* `@Table`
* `@Id`
* `@GeneratedValue`
* `@Column`

### Column Options

```java
@Column(name="user_name", nullable=false, unique=true)
```

---

## 🔹 8. Primary Key Strategies

```java
@GeneratedValue(strategy = GenerationType.AUTO)
@GeneratedValue(strategy = GenerationType.IDENTITY)
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@GeneratedValue(strategy = GenerationType.TABLE)
```

---

## 🔹 9. Relationships

### 🔸 9.1 One-to-One

```java
@OneToOne
@JoinColumn(name = "profile_id")
private Profile profile;
```

### 🔸 9.2 One-to-Many

```java
@OneToMany(mappedBy = "user")
private List<Order> orders;
```

### 🔸 9.3 Many-to-One

```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

### 🔸 9.4 Many-to-Many

```java
@ManyToMany
@JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id")
)
```

---

## 🔹 10. Fetch Types

```java
fetch = FetchType.LAZY
fetch = FetchType.EAGER
```

* **LAZY** → Loads when needed
* **EAGER** → Loads immediately

---

## 🔹 11. Cascade Types

```java
cascade = CascadeType.ALL
```

**Types:**

* PERSIST
* MERGE
* REMOVE
* REFRESH
* DETACH

---

## 🔹 12. EntityManager

**Core operations:**

* `persist()`
* `merge()`
* `remove()`
* `find()`
* `flush()`
* `clear()`

---

## 🔹 13. JPQL (Java Persistence Query Language)

```sql
SELECT u FROM User u WHERE u.name = :name
```

* Works with **entities**, not tables

---

## 🔹 14. Native Queries

```java
@Query(value = "SELECT * FROM users", nativeQuery = true)
```

---

## 🔹 15. Spring Data JPA Repository

```java
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
```

---

## 🔹 16. Query Methods

* `findByName()`
* `findByAgeGreaterThan()`
* `findByEmailAndStatus()`

---

## 🔹 17. Pagination & Sorting

```java
Pageable pageable = PageRequest.of(0, 10);
repo.findAll(pageable);
```

---

## 🔹 18. Transactions

```java
@Transactional
```

**Ensures:**

* Atomicity
* Consistency

---

## 🔹 19. Locking

### Optimistic Locking

```java
@Version
private int version;
```

### Pessimistic Locking

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
```

---

## 🔹 20. Caching

* First-level cache → Persistence Context
* Second-level cache → Hibernate (optional)

---

## 🔹 21. Dirty Checking

* Automatically updates DB when entity changes

---

## 🔹 22. Flush vs Commit

* `flush()` → Sync with DB
* `commit()` → Final save

---

## 🔹 23. Inheritance Mapping

```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
```

**Types:**

* SINGLE_TABLE
* JOINED
* TABLE_PER_CLASS

---

## 🔹 24. Embeddables

```java
@Embeddable
class Address {}

@Embedded
private Address address;
```

---

## 🔹 25. DTO Projection

```sql
SELECT new com.dto.UserDTO(u.name) FROM User u
```

---

## 🔹 26. Criteria API

```java
CriteriaBuilder cb = em.getCriteriaBuilder();
```

* Type-safe queries

---

## 🔹 27. Specifications (Spring)

```java
Specification<User> spec = ...
```

---

## 🔹 28. Auditing

* `@CreatedDate`
* `@LastModifiedDate`

---

## 🔹 29. Performance Best Practices

* Use LAZY loading
* Avoid N+1 problem
* Use JOIN FETCH
* Use projections
* Limit data

---

## 🔹 30. Common Problems

### ❌ N+1 Query Issue

**Fix:**

* JOIN FETCH

### ❌ LazyInitializationException

**Fix:**

* Use transactional scope
* Fetch properly

### ❌ Too many DB calls

**Fix:**

* Batch fetching
* Caching

---

## 🔹 31. JPA vs Hibernate

| JPA           | Hibernate      |
| ------------- | -------------- |
| Specification | Implementation |

---

## 🔹 32. Advanced Topics

* Entity Graphs
* Batch Processing
* Stored Procedures
* Multi-tenancy
* Soft Deletes

---

## 🔹 33. Soft Delete Example

```java
@SQLDelete(sql = "UPDATE user SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
```

---

## 🔹 34. Validation

* `@NotNull`
* `@Size`
* `@Email`

---

## 🔹 35. Indexing

```java
@Table(indexes = @Index(name="idx_name", columnList="name"))
```

---

## 🔹 36. Best Practices Summary

* Keep entities simple
* Use DTOs for APIs
* Avoid EAGER loading
* Use pagination
* Handle transactions properly

---

## ✅ Final Summary

JPA helps:

* Map Java objects to DB
* Reduce boilerplate SQL
* Manage transactions efficiently
