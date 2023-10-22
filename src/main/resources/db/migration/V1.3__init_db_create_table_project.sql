CREATE TABLE project (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2 AND LENGTH(name) <= 1000),
    client_id INT,
    start_date DATE,
    finish_date DATE,
    PRIMARY KEY (id),
    CONSTRAINT project_fk FOREIGN KEY (client_id) REFERENCES client(id)
);