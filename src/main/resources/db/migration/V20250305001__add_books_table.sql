create table books (
    id bigserial primary key,
    title varchar(100) not null unique,
    author varchar(80) not null
)