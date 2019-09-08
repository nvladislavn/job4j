DROP TABLE IF EXISTS
    colors,
    bodyTypes,
    engineTypes,
    transmissionTypes,
    carBodies,
    carEngines,
    transmission,
    producers,
    models,
    stock;

CREATE TABLE bodyTypes
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE engineTypes
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE transmissionTypes
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE colors
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE carBodies
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(2000),
    bodyType_id INT REFERENCES bodyTypes (id),
    color_id    INT REFERENCES colors (id)
);

CREATE TABLE carEngines
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(2000),
    engineType_id INT REFERENCES engineTypes (id),
    volume        DEC NOT NULL
);

CREATE TABLE transmission
(
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR(2000),
    transmissionType_id INT REFERENCES transmissionTypes (id),
    numberOfGearStages  INT
);

CREATE TABLE producers
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE models
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(2000),
    producer_id     INT REFERENCES producers (id),
    carBody_id      INT REFERENCES carBodies (id),
    carEngine_id    INT REFERENCES carEngines (id),
    transmission_id INT REFERENCES transmission (id)
);

CREATE TABLE stock
(
    producer_id INT REFERENCES producers (id),
    model_id    INT REFERENCES models (id),
    quantity    INT DEFAULT 0,
    PRIMARY KEY (producer_id, model_id)
);

INSERT INTO bodyTypes(name)
VALUES ('sedan'),
       ('hatchback'),
       ('pickup'),
       ('cabriolet');

INSERT INTO engineTypes(name)
VALUES ('petrol'),
       ('gaz'),
       ('diesel');

INSERT INTO transmissionTypes(name)
VALUES ('manual'),
       ('automatic');

INSERT INTO colors(name)
VALUES ('white'),
       ('black'),
       ('blue'),
       ('red'),
       ('grey');

INSERT INTO carBodies(name, bodyType_id, color_id)
VALUES ('sedan_blue',
        (SELECT id FROM bodyTypes WHERE bodyTypes.name = 'sedan'),
        (SELECT id FROM colors WHERE colors.name = 'blue')),
       ('sedan_gray',
        (SELECT id FROM bodyTypes WHERE bodyTypes.name = 'sedan'),
        (SELECT id FROM colors WHERE colors.name = 'blue')),
       ('hatchback_red',
        (SELECT id FROM bodyTypes WHERE bodyTypes.name = 'cabriolet'),
        (SELECT id FROM colors WHERE colors.name = 'red')),
       ('pickup_black',
        (SELECT id FROM bodyTypes WHERE bodyTypes.name = 'pickup'),
        (SELECT id FROM colors WHERE colors.name = 'black'));

INSERT INTO carEngines(name, engineType_id, volume)
VALUES ('p_1.6',
        (SELECT id FROM engineTypes WHERE engineTypes.name = 'petrol'),
        1.6),
       ('p_2.0',
        (SELECT id FROM engineTypes WHERE engineTypes.name = 'petrol'),
        2.0),
       ('d_1.5',
        (SELECT id FROM engineTypes WHERE engineTypes.name = 'diesel'),
        1.5),
       ('d_2.4',
        (SELECT id FROM engineTypes WHERE engineTypes.name = 'diesel'),
        2.4);

INSERT INTO transmission(name, transmissionType_id, numberOfGearStages)
VALUES ('manual_5',
        (SELECT id FROM transmissionTypes WHERE transmissionTypes.name = 'manual'),
        5),
       ('manual_6',
        (SELECT id FROM transmissionTypes WHERE transmissionTypes.name = 'manual'),
        6),
       ('automatic_4',
        (SELECT id FROM transmissionTypes WHERE transmissionTypes.name = 'automatic'),
        4),
       ('automatic_6',
        (SELECT id FROM transmissionTypes WHERE transmissionTypes.name = 'automatic'),
        6);

INSERT INTO producers(name)
VALUES ('audi'),
       ('nissan'),
       ('renault'),
       ('toyota'),
       ('hyundai'),
       ('volvo');

INSERT INTO models(name, producer_id, carBody_id, carEngine_id, transmission_id)
VALUES ('corolla_2.0M',
        (SELECT id FROM producers WHERE producers.name = 'toyota'),
        (SELECT id FROM carBodies WHERE carBodies.name = 'sedan_blue'),
        (SELECT id FROM carEngines WHERE carEngines.name = 'p_2.0'),
        (SELECT id FROM transmission WHERE transmission.name = 'manual_5')),
       ('corolla_2.0A',
        (SELECT id FROM producers WHERE producers.name = 'toyota'),
        (SELECT id FROM carBodies WHERE carBodies.name = 'sedan_gray'),
        (SELECT id FROM carEngines WHERE carEngines.name = 'p_2.0'),
        (SELECT id FROM transmission WHERE transmission.name = 'automatic_6')),
       ('i30',
        (SELECT id FROM producers WHERE producers.name = 'hyundai'),
        (SELECT id FROM carBodies WHERE carBodies.name = 'hatchback_red'),
        (SELECT id FROM carEngines WHERE carEngines.name = 'p_2.0'),
        (SELECT id FROM transmission WHERE transmission.name = 'manual_6')),
       ('navara',
        (SELECT id FROM producers WHERE producers.name = 'nissan'),
        (SELECT id FROM carBodies WHERE carBodies.name = 'pickup_black'),
        (SELECT id FROM carEngines WHERE carEngines.name = 'd_2.4'),
        (SELECT id FROM transmission WHERE transmission.name = 'manual_6'));

INSERT INTO stock(producer_id, model_id, quantity)
VALUES ((SELECT id FROM producers WHERE producers.name = 'toyota'),
        (SELECT id FROM models WHERE models.name = 'corolla_2.0A'),
        10),
       ((SELECT id FROM producers WHERE producers.name = 'nissan'),
        (SELECT id FROM models WHERE models.name = 'navara'),
        3),
       ((SELECT id FROM producers WHERE producers.name = 'hyundai'),
        (SELECT id FROM models WHERE models.name = 'i30'),
        7);

--1. Вывести список всех машин и все привязанные к ним детали.
SELECT m.name, b.name, e.name, t.name
FROM models m
         LEFT JOIN carBodies b ON m.carBody_id = b.id
         LEFT JOIN carEngines e ON m.carEngine_id = e.id
         LEFT JOIN transmission t ON m.transmission_id = t.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT DISTINCT b.name
FROM carBodies b
         LEFT JOIN models m on b.id = m.carBody_id
WHERE m.name != 'navara'
   or m.name ISNULL;

SELECT DISTINCT e.name
FROM carEngines e
         LEFT JOIN models m on e.id = m.carEngine_id
WHERE m.name != 'navara'
   OR m.name is null;

SELECT DISTINCT t.name
FROM transmission t
         LEFT JOIN models m on t.id = m.transmission_id
WHERE m.name != 'navara'
   or m.name ISNULL;