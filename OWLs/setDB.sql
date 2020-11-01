DROP TABLE IF EXISTS multipleQuestion cascade;
DROP TABLE IF EXISTS shortQuestion cascade;
DROP TABLE IF EXISTS users cascade;
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
DROP TABLE IF EXISTS administrators cascade;
DROP TABLE IF EXISTS headmasters cascade;
DROP TABLE IF EXISTS locks cascade;

CREATE TABLE locks(
	id SERIAL PRIMARY KEY,
	objectID  VARCHAR(100),
	sessionID VARCHAR(100),
	lockType VARCHAR(20)

);


CREATE TABLE users(
   userId  SERIAL PRIMARY KEY,
   userName           VARCHAR(10)      NOT NULL,
   userPassword            VARCHAR(10)       NOT NULL,
   userType        VARCHAR(1),
   userNumber         VARCHAR(10) UNIQUE
   
);

CREATE TABLE students(
	studentNumber VARCHAR(10) PRIMARY KEY UNIQUE,
	house VARCHAR(1),
	firstName VARCHAR(25),
	lastName VARCHAR(25),
	
	CONSTRAINT fk_studentId
		FOREIGN KEY(studentNumber)
			REFERENCES users(userNumber)
			ON DELETE CASCADE

);


CREATE TABLE teachers(
	teacherNumber VARCHAR(10) PRIMARY KEY,
	house VARCHAR(1),
	firstName VARCHAR(25),
	lastName VARCHAR(25),
	title VARCHAR(100),
	
	CONSTRAINT fk_teacherId
		FOREIGN KEY(teacherNumber)
			REFERENCES users(userNumber)
			ON DELETE CASCADE

);

CREATE TABLE administrators(
	adminNumber VARCHAR(10) PRIMARY KEY,
	
	CONSTRAINT fk_adminId
		FOREIGN KEY(adminNumber)
			REFERENCES users(userNumber)
			ON DELETE CASCADE

);

