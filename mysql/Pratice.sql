/*--------------------------------------------------
  DDL – Data Definition Language
  Used to define and manage database structure
--------------------------------------------------*/
-- CREATE   : Create database objects (table, view, etc.)
-- ALTER    : Modify existing database objects
-- DROP     : Delete database objects
-- TRUNCATE : Remove all records from a table


/*--------------------------------------------------
  DML – Data Manipulation Language
  Used to modify data inside tables
--------------------------------------------------*/
-- INSERT : Add new records
-- UPDATE : Modify existing records
-- DELETE : Remove records


/*--------------------------------------------------
  DQL – Data Query Language
  Used to retrieve data
--------------------------------------------------*/
-- SELECT : Fetch data from database


/*--------------------------------------------------
  TCL – Transaction Control Language
  Used to manage transactions
--------------------------------------------------*/
-- COMMIT      : Save changes permanently
-- ROLLBACK    : Undo changes
-- SAVEPOINT   : Set a point within a transaction
-- SET TRANSACTION : Set transaction properties


/*--------------------------------------------------
  DCL – Data Control Language
  Used to control access/permissions
--------------------------------------------------*/
-- GRANT  : Give privileges to users
-- REVOKE : Remove privileges from users

/*==================================================
  PERSONAL DIARY DATABASE - COMPLETE SQL SCRIPT
==================================================*/


/*--------------------------------------------------
  1. DDL – Data Definition Language
--------------------------------------------------*/

-- Create Database
CREATE DATABASE IF NOT EXISTS PERSONALDIARY;

-- Use Database
USE PERSONALDIARY;

-- Create Table
CREATE TABLE IF NOT EXISTS users (
    userName VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(25) NOT NULL,
    email VARCHAR(100),
    phoneNum VARCHAR(15)
);

-- Modify Column
ALTER TABLE users MODIFY phoneNum VARCHAR(15);


/*==================================================
  BASIC OPERATIONS – ALTER, RENAME, DROP
==================================================*/

USE PERSONALDIARY;


/*--------------------------------------------------
  ALTER TABLE – Modify existing table
--------------------------------------------------*/

-- 1. Add a new column
ALTER TABLE users
ADD dateOfBirth DATE;

-- 2. Modify an existing column
ALTER TABLE users
MODIFY email VARCHAR(150);

-- 3. Rename a column
ALTER TABLE users
CHANGE phoneNum phoneNumber VARCHAR(15);

-- 4. Drop a column
ALTER TABLE users
DROP COLUMN dateOfBirth;

-- 5. Add NOT NULL constraint
ALTER TABLE users
MODIFY role VARCHAR(25) NOT NULL;

-- 6. Add default value
ALTER TABLE users
ALTER role SET DEFAULT 'USER';


/*--------------------------------------------------
  RENAME – Rename table or database objects
--------------------------------------------------*/

-- Rename table
RENAME TABLE users TO app_users;

-- Rename back (important for consistency)
RENAME TABLE app_users TO users;


/*--------------------------------------------------
  DROP – Delete objects permanently
--------------------------------------------------*/

-- Drop a column (already shown above)
ALTER TABLE users DROP COLUMN email;

-- Drop table
-- WARNING: Deletes structure + data
DROP TABLE IF EXISTS users;

-- Recreate table after drop (so script doesn’t break)
CREATE TABLE users (
    userName VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(25) NOT NULL,
    email VARCHAR(100),
    phoneNum VARCHAR(15)
);

-- Drop database
-- WARNING: Deletes everything inside
-- DROP DATABASE PERSONALDIARY;

/*--------------------------------------------------
  2. DML – Data Manipulation Language
--------------------------------------------------*/

-- Insert Data
INSERT INTO users (userName, password, role, email, phoneNum) VALUES
('venkat', 'pass123', 'ADMIN', 'vip@email.com', '+919876543210'),
('anish', 'pass123', 'USER', 'Ani@email.com', '9876543210'),
('steve', 'adminpass', 'USER', 'steve@email.com', '9123456780');

-- Update Data
UPDATE users
SET role = 'ADMIN'
WHERE userName = 'anish';

-- Delete Data
DELETE FROM users
WHERE userName = 'steve';


/*--------------------------------------------------
  3. DQL – Data Query Language
--------------------------------------------------*/

-- Select All Data
SELECT * FROM users;

-- Select Specific Columns
SELECT userName, email
FROM users;

-- Select with Condition
SELECT * FROM users
WHERE role = 'ADMIN';


/*--------------------------------------------------
  4. TCL – Transaction Control Language
--------------------------------------------------*/

START TRANSACTION;

INSERT INTO users VALUES ('tempUser', 'temp123', 'USER', 'temp@email.com', '9999999999');

SAVEPOINT sp1;

UPDATE users SET role = 'ADMIN' WHERE userName = 'tempUser';

-- Rollback only the update
ROLLBACK TO sp1;

-- Commit remaining changes (insert)
COMMIT;


/*--------------------------------------------------
  5. DCL – Data Control Language
--------------------------------------------------*/

-- Grant Permissions
GRANT SELECT, INSERT ON PERSONALDIARY.users TO 'testuser'@'localhost';

-- Revoke Permissions
REVOKE INSERT ON PERSONALDIARY.users FROM 'testuser'@'localhost';



