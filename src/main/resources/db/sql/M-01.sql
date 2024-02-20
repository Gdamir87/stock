CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255),
    price       DOUBLE PRECISION,
    quantity    INT
);