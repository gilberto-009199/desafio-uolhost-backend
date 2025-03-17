CREATE TABLE hero (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    grupo VARCHAR(255)
);

CREATE INDEX idx_hero_email ON hero(email);

ALTER TABLE hero ADD CONSTRAINT unique_email UNIQUE (email);