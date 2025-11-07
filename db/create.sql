create table roles (
    id serial primary key,
    name varchar(50),
    description text
);

create table users (
    id serial primary key,
    username varchar(50) unique,
    password varchar(255),
    email varchar(100),
    role_id int references roles(id)
);

create table rules (
    id serial primary key,
    name varchar(50),
    description text
);

create table role_rules (
    role_id int references roles(id),
    rule_id int references rules(id),
    primary key (role_id, rule_id)
);

create table categories (
    id serial primary key,
    name varchar(50) not null unique
);

create table states (
    id serial primary key,
    name varchar(50) not null unique
);

create table items (
    id serial primary key,
    title varchar(150),
    description text,
    date_created date,
    user_id int references users(id),
    category_id int references categories(id),
    state_id int references states(id)
);

create table comments (
    id serial primary key,
    item_id int references items(id),
    comment text,
    date_created date
);

create table attachs (
    id serial primary key,
    item_id int references items(id),
    file_name varchar(255),
    file_path varchar(500),
    date_uploaded date
);