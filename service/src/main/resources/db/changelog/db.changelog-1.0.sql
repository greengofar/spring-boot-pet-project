--liquibase formatted sql

--changeset andev:1
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    user_name  VARCHAR(128) NOT NULL UNIQUE,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    email      VARCHAR(128) NOT NULL UNIQUE,
    phone      VARCHAR(16),
    role       VARCHAR(64)  NOT NULL
);
--rollback DROP TABLE users;

--changeset andev:2
CREATE TABLE orders
(
    id               SERIAL PRIMARY KEY,
    date_order       DATE NOT NULL,
    date_closing     DATE,
    total_value      INT,
    payment          VARCHAR(16),
    status           VARCHAR(64),
    town             VARCHAR(32),
    street           VARCHAR(32),
    house_number     INT,
    apartment_number INT,
    postal_code      INT,
    user_id          INT REFERENCES users (id) ON DELETE CASCADE
);
--rollback DROP TABLE orders;

--changeset andev:3
CREATE TABLE manufacturer
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) NOT NULL UNIQUE,
    description TEXT
);
--rollback DROP TABLE manufacturer;

--changeset andev:4
CREATE TABLE product
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(64) NOT NULL,
    model           VARCHAR(32),
    category        VARCHAR(32),
    description     TEXT,
    price           DECIMAL     NOT NULL,
    amount          INT,
    manufacturer_id INT         NOT NULL REFERENCES manufacturer (id)
);
--rollback DROP TABLE product;

--changeset andev:5
CREATE TABLE product_order
(
    product_id INT REFERENCES product (id) ON DELETE CASCADE,
    order_id   INT REFERENCES orders (id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, order_id)
);
--rollback DROP TABLE product_order;