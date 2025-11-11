insert into roles (name, description) values
('Admin', 'Администратор системы'),
('User', 'Обычный пользователь'),
('Manager', 'Менеджер');

insert into rules (name, description) values
('CreateItem', 'Создание заявки'),
('EditItem', 'Редактирование заявки'),
('DeleteItem', 'Удаление заявки'),
('CommentItem', 'Добавление комментария');

insert into role_rules (role_id, rule_id) values
(1,1),(1,2),(1,3),(1,4),
(2,1),(2,2),
(3,1),(3,2),(3,3);

insert into users (username, password, email, role_id) values
('alice', 'password1', 'alice@example.com', 1),
('bob', 'password2', 'bob@example.com', 2),
('carol', 'password3', 'carol@example.com', 3);

insert into states (name) values
('New'),
('In Progress'),
('Completed'),
('Closed');

insert into categories (name) values
('Technical'),
('HR'),
('Finance');

insert into items (title, description, user_id, category_id, state_id) values
('Cannot login', 'Пользователь не может войти в систему', 2, 1, 1),
('Salary issue', 'Задержка зарплаты', 3, 3, 1);

insert into comments (item_id, comment) values
(1, 'Проверяем проблему с логином'),
(2, 'Свяжемся с бухгалтерией');

insert into attachs (item_id, file_name, file_path) values
(1,'screenshot.png','/files/screenshot.png'),
(2,'pay_stub.pdf','/files/pay_stub.pdf');