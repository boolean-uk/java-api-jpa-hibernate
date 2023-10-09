CREATE TABLE IF NOT EXISTS games (
    games_id SERIAL PRIMARY KEY,
    title TEXT,
    genre TEXT,
    publisher TEXT,
    developer TEXT,
    release_year INTEGER,
    is_early_access BOOLEAN

);
