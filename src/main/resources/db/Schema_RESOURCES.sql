-- USE RESOURCES;
-- GO
-- 
-- DROP TABLE Book;
-- DROP TABLE Student;
-- DROP TABLE Student_books;

CREATE TABLE Book (
	id            INT PRIMARY KEY AUTO_INCREMENT,
	title         VARCHAR(512) NOT NULL,
	author	 VARCHAR(512) NOT NULL,
	edition	 VARCHAR(512) NOT NULL,
	year_of_publication	INT NOT NULL,
	available	INT DEFAULT 1,
	date_of_give	 VARCHAR(512),
	date_of_take	 VARCHAR(512)
);
CREATE TABLE Student (
	id                    INT PRIMARY KEY AUTO_INCREMENT,
	name                   VARCHAR(512) NOT NULL,
	surname               VARCHAR(512) NOT NULL,
	middle_name           VARCHAR(512) NOT NULL,
	passport_data         VARCHAR(512) NOT NULL UNIQUE,
	registration_date	 VARCHAR(512) NOT NULL,
	blacklist             INT DEFAULT 0,
	blacklist_date	 VARCHAR(512)
);

CREATE TABLE Student_books (
	id            INT PRIMARY KEY AUTO_INCREMENT,
	student_id	INT NOT NULL,
	book_id	INT NOT NULL UNIQUE,
	FOREIGN KEY(student_id) REFERENCES Student(id),-- ON DELETE RESTRICT,
	FOREIGN KEY(book_id) REFERENCES Book(id)-- ON DELETE RESTRICT
);

CREATE TABLE Info (
	id            INT PRIMARY KEY AUTO_INCREMENT,
	info         VARCHAR(512) NOT NULL
);


INSERT INTO Book VALUES (1,'sqlitebrowser','https://sqlitebrowser.org/dl/','https://sqlitebrowser.org/dl/',2016,1,'','');
INSERT INTO Book VALUES (2,'urait','https://urait.ru/book/bazy-dannyh-489693',2022,1,'','');
INSERT INTO Book VALUES (3,'Java 8','Java','https://www.java.com/en/download/help/java8.html',2015,0,'15-03-2022','22-03-2022');
INSERT INTO Book VALUES (4,'Title','Author','Edition',1111,0,'15-03-2022','22-03-2022');
INSERT INTO Book VALUES (22,'book','author','coordinats',2020,1,'','');
INSERT INTO Student VALUES (1,'to simplify search of sources','LIBRARY','libra','university project','15-03-2022',0,'');
INSERT INTO Student VALUES (2,'work project','image tokens','img','.sol + .js','09-02-2022',0,'');
INSERT INTO Student VALUES (3,'purpose of project','full name','pet-name','data about project','01-01-2022',0,'');
INSERT INTO Student VALUES (19,'for_what','name','nickname','info','15-03-2022',0,'');
INSERT INTO Student_books VALUES (33,2,4);
INSERT INTO Student_books VALUES (34,1,3);

INSERT INTO Info VALUES (1,'some info');
INSERT INTO Info VALUES (2,'also info');

COMMIT;
