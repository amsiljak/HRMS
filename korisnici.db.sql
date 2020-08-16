BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "zaposleni" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"first_name"	TEXT NOT NULL,
	"last_name"	TEXT NOT NULL,
	"email"	INTEGER NOT NULL UNIQUE,
	"phone_number"	INTEGER NOT NULL,
	"hire_date"	TEXT NOT NULL,
	"job_id"	INTEGER NOT NULL,
	"salary"	REAL NOT NULL,
	"commission_pct"	REAL,
	"manager_id"	INTEGER NOT NULL,
	"department_id"	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS "korisnici" (
	"username"	TEXT,
	"password"	TEXT
);
INSERT INTO "zaposleni" VALUES (1,'Ellen','Abel','eabel@fa.ke',6505079833,'21.06.2007.','SH_CLERK',2600.0,NULL,124,50);
INSERT INTO "korisnici" VALUES ('username','password');
COMMIT;
