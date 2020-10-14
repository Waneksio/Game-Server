CREATE TABLE IF NOT EXISTS user (
    u_id serial primary key,
    u_nick varchar(30) not null unique,
    u_password varchar(30) not null,
    u_score int
);