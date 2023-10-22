CREATE TABLE project_worker (
    project_id INT,
    worker_id INT,
    CONSTRAINT project_worker__project_fk FOREIGN KEY (project_id) REFERENCES project(id),
    CONSTRAINT project_worker__worker_fk FOREIGN KEY (worker_id) REFERENCES worker(id)
);