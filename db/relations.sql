--один к одному
CREATE TABLE users (
    id serial primary key,
    full_name varchar(300)
);

CREATE TABLE library_cards (
    id serial primary key,
    user_id int references users(id) unique,
    issued_date date,
    expiration_date date
);

select * from users;

select * from library_cards;

--один ко многим
create table authors (
    id serial primary key,
    name varchar(300),
    birth_year int
);

CREATE TABLE books (
    id serial primary key,
    author_id int references authors(id),
    title varchar(200),
    publish_year INT
);

select * from authors;

select * from books;

--многие ко многим
CREATE TABLE genres (
    id serial primary key,
    name varchar(150) unique
);

CREATE TABLE book_genres (
    id serial primary key,
    book_id int references books(id),
    genre_id int references genres(id)
);

select * from genres;

select * from book_genres;