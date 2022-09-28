CREATE TABLE students(
	studentID serial PRIMARY KEY UNIQUE,
	firstname varchar(15) NOT NULL,
	lastname varchar(15) NOT NULL,
	email varchar(25) UNIQUE,
	phonenumber int(10)
);

CREATE TABLE teachers(
	teacherID serial PRIMARY KEY UNIQUE,
	firstname varchar(15) NOT NULL,
	lastname varchar(15),
	email varchar(25) UNIQUE,
	phonenumber int(10)
);

CREATE TABLE courses(
	courseID serial UNIQUE,
	title varchar(15) PRIMARY KEY UNIQUE,
	instructor int REFERENCES teachers	
);

CREATE TABLE topics(
	topicID serial UNIQUE,
	description varchar(225) PRIMARY KEY UNIQUE
);

CREATE TABLE student_courses(
	course varchar(15) REFERENCES courses,
	student int REFERENCES students
);

CREATE TABLE teacher_course(
	instructor int REFERENCES teachers UNIQUE,
	course varchar(15) REFERENCES courses
);
-- above didn't work when I tried to reference the teacher's last name instead of id, teacher's lastname needed to be unique

CREATE TABLE topic_course(
	topicname varchar(225) REFERENCES topics,
	id int REFERENCES courses(courseID) UNIQUE
);

INSERT INTO students (firstname, lastname, email, phonenumber) 
VALUES 
	('Billy', 'Batson', 'notcptmarvel@yahoo.net', 923),
	('Jason', 'Todd', 'phoenixking@gmail.com', 778),
	('Richard', 'Grayson', 'notabird@me.net', 453),
	('Jonathon', 'Kent', 'stuckbtwnw0rlds@ymail.net', 972),
	('Timothy', 'Drake', 'xX_duckboi_Xx@proton.net', 152);
	
INSERT INTO teachers (firstname, lastname, email, phonenumber)
VALUES 
	('Pamela', 'Isley', 'drisley@gh.com', 453),
	('Julian', 'Day', 'holidays@gh.com', 861),
	('Richard', 'Thompson', 'actuary@gh.com', 512);
	
INSERT INTO courses (title, instructor)
VALUES 
	('Biology', 1),
	('AmericanDev', 2),
	('Algebra', 3);

INSERT INTO student_courses
VALUES 
	('Biology', 1),
	('Biology', 2),
	('Algebra', 1),
	('Algebra', 3),
	('AmericanDev', 3),
	('AmericanDev', 4),
	('AmericanDev', 5);

INSERT INTO teacher_course
VALUES
	(1, 'Biology'),
	(2, 'AmericanDev'),
	(3, 'Algebra');
	
INSERT INTO topics (description)
VALUES
	('Science'),
	('History'),
	('Math');

INSERT INTO topic_course 
VALUES 
	('Science', 1),
	('History', 2),
	('Math', 3);
	
