CREATE TABLE IF NOT EXISTS users (
    user_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(255),
    user_password VARCHAR(255),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
    );
