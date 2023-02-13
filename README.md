# Mise en situation
Le son du cocher vous prévenant de votre arrivée imminente vous tire des bras de Morphée. Cela fait maintenant 3 jours que vous voyagez et ce n'est qu'à présent que vous réalisez que ce travail dont vous rêvez depuis tout.e petit.e est enfin à porter de main. Plus que jamais, vous sentez que vous êtes prêt.e à affronter ce que la vie vous réserve. Vous avez toujours voulu être programmeur informatique à la glorieuse Guilde des Programmes Unis (GPU) et rien ne peut plus vous en empêcher! 
... 
Une fois installé.e, vous vous rendez au GPU afin de commencer votre première journée. C'est à ce moment que vous apprenez que votre mandat sera difficile, car le système de communication entre votre succursale et le siège social est à refaire complètement. Celui-ci s'occupe de traiter les commandes du siège social, c'est-à-dire l'embauche de Héros, l'entraînement de Héros, l'affectation de quêtes, la répartition du butin et l'achat d'armures. Si cela n'était pas suffisant, vous apprenez que vous n'avez que 3 semaines pour rendre un produit fini! Vous avez déjà trop sacrifié.e pour cet emploi, impossible de faire marche arrière. Il vous faudra affronter vos peurs et vous surpasser, mais vous êtes confiant.e. Vous vous retroussez les manches et vous poursuivez la lecture du dossier.

# Les instructions
1. buy-hero
  - Format de l'instruction:
    a. Nom du héro {string}
    b. Catégorie (common=0, uncommon=1, rare=2, epic=3, legendary=4) {int}
    c. Coût en argent {double}
    d. Coût en armures {int}
    e. Points de vie {double}

2. buy-armure
  - Format de l'instruction:
    a. Nombre d'armures achetées {int}
    b. Prix par armure {int}

3. do-quest
  - Format de l'instruction:
    a. Catégorie (common=0, uncommon=1, rare=2, epic=3, legendary=4) {int}
    b. Coût en points de vie {double}
    c. Récompense en argent {int}
    d. Récompense en armures {int}

4. train-hero
  - Format de l'instruction:
    a. Nom du héro {string}
    
 # Structure du programme
Avant de commencer à programmer, il faudra mettre en place la structure de votre programme. Pour ce devoir, nous vous imposerons plus de liberté dans la structure de celui-ci. Pourquoi ? Parce qu’il est important que vous soyez en mesure d’écrire un code structuré par vous-même.

Nous vous demandons uniquement d’avoir quelques fichiers et quelques fonctions. Vous devrez enovoyer ces fichier .java pour votre remise:
* Un fichier pour votre code de départ (Main.java)
* Un fichier de gestion qui gère l'inventaire (Bank)
* Deux fichier de gestion pour les commandes (GuildCommand.java & GuildCommandSystem.java)
* Un fichier pour chaque niveau de héros
* Un fichier permettant le lancement des quêtes(Quete.java)
* Un fichier qui regroupe toutes les informations en lien avec le guild(Guild.java)
