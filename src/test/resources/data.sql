INSERT INTO user_info (email, first_name, last_name, status, logged_in_at)
VALUES
('abc@dummy.com','John 1','Doe','ACTIVE',CURRENT_TIMESTAMP - INTERVAL '1' HOUR),
('def@dummy.com','John 2','Doe','ACTIVE',CURRENT_TIMESTAMP - INTERVAL '30' MINUTE),
('ghi@dummy.com','John 3','Doe','ACTIVE',CURRENT_TIMESTAMP - INTERVAL '10' MINUTE),
('jkl@dummy.com','John 4','Doe','ACTIVE',CURRENT_TIMESTAMP);