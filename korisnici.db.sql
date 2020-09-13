BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "leaves" (
	"id"	INTEGER,
	"employee_id"	INTEGER,
	"from_date"	TEXT,
	"to_date"	TEXT,
	"reason"	TEXT,
	"state"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "users" (
	"username"	TEXT,
	"password"	TEXT,
	"privilege"	TEXT
);
CREATE TABLE IF NOT EXISTS "jobs" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"job_title"	TEXT NOT NULL,
	"min_salary"	REAL NOT NULL,
	"max_salary"	REAL NOT NULL
);
CREATE TABLE IF NOT EXISTS "employees" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"first_name"	TEXT NOT NULL,
	"last_name"	TEXT NOT NULL,
	"email"	TEXT NOT NULL UNIQUE,
	"phone_number"	TEXT NOT NULL,
	"hire_date"	TEXT NOT NULL,
	"job_id"	TEXT NOT NULL,
	"salary"	REAL NOT NULL,
	"commission_pct"	REAL,
	"department_id"	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS "departments" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"department_name"	TEXT NOT NULL,
	"manager_id"	INTEGER NOT NULL,
	"street_adress"	TEXT,
	"postal_code"	INTEGER,
	"city"	TEXT
);
INSERT INTO "leaves" VALUES (0,NULL,'2020-09-10','2020-09-17','jjjjjjjjjjj','pending');
INSERT INTO "users" VALUES ('u','u','Admin');
INSERT INTO "users" VALUES ('eabel','e','Employee');
INSERT INTO "users" VALUES ('ado','a','Employee');
INSERT INTO "users" VALUES ('jwhalen','j','Employee');
INSERT INTO "jobs" VALUES (1,'President',2085.0,40000.0);
INSERT INTO "jobs" VALUES (2,'Stock Manager',5500.0,8500.0);
INSERT INTO "jobs" VALUES (3,'Stock Cler',2008.0,5000.0);
INSERT INTO "jobs" VALUES (4,'Administration Assistant',3000.0,6000.0);
INSERT INTO "employees" VALUES (121,'Adam','Do','ado@fa.ke','333444555','2003-04-02','2',8205.0,0.0,50);
INSERT INTO "employees" VALUES (174,'Ellen','Abel','eabel@fa.ke','6505079833','2007-06-21','3',2600.0,NULL,50);
INSERT INTO "employees" VALUES (200,'Jennifer','Whalen','jwhalen@fa.ke','5151234444','2006-03-22','4',4400.0,0.0,10);
INSERT INTO "departments" VALUES (10,'Administration',121,' 2004 Charade Rd',98199,'Seattle');
INSERT INTO "departments" VALUES (50,'Shipping',121,'2011 Interiors Blvd',99236,'Sna Francisco');
COMMIT;
