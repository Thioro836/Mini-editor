# Mini-Editeur de Texte

## Membres du Binôme
- **Nom 1**: Fatoumata Thioro Bah  
- **Nom 2**: Oumou Sadio Bah

## Introduction
Ce projet est un mini-éditeur de texte implémentant plusieurs fonctionnalités permettant d’éditer et de manipuler du texte dans un buffer. Il a été développé dans le cadre des travaux pratiques en suivant une approche incrémentale sur trois versions :
- **V1** : Fonctionnalités de base (insertion, sélection, couper, copier, coller).
- **V2** : Enregistrement et relecture des commandes utilisateur.
- **V3** : Annulation et rétablissement des commandes sans limite d'historique.

## Contenu du Dépôt
Le dépôt contient les éléments suivants :

- **Sources Java** : Les fichiers sources organisés par fonctionnalité.
- **Cas de tests JUnit** : Tests unitaires vérifiant la conformité des fonctionnalités.
- **Javadoc** : Documentation du code générée au format HTML.
- **Rapport de conception** : Document PDF expliquant les choix de conception, la synthèse des tests et les instructions d’utilisation.
- **README.md** : Ce fichier contenant une présentation globale du projet.

## Fonctionnalités

### Version 1 (V1) : Fonctionnalités de base
1. **Insertion** : Insertion de texte dans le buffer.
2. **Sélection** : Définir un début et une fin pour une sélection dans le texte.
3. **Couper** : Couper la sélection et la copier dans le presse-papier.
4. **Copier** : Copier la sélection dans le presse-papier sans la supprimer.
5. **Coller** : Coller le contenu du presse-papier dans le buffer à l’emplacement de la sélection.

### Version 2 (V2) : Enregistrement et relecture
1. **Start Recording** : Démarre l’enregistrement des actions utilisateur.
2. **Stop Recording** : Arrête l’enregistrement des actions.
3. **Replay** : Rejoue les commandes enregistrées.

### Version 3 (V3) : Annulation et rétablissement
1. **Undo** : Annule la dernière action.
2. **Redo** : Rétablit une action annulée.

## Instructions d’Utilisation
### Lancer le programme
1. Assurez-vous d’avoir Java 8+ installé sur votre machine.
2. Clonez ce dépôt en utilisant la commande :

   git clone [URL_DU_DEPOT]<https://gitlab2.istic.univ-rennes1.fr./fbah/editor2020>


### Commandes de l’Interface Utilisateur
L’interface utilisateur est textuelle. Voici les commandes disponibles :

| Commande   | Description                                |
|------------|--------------------------------------------|
| **insert** | Insère un texte dans le buffer.            |
| **cut**    | Coupe la sélection dans le presse-papier.  |
| **copy**   | Copie la sélection dans le presse-papier.  |
| **paste**  | Colle le contenu du presse-papier.         |
| **select** | Sélectionne une portion du texte.         |
| **start**  | Démarre l’enregistrement des commandes.   |
| **stop**   | Arrête l’enregistrement des commandes.    |
| **replay** | Rejoue les commandes enregistrées.         |
| **undo**   | Annule la dernière commande.              |
| **redo**   | Rétablit une commande annulée.           |
| **exit**   | Quitte le programme.                      |

## Résultats des Tests Unitaires
Les tests unitaires ont été réalisés avec JUnit 5 et couvrent les cas suivants :
- Insertion, suppression et modification de texte dans le buffer.
- Fonctionnement du presse-papier (copier, couper, coller).
- Enregistrement et relecture des commandes.
- Annulation et rétablissement d’actions.


## Auteurs
- Fatoumata Thioro Bah 

---
Merci de nous avoir accompagnés dans la découverte de cet éditeur minimaliste mais puissant !

