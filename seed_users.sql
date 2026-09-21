CREATE EXTENSION IF NOT EXISTS pgcrypto;

DELETE FROM users WHERE username IN ('ashutosh', 'aditi', 'sarthak', 'varad', 'siddhi', 'shrawan');

INSERT INTO users (id, username, password, role) VALUES 
(1, 'ashutosh', crypt('1234', gen_salt('bf', 12)), 'USER'),
(2, 'aditi', crypt('1234', gen_salt('bf', 12)), 'USER'),
(3, 'sarthak', crypt('1234', gen_salt('bf', 12)), 'USER'),
(4, 'varad', crypt('1234', gen_salt('bf', 12)), 'USER'),
(5, 'siddhi', crypt('1234', gen_salt('bf', 12)), 'USER'),
(6, 'shrawan', crypt('3035', gen_salt('bf', 12)), 'ADMIN');
