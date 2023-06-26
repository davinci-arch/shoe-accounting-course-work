--liquibase formatted sql

--changeset Alex:1
create table boots(
    id_footwear int primary key auto_increment,
    category varchar(25) not null,
    type varchar(45) not null,
    model varchar(45) not null,
    brand varchar(45) not null,
    price varchar(6) not null,
    fastener varchar(25) not null,
    color varchar(25) not null,
    material varchar(25) not null,
    weight double not null,
    size int not null,
    seasons varchar(16) not null

);

--changeset Alex:2
create table sandals(
    id_footwear int primary key auto_increment,
    category varchar(25) not null,
    type varchar(45) not null,
    model varchar(45) not null,
    brand varchar(45) not null,
    price varchar(6) not null,
    fastener varchar(25) not null,
    color varchar(25) not null,
    appointment varchar(25) not null,
    size int not null,
    seasons varchar(16) not null
);

--changeset Alex:3
create table shoes(
    id_footwear int primary key auto_increment,
    category varchar(25) not null,
    type varchar(45) not null,
    model varchar(45) not null,
    brand varchar(45) not null,
    price varchar(6) not null,
    color varchar(25) not null,
    size int not null,
    seasons varchar(16) not null

);

--changeset Alex:4
create table slippers(
    id_footwear int primary key auto_increment,
    category varchar(25) not null,
    type varchar(45) not null,
    model varchar(45) not null,
    brand varchar(45) not null,
    price varchar(6) not null,
    color varchar(25) not null,
    appointment varchar(25) not null,
    size int not null,
    seasons varchar(16) not null

);