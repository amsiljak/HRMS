BEGIN TRANSACTION;
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
CREATE TABLE IF NOT EXISTS "users" (
	"username"	TEXT,
	"password"	TEXT
);
INSERT INTO "jobs" VALUES (1,'President',2085.0,40000.0);
INSERT INTO "jobs" VALUES (2,'Stock Manager',5500.0,8500.0);
INSERT INTO "jobs" VALUES (3,'Stock Clerk',2008.0,5000.0);
INSERT INTO "jobs" VALUES (4,'Administration Assistant',3000.0,6000.0);
INSERT INTO "employees" VALUES (-1,'nu','u','nduaw','99','dauh','3',99.0,99.0,10);
INSERT INTO "employees" VALUES (121,'Adam','Do','ado@fa.ke','333444555','03.04.2003','2',8205.0,0.0,50);
INSERT INTO "employees" VALUES (174,'Ellen','Abel','eabel@fa.ke','6505079833','21.06.2007.','3',2600.0,NULL,50);
INSERT INTO "employees" VALUES (200,'Jennifer','Whalen','jwhalen@fa.ke','5151234444','22.03.2006.','4',4400.0,NULL,10);
INSERT INTO "departments" VALUES (10,'Administration',121,' 2004 Charade Rd',98199,'Seattle');
INSERT INTO "departments" VALUES (50,'Shipping',121,'2011 Interiors Blvd',99236,'Sna Francisco');
INSERT INTO "users" VALUES ('u','u');
COMMIT;
