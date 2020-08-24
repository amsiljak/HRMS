BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "jobs" (
	"id"	TEXT,
	"job_title"	TEXT NOT NULL,
	"min_salary"	REAL NOT NULL,
	"max_salary"	REAL NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "departments" (
	"id"	INTEGER,
	"department_name"	TEXT NOT NULL,
	"manager_id"	INTEGER NOT NULL,
	"street_adress"	TEXT,
	"postal_code"	INTEGER,
	"city"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "employees" (
	"id"	INTEGER NOT NULL,
	"first_name"	TEXT NOT NULL,
	"last_name"	TEXT NOT NULL,
	"email"	TEXT NOT NULL UNIQUE,
	"phone_number"	TEXT NOT NULL,
	"hire_date"	TEXT NOT NULL,
	"job_id"	INTEGER NOT NULL,
	"salary"	REAL NOT NULL,
	"commission_pct"	REAL,
	"department_id"	INTEGER NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "users" (
	"username"	TEXT,
	"password"	TEXT
);
INSERT INTO "jobs" VALUES ('AD_PRES','President',2080.0,40000.0);
INSERT INTO "jobs" VALUES ('ST_MAN','Stock Manager',5500.0,8500.0);
INSERT INTO "jobs" VALUES ('SH_CLERK','Stock Clerk',2008.0,5000.0);
INSERT INTO "jobs" VALUES ('AD_ASST','Administration Assistant',3000.0,6000.0);
INSERT INTO "departments" VALUES (10,'Administration',200,' 2004 Charade Rd',98199,'Seattle');
INSERT INTO "departments" VALUES (50,'Shipping',121,'2011 Interiors Blvd',99236,'Sna Francisco');
INSERT INTO "employees" VALUES (121,'Adam','Do','ado@fa.ke','333444555','03.04.2003','ST_MAN',8200.0,0.0,50);
INSERT INTO "employees" VALUES (174,'Ellen','Abel','eabel@fa.ke','6505079833','21.06.2007.','SH_CLERK',2600.0,NULL,50);
INSERT INTO "employees" VALUES (200,'Jennifer','Whalen','jwhalen@fa.ke','5151234444','22.03.2006.','AD_ASST',4400.0,NULL,10);
INSERT INTO "users" VALUES ('u','u');
COMMIT;
