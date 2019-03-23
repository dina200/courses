BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Type` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT NOT NULL UNIQUE
);
INSERT INTO `Type` (id,name) VALUES (2,'sport'),
 (3,'daily'),
 (4,'holiday');
CREATE TABLE IF NOT EXISTS `Storage` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`socks`	INTEGER NOT NULL,
	`added`	TEXT NOT NULL,
	`retired`	TEXT,
	`usage`	INTEGER,
	FOREIGN KEY(`socks`) REFERENCES `Socks`(`id`)
);
CREATE TABLE IF NOT EXISTS `SocksMaterial` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`socks`	INTEGER NOT NULL,
	`material`	INTEGER NOT NULL,
	`percentage`	INTEGER NOT NULL,
	FOREIGN KEY(`material`) REFERENCES `Material`(`id`),
	FOREIGN KEY(`socks`) REFERENCES `Socks`(`id`)
);
INSERT INTO `SocksMaterial` (id,socks,material,percentage) VALUES (1,1,2,90),
 (2,1,3,10);
CREATE TABLE IF NOT EXISTS `Socks` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`size`	REAL NOT NULL,
	`colour`	INTEGER NOT NULL,
	`type`	INTEGER NOT NULL,
	`manufacture`	INTEGER NOT NULL,
	FOREIGN KEY(`manufacture`) REFERENCES `Manufacture`(`id`),
	FOREIGN KEY(`type`) REFERENCES `Type`(`id`)
);
INSERT INTO `Socks` (id,size,colour,type,manufacture) VALUES (1,43.0,-16323763,2,2);
CREATE TABLE IF NOT EXISTS `Material` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT NOT NULL UNIQUE
);
INSERT INTO `Material` (id,name) VALUES (2,'cotton'),
 (3,'polyester'),
 (4,'elastane'),
 (5,'wool');
CREATE TABLE IF NOT EXISTS `Manufacture` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT NOT NULL
);
INSERT INTO `Manufacture` (id,name) VALUES (1,'житомир'),
 (2,'lotto'),
 (3,'demix');
COMMIT;
