create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values
('Goldfish', 1200, '1700-01-01'),
('Catfish', 8000, '1800-06-15'),
('Swordfish', 15000, '1920-03-10'),
('Dog', 5000, '1750-05-20'),
('Parrot', 20000, null),
('Shark', 25000, '1600-08-30'),
('Jellyfish', 36500, '1705-11-11'),
('Seahorse', 9000, '1960-09-01'),
('Starfish', 10000, null);

select * from fauna where name like '%fish%';

select * from fauna where avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';