# 📘 MySQL Complete Notes (Beginner → Advanced)

## 🧠 1. Introduction to DBMS

### What is DBMS?
A Database Management System (DBMS) is software used to:
- Store data  
- Retrieve data  
- Modify data  

### Why DBMS?
Example: Bank system  
- Customer details  
- Transactions  
- Employee records  

DBMS allows:
- Insert
- Update
- Delete
- Retrieve

---

## 🧠 2. RDBMS

### What is RDBMS?
- Stores data in tables (rows + columns)
- More structured and efficient

---

## 🧠 3. Table Basics

- Table → collection of data  
- Row → single record  
- Column → attribute  

---

## 🔑 4. Keys

- Primary Key → unique + not null  
- Foreign Key → link between tables  
- Candidate Key → possible keys  
- Unique Key → unique but allows null  

---

## 🧠 5. SQL Commands

- DDL → structure (CREATE, DROP)
- DML → data (INSERT, UPDATE, DELETE)
- DQL → query (SELECT)

---

## 🏗️ Create Table Example

```sql
CREATE TABLE customers(
    CustomerId INT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    country VARCHAR(20)
);
```

---

## ➕ Insert Example

```sql
INSERT INTO customers VALUES (1, 'Mike', 'USA');
```

---

## ✏️ Update Example

```sql
UPDATE customers SET first_name='John' WHERE CustomerId=1;
```

---

## ❌ Delete Example

```sql
DELETE FROM customers WHERE CustomerId=1;
```

---

## 🔍 Select Example

```sql
SELECT * FROM customers;
```

---

## 🎯 Where Clause

```sql
SELECT * FROM customers WHERE country='India';
```

---

## ⚠️ NULL Handling

```sql
SELECT * FROM customers WHERE salary IS NULL;
```
