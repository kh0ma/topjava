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
  (100001,'meal1',now(),100),
  (100001,'meal2',now(),200),
  (100001,'meal3',now(),300),
  (100001,'meal4',now(),400),
  (100001,'meal5',now(),500),
  (100001,'meal6',now(),600)
