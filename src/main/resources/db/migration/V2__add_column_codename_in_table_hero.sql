ALTER TABLE hero ADD COLUMN codename VARCHAR(255) NOT NULL DEFAULT '';

CREATE INDEX idx_hero_codename ON hero(codename);

ALTER TABLE hero ADD CONSTRAINT unique_codename UNIQUE (codename);