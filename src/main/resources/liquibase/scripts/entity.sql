-- liquibase formatted sql

--changeset sev:1
create table socks
(
    id          serial primary key,
    color       varchar(255),
    cotton_part int,
    quantity    int
);