CREATE DATABASE job4j;

/*DDL*/
CREATE TABLE users
(
    id   SERIAL,
    name VARCHAR(2000),
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   SERIAL,
    name VARCHAR(2000),
    PRIMARY KEY (id)
);

CREATE TABLE rules
(
    id   SERIAL,
    name VARCHAR(2000),
    PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id   SERIAL,
    name VARCHAR(2000),
    PRIMARY KEY (id)
);

CREATE TABLE states
(
    id   SERIAL,
    name VARCHAR(2000),
    PRIMARY KEY (id)
);

CREATE TABLE ru.job4j.items
(
    id          SERIAL,
    name        VARCHAR(2000),
    category_id INT REFERENCES categories (id),
    state_id    INT REFERENCES states (id),
    PRIMARY KEY (id)
);

CREATE TABLE comments
(
    id      SERIAL,
    name    VARCHAR(2000),
    item_id INT REFERENCES ru.job4j.items (id) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE attachments
(
    id      SERIAL,
    name    VARCHAR(2000),
    item_id INT REFERENCES ru.job4j.items (id) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id    INT REFERENCES users (id),
    role_id    INT REFERENCES roles (id),
    start_date TIMESTAMP,
    end_date   TIMESTAMP,
    PRIMARY KEY (user_id, role_id, start_date)
);

CREATE TABLE roles_rules
(
    role_id INT REFERENCES roles (id),
    rule_id INT REFERENCES rules (id),
    PRIMARY KEY (role_id, rule_id)
);

/*DML*/
INSERT INTO users(name)
VALUES ('Ivanov'),
       ('Petrov'),
       ('Sidorova');

INSERT INTO roles(name)
VALUES ('Student'),
       ('Teacher'),
       ('Administrator');

INSERT INTO rules(name)
VALUES ('Write'),
       ('Edit'),
       ('Read');

INSERT INTO categories(name)
VALUES ('Important'),
       ('Secondary');

INSERT INTO states(name)
VALUES ('Ivanov'),
       ('Petrov'),
       ('Sidorova');

INSERT INTO ru.job4j.items(name)
VALUES ('Ivanov item'),
       ('Petrov item'),
       ('Sidorova item');

INSERT INTO comments(name, item_id)
VALUES ('Ivanov item', 1),
       ('Petrov item', 2),
       ('Sidorova item', 3);

INSERT INTO attachments(name, item_id)
VALUES ('Ivanov attachment', 1),
       ('Petrov attachment', 2),
       ('Sidorova attachment', 3);

INSERT INTO roles_rules(role_id, rule_id)
VALUES (1, 3),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3);

INSERT INTO users_roles(user_id, role_id, start_date)
VALUES (1, 1, current_timestamp),
       (2, 2, current_timestamp),
       (3, 3, current_timestamp);

