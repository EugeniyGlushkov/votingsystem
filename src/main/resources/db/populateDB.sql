DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM menus;
DELETE FROM prices;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name) VALUES
  ('Alex'),                                     /*100000*/
  ('Sindy'),                                    /*100001*/
  ('Fox');                                      /*100002*/

INSERT INTO user_roles (user_id, role) VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_ADMIN'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_USER');

INSERT INTO restaurants (restaurants_name) VALUES
  ('Ambassador'),                               /*100003*/
  ('Mandalay');                                 /*100004*/

INSERT INTO menus (restaurants_id, date) VALUES
  (100003, '2018-05-09'),                       /*100005*/
  (100004, '2018-05-09');                       /*100006*/

INSERT INTO menus (restaurants_id) VALUES
  (100003),                                      /*100007*/
  (100004);                                      /*100008*/

INSERT INTO prices (menu_id, dish, price) VALUES
  (100005, 'cake', 12),
  (100005, 'fish', 5.4),
  (100005, 'cheaps', 4.56),
  (100006, 'soup', 3.86),
  (100006, 'rooster', 8.96),
  (100006, 'eggs', 12.2),
  (100006, 'rabbit', 4.3),
  (100007, 'soup', 3.86),
  (100007, 'rooster', 8.96),
  (100007, 'eggs', 12.2),
  (100007, 'rabbit', 4.3),
  (100008, 'cake', 12),
  (100008, 'fish', 5.4),
  (100008, 'cheaps', 4.56);

INSERT INTO vouts (user_id, menu_id) VALUES
  (100000, 100005),
  (100001, 100006),
  (100002, 100006),
  (100002, 100008);