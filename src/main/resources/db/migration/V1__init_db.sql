CREATE TABLE IF NOT EXISTS worker (
                        id IDENTITY PRIMARY KEY,
                        name VARCHAR(1000) NOT NULL
                            CONSTRAINT name_length_worker
                            CHECK (length(name) > 2),
                        birthday DATE
                            CONSTRAINT data_start
                            CHECK (birthday >= '1901-01-01'),
                        level VARCHAR(10) NOT NULL
                            CONSTRAINT enum_level
                            CHECK (level IN ('Trainee', 'Junior', 'Middle', 'Senior')),
                        salary SMALLINT
                            CONSTRAINT amount_salary
                            CHECK (100000 >= salary AND salary >= 100)
);
CREATE TABLE IF NOT EXISTS client (
                        id IDENTITY PRIMARY KEY,
                        name VARCHAR(1000) NOT NULL
                            CONSTRAINT name_length_client
                            CHECK (length(name) > 2)
);
CREATE TABLE IF NOT EXISTS project (
                        id IDENTITY PRIMARY KEY,
                        client_id BIGINT,
                        start_date date,
                        finish_date date
);
CREATE TABLE IF NOT EXISTS project_worker (
                         project_id BIGINT,
                         worker_id BIGINT,
                         FOREIGN KEY (project_id) REFERENCES project(id),
                         FOREIGN KEY (worker_id) REFERENCES worker(id),
                         PRIMARY KEY (project_id, worker_id)
);
