-- ============================================================================
-- SQL SETUP SCRIPT
-- Run this script in your MySQL/PostgreSQL database before running the app!
-- ============================================================================

-- TODO 1: Create the database (run this first, separately)
-- CREATE DATABASE IF NOT EXISTS evaitcs_users;
-- USE evaitcs_users;

-- TODO 2: Create the users table
-- CREATE TABLE users (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     username VARCHAR(50) NOT NULL UNIQUE,
--     email VARCHAR(100) NOT NULL UNIQUE,
--     first_name VARCHAR(50) NOT NULL,
--     last_name VARCHAR(50) NOT NULL,
--     age INT CHECK (age >= 18 AND age <= 120),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
-- );

-- TODO 3: Insert some sample data
-- INSERT INTO users (username, email, first_name, last_name, age) VALUES
--     ('jsmith', 'john@email.com', 'John', 'Smith', 30),
--     ('jdoe', 'jane@email.com', 'Jane', 'Doe', 25),
--     ('bwilson', 'bob@email.com', 'Bob', 'Wilson', 35);

-- TODO 4: Verify the data
-- SELECT * FROM users;

