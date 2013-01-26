-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 21 Sty 2013, 09:36
-- Wersja serwera: 5.5.16
-- Wersja PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `movies`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `locale` tinyint(1) DEFAULT NULL,
  `server` tinyint(1) DEFAULT NULL,
  `created` date NOT NULL,
  `modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `categories`
--

INSERT INTO `categories` (`id`, `name`, `locale`, `server`, `created`, `modified`) VALUES
(1, 'animowane', 1, 1, '2012-10-10', '2012-10-10'),
(2, 'przygodowe', 0, 1, '2012-10-10', '2012-10-10'),
(3, 'naukowe', 1, 0, '2012-10-10', '2012-10-10');

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `categories_movies`
--

CREATE TABLE IF NOT EXISTS `categories_movies` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `movie_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=11 ;

--
-- Zrzut danych tabeli `categories_movies`
--

INSERT INTO `categories_movies` (`id`, `category_id`, `movie_id`) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 1, 8),
(4, 2, 2),
(5, 2, 3),
(6, 2, 4),
(7, 2, 8),
(8, 3, 3),
(9, 1, 9),
(10, 2, 10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `movies`
--

CREATE TABLE IF NOT EXISTS `movies` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `content` text COLLATE utf8_polish_ci NOT NULL,
  `file` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `local` tinyint(1) DEFAULT NULL,
  `server` tinyint(1) DEFAULT NULL,
  `created` date NOT NULL,
  `modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=11 ;

--
-- Zrzut danych tabeli `movies`
--

INSERT INTO `movies` (`id`, `name`, `content`, `file`, `local`, `server`, `created`, `modified`) VALUES
(1, 'Shrek', 'OpowieÅ›Ä‡ o ogrze i gadatliwym oÅ›le.', 'shrek.avi', 1, 1, '2012-10-11', '2013-01-21'),
(2, 'Botanika', 'Film naukowy o botanikach prowadzÄ…cych prace w dolinie Uge Bere w Egipcie.', 'botanika.avi', 1, 0, '2012-10-09', '2013-01-21'),
(3, 'Felek i Zosia', 'Bajka opowiadająca o przyjaźni dwóch surykatek zamieszkujących prerię. Akcja bajki rozgrywa się na przełomie XII i  XIII wieku przed naszą erą. Ale głupi opis!', 'FiZ.mpeg', 1, 0, '2012-10-13', '0000-00-00'),
(4, 'Kevin sam w kosmosie', 'Przygoda Kevina tym razem ma miejsce w kosmosie. Film ten bÄ™dzie pokazywany co roku w okresie Å›wiÄ…t BoÅ¼ego Narodzenia od 2030 roku.', 'kevin_wk.avi', 1, 0, '2012-10-01', '2013-01-21'),
(5, 'The Tower', 'Obrońcy wieży na której leży gniazdo jeżozwierzy a pod nią stoi Jerzy z Białowierzy i nie wieży, że się zmierzy z obrońcami wieży by kradzieży ustrzec się.', 'the-tower.mpeg', 0, 1, '2012-10-10', '2012-10-22'),
(6, 'Wegetatianki', 'Druga czeÅ›Ä‡ Galerianek - Wegetatianki - to zakrÄ™cona historia mÅ‚odych kobiet zarabiajacych na dania bez miÄ™sa czekajÄ…c na okazjÄ™ przy fastfoodach z chiÅ„szczyznÄ….', 'wegetarianki.flv', 1, 1, '2012-10-15', '2013-01-21'),
(7, 'BoÅ¼ena Z.', 'brak opisu', 'bozenaZ.avi', 0, 1, '2012-10-02', '2013-01-21'),
(8, 'Gargamel i przyjaciele', 'Nowe wydanie SmerfÃ³w', 'gragrmel.mov', 1, 1, '2012-10-04', '2013-01-21'),
(9, 'b s Å¼', 'sda', 'By_o_sobie_ycie_E26_DVDrip_PL_XviD.avi', 1, 0, '2013-01-17', '2013-01-21'),
(10, 'Janowicz', 'Jerzy Janowicz', 'Jerzy_Janowicz_Meltdown.mp4', 0, 0, '2013-01-17', '2013-01-17');

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `playlists`
--

CREATE TABLE IF NOT EXISTS `playlists` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `created` date DEFAULT NULL,
  `modified` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=6 ;

--
-- Zrzut danych tabeli `playlists`
--

INSERT INTO `playlists` (`id`, `name`, `user_id`, `created`, `modified`) VALUES
(5, 'WieÅ›ka_playlist', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `playlist_movies`
--

CREATE TABLE IF NOT EXISTS `playlist_movies` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `playlist_id` int(11) unsigned NOT NULL,
  `movie_id` int(11) unsigned NOT NULL,
  `created` date DEFAULT NULL,
  `modified` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=7 ;

--
-- Zrzut danych tabeli `playlist_movies`
--

INSERT INTO `playlist_movies` (`id`, `playlist_id`, `movie_id`, `created`, `modified`) VALUES
(1, 5, 1, NULL, '2013-01-15'),
(2, 5, 3, NULL, '2013-01-15'),
(3, 5, 8, NULL, '2013-01-15'),
(4, 5, 7, NULL, '2013-01-15'),
(5, 5, 1, NULL, '2013-01-15'),
(6, 5, 2, NULL, '2013-01-15');

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `created` date DEFAULT NULL,
  `modified` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `created`, `modified`) VALUES
(1, 'wiesiek', 'wiesiek', NULL, NULL),
(2, 'grzesiek', 'grzesiek', NULL, NULL),
(3, 'Å›Ä‡Ã³Å‚', '', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `users_categories`
--

CREATE TABLE IF NOT EXISTS `users_categories` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  `created` date DEFAULT NULL,
  `modified` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=5 ;

--
-- Zrzut danych tabeli `users_categories`
--

INSERT INTO `users_categories` (`id`, `user_id`, `category_id`, `created`, `modified`) VALUES
(1, 2, 1, NULL, NULL),
(2, 3, 1, NULL, NULL),
(3, 3, 2, NULL, NULL),
(4, 3, 3, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `users_movies`
--

CREATE TABLE IF NOT EXISTS `users_movies` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `movie_id` int(10) unsigned NOT NULL,
  `created` date DEFAULT NULL,
  `modified` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `users_movies`
--

INSERT INTO `users_movies` (`id`, `user_id`, `movie_id`, `created`, `modified`) VALUES
(1, 1, 2, NULL, NULL),
(2, 1, 5, NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
