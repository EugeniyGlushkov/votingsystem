DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM menus;
DELETE FROM prices;
DELETE FROM votes;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;
ALTER SEQUENCE VOTE_SEQ RESTART WITH 1000;

INSERT INTO users (name) VALUES                 /*del*/
  ('Alex'),                                     /*100000*/
  ('Sindy'),                                    /*100001*/
  ('Fox');                                      /*100002*/

INSERT INTO user_roles (user_id, role) VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_ADMIN'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_USER');

INSERT INTO restaurants (name) VALUES
  ('Ambassador'),                               /*100003*/
  ('Eleon'),                                    /*100004*/
  ('Mandalay');                                 /*100005*/

INSERT INTO menus (restaurants_id, date) VALUES
  (100003, '2018-05-09'),                       /*100006*/
  (100004, '2018-05-09');                       /*100007*/

INSERT INTO menus (restaurants_id) VALUES
  (100004),                                      /*100008*/
  (100003);                                      /*100009*/

INSERT INTO prices (menu_id, dish, price) VALUES
  (100006, 'cake', 12),
  (100006, 'fish', 5.4),
  (100006, 'cheaps', 4.56),
  (100007, 'soup', 3.86),
  (100007, 'rooster', 8.96),
  (100007, 'eggs', 12.2),
  (100007, 'rabbit', 4.3),
  (100009, 'soup', 3.86),
  (100009, 'rooster', 8.96),
  (100009, 'eggs', 12.2),
  (100009, 'rabbit', 4.3),
  (100008, 'cake', 12),
  (100008, 'fish', 5.4),
  (100008, 'cheaps', 4.56);

INSERT INTO votes (user_id, menu_id) VALUES
  (100000, 100006),                               /*1000*/
  (100001, 100007),                               /*1001*/
  (100002, 100007),                               /*1002*/
  (100002, 100009);                               /*1003*/