CREATE TABLE items
(
    id VARCHAR(13) PRIMARY KEY,
    name VARCHAR(2000),
    description VARCHAR(3000),
    created BIGINT
);

CREATE TABLE  comments
(
    id SERIAL PRIMARY KEY,
    commentText TEXT,
    item_id VARCHAR(13) REFERENCES items(id)
);

