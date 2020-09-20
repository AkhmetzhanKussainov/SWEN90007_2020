DROP TABLE multiq;
DROP TABLE shortq;

CREATE TABLE multiq (
	id	INT,
	question 	VARCHAR(100),
	ans1		VARCHAR(50),
	ans2		VARCHAR(50),
	ans3		VARCHAR(50),
	ans4		VARCHAR(50),
	chosen_ans	VARCHAR(1),
	marks		VARCHAR(2),
	PRIMARY KEY(id));

CREATE TABLE shortq(
	id	INT,
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
DROP TABLE students;

CREATE TABLE users(
   userId  SERIAL PRIMARY KEY,
   userName           VARCHAR(10)      NOT NULL,
   userPassword            VARCHAR(10)       NOT NULL,
   userType        VARCHAR(1),
   userNumber         VARCHAR(10)
);

CREATE TABLE students(
	studentNumber VARCHAR(10) PRIMARY KEY,
	house VARCHAR(1),
	firstName VARCHAR(25),
	lastName VARCHAR(25)

);


INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('HarryP', 'Abra', 'S', 'S12345');

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('HerG', 'Dobb', 'S', 'S12346');

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('RonW', 'Broken', 'S', 'S12347');

INSERT INTO students
VALUES ('S12345', 'G','Harry', 'Potter');

INSERT INTO students
VALUES ('S12346', 'G','Hermione', 'Granger');

INSERT INTO students
VALUES ('S12347', 'G','Ron', 'Weasley');


--SELECT * FROM users ORDER BY userId
--SELECT * FROM users WHERE userId=2

--SELECT * FROM students WHERE studentnumber='S12346';
--SELECT * FROM users WHERE usernumber='S12346';

--SELECT * FROM students JOIN users ON usernumber=studentnumber WHERE studentnumber='S12346';