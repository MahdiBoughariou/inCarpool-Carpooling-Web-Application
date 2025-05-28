-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 28 mai 2025 à 04:09
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `newcovoiturage`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `password`, `username`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `brand`
--

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `brand`
--

INSERT INTO `brand` (`id`, `name`) VALUES
(1, 'ALFA ROMEO'),
(2, 'Aston Martin'),
(3, 'Audi'),
(4, 'Chevrolet'),
(5, 'Dodge'),
(6, 'Ferrari'),
(7, 'Honda'),
(8, 'Jaguar'),
(9, 'Lamborghini'),
(10, 'MAZDA'),
(11, 'McLaren'),
(12, 'NISSAN'),
(13, 'Porsche'),
(14, 'FIAT'),
(15, 'Mini'),
(16, 'Ford'),
(17, 'HYUNDAI'),
(18, 'LEXUS'),
(19, 'MASERATI'),
(20, 'Roush'),
(21, 'KIA'),
(22, 'Mitsubishi'),
(23, 'TOYOTA'),
(24, 'Volvo'),
(25, 'GMC'),
(26, 'CHEVROLET'),
(27, 'Jeep'),
(28, 'Range Rover'),
(29, 'Volkswagen'),
(30, 'BMW'),
(31, 'Mercedes');

-- --------------------------------------------------------

--
-- Structure de la table `car`
--

CREATE TABLE `car` (
  `id` bigint(20) NOT NULL,
  `model` varchar(255) NOT NULL,
  `platenumber` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `id_brand` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Structure de la table `city`
--

CREATE TABLE `city` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `city`
--

INSERT INTO `city` (`id`, `name`) VALUES
(1, ' Ariana'),
(2, ' Béja'),
(3, ' Ben Arous'),
(4, ' Bizerte'),
(5, ' Gabès'),
(6, ' Gafsa'),
(7, ' Jendouba'),
(8, ' Kairouan'),
(9, ' Kasserine'),
(10, ' Kebili'),
(11, ' Le Kef'),
(12, ' Mahdia'),
(13, ' Manouba'),
(14, ' Médenine'),
(15, ' Monastir'),
(16, ' Nabeul'),
(17, ' Sfax'),
(18, ' Sidi Bouzid'),
(19, ' Siliana'),
(20, ' Sousse'),
(21, ' Tataouine'),
(22, ' Tozeur'),
(23, ' Tunis'),
(24, ' Zaghouan');

-- --------------------------------------------------------

--
-- Structure de la table `offer`
--

CREATE TABLE `offer` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime(6) NOT NULL,
  `nbplaces` int(11) NOT NULL,
  `price` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL DEFAULT 'SCHEDULED',
  `time` time(6) NOT NULL,
  `id_car` bigint(20) NOT NULL,
  `id_departure` bigint(20) NOT NULL,
  `id_destination` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--

-- --------------------------------------------------------

--
-- Structure de la table `ride`
--

CREATE TABLE `ride` (
  `id` bigint(20) NOT NULL,
  `id_offer` bigint(20) NOT NULL,
  `id_passager` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `birthdate` date NOT NULL,
  `cin` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `imgcin` varchar(255) DEFAULT NULL,
  `imglic` varchar(255) DEFAULT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL DEFAULT 'NOT_QUALIFIED',
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKgfn44sntic2k93auag97juyij` (`username`);

--
-- Index pour la table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK8w56kt5jbhqbr7pam76ms734b` (`platenumber`),
  ADD KEY `FKdlug1wp8pe40fg063f1ui48ix` (`id_brand`),
  ADD KEY `FK8cy6cnh3jgkmqxha67e3i60oe` (`id_user`);

--
-- Index pour la table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKqsstlki7ni5ovaariyy9u8y79` (`name`);

--
-- Index pour la table `offer`
--
ALTER TABLE `offer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt3n1pa245m33ya19s0gcrf91j` (`id_car`),
  ADD KEY `FK43xvf4w2f82ofnmkremjhm9v7` (`id_departure`),
  ADD KEY `FKcnlrb51mqxwxybym9pttyrc5c` (`id_destination`);

--
-- Index pour la table `ride`
--
ALTER TABLE `ride`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmykl5xyph2ea0uj89ikvy420d` (`id_offer`),
  ADD KEY `FK2p2i52lmqnoxgyj7ikjl08d25` (`id_passager`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `brand`
--
ALTER TABLE `brand`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `car`
--
ALTER TABLE `car`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `city`
--
ALTER TABLE `city`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `offer`
--
ALTER TABLE `offer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `ride`
--
ALTER TABLE `ride`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `FK8cy6cnh3jgkmqxha67e3i60oe` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKdlug1wp8pe40fg063f1ui48ix` FOREIGN KEY (`id_brand`) REFERENCES `brand` (`id`);

--
-- Contraintes pour la table `offer`
--
ALTER TABLE `offer`
  ADD CONSTRAINT `FK43xvf4w2f82ofnmkremjhm9v7` FOREIGN KEY (`id_departure`) REFERENCES `city` (`id`),
  ADD CONSTRAINT `FKcnlrb51mqxwxybym9pttyrc5c` FOREIGN KEY (`id_destination`) REFERENCES `city` (`id`),
  ADD CONSTRAINT `FKt3n1pa245m33ya19s0gcrf91j` FOREIGN KEY (`id_car`) REFERENCES `car` (`id`);

--
-- Contraintes pour la table `ride`
--
ALTER TABLE `ride`
  ADD CONSTRAINT `FK2p2i52lmqnoxgyj7ikjl08d25` FOREIGN KEY (`id_passager`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKmykl5xyph2ea0uj89ikvy420d` FOREIGN KEY (`id_offer`) REFERENCES `offer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
