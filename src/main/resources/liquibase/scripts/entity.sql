-- liquibase formatted sql

--changeset sev:1
create table socks
(
    id          serial primary key,
    color       varchar(255),
    cotton_part int,
    quantity    int
);

--changeset sev:2
alter table socks
    alter column color set not null;

alter table socks
    add constraint cotton_constraint check (cotton_part >= 0 and cotton_part < 101);

alter table socks
    add constraint quantity_constraint check (quantity > 0);