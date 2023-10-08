DROP TABLE user_info;

CREATE TABLE user_info (
    user_id BIGSERIAL,
    email VARCHAR(90),
    first_name VARCHAR(90),
    last_name VARCHAR(90),
    status VARCHAR(20),
    loggedin_at TIMESTAMP,
    CONSTRAINT user_pk PRIMARY KEY(user_id)
);