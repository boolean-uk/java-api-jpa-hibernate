CREATE DATABASE java_api_jpa_hibernate;

\c java_api_jpa_hibernate;

DROP TABLE users;
DROP TABLE games;

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  email TEXT,
  first_name TEXT,
  last_name TEXT,
  username TEXT,
  phone TEXT,

  UNIQUE(email)
);

CREATE TABLE games(
  id SERIAL PRIMARY KEY,
  title TEXT,
  genre TEXT,
  publisher TEXT,
  developer TEXT,
  release_year INTEGER,
  is_early_access BOOLEAN
);
