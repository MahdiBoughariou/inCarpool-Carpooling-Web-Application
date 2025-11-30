# inCarpool - Application de Covoiturage

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green) ![Docker](https://img.shields.io/badge/Docker-Enabled-blue) ![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1)

**inCarpool** est une application web de covoiturage conçue pour faciliter les déplacements entre les villes (ex: Sfax, Tunis, Bizerte). Le projet vise à connecter des conducteurs disposant de places libres avec des passagers cherchant un moyen de transport fiable et économique.

Ce projet a été réalisé par **Mahdi Boughariou** (2ème année Ingénierie en Génie Logiciel).

---

## 📂 Structure du Dépôt

Ce dépôt contient les codes sources, les données et la documentation officielle du projet :

* **`projet`** (Dossier Principal) : Contient le code source de l'application finale développée avec **Spring Boot**. C'est la version recommandée pour l'exécution et le déploiement.
* **`ancien_avec_servlets`** : Contient une version antérieure de l'application développée avec des **Servlets Java / JEE**. Ce dossier est conservé à des fins d'archivage.
* **`projet_co_data`** : Contient les ressources de données, notamment les fichiers `.csv` et les scripts `.sql` nécessaires pour peupler la base de données.
* **`Rapport.pdf`** : Le rapport complet du projet. Il contient la documentation technique, les diagrammes de conception (UML) ainsi que **l'ensemble des captures d'écran** des interfaces de l'application.

---

## 🚀 Fonctionnalités

L'application est divisée en deux interfaces distinctes :

### 1. Espace Utilisateur (Passager & Conducteur)
* **Authentification :** Création de compte, connexion et gestion du profil (modification mot de passe, informations personnelles).
* **Recherche de trajets :** Recherche de covoiturages par ville de départ, destination et date.
* **Réservation :** Confirmation de réservation et annulation si nécessaire.
* **Devenir Conducteur :** Soumission d'une demande "Upgrade" avec téléchargement de la Carte d'Identité (CIN) et du Permis de conduire.
* **Gestion des offres (Après validation) :**
    * Ajout de véhicules (Marque, Modèle, État).
    * Publication d'offres de covoiturage (Prix, Heure, Places disponibles).

### 2. Espace Administrateur
* **Tableau de bord (Dashboard) :** Statistiques en temps réel (nombre de clients, offres, réservations).
* **Gestion des utilisateurs :** Visualisation des statuts (Approved, Not Approved, Pending).
* **Validation des conducteurs :** L'admin examine les demandes (CIN/Permis) et accepte ou refuse le passage d'un utilisateur au statut de conducteur.

---

## 🛠️ Stack Technique

Le projet (dossier `projet`) repose sur les technologies suivantes :

* **Backend :** Java 21, Spring Boot.
* **Base de données :** MySQL 8.0.
* **Frontend :** HTML, CSS, Thymeleaf.
* **Conteneurisation :** Docker & Docker Compose.
* **Build Tool :** Maven.
* **Administration DB :** phpMyAdmin (inclus dans la stack Docker).

---

## ⚙️ Installation et Démarrage

### Prérequis
* Docker et Docker Compose installés sur votre machine.
* Java JDK 21 (pour la compilation locale avec Maven).
* Maven.

### Étapes de déploiement

1.  **Cloner le dépôt :**
    ```bash
    git clone [https://github.com/MahdiBoughariou/inCarpool-Carpooling-Web-Application.git](https://github.com/MahdiBoughariou/inCarpool-Carpooling-Web-Application.git)
    cd inCarpool-Carpooling-Web-Application
    ```

2.  **Préparer le projet Spring Boot :**
    Allez dans le dossier du projet principal et compilez le fichier `.jar`. Le Dockerfile s'attend à trouver le fichier dans `target/`.
    ```bash
    cd projet
    mvn clean package -DskipTests
    ```
    *(Note : L'option `-DskipTests` est recommandée pour accélérer le build si vous ne lancez pas les tests unitaires localement).*

3.  **Lancer avec Docker Compose :**
    Le fichier `docker-compose.yml` va orchestrer l'application, la base de données MySQL et phpMyAdmin.
    ```bash
    docker-compose up --build
    ```

4.  **Accéder à l'application :**
    * **Application inCarpool :** `http://localhost:8080`
    * **phpMyAdmin :** `http://localhost:8081`

---

## 🗄️ Base de Données

Le schéma relationnel comprend les entités suivantes : `User`, `Admin`, `Car`, `Brand`, `Offer`, `Ride`, `City`.

Si vous avez besoin d'importer des données initiales, vous pouvez utiliser les fichiers présents dans le dossier **`projet_co_data`** via l'interface phpMyAdmin une fois les conteneurs lancés.

---

## 📸 Aperçu et Documentation

Pour visualiser le rendu visuel de l'application, veuillez consulter le fichier **`Rapport.pdf`** situé à la racine de ce dépôt. Il inclut :
* Les diagrammes de classes et de conception.
* Les captures d'écran des interfaces **Admin** (Dashboard, Gestion des requêtes) et **Utilisateur** (Login, Recherche, Ajout d'offres).

---

## 📞 Contact

* **Auteur :** Mahdi Boughariou
* **GitHub :** [MahdiBoughariou](https://github.com/MahdiBoughariou)

---
*Ce projet a été réalisé dans le cadre du cursus académique en Génie Logiciel.*
