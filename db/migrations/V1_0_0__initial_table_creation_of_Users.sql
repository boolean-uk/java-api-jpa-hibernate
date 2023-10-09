CREATE TABLE IF NOT EXISTS Users (
    user_id SERIAL PRIMARY KEY,
    email TEXT,
    first_name TEXT,
    last_name TEXT,
    username TEXT,
    phone INTEGER

);
