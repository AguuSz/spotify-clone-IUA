-- MySQL Workbench Synchronization
-- Generated: 2021-06-06 16:41
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Agus

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `spotify-clone` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`User` (
  `id-user` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `last-name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(90) NOT NULL,
  `birthdate` DATETIME NULL DEFAULT NULL,
  `password` VARCHAR(255) NOT NULL,
  `id-country` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id-user`),
  UNIQUE INDEX `id-user_UNIQUE` (`id-user` ASC) VISIBLE,
  INDEX `fk_country_user_idx` (`id-country` ASC) VISIBLE,
  CONSTRAINT `fk_country_user`
    FOREIGN KEY (`id-country`)
    REFERENCES `spotify-clone`.`Country` (`id-country`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Device` (
  `id-device` INT(11) NOT NULL AUTO_INCREMENT,
  `mac-address` VARCHAR(45) NOT NULL,
  `id-user` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NULL DEFAULT NULL,
  `pairing-date` DATETIME NOT NULL,
  PRIMARY KEY (`id-device`),
  UNIQUE INDEX `id-device_UNIQUE` (`id-device` ASC) VISIBLE,
  INDEX `fk_user_device_idx` (`id-user` ASC) VISIBLE,
  CONSTRAINT `fk_user_device`
    FOREIGN KEY (`id-user`)
    REFERENCES `spotify-clone`.`User` (`id-user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Language` (
  `id-language` INT(11) NOT NULL AUTO_INCREMENT,
  `language` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id-language`),
  UNIQUE INDEX `id-language_UNIQUE` (`id-language` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Prefer` (
  `id-user` INT(11) NOT NULL,
  `id-language` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id-user`),
  INDEX `fk_language_prefer_idx` (`id-language` ASC) VISIBLE,
  CONSTRAINT `fk_user_prefer`
    FOREIGN KEY (`id-user`)
    REFERENCES `spotify-clone`.`User` (`id-user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_language_prefer`
    FOREIGN KEY (`id-language`)
    REFERENCES `spotify-clone`.`Language` (`id-language`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Content` (
  `id-content` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `release-date` DATETIME NOT NULL,
  `length` INT(11) NOT NULL,
  `id-genre` INT(11) NULL DEFAULT NULL,
  `id-language` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id-content`),
  UNIQUE INDEX `id-content_UNIQUE` (`id-content` ASC) VISIBLE,
  INDEX `fk_genre_content_idx` (`id-genre` ASC) VISIBLE,
  INDEX `fk_language_content_idx` (`id-language` ASC) VISIBLE,
  CONSTRAINT `fk_genre_content`
    FOREIGN KEY (`id-genre`)
    REFERENCES `spotify-clone`.`Genre` (`id-genre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_language_content`
    FOREIGN KEY (`id-language`)
    REFERENCES `spotify-clone`.`Language` (`id-language`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Genre` (
  `id-genre` INT(11) NOT NULL AUTO_INCREMENT,
  `genre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id-genre`),
  UNIQUE INDEX `id-genre_UNIQUE` (`id-genre` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Listen` (
  `id-user` INT(11) NOT NULL,
  `id_content` INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id-user`, `id_content`),
  INDEX `fk_content_listen_idx` (`id_content` ASC) VISIBLE,
  CONSTRAINT `fk_user_listen`
    FOREIGN KEY (`id-user`)
    REFERENCES `spotify-clone`.`User` (`id-user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_listen`
    FOREIGN KEY (`id_content`)
    REFERENCES `spotify-clone`.`Content` (`id-content`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Podcast` (
  `id-content` INT(11) NOT NULL,
  `chapter` INT(11) NOT NULL,
  PRIMARY KEY (`id-content`),
  UNIQUE INDEX `chapter_UNIQUE` (`chapter` ASC) VISIBLE,
  CONSTRAINT `fk_content_podcast`
    FOREIGN KEY (`id-content`)
    REFERENCES `spotify-clone`.`Content` (`id-content`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Song` (
  `id-song` INT(11) NOT NULL,
  PRIMARY KEY (`id-song`),
  CONSTRAINT `fk_content_song`
    FOREIGN KEY (`id-song`)
    REFERENCES `spotify-clone`.`Content` (`id-content`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Playlist` (
  `id-playlist` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created-at` VARCHAR(45) NULL DEFAULT NULL,
  `id-usuario` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id-playlist`),
  UNIQUE INDEX `id-playlist_UNIQUE` (`id-playlist` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Creates` (
  `id-user` INT(11) NOT NULL,
  `id-playlist` INT(11) NULL DEFAULT NULL,
  `id-content` INT(11) NULL DEFAULT NULL,
  `created-date` DATETIME NOT NULL,
  PRIMARY KEY (`id-user`),
  INDEX `fk_playlist_creates_idx` (`id-playlist` ASC) VISIBLE,
  INDEX `fk_content_content_idx` (`id-content` ASC) VISIBLE,
  CONSTRAINT `fk_user_creates`
    FOREIGN KEY (`id-user`)
    REFERENCES `spotify-clone`.`User` (`id-user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_playlist_creates`
    FOREIGN KEY (`id-playlist`)
    REFERENCES `spotify-clone`.`Playlist` (`id-playlist`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_content`
    FOREIGN KEY (`id-content`)
    REFERENCES `spotify-clone`.`Content` (`id-content`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Artist` (
  `id-artist` INT(11) NOT NULL,
  `stage-name` VARCHAR(90) NOT NULL,
  `birthdate` DATETIME NULL DEFAULT NULL,
  `id-country` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id-artist`),
  INDEX `fk_country_artist_idx` (`id-country` ASC) VISIBLE,
  CONSTRAINT `fk_country_artist`
    FOREIGN KEY (`id-country`)
    REFERENCES `spotify-clone`.`Country` (`id-country`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Country` (
  `id-country` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id-country`),
  UNIQUE INDEX `id-country_UNIQUE` (`id-country` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Makes` (
  `id-artist` INT(11) NOT NULL,
  `id-podcast` INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id-artist`, `id-podcast`),
  INDEX `fk_podcast_makes_idx` (`id-podcast` ASC) VISIBLE,
  CONSTRAINT `fk_artist_makes`
    FOREIGN KEY (`id-artist`)
    REFERENCES `spotify-clone`.`Artist` (`id-artist`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_podcast_makes`
    FOREIGN KEY (`id-podcast`)
    REFERENCES `spotify-clone`.`Podcast` (`id-content`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Album` (
  `id-album` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id-album`),
  UNIQUE INDEX `id-album_UNIQUE` (`id-album` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spotify-clone`.`Uploads` (
  `id-artist` INT(11) NOT NULL,
  `id-song` INT(11) NOT NULL,
  `id-album` INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id-artist`, `id-song`, `id-album`),
  INDEX `fk_album_uploads_idx` (`id-album` ASC) VISIBLE,
  INDEX `fk_song_uploads_idx` (`id-song` ASC) VISIBLE,
  CONSTRAINT `fk_artist_uploads`
    FOREIGN KEY (`id-artist`)
    REFERENCES `spotify-clone`.`Artist` (`id-artist`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_song_uploads`
    FOREIGN KEY (`id-song`)
    REFERENCES `spotify-clone`.`Song` (`id-song`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_uploads`
    FOREIGN KEY (`id-album`)
    REFERENCES `spotify-clone`.`Album` (`id-album`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
