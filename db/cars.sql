CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    model varchar(255),
	color varchar(255),
	max_speed int
);

insert into cars (model, color, max_speed) values ('Skoda', 'blue', 200);

select * from cars;

update cars set color = 'white';

delete from cars;