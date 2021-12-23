INSERT INTO user_entity (username, password, money) VALUES
  ('username', 'password', '22.2$'),
  ('password', 'username', '24.1$'),
  ('asdfg', '12345', '522.1$'),
  ('u', '1', '5341$'),
  ('entity', 'money', '32.2$');


INSERT INTO item_entity (name, price) VALUES
  ('cucumber', '7$'),
  ('pad', '5.3$'),
  ('lamp', '14.1$'),
  ('mouse', '1.11$');

INSERT INTO basket_entity (item_name, quantity, total_price, owner_id) VALUES
  ('cucumber', 2, '14$', 1),
  ('mouse', 5, '5.55$', 3);
