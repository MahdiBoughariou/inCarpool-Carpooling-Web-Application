# inCarpool - Application de Covoiturage

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green) ![Docker](https://img.shields.io/badge/Docker-Enabled-blue) ![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1)

[cite_start]**inCarpool** est une application web de covoiturage conçue pour faciliter les déplacements entre les villes (ex: Sfax, Tunis, Bizerte)[cite: 298]. Le projet vise à connecter des conducteurs disposant de places libres avec des passagers cherchant un moyen de transport fiable et économique.

[cite_start]Ce projet a été réalisé par **Mahdi Boughariou** (2ème année Ingénierie en Génie Logiciel)[cite: 4, 5].

---

## 📂 Structure du Dépôt

Ce dépôt contient trois dossiers principaux représentant l'évolution et les ressources du projet :

* **`projet`** (Dossier Principal) : Contient le code source de l'application finale développée avec **Spring Boot**. [cite_start]C'est la version recommandée pour l'exécution et le déploiement[cite: 8].
* **`ancien_avec_servlets`** : Contient une version antérieure de l'application développée en utilisant uniquement les **Servlets Java / JEE**. Ce dossier est conservé à des fins d'archivage et de comparaison architecturale.
* **`projet_co_data`** : Contient les ressources de données, notamment les fichiers `.csv` et les scripts `.sql` nécessaires pour peupler la base de données ou pour l'analyse de données.

---

## 🚀 Fonctionnalités

[cite_start]L'application est divisée en deux interfaces distinctes[cite: 8]:

### 1. Espace Utilisateur (Passager & Conducteur)
* [cite_start]**Authentification :** Création de compte, connexion et gestion du profil (modification mot de passe, informations personnelles)[cite: 22].
* [cite_start]**Recherche de trajets :** Recherche de covoiturages par ville de départ, destination et date[cite: 27].
* [cite_start]**Réservation :** Confirmation de réservation et annulation si nécessaire[cite: 28, 30].
* [cite_start]**Devenir Conducteur :** Soumission d'une demande "Upgrade" avec téléchargement de la Carte d'Identité (CIN) et du Permis de conduire[cite: 32, 315].
* **Gestion des offres (Après validation) :**
    * [cite_start]Ajout de véhicules (Marque, Modèle, État)[cite: 34].
    * [cite_start]Publication d'offres de covoiturage (Prix, Heure, Places disponibles)[cite: 35].

### 2. Espace Administrateur
* [cite_start]**Tableau de bord (Dashboard) :** Statistiques en temps réel (nombre de clients, offres, réservations)[cite: 14].
* [cite_start]**Gestion des utilisateurs :** Visualisation des statuts (Approved, Not Approved, Pending)[cite: 17].
* [cite_start]**Validation des conducteurs :** L'admin examine les demandes (CIN/Permis) et accepte ou refuse le passage d'un utilisateur au statut de conducteur[cite: 20].

---

## 🛠️ Stack Technique

[cite_start]Le projet (dossier `projet`) repose sur les technologies suivantes[cite: 8, 445, 507]:

* **Backend :** Java 21, Spring Boot.
* **Base de données :** MySQL 8.0.
* **Frontend :** HTML, CSS, Thymeleaf (impliqué par l'architecture Spring MVC classique).
* **Conteneurisation :** Docker & Docker Compose.
* **Build Tool :** Maven.
* [cite_start]**Administration DB :** phpMyAdmin (inclus dans la stack Docker)[cite: 555].

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
    [cite_start]*(Note : L'option `-DskipTests` est recommandée pour accélérer le build si vous ne lancez pas les tests unitaires localement [cite: 483]).*

3.  **Lancer avec Docker Compose :**
    [cite_start]Le fichier `docker-compose.yml` va orchestrer l'application, la base de données MySQL et phpMyAdmin[cite: 523].
    ```bash
    docker-compose up --build
    ```

4.  **Accéder à l'application :**
    * [cite_start]**Application inCarpool :** `http://localhost:8080` [cite: 542]
    * [cite_start]**phpMyAdmin :** `http://localhost:8081` [cite: 565]

---

## 🗄️ Base de Données

[cite_start]Le schéma relationnel comprend les entités suivantes : `User`, `Admin`, `Car`, `Brand`, `Offer`, `Ride`, `City`[cite: 36].

Si vous avez besoin d'importer des données initiales, vous pouvez utiliser les fichiers présents dans le dossier **`projet_co_data`** via l'interface phpMyAdmin une fois les conteneurs lancés.

---

## 📸 Aperçu de l'application

| Login | Dashboard Admin |
|:---:|:---:|
| ![Login](path/to/screenshot_login.png) | ![Dashboard](path/to/screenshot_dashboard.png) |
| *Interface de connexion* | *Gestion des utilisateurs et statistiques* |

| Recherche | Détails Offre |
|:---:|:---:|
| ![Search](path/to/screenshot_search.png) | ![Offer](path/to/screenshot_offer.png) |
| *Recherche de trajets* | *Détail et confirmation* |

*(Note : Les captures d'écran sont disponibles dans le rapport PDF).*

---

## 📞 Contact

* **Auteur :** Mahdi Boughariou
* [cite_start]**Email :** idham.boughariou@gmail.com [cite: 197]
* **GitHub :** [MahdiBoughariou](https://github.com/MahdiBoughariou)

---
*Ce projet a été réalisé dans le cadre du cursus académique en Génie Logiciel.*
