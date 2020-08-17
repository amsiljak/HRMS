BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "poslovi" (
	"job_id"	TEXT,
	"job_title"	TEXT NOT NULL,
	"min_salary"	REAL NOT NULL,
	"max_salary"	REAL NOT NULL,
	PRIMARY KEY("job_id")
);
CREATE TABLE IF NOT EXISTS "zaposleni" (
	"id"	INTEGER NOT NULL,
	"first_name"	TEXT NOT NULL,
	"last_name"	TEXT NOT NULL,
	"email"	INTEGER NOT NULL UNIQUE,
	"phone_number"	INTEGER NOT NULL,
	"hire_date"	TEXT NOT NULL,
	"job_id"	INTEGER NOT NULL,
	"salary"	REAL NOT NULL,
	"commission_pct"	REAL,
	"manager_id"	INTEGER NOT NULL,
	"department_id"	INTEGER NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "odjeli" (
	"department_id"	INTEGER,
	"department_name"	TEXT NOT NULL,
	"manager_id"	INTEGER NOT NULL,
	"street_adress"	TEXT,
	"postal_code"	INTEGER,
	"city"	TEXT,
	PRIMARY KEY("department_id")
);
CREATE TABLE IF NOT EXISTS "korisnici" (
	"username"	TEXT,
	"password"	TEXT
);
INSERT INTO "poslovi" VALUES ('AD_PRES','President',2080.0,40000.0);
INSERT INTO "zaposleni" VALUES (174,'Ellen','Abel','eabel@fa.ke',6505079833,'21.06.2007.','SH_CLERK',2600.0,NULL,124,50);
INSERT INTO "odjeli" VALUES (10,'Administration',200,' 2004 Charade Rd',98199,'Seattle');
INSERT INTO "korisnici" VALUES ('u','u');
COMMIT;
