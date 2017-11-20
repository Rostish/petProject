DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time,description,calories,user_id) VALUES
  ('2015-05-31 10:00:00','Dinner',500,100000),
  ('2015-05-31 11:00:00','Dinner',510,100000),
  ('2015-05-31 12:00:00','Dinner',520,100000),
  ('2015-05-31 10:00:00','Dinner',600,100001),
  ('2015-05-31 11:00:00','Dinner',610,100001),
  ('2015-05-31 12:00:00','Dinner',620,100001);
