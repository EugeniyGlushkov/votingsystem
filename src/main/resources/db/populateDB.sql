  DELETE FROM VOUTS;
  DELETE FROM DISHES;
  DELETE FROM USER_ROLES;
  DELETE FROM RESTAURANTS;
  DELETE FROM USERS;
  ALTER SEQUENCE global_seq RESTART WITH 100000;

  INSERT INTO USERS (name) VALUES
  ('User'),
  ('Admin');

  INSERT INTO user_roles (user_id, role) VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_ADMIN'),
  (100001, 'ROLE_USER');

  INSERT INTO restaurants (restaurants_name) VALUES
  ('Eleon'),
  ('Grand'),
  ('Royal');

  INSERT INTO dishes (restaurants_id, dish, date) VALUES
  (100002, 'Борщ', '2018-02-11'),
  (100002, 'Щи', '2018-02-11'),
  (100002, 'Оливье', '2018-02-11'),
  (100003, 'Окрошка', '2018-02-11'),
  (100003, 'Чебуреки', '2018-02-11'),
  (100004, 'Салат', '2018-02-11'),
  (100004, 'Макароны', '2018-02-11'),
  (100004, 'Котлеты', '2018-02-11'),
  (100004, 'Отбивные', '2018-02-11'),
  (100002, 'Картошка', '2018-02-12'),
  (100002, 'Пюре', '2018-02-12'),
  (100003, 'Окрошка', '2018-02-12'),
  (100003, 'Чебуреки', '2018-02-12'),
  (100004, 'Грибы', '2018-02-12'),
  (100004, 'Пельмени', '2018-02-12'),
  (100004, 'Голубцы', '2018-02-12');

  INSERT INTO vouts (user_id, restaurants_id, vote_date) VALUES
  (100000, 100002, '2018-02-11'),
  (100001, 100004, '2018-02-11'),
  (100000, 100003, '2018-02-12'),
  (100001, 100002, '2018-02-12');


