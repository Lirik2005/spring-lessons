--liquibase formatted sql

--changeset  kgusakov:1
ALTER TABLE  users_aud
DROP CONSTRAINT users_aud_username_key;

--changeset  kgusakov:2
ALTER TABLE  users_aud
    ALTER COLUMN username DROP NOT NULL ;