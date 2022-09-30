--Requirements:
-- All: 
-- create new login and have it added to database 
-- able to login with existing info
-- submit reimbursement request (for managers have it sent to senior manager)
-- view completed requests
-- Manager specific:
-- view pending requests
-- approve/deny existing requests

-- Goals:
-- change password function on login and in main screen
-- have log in screen show current time 

-- Extras:
-- have set finance manager be Julius Caeser (make him senior manager if possible)
-- current employees will be his assassins, each with outrageous requests that have been denied
-- except Brutus, who will have a few reasonable requests, mix of approved and denied
-- a few existing pending requests

-- employee list
-- Julius Caeser, finance manager
-- Gaius Cassius Longinus, employee
-- Marcus Brutus, employee
-- Decimus Brutus Albinus, employee
-- Gaius Trebonius, employee



CREATE TABLE employeedatabase (
	employeeID serial UNIQUE, --sets id as primary identifer and is unique
	employeeFirstname varchar(15), --allows for long first names
	employeeLastname varchar(15), --see above
	employeeUsername char(8) PRIMARY KEY, --ensures each login is unique and can't be accidentally copied
	employeePassword varchar(20), --allows for more secure passwords
	Manager bool DEFAULT FALSE --automatically sets manager status as false unless otherwise indicated
);


CREATE TABLE reimbursementrequests (
	reimbursementID int PRIMARY KEY,
	employeeUsername varchar(8) REFERENCES employeedatabase,
	reimbursementTitle varchar (10),
	reimbursmentAmount int,
	"location" varchar(25),
	ApprovalStatus bool DEFAULT NULL
);

--CREATE TABLE completedrequests (
--	reimbursementID int REFERENCES reimbursementrequests,
--	employeeUsername varchar(8) REFERENCES employeedatabase,
--	reimbursementTitle varchar (10),
--	ApprovalStatus bool DEFAULT FALSE
--);
-- I'm debating on keeping the two separate tables, one for pending and one for approved.
-- my thought is that it may be easier coding wise if I'm referencing two TABLES 
-- but it may end up easier to keep it as one and make it an if statement

INSERT INTO employeedatabase(employeeFirstname, employeeLastname, employeeUsername, employeePassword, Manager) 
VALUES 
	('Julius', 'Caeser', 'jucae875', 'R0ma!naEternuM', TRUE), 
	('Gaius', 'Longinus', 'gaica931', 'd3@th2tyr@nt$', FALSE), 
	('Marcus', 'Brutus', 'marbr643', 'enterpassword', FALSE), --NOTE: CREATE passwords based ON people themselves
	('Decimus', 'Albinus', 'decal414', '@nt0ny$Uckz', FALSE),
	('Gaius', 'Trebonius', 'gaitr100', 'enterpassword', FALSE);


-- employee search function, shows completed tickets
SELECT e.employeeFirstname, r.reimbursementTitle, r.ApprovalStatus
FROM employeedatabase e
JOIN reimbursementrequests r 
ON e.employeeUsername = r.employeeUsername
WHERE e.employeeUsername = 'javacodeconnection'; -- goal IS TO have code that runs this JOIN WHEN they INPUT their username so the employee can see ALL completed requests AND managers can see ALL, regardless OF status

-- manager search function, filters by approval status
SELECT e.employeeFirstname, r.reinmbursementTitle, r.ApprovalStatus
FROM employeedatabase e 
JOIN reimbursementrequests r ON e.employeeUsername = r.employeeUsername 
WHERE r.ApprovalStatus = 'javacodeconnection';