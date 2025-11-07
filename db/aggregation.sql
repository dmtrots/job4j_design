CREATE TABLE devices
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT
);

CREATE TABLE people
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_people
(
    id        SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices (id),
    people_id INT REFERENCES people (id)
);

insert into devices (name, price) values
('Laptop', 1200.50),
('Smartphone', 850.00),
('Tablet', 600.00),
('Smartwatch', 250.00),
('Desktop PC', 1500.00),
('E-book Reader', 130.00);

insert into people (name) values
('Alice'),
('Bob'),
('Charlie'),
('Diana'),
('Eve');

insert into devices_people (device_id, people_id) values
(1, 1),
(1, 1),
(1, 1),
(1, 1),
(1, 1),
(2, 1),
(3, 2),
(5, 2),
(2, 3),
(4, 3),
(6, 4),
(1, 5),
(2, 5);

select name, avg(price)
from devices
group by name;

select p.name, avg(d.price)
from devices d
join devices_people dp on d.id = dp.device_id
join people p on p.id = dp.people_id
group by p.name;

select p.name, avg(d.price) avrg
from devices d
join devices_people dp on d.id = dp.device_id
join people p on p.id = dp.people_id
group by p.name
having avg(d.price) > 500;