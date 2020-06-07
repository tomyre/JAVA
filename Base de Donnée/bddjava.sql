-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : Dim 07 juin 2020 à 23:18
-- Version du serveur :  10.3.22-MariaDB-1ubuntu1
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bddjava`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`ID`, `NOM`) VALUES
(1, 'Informatique'),
(2, 'Traitement du signal'),
(3, 'Analyse de Fourier'),
(4, 'POO Java'),
(5, 'Anglais'),
(6, 'Droit du Travail'),
(7, 'Electronique'),
(8, 'Thermodynamique'),
(9, 'Allemand'),
(10, 'Probabilités');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `ID_UTILISATEUR` int(11) NOT NULL,
  `ID_COURS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`ID_UTILISATEUR`, `ID_COURS`) VALUES
(6, 1),
(7, 2),
(8, 3),
(8, 10),
(9, 1),
(9, 4),
(10, 5),
(11, 6),
(12, 2),
(12, 7),
(13, 8),
(14, 9);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `ID_UTILISATEUR` int(11) NOT NULL,
  `NUMERO` int(11) NOT NULL,
  `ID_GROUPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`ID_UTILISATEUR`, `NUMERO`, `ID_GROUPE`) VALUES
(1, 1101, 1),
(2, 1102, 2),
(3, 1103, 3),
(15, 1104, 4),
(16, 1105, 5);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `ID_PROMOTION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`ID`, `NOM`, `ID_PROMOTION`) VALUES
(1, 'TD1', 1),
(2, 'TD2', 2),
(3, 'TD3', 3),
(4, 'TD4', 1),
(5, 'TD5', 2);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`ID`, `NOM`) VALUES
(1, '2021'),
(2, '2022'),
(3, '2023');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE `salle` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `CAPACITE` int(11) NOT NULL,
  `ID_SITE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`ID`, `NOM`, `CAPACITE`, `ID_SITE`) VALUES
(1, 'EM10', 100, 1),
(2, 'EM11', 100, 1),
(3, 'SC01', 40, 2),
(4, 'P01', 50, 3),
(5, 'G019', 80, 4),
(6, 'H005', 60, 5),
(7, 'SC02', 50, 2),
(8, 'P02', 500, 3),
(9, 'G016', 30, 4),
(10, 'H001', 100, 5);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

CREATE TABLE `seance` (
  `ID` int(11) NOT NULL,
  `SEMAINE` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `HEURE_DEBUT` time NOT NULL,
  `HEURE_FIN` time NOT NULL,
  `ETAT` int(11) NOT NULL,
  `ID_COURS` int(11) NOT NULL,
  `ID_TYPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`ID`, `SEMAINE`, `DATE`, `HEURE_DEBUT`, `HEURE_FIN`, `ETAT`, `ID_COURS`, `ID_TYPE`) VALUES
