CREATE TABLE multiq (
	multiq_id	INT,
	question 	VARCHAR(100),
	ans1		VARCHAR(50),
	ans2		VARCHAR(50),
	ans3		VARCHAR(50),
	ans4		VARCHAR(50),
	chosen_ans	VARCHAR(1),
	marks		VARCHAR(2),
	PRIMARY KEY(id));

CREATE TABLE shortq(
	shortq_id	INT,
	question	VARCHAR(200),
	ans			VARCHAR(500),
	marks		VARCHAR(2),
	PRIMARY KEY(id));
	
INSERT INTO multiq
VALUES (001, 'What is your favourite fruit?', 'Apple', 'Orange', 'Banana', 'None of Above', 'A', '2');
INSERT INTO multiq
VALUES (002, 'What is your favourite animal?', 'Cat', 'Dog', 'Pig', 'None of Above', 'B', '0');
INSERT INTO shortq
VALUES (001, 'Why you love the animal you chosen above?', 'Because I have a pet dog.', '15');


DROP TABLE users;

CREATE TABLE users(
   userId  SERIAL PRIMARY KEY,
   userName           VARCHAR(10)      NOT NULL,
   userPassword            VARCHAR(10)       NOT NULL,
   userType        VARCHAR(1),
   userNumber         VARCHAR(10)
);

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('HarryP', 'Abra', 'S', '12345');

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('HerG', 'Dobb', 'S', '12346');

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('RonW', 'Broken', 'S', '12347');

--SELECT * FROM users ORDER BY userId
--SELECT * FROM users WHERE userId=2