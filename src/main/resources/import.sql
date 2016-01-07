# each statement must be on one line
INSERT INTO user (id, email, enabled, first_name, last_name, password_hash, token_expired) VALUES (1, 'user@determind.io', 1, 'Brian', 'Bahner', '$2a$10$J0uMpd6d0vifgEaVHPDQ2uWtNaDRhm3G/ozoG6Au94GOj2ch6s8cC', 0);
INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
