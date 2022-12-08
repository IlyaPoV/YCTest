INSERT INTO subjects
(subject_id, title, grade)
VALUES(uuid_generate_v4(), 'Питон', 3);

INSERT INTO subjects
(subject_id, title, grade)
VALUES(uuid_generate_v4(), 'Мобилки', 3);

INSERT INTO users
(user_id, "password", first_name, second_name, "role")
VALUES('kok@mail.ru', '123', 'Константин', 'Константинович', 'STUDENT');

INSERT INTO users
(user_id, "password", first_name, second_name, "role")
VALUES('kot@mail.ru', '123', 'Учитель', 'Константинович', 'TEACHER');

INSERT INTO users
(user_id, "password", first_name, second_name, "role")
VALUES('koa@mail.ru', '123', 'Администратор', 'Константинович', 'ADMINISTRATOR');

INSERT INTO "groups"
(group_id, subject_id, title, grade)
VALUES(uuid_generate_v4(), (select subject_id from subjects where title = 'Питон'), 'Костя К-Питон', 2);

INSERT INTO "groups"
(group_id, subject_id, title, grade)
VALUES(uuid_generate_v4(), (select subject_id from subjects where title = 'Мобилки'), 'Костя К-Мобилки', 2);

INSERT INTO user_i_group
(group_id, user_id)
VALUES((select group_id from groups where title = 'Костя К-Питон'), (select user_id from users where first_name = 'Константин'));

INSERT INTO user_i_group
(group_id, user_id)
VALUES((select group_id from groups where title = 'Костя К-Питон'), (select user_id from users where first_name = 'Учитель'));

INSERT INTO user_i_group
(group_id, user_id)
VALUES((select group_id from groups where title = 'Костя К-Мобилки'), (select user_id from users where first_name = 'Константин'));

INSERT INTO user_i_group
(group_id, user_id)
VALUES((select group_id from groups where title = 'Костя К-Мобилки'), (select user_id from users where first_name = 'Учитель'));
