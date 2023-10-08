INSERT INTO user_info (email, first_name, last_name, status, loggedin_at)
VALUES
('abc@dummy.com','John 1','Doe','ACTIVE',now() AT TIME ZONE 'utc' - INTERVAL '1 hour'),
('def@dummy.com','John 2','Doe','ACTIVE',now() AT TIME ZONE 'utc' - INTERVAL '30 minutes'),
('ghi@dummy.com','John 3','Doe','ACTIVE',now() AT TIME ZONE 'utc' - INTERVAL '10 minutes'),
('jkl@dummy.com','John 4','Doe','ACTIVE',now() AT TIME ZONE 'utc');