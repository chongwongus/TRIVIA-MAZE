BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "TriviaMC" (
	"Question"	TEXT NOT NULL,
	"Choice1"	TEXT NOT NULL,
	"Choice2"	TEXT NOT NULL,
	"Choice3"	TEXT NOT NULL,
	"Choice4"	TEXT NOT NULL,
	"Answer"	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS "QuestionTF" (
	"QUESTION"	TEXT NOT NULL,
	"CHOICE1"	TEXT NOT NULL,
	"CHOICE2"	TEXT NOT NULL,
	"ANSWER"	TEXT NOT NULL
);
INSERT INTO "TriviaMC" VALUES ('Which planet has the most moons?','Mars','Saturn','Earth','Mercury','Saturn');
INSERT INTO "TriviaMC" VALUES ('Which star is the center of our solar system?','The Sun','Ursa Major','Hercules','Proxima Centauri','The Sun');
INSERT INTO "TriviaMC" VALUES ('What year was Nintendo founded?','1949','1776','1492','1889','1889');
INSERT INTO "TriviaMC" VALUES ('Which famous video game franchise is the game ''V-Bucks'' from?','Fortnite','League of Legends','Dota','Apex Legends','Fortnite');
INSERT INTO "TriviaMC" VALUES ('What is the best selling videogame of all time?','Halo','Madden NFL','Minecraft','Tetris','Minecraft');
INSERT INTO "TriviaMC" VALUES ('What year was the first virtual reality headset created?','2012','1993','1997','1995','1995');
INSERT INTO "TriviaMC" VALUES ('What food was the character Pac Man modeled after?','Jell-O','Pizza','Bananas','Ramen','Pizza');
INSERT INTO "TriviaMC" VALUES ('What is the name of Mario’s dinosaur sidekick?','Yoshi','Luigi','Jack','Woody','Yoshi');
INSERT INTO "TriviaMC" VALUES ('What is the name of the first video game to be played in outer space?','Star Wars: The Old Republic','Starcraft','Mass Effect','Genshin Impact','Starcraft');
INSERT INTO "TriviaMC" VALUES ('What was Mario''s first job?','Plumber','Carpenter','Hero','Lumberjack','Carpenter');
INSERT INTO "TriviaMC" VALUES ('Which is the smallest planet within our solar system?','Pluto','Mercury','Mars','Earth','Mercury');
INSERT INTO "TriviaMC" VALUES ('How long is one year on Jupiter?','12 Earth Years','100 Mars Years','1 Earth Year','50 Saturn Years','12 Earth Years');
INSERT INTO "TriviaMC" VALUES ('When did the solar system form?','4.6 BYA','2.3 TYA','162 MYA','60 BYA','4.6 BYA');
INSERT INTO "TriviaMC" VALUES ('What''s the best selling video game console of all time?','PS2','PS3','XBox 360','Nintendo Switch','PS2');
INSERT INTO "QuestionTF" VALUES ('Blizzard Entertainment is most well known for World of Warcraft','TRUE','FALSE','TRUE');
INSERT INTO "QuestionTF" VALUES ('NES stands for "Nintendo Entertainment System"','TRUE','FALSE','TRUE');
INSERT INTO "QuestionTF" VALUES ('The Romans invented astronomy','TRUE','FALSE','FALSE');
INSERT INTO "QuestionTF" VALUES ('"PvE" stands for "Player versus Everything"','TRUE','FALSE','FALSE');
INSERT INTO "QuestionTF" VALUES ('In GTA5, you can control 3 characters','TRUE','FALSE','FALSE');
INSERT INTO "QuestionTF" VALUES ('The character "Master Hand" originated from Super Mario Bros.','TRUE','FALSE','FALSE');
INSERT INTO "QuestionTF" VALUES ('Blizzard developed the game "Destiny"','TRUE','FALSE','FALSE');
INSERT INTO "QuestionTF" VALUES ('Amelia Earheart was the first woman to travel into space','TRUE','FALSE','FALSE');
INSERT INTO "QuestionTF" VALUES ('Red is the color of Mars'' sunset','TRUE','FALSE','FALSE');
INSERT INTO "QuestionTF" VALUES ('7 stars make up the Big Dipper','TRUE','FALSE','TRUE');
INSERT INTO "QuestionTF" VALUES ('There are 62 constellations out there','TRUE','FALSE','FALSE');
INSERT INTO "QuestionTF" VALUES ('To form obsidian, you mix water and lava together','TRUE','FALSE','TRUE');
INSERT INTO "QuestionTF" VALUES ('Microsoft released the first flight simulator type game','TRUE','FALSE','TRUE');
INSERT INTO "QuestionTF" VALUES ('It took Notch 1 week to develop Minecraft','TRUE','FALSE','TRUE');
COMMIT;
