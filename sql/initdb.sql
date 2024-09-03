CREATE DATABASE java_api_jpa_hibernate;

\c java_api_jpa_hibernate;

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  email TEXT,
  first_name TEXT,
  is_active BOOLEAN,

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
