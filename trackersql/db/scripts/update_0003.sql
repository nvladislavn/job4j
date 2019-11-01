CREATE TABLE IF NOT EXISTS company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer references company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company(id, name)
VALUES (0, 'IBM'),
       (1, 'Oracle'),
       (2, 'Google'),
       (3, 'Microsoft'),
       (4, 'Job4J'),
       (5, 'Yandex'),
       (6, 'ABBYY');

INSERT INTO person(id, name, company_id)
VALUES (0, 'Ivan', 0),
       (1, 'John', 1),
       (2, 'Sidor', 2),
       (3, 'Serge', 3),
       (4, 'Jack', 4),
       (5, 'Kat', 5),
       (6, 'Linda', 6),
       (7, 'Jane', 5),
       (8, 'Miranda', 0),
       (9, 'Angelo', 0);

-- 1) Retrieve in a single query:
-- names of all persons that are NOT in the company with id = 5
-- company name for each person
SELECT P.name, C.name
FROM person P
         LEFT JOIN company C
                   ON P.company_id = C.id
WHERE NOT company_id = 5;

-- 2) Select the name of the company with the maximum number of persons + number of persons in this company
SELECT comp, nm
FROM (SELECT C.name comp, COUNT(*) nm
      FROM company C
               LEFT JOIN person P
                         ON C.id = P.company_id
      GROUP BY C.name
      ORDER BY nm DESC) as list
LIMIT 1
