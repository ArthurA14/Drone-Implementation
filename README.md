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
