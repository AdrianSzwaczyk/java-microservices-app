CREATE TABLE IF NOT EXISTS countries (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    population INT NOT NULL
);