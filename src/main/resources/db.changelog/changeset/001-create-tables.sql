--liquibase formatted sql

--changeset vpetrovaa:create_tickets_tables

create schema if not exists cinema_schema;
set schema 'cinema_schema';

create table if not exists films
(
    id bigserial,
	name varchar(45) not null,
	year int not null,
	genre varchar(45) not null,
	showing_date timestamp not null,
	price real not null,
	primary key (id)
);

create table if not exists users
(
    id bigserial,
	email varchar(45) not null unique,
	password varchar(200) not null,
	name varchar(45) not null,
	surname varchar(45) not null,
	phone varchar(12) not null,
	birthday date not null,
	primary key (id)
);

create table if not exists tickets
(
    id bigserial,
	amount int not null,
	film_id bigint null,
    user_id bigint null,
    constraint fk_user foreign key(user_id) references users(id) on delete cascade on update cascade,
    constraint fk_film foreign key(film_id) references films(id) on delete cascade on update cascade,
	primary key (id)
);
