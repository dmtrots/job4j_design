create table habitat (
    id serial primary key,
    name text,
    region text
);

create table animal (
    id serial primary key,
    name text,
    habitat_id int references habitat(id),
    avg_age int
);

insert into habitat (name, region) values
('Ocean', 'Global'),
('Rainforest', 'South America'),
('Desert', 'Africa');

insert into animal (name, habitat_id, avg_age) values
('Shark', 1, 25000),
('Parrot', 2, 20000),
('Camel', 3, 15000);

select a.name animal_name, h.name habitat_name
from animal a
inner join habitat h on a.habitat_id = h.id;

select a.name, a.avg_age, h.region
from animal a
inner join habitat h on a.habitat_id = h.id
where a.avg_age > 18000;

select h.region, a.name
from animal a
inner join habitat h on a.habitat_id = h.id
order by h.region;