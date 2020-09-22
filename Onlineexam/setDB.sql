DROP TABLE IF EXISTS multipleQuestion cascade;
DROP TABLE IF EXISTS shortQuestion cascade;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS students cascade;
DROP TABLE IF EXISTS teachers cascade;
DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS subjects cascade;
DROP TABLE IF EXISTS exams;


CREATE TABLE teachers(
	teacherNumber VARCHAR(10) PRIMARY KEY,
	house VARCHAR(1),
	firstName VARCHAR(25),
	lastName VARCHAR(25),
	title VARCHAR(25)

);


CREATE TABLE subjects(
	subjectId VARCHAR(10) PRIMARY KEY,
	subjectName VARCHAR(100)
);


CREATE TABLE exams(
	examId VARCHAR(50) PRIMARY KEY,
	subjectId VARCHAR(10),
	examName VARCHAR(100),
	examCreator VARCHAR(10),
	year VARCHAR(10),
	semester VARCHAR(1),
	totalMarks smallint,
	CONSTRAINT fk_subjectId
		FOREIGN KEY(subjectId)
			REFERENCES subjects(subjectId)
			ON DELETE CASCADE,
	CONSTRAINT fk_examCreator
		FOREIGN KEY(examCreator)
			REFERENCES teachers(teacherNumber)
			ON DELETE CASCADE		
	
	
	);

CREATE TABLE multipleQuestion (
	questionId  SERIAL PRIMARY KEY,
	examId VARCHAR(50),
	questionText 	VARCHAR(500),
	ansA		VARCHAR(100),
	ansB		VARCHAR(100),
	ansC		VARCHAR(100),
	ansD		VARCHAR(100),
	correctAnswer	VARCHAR(1),
	possibleMarks smallint,
	answerNumber smallint,
	CONSTRAINT fk_examId
		FOREIGN KEY(examId)
			REFERENCES exams(examId)
			ON DELETE CASCADE);

CREATE TABLE shortQuestion(
	questionId  SERIAL PRIMARY KEY,
	examId VARCHAR(50),
	questionText 	VARCHAR(500),
	possibleMarks smallint,
	CONSTRAINT fk_examId
		FOREIGN KEY(examId)
			REFERENCES exams(examId)
			ON DELETE CASCADE);


INSERT INTO teachers
VALUES ('T12', 'S','Severus', 'Snape','Professor of Potions');


INSERT INTO subjects
VALUES ('DEF101', 'Defense against the Dark Arts');
INSERT INTO subjects
VALUES ('TRA101', 'Transfiguration');
INSERT INTO subjects
VALUES ('CHA101', 'Charms');
INSERT INTO subjects
VALUES ('POT101', 'Potions');
INSERT INTO subjects
VALUES ('HIS101', 'History of Magic');
INSERT INTO subjects
VALUES ('AST101', 'Astronomy');
INSERT INTO subjects
VALUES ('HER102', 'Herbology');
INSERT INTO subjects
VALUES ('HIS102', 'Muggle Studies');
INSERT INTO subjects
VALUES ('DIV101', 'Divination');			
			
	
INSERT INTO exams
VALUES ('HIS101EX2020SEM2', 'HIS101','History of Magic End of Semester Exam','T12','2020','2',70);

INSERT INTO exams
VALUES ('HIS101EX2020SEM2MID', 'HIS101','History of Magic End of Midsemester Exam','T12','2020','2',30);

			
INSERT INTO multipleQuestion (examId, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES ('HIS101EX2020SEM2','A Patronus is a kind of what?', 'Dementor', 'Anti-Dementor', 'Banana', 'None of Above', 'B', 2, 4);
INSERT INTO multipleQuestion (examId, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES ('HIS101EX2020SEM2','True or false, Thestles can be seen by those that have lost a loved one?', 'True', 'False', '', '', 'A', 2, 2);

INSERT INTO shortQuestion (examId, questionText, possibleMarks)
VALUES ('HIS101EX2020SEM2','Who killed Lord Voldemorte and why?', 10);


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



CREATE TABLE appointments(
	appointmentsId  SERIAL PRIMARY KEY,
	year VARCHAR(10),
	semester VARCHAR(1),
	subjectId VARCHAR(10),
	teacherNumber VARCHAR(10),
	CONSTRAINT fk_subjectId
		FOREIGN KEY(subjectId)
			REFERENCES subjects(subjectId)
			ON DELETE CASCADE,
	CONSTRAINT fk_teacherNumber
		FOREIGN KEY(teacherNumber)
			REFERENCES teachers(teacherNumber)
				ON DELETE CASCADE

);

CREATE TABLE enrollments(
	enrollmentsId  SERIAL PRIMARY KEY,
	year VARCHAR(10),
	semester VARCHAR(1),
	subjectId VARCHAR(10),
	studentNumber VARCHAR(10),
	CONSTRAINT fk_subjectId
		FOREIGN KEY(subjectId)
			REFERENCES subjects(subjectId)
			ON DELETE CASCADE,
	CONSTRAINT fk_studentNumber
		FOREIGN KEY(studentNumber)
			REFERENCES students(studentNumber)	
			ON DELETE CASCADE

);



INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('HarryP', 'Abra', 'S', 'S10');

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('HerG', 'Dobb', 'S', 'S11');

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('RonW', 'Broken', 'S', 'S12');

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('SSnape', 'ih8harry', 'T', 'T12');


INSERT INTO students
VALUES ('S10', 'G','Harry', 'Potter');

INSERT INTO students
VALUES ('S11', 'G','Hermione', 'Granger');

INSERT INTO students
VALUES ('S12', 'G','Ron', 'Weasley');

INSERT INTO appointments (year, semester, subjectId, teacherNumber)
VALUES ('2020', '2','DEF101', 'T12');

INSERT INTO appointments (year, semester, subjectId, teacherNumber)
VALUES ('2020', '2','POT101', 'T12');

INSERT INTO enrollments (year, semester, subjectId, studentNumber)
VALUES ('2020', '2','POT101', 'S10');

INSERT INTO enrollments (year, semester, subjectId, studentNumber)
VALUES ('2020', '2','POT101', 'S11');

INSERT INTO enrollments (year, semester, subjectId, studentNumber)
VALUES ('2020', '2','POT101', 'S12');

INSERT INTO enrollments (year, semester, subjectId, studentNumber)
VALUES ('2020', '2','DEF101', 'S10');
INSERT INTO enrollments (year, semester, subjectId, studentNumber)
VALUES ('2020', '2','DEF101', 'S11');
INSERT INTO enrollments (year, semester, subjectId, studentNumber)
VALUES ('2020', '2','DEF101', 'S12');







--SELECT * FROM users ORDER BY userId
--SELECT * FROM users WHERE userId=2

--SELECT * FROM students WHERE studentnumber='S12346';
--SELECT * FROM users WHERE usernumber='S12346';

--SELECT * FROM students JOIN users ON usernumber=studentnumber WHERE studentnumber='S12346';