DROP TABLE IF EXISTS user_info;

CREATE TABLE IF NOT EXISTS user_info (
    user_id BIGSERIAL,
    email VARCHAR(90),
    first_name VARCHAR(90),
    last_name VARCHAR(90),
    status VARCHAR(20),
    logged_in_at TIMESTAMP,
    CONSTRAINT user_pk PRIMARY KEY(user_id)
);