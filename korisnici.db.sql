BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "korisnici" (
	"username"	TEXT,
	"password"	TEXT
);
INSERT INTO "korisnici" VALUES ('username','password');
COMMIT;
