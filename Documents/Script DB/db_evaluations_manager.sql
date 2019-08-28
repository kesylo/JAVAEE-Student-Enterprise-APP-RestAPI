-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Dim 06 Janvier 2019 à 12:49
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_evaluations_manager`
--

-- --------------------------------------------------------

--
-- Structure de la table `answers`
--

CREATE TABLE `answers` (
  `id` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `code` varchar(100) NOT NULL,
  `title` varchar(500) NOT NULL,
  `truth` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `answers`
--

INSERT INTO `answers` (`id`, `id_question`, `code`, `title`, `truth`) VALUES
(2, 1, 'asw1', '= 2', 1),
(3, 2, 'asw2', '= 5', 1),
(4, 3, 'asw3', '= 4', 0),
(5, 4, 'asw4', '= 5', 0),
(6, 1, 'asw5', '= 6', 0),
(7, 4, 'asw6', '= 4', 1),
(8, 3, 'asw7', '= 8', 0),
(9, 3, 'asw8', '= 21', 0),
(10, 3, 'asw9', '= 1', 1),
(11, 8, 'asw10', '= 7', 1),
(12, 8, 'asw11', '= -1', 0),
(13, 9, 'asw12', '= 18', 0),
(14, 9, 'asw13', '= 5', 1),
(15, 10, 'asw14', '= aa', 1),
(16, 10, 'asw15', 'ab', 0),
(17, 11, 'aws16', 'bb', 1),
(18, 11, 'asw17', 'ba', 0);

-- --------------------------------------------------------

--
-- Structure de la table `evaluations`
--

CREATE TABLE `evaluations` (
  `id` int(11) NOT NULL,
  `code` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `duration` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `evaluations`
--

INSERT INTO `evaluations` (`id`, `code`, `title`, `duration`) VALUES
(1, 'eva1', 'Evaluation N°1', 5),
(2, 'eva2', 'Evaluation N°2', 5),
(3, 'eva3', 'Evaluation N°3', 5),
(4, 'eva4', 'Evaluation N°4', 5),
(5, 'eva5', 'Evaluation N°5', 5),
(7, 'eva6', 'Evaluation N°6', 5),
(8, 'eva7', 'Evaluation N°7', 5),
(10, '999', '999', 5),
(11, 'aaa', 'aaa', 180);

-- --------------------------------------------------------

--
-- Structure de la table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `id_evaluation` int(11) NOT NULL,
  `code` varchar(100) NOT NULL,
  `title` varchar(500) NOT NULL,
  `marks` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `questions`
--

INSERT INTO `questions` (`id`, `id_evaluation`, `code`, `title`, `marks`) VALUES
(1, 1, 'qr1', 'Addition 1 & 1', 3),
(2, 2, 'qr2', 'Soustraction 10 & 5', 2),
(3, 1, 'qr3', 'Division 2 & 2', 1),
(4, 8, 'qr4', 'Racine carrée 16', 2),
(5, 3, 'qr5', 'Multiplication de 2 & 9', 2),
(6, 1, 'qr6', 'Modulo 100 & 3', 2),
(8, 1, 'qr7', 'Mutiplication 1 & 7', 2),
(9, 1, 'qr8', 'Soustraction 8 & 3', 1),
(10, 4, 'qr9', 'a et a', 2),
(11, 4, 'qr10', 'b & b', 3);

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

-- --------------------------------------------------------

--
-- Structure de la table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `matricule` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `mobile_phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `students`
--

INSERT INTO `students` (`id`, `id_user`, `matricule`, `firstname`, `lastname`, `address`, `mobile_phone`, `email`, `birthday`) VALUES
(1, 1, '0987654321', 'Ulrich', 'POKA', 'Bessengue', '+237699888888', 'yorich.poka@gmail.com', '2000-02-01 00:00:00'),
(2, 4, '123456789', 'Loic', 'KEMING', 'aaaaa', 'aaaaa', 'aaaaa@aaa.com', '1990-12-30 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `students_answers`
--

CREATE TABLE `students_answers` (
  `id` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `id_answer` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `students_answers`
--

INSERT INTO `students_answers` (`id`, `id_student`, `id_answer`, `date`) VALUES
(12, 2, 2, '2019-01-03 00:06:31'),
(13, 2, 12, '2019-01-03 00:06:31'),
(14, 2, 14, '2019-01-03 00:06:31'),
(15, 2, 10, '2019-01-03 00:06:31'),
(20, 1, 11, '2019-01-05 16:29:14'),
(21, 1, 10, '2019-01-05 16:29:14'),
(22, 1, 2, '2019-01-05 16:29:14'),
(23, 1, 14, '2019-01-05 16:29:14'),
(24, 1, 3, '2019-01-05 16:40:33'),
(25, 1, 17, '2019-01-05 16:44:50'),
(26, 1, 16, '2019-01-05 16:44:50');

-- --------------------------------------------------------

--
-- Structure de la table `teachers`
--

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `matricule` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `mobile_phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `teachers`
--

INSERT INTO `teachers` (`id`, `id_user`, `matricule`, `firstname`, `lastname`, `address`, `mobile_phone`, `email`, `birthday`) VALUES
(1, 3, '1234567890', 'John', 'DOE', 'Logpom, Basson', '+237699999999', 'john.doe@gmail.com', '1990-12-09 13:41:30');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(100) CHARACTER SET utf8 NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `enabled`) VALUES
(1, 'admin', 'admin', 1),
(3, 'john', 'doe', 1),
(4, 'loic', 'loic', 1),
(6, 'aaa', 'aaa', 1);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`),
  ADD KEY `id_question` (`id_question`);

--
-- Index pour la table `evaluations`
--
ALTER TABLE `evaluations`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`);

--
-- Index pour la table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`),
  ADD KEY `fk_1_idx` (`id_evaluation`),
  ADD KEY `id_evaluation` (`id_evaluation`);

--
-- Index pour la table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Index pour la table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `matricule_UNIQUE` (`matricule`),
  ADD UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `students_answers`
--
ALTER TABLE `students_answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_students_idx` (`id_student`),
  ADD KEY `fk_answers_idx` (`id_answer`);

--
-- Index pour la table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  ADD UNIQUE KEY `matricule_UNIQUE` (`matricule`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login_UNIQUE` (`login`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `answers`
--
ALTER TABLE `answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT pour la table `evaluations`
--
ALTER TABLE `evaluations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT pour la table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT pour la table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `students_answers`
--
ALTER TABLE `students_answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT pour la table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `fk_question` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `fk_evaluation` FOREIGN KEY (`id_evaluation`) REFERENCES `evaluations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `fk_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `students_answers`
--
ALTER TABLE `students_answers`
  ADD CONSTRAINT `fk_answers` FOREIGN KEY (`id_answer`) REFERENCES `answers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_students` FOREIGN KEY (`id_student`) REFERENCES `students` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `teachers`
--
ALTER TABLE `teachers`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
