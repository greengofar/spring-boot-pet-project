--liquibase formatted sql

--changeset andev:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);
