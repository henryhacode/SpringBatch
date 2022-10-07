DROP TABLE IF EXISTS people CASCADE;

CREATE TABLE people  (
                         person_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                         first_name VARCHAR(20),
                         last_name VARCHAR(20),
                         dob VARCHAR(20)
);