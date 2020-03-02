DROP TABLE IF EXISTS plays;

CREATE TABLE plays (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  type VARCHAR(250) NOT NULL
);

INSERT INTO plays (name, type) VALUES
  ('Hamlet', 'tragedy'),
  ('As You Like It', 'comedy'),
  ('Othello', 'tragedy');