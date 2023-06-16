--liquibase formatted sql

--changeset andev:1
ALTER TABLE product
ADD COLUMN image_Name VARCHAR(64);
