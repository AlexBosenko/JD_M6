CREATE TABLE worker (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2 AND LENGTH(name) <= 1000),
    birthday DATE CHECK (birthday > '1900-01-01'),
    level VARCHAR(10) NOT NULL CHECK (level IN ('Trainee', 'Junior', 'Middle', 'Senior')),
    salary INT CHECK (salary >= 100 AND salary <= 100000),
    PRIMARY KEY (id)
);