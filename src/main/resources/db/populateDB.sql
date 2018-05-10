DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM menus;
DELETE FROM prices;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name) VALUES
  ('Alex'),  100000
  ('Sindy'), 100001
  ('Fox');   100002

INSERT INTO user_roles (user_id, role) VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_ADMIN'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_USER');

INSERT INTO restaurants (restaurants_name) VALUES
  ('Ambassador'), 100003
  ('Mandalay');   100004

INSERT INTO menus (restaurants_id, date) VALUES
  (100003, '2018-05-09'), 100005
  (100004, '2018-05-09'); 100006

INSERT INTO menus (restaurants_id) VALUES
  (100003), 100007
  (100004); 100008

