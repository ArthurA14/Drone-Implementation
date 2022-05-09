# Projet Scala S8

## Client :  

Peaceland est un pays béni, dirigé par un dirigeant affable et clairvoyant. Il est très fièr de ses efforts pour apporter la paix, le bonheur et l'harmonie à tous ses citoyens. Pour ce faire, il compte sur ses artisans de la paix. Une agence gouvernementale dédiée à la paix dans le pays. Pour atteindre leur ambition, ils portent assistance à toute personne agitée/derangée/dangereuse et l'aident à retrouver la paix. Plus généralement, ils aident les citoyens à rester en phase avec l'objectif d'harmonie de leur pays. Pour aider les (peacemakers) pacificateurs, les ingénieurs de Peaceland ont créé un drone autonome appelé ‘peacewatcher’. Ils ont besoin que vous créiez le programme qui recevra et gérera les données des peacewatchers. 

Ce programme doit :  
- stocker toutes les données des peacewatchers, 
- déclencher des alertes 
- permettre aux officiers de peacemaker d'effectuer des analyses sur les données des peacewatchers. 
 
## Description drone :  

Chaque peacewatcher envoie un rapport toutes les minutes. Un rapport contient : 
- l'identifiant du peacewatcher 
- l'emplacement actuel du peacewatcher (latitude, longitude) 
- le nom du citoyen environnant (identifié par reconnaissance faciale) avec son "peacescore" 
- les mots entendus par le peacewatcher dans son environnement. 
 
## Alerte :  

Lorsqu'un citoyen a un mauvais score (mauvais peacescore), votre programme doit déclencher une alerte avec la localisation du drone (peacewatcher) et le nom du citoyen agité. Les pacificateurs (peacemakers) prendront le relais et aideront la personne à trouver la paix. Ils peuvent l'envoyer dans un camp de paix. Dans ce camp, le citoyen apprend à atteindre le bonheur en suivant les idées du leader bienveillant de Peaceland. Ou sinon, ils le mettront dans un état de paix sans fin. Cette alerte doit être déclenchée le plus rapidement possible car un citoyen agité peut propager son manque de paix à d'autres citoyens. Ainsi, la réaction du pacificateur (peacemaker) doit être aussi rapide que possible. 
 
## Statistique :  

Les (peacemakers) sont convaincus qu'il faut conserver tous les rapports des (peacewatcher) afin d'établir des statistiques et d'améliorer l'harmonie de la paix. Mais ils ne savent toujours pas quels types de rapports demandés/stats/rapports demander. 
Les ingénieurs de Peaceland estiment que lorsque la première vague de Peacewatcher sera opérationnelle, la somme de tous leurs rapports quotidiens pèsera 200 Go. Ils estiment également que moins de 1 % des rapports de Peacewatcher contiendront des alertes. 
 
## Tentative ratée :  

Pour créer un POC (proof of concept) du programme, Peaceland a engagé une équipe de data scientist spécialisés dans les données et malgré tous leurs efforts, cette équipe n'a pas été en mesure de mettre en place un programme évolutif capable de gérer la charge. (il ont pas été capable de set up un programme scalable pouvant supporter la charge). 
 
## Projet : 

Peaceland comprend que cela dépasse les limites de leur équipe, elle ne peut pas mettre en place un programme pour traiter les données du drone. Peaceland vous demande conseil pour concevoir une architecture leur permettant de créer un produit qu'ils pourraient vendre à différentes forces de police. C'est à vous de signaler et de recommander la bonne architecture.  

Sur la base des questions préliminaires, votre solution comprendra très probablement : 
- au moins un stockage distribué 
- au moins un flux distribué (une stream distribuée) 
- au moins deux consommateurs de flux (stream consumer)