CREATE TABLE headmasters(
	adminNumber VARCHAR(10) PRIMARY KEY,
	
	CONSTRAINT fk_adminId
		FOREIGN KEY(adminNumber)
			REFERENCES users(userNumber)
			ON DELETE CASCADE

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
	
	startTime VARCHAR(100)  Null,
	endTime VARCHAR(100) Null,
	
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
	version smallint,
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
	version smallint,
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
VALUES ('MM', 'cat', 'T', 'T11');


INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('DracoM', 'ih8harry2', 'S', 'S13');


INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('Dobby', 'iluvharry', 'A', 'A1');

INSERT INTO users (userName, userPassword, userType, userNumber)
VALUES ('Dumb', 'Light', 'H', 'A2');

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

INSERT INTO teachers
VALUES ('T11', 'G','Minerva', 'McGonagall','Professor of Transfiguration');
 
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
VALUES ('HIS101', '2020','2','F','History of Magic End of Semester Exam','T12','Y', 'N', 70, '1','2');

INSERT INTO exams
VALUES ('HIS101', '2020','2','M','History of Magic Midsemester Exam','T12','Y', 'N', 30, '1','2');

INSERT INTO exams
VALUES ('DEF101', '2020','2','F','Defense Against the Dark Arts End of Magic End of Semester Exam','T12','Y', 'N', 30, '1','2');

--Exam type is col 4, submitted then marked
INSERT INTO scriptbooks
VALUES ('HIS101', '2020','2','F',true,'S12',0,false);

--last boolean indicates whether its been marked (ignore), second last is mark
INSERT INTO multipleAttempt
VALUES (1,'HIS101', '2020','2','F','S12','A',0,false,1);

--last boolean indicates whether its been marked LAST IS VERSION
INSERT INTO multipleAttempt
VALUES (2,'HIS101', '2020','2','F','S12','B',1,false,2);

--last boolean indicates whether its been marked
INSERT INTO multipleAttempt
VALUES (3,'HIS101', '2020','2','F','S12','C',3,false,7);

--last boolean indicates whether its been marked LAST IS VERSION
INSERT INTO shortAttempt
VALUES (1,'HIS101', '2020','2','F','S12','I killed Lord Voldemort',0,true,1);

INSERT INTO shortAttempt
VALUES (2,'HIS101', '2020','2','F','S12','Ive done it and it is weird',1,true,1);

INSERT INTO shortAttempt
VALUES (3,'HIS101', '2020','2','F','S12','Dodgy shops',2,true,9);

--Scriptbook 2
INSERT INTO scriptbooks
VALUES ('HIS101', '2020','2','F',true,'S13',60,false);

--last boolean indicates whether its been marked (ignore), second last is mark
INSERT INTO multipleAttempt
VALUES (1,'HIS101', '2020','2','F','S13','C',0,false, 1);

--last boolean indicates whether its been marked
INSERT INTO multipleAttempt
VALUES (2,'HIS101', '2020','2','F','S13','C',1,false, 1);

--last boolean indicates whether its been marked
INSERT INTO multipleAttempt
VALUES (3,'HIS101', '2020','2','F','S13','C',3,false, 1);

--last boolean indicates whether its been marked
INSERT INTO shortAttempt
VALUES (1,'HIS101', '2020','2','F','S13','Harry Potter killed Lord Voldemort',0,true, 1);

INSERT INTO shortAttempt
VALUES (2,'HIS101', '2020','2','F','S13','Time Travel is impossible',1,true, 1);

INSERT INTO shortAttempt
VALUES (3,'HIS101', '2020','2','F','S13','All manner of unscrupulous wizards and witches',2,true, 1);


--HIS101 MIDSEMESTER

--possibleMarks, answerNumber
INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (1,'HIS101', '2020','2','M','What is the purpose of the sorting hat?', 'Sort students', 'Kill students', 'Keep head warm', 'None of Above', 'A', 2, 4);

INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (2,'HIS101', '2020','2','M','In Quidditch, how many points is the Snitch worth??', '50', '100', '150', '200', 'C', 2, 4);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (1,'HIS101', '2020','2','M','Which house is associated with Snakes?', 10);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (2,'HIS101', '2020','2','M','How many wizards founded Hogwarts and who are they?', 10);



--HIS101 FINAL

INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (1,'HIS101', '2020','2','F','A Patronus is a kind of what?', 'Dementor', 'Anti-Dementor', 'Banana', 'None of Above', 'B', 2, 4);

INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (2,'HIS101', '2020','2','F','Thestles can be seen by whom?', 'Syltherin', 'Dementors', 'Those that have lost loved one', 'Teachers', 'A', 2, 4);

INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (3,'HIS101', '2020','2','F','What kind of plant screams?', 'Oak tree', 'Mandrake plant', 'Griffin', 'Potato', 'B', 2, 4);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (1,'HIS101', '2020','2','F','Who killed Lord Voldemorte and why?', 10);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (2,'HIS101', '2020','2','F','Is time travel possible and what are the dangers?', 10);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (3,'HIS101', '2020','2','F','What kind of shops can be found on Diagon Alley?', 10);



--DEF101 FINAL

INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (1,'DEF101', '2020','2','F','A Boggart will appear in what form?', 'Your worst fear', 'Your worst enemy', 'Banana', 'None of Above', 'A', 2, 4);

INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (2,'DEF101', '2020','2','F','AbraCadavre is used by which kind of Wizards?', 'Elves', 'Dark Wizards', 'Griffins', 'Muggles', 'B', 2, 4);

INSERT INTO multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)
VALUES (3,'DEF101', '2020','2','F','Unforgivable curses should be used by', 'Wizards', 'Muggles', 'Students', 'None of the above', 'D', 2, 4);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (1,'DEF101', '2020','2','F','How many Unforgivable Curses are there and what are they?', 10);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (2,'DEF101', '2020','2','F','What do you think of Gilderoy Lockharts latest book?', 10);

INSERT INTO shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)
VALUES (3,'DEF101', '2020','2','F','You walk down Diagon alley and come across an aggresive troll. What spell do you use?', 10);

	







INSERT INTO appointments (year, semester, subjectId, teacherNumber)
VALUES ('2020', '2','DEF101', 'T12');

INSERT INTO appointments (year, semester, subjectId, teacherNumber)
VALUES ('2020', '2','POT101', 'T12');

INSERT INTO appointments (year, semester, subjectId, teacherNumber)
VALUES ('2020', '2','HIS101', 'T12');


INSERT INTO appointments (year, semester, subjectId, teacherNumber)
VALUES ('2020', '2','DEF101', 'T11');

INSERT INTO appointments (year, semester, subjectId, teacherNumber)
VALUES ('2020', '2','POT101', 'T11');

INSERT INTO appointments (year, semester, subjectId, teacherNumber)
VALUES ('2020', '2','HIS101', 'T11');

INSERT INTO enrollments (year, semester, subjectId, studentNumber)
VALUES ('2020', '2','HIS101', 'S10');

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