CREATE TABLE student (
     id BIGSERIAL PRIMARY KEY,
     first_name VARCHAR(50) NOT NULL,
     last_name VARCHAR(50) NOT NULL,
     email VARCHAR(100) UNIQUE NOT NULL
);