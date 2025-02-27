CREATE TYPE difficulty_enum AS ENUM ('EASY', 'MEDIUM', 'HARD');

CREATE TABLE IF NOT EXISTS recipe
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(255)    NOT NULL,
    image_url  VARCHAR(255)    NOT NULL,
    difficulty difficulty_enum NOT NULL
);

CREATE TABLE IF NOT EXISTS trending_recipe
(
    id        BIGSERIAL PRIMARY KEY,
    recipe_id BIGINT  NOT NULL,
    position  INTEGER NOT NULL,
    CONSTRAINT fk_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (id)
);
