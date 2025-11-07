create table car_bodies (
    id serial primary key,
    name varchar(255)
);

create table car_engines (
    id serial primary key,
    name varchar(255)
);

create table car_transmissions (
    id serial primary key,
    name varchar(255)
);

create table cars (
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values
('Седан'),
('Хэтчбек'),
('Внедорожник'),
('Купе'),
('Кабриолет');

insert into car_engines (name) values
('Бензиновый 1.6L'),
('Бензиновый 2.0L'),
('Дизель 2.0L'),
('Электро'),
('Водородный');

insert into car_transmissions (name) values
('Механическая'),
('Автоматическая'),
('Вариатор')
('Роботизированная');

insert into cars (name, body_id, engine_id, transmission_id) values
('Car A', 1, 1, 2),
('Car B', 2, 2, 1),
('Car C', 3, 3, 2),
('Car D', 4, 4, null),
('Car E', 1, 2, 2),
('Car F', 3, 1, 1),
('Car G', 2, 4, null);


drop table cars;
select * from car_transmissions;

select c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name
from cars c
left join car_bodies cb on cb.id = c.body_id
left join car_engines ce on ce.id = c.engine_id
left join car_transmissions ct on ct.id = c.transmission_id;

select cb.name
from car_bodies cb
left join cars c on cb.id = c.body_id
where c.id is null;

select ce.name
from car_engines ce
left join cars c on ce.id = c.engine_id
where c.id is null;

select ct.name
from car_transmissions ct
left join cars c on ct.id = c.transmission_id
where c.id is null;