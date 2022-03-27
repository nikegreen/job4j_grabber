create table if not exists post (
    id serial primary key,
    name varchar,
    text varchar,
    link varchar unique not null,
    created timestamp
);
truncate table post;

create table if not exists rabbit (
    id serial primary key,
    created_date timestamp
);