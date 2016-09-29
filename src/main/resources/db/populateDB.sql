DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id,description, date_time, calories) VALUES
  (100000,'Завтрак','2016-09-29 08:00:00.000000',400),
  (100000,'Обед','2016-09-29 12:00:00.000000',600),
  (100000,'Ужин','2016-09-29 17:00:00.000000',500),
  (100000,'Завтрак','2016-09-30 09:00:00.000000',700),
  (100000,'Обед','2016-09-30 14:00:00.000000',800),
  (100000,'Ужин','2016-09-30 19:00:00.000000',520),
  (100001,'Админ завтрак','2016-09-29 08:00:00.000000',400),
  (100001,'Админ обед','2016-09-29 12:00:00.000000',600),
  (100001,'Админ ужин','2016-09-29 17:00:00.000000',500),
  (100001,'Админ завтрак','2016-09-30 09:00:00.000000',700),
  (100001,'Админ обед','2016-09-30 14:00:00.000000',800),
  (100001,'Админ ужин','2016-09-30 19:00:00.000000',520)
