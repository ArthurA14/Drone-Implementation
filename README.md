# Projet Ecole Scala

## Client :  

Ce projet ***ACADÉMIQUE FICTIF*** porte sur la création d'un drone anti-émeute intelligent. 

Ce programme doit :  
- stocker toutes les données des drones
- déclencher des alertes 
- permettre aux agents de maintien de l'ordre d'effectuer des analyses sur les données des drones
 
## Description drone :  

Chaque drone envoie un rapport toutes les minutes. Un rapport contient : 
- l'identifiant du drone 
- l'emplacement actuel du drone (latitude, longitude) 
- le nom du citoyen environnant (identifié par reconnaissance faciale) avec son "score" 
- les mots entendus par le drone dans son environnement 
 
## Alerte :  

Lorsqu'un citoyen a un faible score, votre programme doit déclencher une alerte avec la localisation du drone et le nom du citoyen.
Cette alerte doit être déclenchée le plus rapidement possible, pour éviter la propagation de l'émeute. 
 
## Statistique :

Les agents de maintien de l'ordre souhaitent conserver tous les rapports des drones afin d'établir des statistiques et d'améliorer leur temps de réaction.
Cependant, ils ne savent toujours pas quels types de questions/stats/rapports demander. 
Les ingénieurs estiment que lorsque la première vague de drones sera opérationnelle, la somme de tous leurs rapports quotidiens pèsera 200 Go.
Ils estiment également que moins de 1% des rapports de drone contiendront des alertes. 
 
## Tentative ratée :  

Pour créer un POC (proof of concept) du programme, le client a engagé une équipe de data scientists spécialisés dans les données et malgré tous leurs efforts, cette équipe n'a pas été en mesure de mettre en place un programme évolutif capable de gérer la charge (ils n'ont pas été capables de set up un programme scalable pouvant supporter la charge). 
 
## Projet : 

Le client comprend que cela dépasse les limites de son équipe, elle ne peut donc pas mettre en place un programme pour traiter les données du drone.
Il vous demande donc conseil afin de concevoir une architecture lui permettant de créer un produit qu'ils pourraient vendre à différentes forces de police.
C'est à vous de signaler et de recommander la bonne architecture.  

Sur la base des questions préliminaires, votre solution comprendra très probablement : 
- au moins un stockage distribué 
- au moins un flux distribué (une stream distribuée) 
- au moins deux consommateurs de flux (stream consumer)

---

# Setup du projet :

1. Télécharger Kafka : https://kafka.apache.org/quickstart

2. Aller dans le dossier kafka :

```bash
arthur@DESKTOP-OBTCMJQ:/mnt/c$
cd users/arthu/Efrei/DATA_ENGINEER/kafka_2.13-3.1.0
```

- A CHAQUE FOIS DANS UN TERMINAL DIFFÉRENT

- start le service Zookeeper : ```bin/zookeeper-server-start.sh config/zookeeper.properties```

- start le broker Kafka : ```bin/kafka-server-start.sh config/server.properties``` 

Kafka est une plate-forme de diffusion d'événements distribuée qui vous permet de stocker et de traiter des événements sur de nombreuses machines.

- si il n'existe pas encore, créer un topic pour stocker les messages dans la stream : ```bin/kafka-topics.sh --create --topic reports --bootstrap-server localhost:9092```

Note : On créé un topic nommer reports et on le connecte à Kafka. 

3. Aller dans le repo du projet :

- A CHAQUE FOIS DANS UN TERMINAL DIFFÉRENT

- taper sbt run et choisir Producer (on génère les rapports)

- taper sbt run et choisir Consumer (on consomme les rapports)

- taper sbt run et choisir Storage (on envoie les donnees dans HDFS)

- taper sbt run et choisir Analysis (on lance l'analyse des données)
