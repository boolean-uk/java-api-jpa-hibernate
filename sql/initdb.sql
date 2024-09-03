CREATE DATABASE java_api_jpa_hibernate;

\c java_api_jpa_hibernate;

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  email TEXT,
  first_name TEXT,
  is_active BOOLEAN,

  UNIQUE(email)
);
