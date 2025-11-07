create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments (name) values
('HR'),
('IT'),
('Finance'),
('Marketing');

insert into employees (name, department_id) values
('Alice', 1),
('Bob', 2),
('Charlie', 2),
('Diana', 3),
('Mark', 1),
('Alex', 2),
('Carl', null);

-- left join
select d.name department_name, e.name employee_name
from departments d
left join employees e on d.id = e.department_id;

-- right join
select d.name department_name, e.name employee_name
from departments d
right join employees e on d.id = e.department_id;

-- full join
select d.name department_name, e.name employee_name
from departments d
full join employees e on d.id = e.department_id;

-- cross join
select d.name department_name, e.name employee_name
from departments d
cross join employees e;

-- департаменты без работников
select d.name as department_name
from departments d
left join employees e on d.id = e.department_id
where e.id is null;

-- одинаковый результат
select d.name as department_name, e.name as employee_name
from departments d
left join employees e on d.id = e.department_id;

select d.name as department_name, e.name as employee_name
from employees e
right join departments d on d.id = e.department_id;

create table teens (
    id serial primary key,
    name varchar(255),
    gender char(1)
);

insert into teens (name, gender) values
('Vasya', 'M'),
('Petya', 'M'),
('Masha', 'F'),
('Anya', 'F');

-- пары
select t1.name as male_name, t2.name as female_name
from teens t1
cross join teens t2
where t1.gender = 'M' and t2.gender = 'F';