CREATE DATABASE java_api_jpa_hibernate;

\c java_api_jpa_hibernate;

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  email TEXT,
  firstName TEXT,
  isActive BOOLEAN,

  UNIQUE(email)
);
