CREATE TABLE IF NOT EXISTS countries (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cities (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    population INT NOT NULL,
    country_id UUID NOT NULL,
    FOREIGN KEY (country_id) REFERENCES countries (id) ON DELETE CASCADE
);
