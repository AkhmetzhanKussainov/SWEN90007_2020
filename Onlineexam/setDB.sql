DROP TABLE IF EXISTS multipleQuestion cascade;
DROP TABLE IF EXISTS shortQuestion cascade;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS students cascade;
DROP TABLE IF EXISTS teachers cascade;
DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS subjects cascade;
DROP TABLE IF EXISTS exams cascade;
DROP TABLE IF EXISTS scriptbooks cascade;
DROP TABLE IF EXISTS multipleAttempt cascade;
DROP TABLE IF EXISTS shortAttempt cascade;
DROP TABLE IF EXISTS teacher_subject cascade;
DROP TABLE IF EXISTS student_subject cascade;

CREATE TABLE users(
   userId  SERIAL PRIMARY KEY,
   userName           VARCHAR(10)      NOT NULL,
   userPassword            VARCHAR(10)       NOT NULL,
   userType        VARCHAR(1),
   userNumber         VARCHAR(10)
   
);

CREATE TABLE students(
	studentNumber VARCHAR(10) PRIMARY KEY UNIQUE,
	house VARCHAR(1),
	firstName VARCHAR(25),
	lastName VARCHAR(25)

);


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
	
	subjectId VARCHAR(10),
	year VARCHAR(10),
	semester VARCHAR(1),
	examType VARCHAR(1),
	examName VARCHAR(100),
	examCreator VARCHAR(10),
	published VARCHAR(1),
	closed VARCHAR(1),
	
	totalMarks smallint,
	
	--startTime VARCHAR(50),
	--endTime VARCHAR(50),
	
	--startTime time,
	--endTime time,
	
	PRIMARY KEY(subjectId,year,semester,examType), 
	CONSTRAINT fk_subjectId
		FOREIGN KEY(subjectId)
			REFERENCES subjects(subjectId)
			ON DELETE CASCADE,
	CONSTRAINT fk_examCreator
		FOREIGN KEY(examCreator)
			REFERENCES teachers(teacherNumber)
			ON DELETE CASCADE		
	
	
	);
	
CREATE TABLE scriptbooks(
	
	subjectId VARCHAR(10),
	year VARCHAR(10),
	semester VARCHAR(1),
	examType VARCHAR(1),
	submitted boolean,
	
	studentNumber VARCHAR(10),
	
	scriptTotalMarks smallint,
	marked boolean,
	
	
	PRIMARY KEY(subjectId,year,semester,examType,studentNumber),
	CONSTRAINT fk_examId
		FOREIGN KEY(subjectId,year,semester,examType)
			REFERENCES exams(subjectId,year,semester,examType)
			ON DELETE CASCADE,
	
	CONSTRAINT fk_studentNumber
		FOREIGN KEY(studentNumber)
			REFERENCES students(studentNumber)	
			ON DELETE CASCADE		
			
	);
	
	
CREATE TABLE multipleQuestion (
		--questionId  SERIAL PRIMARY KEY,
		questionId  smallint,
		subjectId VARCHAR(10),
		year VARCHAR(10),
		semester VARCHAR(1),
		examType VARCHAR(1),
		questionText 	VARCHAR(500),
		ansA		VARCHAR(100),
		ansB		VARCHAR(100),
		ansC		VARCHAR(100),
		ansD		VARCHAR(100),
		correctAnswer	VARCHAR(1),
		possibleMarks smallint,
		answerNumber smallint,
		
		PRIMARY KEY (subjectId,year,semester,examType, questionId), 
		
		CONSTRAINT fk_examId
			FOREIGN KEY(subjectId,year,semester,examType)
				REFERENCES exams(subjectId,year,semester,examType)
				ON DELETE CASCADE);

CREATE TABLE shortQuestion(
		--questionId  SERIAL PRIMARY KEY,
		questionId  smallint,
		subjectId VARCHAR(10),
		year VARCHAR(10),
		semester VARCHAR(1),
		examType VARCHAR(1),
		questionText 	VARCHAR(500),
		possibleMarks smallint,
		
		PRIMARY KEY (subjectId,year,semester,examType, questionId),
		
		CONSTRAINT fk_examId
			FOREIGN KEY(subjectId,year,semester,examType)
				REFERENCES exams(subjectId,year,semester,examType)
				ON DELETE CASCADE);
	
CREATE TABLE multipleAttempt (
	
	questionId  smallint,
	subjectId VARCHAR(10),
	year VARCHAR(10),
	semester VARCHAR(1),
	examType VARCHAR(1),
	studentNumber VARCHAR(10) REFERENCES students(studentNumber),
	attemptAns 	VARCHAR(1),	
	mark smallint,
	marked boolean,
	PRIMARY KEY (subjectId,year,semester,examType, questionId, studentNumber)			
			);
			
CREATE TABLE shortAttempt (
	questionId  smallint,
	subjectId VARCHAR(10),
	year VARCHAR(10),
	semester VARCHAR(1),
	examType VARCHAR(1),
	studentNumber VARCHAR(10) REFERENCES students(studentNumber),
	attemptAns 	VARCHAR(500),
	
	mark smallint,
	marked boolean,
	PRIMARY KEY (subjectId,year,semester,examType, questionId, studentNumber)

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

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('DracoM', 'ih8harry2', 'S', 'S13');


INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('Dobby', 'iluvharry', 'A', 'A1');

INSERT INTO students
VALUES ('S10', 'G','Harry', 'Potter');

INSERT INTO students
VALUES ('S11', 'G','Hermione', 'Granger');

INSERT INTO students
VALUES ('S12', 'G','Ron', 'Weasley');


INSERT INTO students
VALUES ('S13', 'S','Draco', 'Malfoy');	
	




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
VALUES ('HIS101', '2020','2','F','History of Magic End of Semester Exam','T12','N', 70);

INSERT INTO exams
VALUES ('HIS101', '2020','2','M','History of Magic End of Midsemester Exam','T12','Y', 30);

INSERT INTO exams
VALUES ('DEF101', '2020','2','F','Defense Against the Dark Arts End of Magic End of Semester Exam','T12','Y', 30);

INSERT INTO scriptbooks
VALUES ('HIS101', '2020','2','F','S12',0,false);

INSERT INTO scriptbooks
VALUES ('HIS101', '2020','2','F','S13',60,true);

INSERT INTO multipleAttempt
VALUES (1,'HIS101', '2020','2','F','S12','A',0,false);

INSERT INTO shortAttempt
VALUES (1,'HIS101', '2020','2','F','S12','I killed Lord Voldemort because I''m a boss',4,true);

INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (1,'HIS101', '2020','2','F','A Patronus is a kind of what?', 'Dementor', 'Anti-Dementor', 'Banana', 'None of Above', 'B', 2, 4);
INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (2,'HIS101', '2020','2','F','True or false, Thestles can be seen by those that have lost a loved one?', 'True', 'False', '', '', 'A', 2, 2);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (1,'HIS101', '2020','2','F','Who killed Lord Voldemorte and why?', 10);


INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (1,'DEF101', '2020','2','F','A Boggart will appear in what form?', 'Your worst fear', 'Your worst enemy', 'Banana', 'None of Above', 'A', 2, 4);
INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (2,'DEF101', '2020','2','F','True or false, AbraCadavre can only be used by Dark Wizards?', 'True', 'False', '', '', 'B', 2, 2);










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