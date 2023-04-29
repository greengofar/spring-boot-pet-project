INSERT INTO users (id, user_name, first_name, last_name, email, phone, role)
VALUES (1, 'ivan@gmail.com', 'Ivan', 'Ivanov', 'ivan@gmail.com', '+375295252622', 'ADMIN'),
       (2, 'petr@gmail.com', 'Petr', 'Petrov', 'petr@gmail.com', '+375295252623', 'CUSTOMER'),
       (3, 'sveta@gmail.com', 'Sveta', 'Svetikova', 'sveta@gmail.com', '+375295252624', 'CUSTOMER'),
       (4, 'vlad@gmail.com', 'Vlad', 'Vladikov', 'vlad@gmail.com', '+375295252625', 'CUSTOMER'),
       (5, 'kate@gmail.com', 'Kate', 'Smith', 'kate@gmail.com', '+375295252626', 'ADMIN');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO orders (id, date_order, date_closing, total_value, payment, status, town, street, house_number,
                    apartment_number, postal_code, user_id)
VALUES (1, '2022-09-22', '2022-09-25', 1750, 'CARD', 'RECEIVED', 'Warsaw', 'Mickevicza', '56', '12', '00028',
        (SELECT id FROM users WHERE user_name = 'petr@gmail.com')),
       (2, '2022-09-22', '2022-09-26', 4250, 'CASH', 'RECEIVED', 'Gdansk', 'Piwna', '72', '81', '80001',
        (SELECT id FROM users WHERE user_name = 'sveta@gmail.com')),
       (3, '2022-09-23', '2022-09-25', 1650, 'CARD', 'RECEIVED', 'Warsaw', 'Mickevicza', '56', '12', '00028',
        (SELECT id FROM users WHERE user_name = 'petr@gmail.com')),
       (4, '2022-09-23', '2022-09-25', 3450, 'CASH', 'RECEIVED', 'Brest', 'Luckaya', '76', '11', '224009',
        (SELECT id FROM users WHERE user_name = 'vlad@gmail.com')),
       (5, '2022-09-23', '2022-09-26', 2100, 'CARD', 'RECEIVED', 'Warsaw', 'Mickevicza', '56', '12', '00028',
        (SELECT id FROM users WHERE user_name = 'petr@gmail.com')),
       (6, '2022-09-24', '2022-09-27', 4100, 'CASH', 'RECEIVED', 'Warsaw', 'Mickevicza', '56', '12', '00028',
        (SELECT id FROM users WHERE user_name = 'petr@gmail.com')),
       (7, '2022-09-24', '2022-09-28', 3000, 'CARD', 'RECEIVED', 'Gdansk', 'Piwna', '72', '81', '80001',
        (SELECT id FROM users WHERE user_name = 'sveta@gmail.com')),
       (8, '2022-09-24', '2022-09-29', 3450, 'CASH', 'RECEIVED', 'Brest', 'Luckaya', '76', '11', '224009',
        (SELECT id FROM users WHERE user_name = 'vlad@gmail.com')),
       (9, '2022-09-24', '2022-09-29', 3400, 'CASH', 'RECEIVED', 'Brest', 'Luckaya', '76', '11', '224009',
        (SELECT id FROM users WHERE user_name = 'vlad@gmail.com')),
       (10, '2022-09-25', '2022-09-29', 2500, 'CASH', 'RECEIVED', 'Gdansk', 'Piwna', '72', '81', '80001',
        (SELECT id FROM users WHERE user_name = 'sveta@gmail.com'));
SELECT SETVAL('orders_id_seq', (SELECT MAX(id) FROM orders));

INSERT INTO manufacturer (id, name, description)
VALUES (1, 'Apple', 'description from Apple'),
       (2, 'Samsung', 'description from Samsung'),
       (3, 'Bosch', 'description from Bosch');
SELECT SETVAL('manufacturer_id_seq', (SELECT MAX(id) FROM manufacturer));

INSERT INTO product (id, name, model, category, description, price, amount, manufacturer_id)
VALUES (1, 'smartphone', 'iPhone 11', 'ELECTRONICS', 'description about iPhone 11', 850, 10,
        (SELECT id FROM manufacturer WHERE name = 'Apple')),
       (2, 'smartphone', 'iPhone 12', 'ELECTRONICS', 'description about iPhone 12', 900, 28,
        (SELECT id FROM manufacturer WHERE name = 'Apple')),
       (3, 'laptop', 'Macbook Pro 14', 'COMPUTERS', 'description about Macbook Pro 14', 2500, 8,
        (SELECT id FROM manufacturer WHERE name = 'Apple')),
       (4, 'smartphone', 'Galaxy S22', 'ELECTRONICS', 'description about Galaxy S22', 750, 11,
        (SELECT id FROM manufacturer WHERE name = 'Samsung')),
       (5, 'monitor', 'S33', 'COMPUTERS', 'description about S33', 200, 5,
        (SELECT id FROM manufacturer WHERE name = 'Samsung')),
       (6, 'fridge', 'Serie 6 VitaFresh Plus', 'HOME_TOOLS', 'description about Serie 6 VitaFresh Plus', 2100, 3,
        (SELECT id FROM manufacturer WHERE name = 'Bosch'));
SELECT SETVAL('product_id_seq', (SELECT MAX(id) FROM product));

INSERT INTO product_order (product_id, order_id)
VALUES (1, 1),
       (2, 1),
       (1, 2),
       (2, 2),
       (3, 2),
       (2, 3),
       (4, 3),
       (3, 4),
       (4, 4),
       (5, 4),
       (6, 5),
       (1, 6),
       (3, 6),
       (4, 6),
       (2, 7),
       (6, 7),
       (3, 8),
       (4, 8),
       (5, 8),
       (2, 9),
       (3, 9),
       (3, 10);
