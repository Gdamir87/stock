CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    article     VARCHAR(255) UNIQUE,
    name        VARCHAR(255),
    description VARCHAR(255),
    price       DOUBLE PRECISION,
    quantity    INT,
    category_id SERIAL REFERENCES categories (id)
);