(1, 1, '2020-06-15', '08:30:00', '11:45:00', 2, 1, 4),
(2, 1, '2020-06-15', '15:30:00', '17:00:00', 2, 5, 4),
(3, 1, '2020-06-16', '14:00:00', '16:00:00', 2, 8, 3),
(4, 1, '2020-06-17', '10:00:00', '12:00:00', 1, 3, 3),
(5, 1, '2020-06-19', '15:30:00', '17:00:00', 2, 2, 1),
(6, 2, '2020-06-22', '10:15:00', '11:45:00', 2, 4, 5),
(7, 2, '2020-06-22', '12:00:00', '13:30:00', 2, 9, 4),
(8, 2, '2020-06-23', '13:30:00', '15:00:00', 1, 6, 4),
(9, 2, '2020-06-24', '10:15:00', '11:45:00', 3, 7, 2),
(10, 1, '2020-06-19', '10:15:00', '12:00:00', 1, 10, 5),
(11, 1, '2020-06-25', '10:00:00', '22:04:00', 3, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `seance_enseignants`
--

CREATE TABLE `seance_enseignants` (
  `ID_SEANCE` int(11) NOT NULL,
  `ID_ENSEIGNANT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_enseignants`
--

INSERT INTO `seance_enseignants` (`ID_SEANCE`, `ID_ENSEIGNANT`) VALUES
(1, 6),
(1, 9),
(2, 10),
(3, 13),
(4, 8),
(5, 7),
(5, 12),
(6, 9),
(7, 14),
(8, 11),
(9, 12),
(10, 8),
(11, 14);

-- --------------------------------------------------------

--
-- Structure de la table `seance_groupes`
--

CREATE TABLE `seance_groupes` (
  `ID_SEANCE` int(11) NOT NULL,
  `ID_GROUPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_groupes`
--

INSERT INTO `seance_groupes` (`ID_SEANCE`, `ID_GROUPE`) VALUES
(1, 1),
(1, 3),
(2, 2),
(3, 3),
(4, 1),
(5, 4),
(6, 5),
(7, 1),
(8, 6),
(9, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance_salles`
--

CREATE TABLE `seance_salles` (
  `ID_SEANCE` int(11) NOT NULL,
  `ID_SALLE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_salles`
--

INSERT INTO `seance_salles` (`ID_SEANCE`, `ID_SALLE`) VALUES
(1, 1),
(2, 2),
(3, 5),
(4, 6),
(5, 4),
(6, 3),
(7, 1),
(8, 2);

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

CREATE TABLE `site` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`ID`, `NOM`) VALUES
(1, 'Eiffel1'),
(2, 'Eiffel2'),
(3, 'Eiffel3'),
(4, 'Eiffel4'),
(5, 'Eiffel5');

-- --------------------------------------------------------

--
-- Structure de la table `type_cours`
--

CREATE TABLE `type_cours` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_cours`
--

INSERT INTO `type_cours` (`ID`, `NOM`) VALUES
(1, 'TD'),
(2, 'TP'),
(3, 'EXAMEN'),
(4, 'COURS'),
(5, 'PROJET');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `ID` int(11) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWD` varchar(50) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `PRENOM` varchar(255) NOT NULL,
  `DROIT` enum('1','2','3','4') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID`, `EMAIL`, `PASSWD`, `NOM`, `PRENOM`, `DROIT`) VALUES
(1, 'Lindrit.hyseni@edu.ece.fr', 'lindrit', 'Hyseni', 'Lindrit', '4'),
(2, 'amandine.ziegler@edu.ece.fr', 'amandine', 'Ziegler', 'Amandine', '4'),
(3, 'tom.larnicol@edu.ece.fr', 'tom', 'Larnicol', 'Tom', '4'),
(4, 'tom191298@gmail.com', 'tomyre191298', 'Larnicol', 'Tom', '1'),
(5, 'fabienne.coudray@ece.fr', 'fabienne', 'Coudray', 'Fabienne', '2'),
(6, 'frederic.ravaut@ece.fr', 'frederic', 'Ravaut', 'Frederic', '3'),
(7, 'nadia.aitbouzit@ece.fr', 'nadia', 'Aitbouzit', 'Nadia', '3'),
(8, 'said.cerbah@ece.fr', 'said', 'Cerbah', 'Said', '3'),
(9, 'jp.segado@ece.fr', 'jp', 'Segado', 'Jp', '3'),
(10, 'marie.leonard@ece.fr', 'marie', 'Leonard', 'Marie', '3'),
(11, 'yves.maupile@ece.fr', 'yves', 'Maupile', 'Yves', '3'),
(12, 'arash.mokhber@ece.fr', 'arash', 'Mokhber', 'Arash', '3'),
(13, 'remi.richaud@ece.fr', 'remi', 'Richaud', 'Remi', '3'),
(14, 'cornelia.mihesso@ece.fr', 'cornelia', 'Mihesso', 'Cornelia', '3'),
(15, 'lea.desbiens@edu.ece.fr', 'lea', 'Desbiens', 'Lea', '4'),
(16, 'paola.peretti@edu.ece.fr', 'paola', 'Peretti', 'Paola', '4'),
(17, 'pa.rebuffi@edu.ece.fr', 'pa', 'Rebuffi', 'PA', '4');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`ID_UTILISATEUR`,`ID_COURS`),
  ADD KEY `ID_COURS` (`ID_COURS`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`ID_UTILISATEUR`),
  ADD KEY `ID_GROUPE` (`ID_GROUPE`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_PROMOTION` (`ID_PROMOTION`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_SITE` (`ID_SITE`);

--
-- Index pour la table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_COURS` (`ID_COURS`),
  ADD KEY `ID_TYPE` (`ID_TYPE`);

--
-- Index pour la table `seance_enseignants`
--
ALTER TABLE `seance_enseignants`
  ADD PRIMARY KEY (`ID_SEANCE`,`ID_ENSEIGNANT`),
  ADD KEY `ID_ENSEIGNANT` (`ID_ENSEIGNANT`);

--
-- Index pour la table `seance_groupes`
--
ALTER TABLE `seance_groupes`
  ADD PRIMARY KEY (`ID_SEANCE`,`ID_GROUPE`),
  ADD KEY `ID_GROUPE` (`ID_GROUPE`);

--
-- Index pour la table `seance_salles`
--
ALTER TABLE `seance_salles`
  ADD PRIMARY KEY (`ID_SEANCE`,`ID_SALLE`),
  ADD KEY `ID_SALLE` (`ID_SALLE`);

--
-- Index pour la table `site`
--
ALTER TABLE `site`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `type_cours`
--
ALTER TABLE `type_cours`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `ID_UTILISATEUR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `salle`
--
ALTER TABLE `salle`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `seance`
--
ALTER TABLE `seance`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `site`
--
ALTER TABLE `site`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `type_cours`
--
ALTER TABLE `type_cours`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD CONSTRAINT `enseignant_ibfk_1` FOREIGN KEY (`ID_UTILISATEUR`) REFERENCES `utilisateur` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `enseignant_ibfk_2` FOREIGN KEY (`ID_COURS`) REFERENCES `cours` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`ID_UTILISATEUR`) REFERENCES `utilisateur` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `etudiant_ibfk_2` FOREIGN KEY (`ID_GROUPE`) REFERENCES `groupe` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`ID_PROMOTION`) REFERENCES `promotion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`ID_SITE`) REFERENCES `site` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
