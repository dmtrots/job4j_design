create table type
(
    id   serial primary key,
    name varchar(255)
);

create table product
(
    id           serial primary key,
    name         varchar(255),
    type_id      int references type(id),
    expired_date date,
    price        numeric(10,2)
);

insert into type (name) values
('СЫР'),
('МОЛОКО'),
('МОРОЖЕНОЕ'),
('ХЛЕБ');

insert into product (name, type_id, expired_date, price) values
('Сыр Моцарелла', 1, '2025-12-01', 200.00),
('Сыр Гауда', 1, '2025-11-01', 200.00),
('Сыр плавленный', 1, '2025-10-01', 180.00),
('Молоко деревенское', 2, '2025-11-10', 90.00),
('Молоко ультрапастеризованное', 2, '2025-11-20', 95.00),
('Мороженое ванильное', 3, '2025-06-01', 120.00),
('Мороженое шоколадное', 3, '2025-05-20', 120.00),
('Хлеб пшеничный', 4, '2025-11-15', 50.00),
('Хлеб ржаной', 4, '2025-11-12', 55.00);

select *
from product p
join type t on p.type_id = t.id
where t.name = 'СЫР';

select *
from product
where name like '%Мороженое%';

select *
from product
where expired_date < current_date;

select *
from product
where price = (select max(price) from product);

select t.name as type_name, count(p.id) as product_count
from type t
left join product p on p.type_id = t.id
group by t.name;

select *
from product p
join type t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

select t.name as type_name, count(p.id) as product_count
from type t
left join product p on p.type_id = t.id
group by t.name
having count(p.id) < 10;

select p.name as product_name, t.name as type_name, p.price, p.expired_date
from product p
join type t on p.type_id = t.id;