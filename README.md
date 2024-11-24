# Psyche
Ce jeu a était réaliser pour un projet d'étudiant, c'est un jeu de plateau ou il faut récupère le plus de mine en créant des route 


# Lancement du jeu 
Ouvrez votre invite de commandes sur votre ordinateur 


Pour compiler et executer le jeu de base , rendez-vous dans PSYCHE_equipe A4_B4 :

 cd PSYCHE_equipe\ A4_B4/

Et mettez la commande suivante : 

javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur && cd ..

Pour compiler et executer le scénario , rendez-vous dans PSYCHE_equipe A4_B4 et mettez la commande suivante: 

javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur "nom_scenario.run"  && cd class

Pour rappel, il y'a comme fichier scénario :

scenario_1.run
scenario_2.run
scenario_3.run

Donc pour le scénario 1 :

javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur "scenario_1.run"  && cd class

Donc pour le scénario 2 : (c'est le jeu complet, commencer à l'etape 26 pour voir la fin)

javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur "scenario_2.run"  && cd class

Donc pour le scénario 3 :

javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur "scenario_3.run"  && cd class
