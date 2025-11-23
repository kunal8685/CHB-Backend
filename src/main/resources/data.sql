INSERT INTO halls (id, name, address, capacity, fee, ulb_id, image_url)
VALUES (1, 'Central Community Hall', 'MG Road, City', 200, 2000, 1, NULL),
       (2, 'Eastside Hall', 'East Street', 120, 1200, 1, NULL),
       (3, 'Westend Hall', 'West Avenue', 150, 1500, 1, NULL);

-- admin user: password 'admin123' bcrypt (example)
INSERT INTO users (id, username, password, name, email, mobile, role)
SELECT 1, 'admin', '$2a$10$7q0h8Qc6sGJpH82Zmoq/.uw7JY2mJzG4Aq8JxPq6H8gk6xw3qXQpG', 'Administrator', 'admin@demo', '9999999999', 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='admin');
