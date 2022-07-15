INSERT INTO currencies (id, symbol) VALUES (90, 'BTC');
INSERT INTO currencies (id, symbol) VALUES (80, 'ETH');
INSERT INTO currencies (id, symbol) VALUES (48543, 'SOL');

INSERT INTO prices (id, currency_id, price, updated) VALUES (1, 90, 55.55, '2022-06-12T08:12:15');
INSERT INTO prices (id, currency_id, price, updated) VALUES (2, 90, 56.55, '2022-06-12T08:13:16');
INSERT INTO prices (id, currency_id, price, updated) VALUES (3, 80, 60.55, '2022-06-12T08:14:17');

INSERT INTO users (id, username, price_id) VALUES (1, 'gena', 2);