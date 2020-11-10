DROP TABLE IF EXISTS PROMOTION;

CREATE TABLE PROMOTION (
  ID INTEGER AUTO_INCREMENT PRIMARY KEY,
  TYPE VARCHAR(250) NOT NULL,
  QUANTITY INTEGER NOT NULL,
  SKUS VARCHAR(250) DEFAULT NULL,
  DISCOUNTED_PRICE INT NOT NULL
);

INSERT INTO PROMOTION (TYPE, QUANTITY, SKUS, DISCOUNTED_PRICE) VALUES
  ('QUANTITY', 3, 'A', 130),
  ('QUANTITY', 2, 'B', 45),
  ('COMBINATION', 1, 'C,D', 30);

DROP TABLE IF EXISTS SKU;

CREATE TABLE SKU (
    SKU VARCHAR (10) NOT NULL,
    PRICE INTEGER   NOT NULL
);

INSERT INTO SKU(SKU, PRICE) VALUES
    ('A', 50),
    ('B', 30),
    ('C', 20),
    ('D', 15);