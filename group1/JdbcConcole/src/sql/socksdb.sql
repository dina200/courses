BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Type`
(
  `id`   INTEGER PRIMARY KEY AUTOINCREMENT,
  `name` TEXT NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS `Storage`
(
  `id`      INTEGER PRIMARY KEY AUTOINCREMENT,
  `socks`   INTEGER,
  `added`   TEXT NOT NULL,
  `retired` TEXT,
  `usage`   INTEGER,
  FOREIGN KEY (`socks`) REFERENCES `Socks` (`id`)
);
CREATE TABLE IF NOT EXISTS `SocksMaterial`
(
  `id`         INTEGER PRIMARY KEY AUTOINCREMENT,
  `socks`      INTEGER,
  `material`   INTEGER,
  `percentage` INTEGER NOT NULL,
  FOREIGN KEY (`socks`) REFERENCES `Socks` (`id`),
  FOREIGN KEY (`material`) REFERENCES `Material` (`id`)
);
CREATE TABLE IF NOT EXISTS `Socks`
(
  `id`     INTEGER PRIMARY KEY AUTOINCREMENT,
  `size`   REAL    NOT NULL,
  `colour` INTEGER NOT NULL,
  `type`   INTEGER,
  FOREIGN KEY (`type`) REFERENCES `Type` (`id`)
);
CREATE TABLE IF NOT EXISTS `Material`
(
  `id`   INTEGER PRIMARY KEY AUTOINCREMENT,
  `name` TEXT NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS `Manufacture`
(
  `id`   INTEGER PRIMARY KEY AUTOINCREMENT,
  `name` TEXT NOT NULL
);
COMMIT;