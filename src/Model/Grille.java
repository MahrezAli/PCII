package Model;

import Model.Entity.Monstre;
import Model.Entity.Villageois;
import Model.Potager.Potager;
import Utils.PairBatP;
import Vue.Affichage;
import Vue.VueCase;

import java.awt.*;
import java.util.*;

// Classe représentant la grille du jeu
public class Grille extends Observable {
    // Constante représentant la hauteur de la grille
    public static final int HAUTEUR = Affichage.FENETRE_HAUTEUR / VueCase.tailleCase;
    // Constante représentant la largeur de la grille
    public static final int LARGEUR = Affichage.FENETRE_LARGEUR / VueCase.tailleCase;

    // Tableau de cases représentant la grille
    private Case[][] cases;

    // Liste de villageois dans le jeu
    private ArrayList<Villageois> villageois;
    // Liste des monstres dans le jeu
    private ArrayList<Monstre> monstres;
    // Hôtel de ville sur la grille
    private Batiment hotelVille;
    // Liste de cabanes sur la grille
    private ArrayList<Batiment> cabanes;
    // Liste d'arbres sur la grille
    private ArrayList<Arbre> arbres;
    // Liste de défenses sur la grille
    private ArrayList<Defense> defenses;
    // Liste de potagers sur la grille
    private ArrayList<Potager> potagers;
    private ArrayList<Argent> argents;

    private Batiment potagerEmplacement;
    private Batiment defenseEmplacement;
    private Batiment cabaneEmplacement;
    private ArrayList<Batiment>selectBatiment;

    public Grille(ArrayList<Villageois> villageois){
        this.selectBatiment = new ArrayList<>();
        //Création d'une grille de cases :
        this.cases = new Case[LARGEUR][HAUTEUR];
        //Parcours des cases pour les instancier :
        for(int i = 0; i < LARGEUR; i++){
            for(int j = 0; j < HAUTEUR; j++){
                this.cases[i][j] = new Case(i, j);
            }
        }

        //Déclaration des arbres :
        this.arbres = new ArrayList<>();
        //Parcours de la grille pour placer les arbres :
        for(int i = 0; i < Grille.LARGEUR; i = i + Arbre.LARGEUR){
            Arbre a = new Arbre(new Point(i, 0), "Arbre");
            this.arbres.add(a);
            for(int i1 = i; i1 < i + Arbre.LARGEUR; i1++){
                for(int i2 = 0; i2 < Arbre.HAUTEUR; i2++){
                    this.cases[i1][i2].setBatiment(a);
                    this.cases[i1][i2].setOccupee(true);
                }
            }
        }
        for(int i = 0; i < Grille.LARGEUR; i = i + Arbre.LARGEUR){
            Arbre a = new Arbre(new Point(i, Grille.HAUTEUR-Arbre.HAUTEUR), "Arbre");
            this.arbres.add(a);
            for(int i1 = i; i1 < i + Arbre.LARGEUR; i1++){
                for(int i2 = Grille.HAUTEUR-Arbre.HAUTEUR; i2 < Grille.HAUTEUR; i2++){
                    this.cases[i1][i2].setBatiment(a);
                    this.cases[i1][i2].setOccupee(true);
                }
            }
        }
        for(int j = Arbre.HAUTEUR; j < Grille.HAUTEUR - Arbre.HAUTEUR; j = j + Arbre.HAUTEUR){
            Arbre a = new Arbre(new Point(0, j), "Arbre");
            this.arbres.add(a);
            for(int i2 = j; i2 < j + Arbre.HAUTEUR; i2++){
                for(int i1 = 0; i1 < Arbre.LARGEUR; i1++){
                    this.cases[i1][i2].setBatiment(a);
                    this.cases[i1][i2].setOccupee(true);
                }
            }
        }
        for(int j = Arbre.HAUTEUR; j < Grille.HAUTEUR - Arbre.HAUTEUR; j = j + Arbre.HAUTEUR){
            Arbre a = new Arbre(new Point(Grille.LARGEUR-Arbre.LARGEUR, j), "Arbre");
            this.arbres.add(a);
            for(int i2 = j; i2 < j + Arbre.HAUTEUR; i2++){
                for(int i1 = Grille.LARGEUR-Arbre.LARGEUR; i1 < Grille.LARGEUR; i1++){
                    this.cases[i1][i2].setBatiment(a);
                    this.cases[i1][i2].setOccupee(true);
                }
            }
        }
        //Déclaration de l'hôtel de ville :
        Point positionHotelVille = new Point((Grille.LARGEUR/2), (Grille.HAUTEUR/2));
        this.hotelVille = new Batiment(positionHotelVille, "HotelVille", 26, 33);
        //Redéfinition de la position de l'hôtel de ville :
        positionHotelVille = new Point((Grille.LARGEUR/2) - (this.hotelVille.getLargeur()/2), (Grille.HAUTEUR/2)- (this.hotelVille.getHauteur()/2));
        this.hotelVille.setPosition(positionHotelVille);
        //Parcours des cases associées à l'hôtel de ville :
        for(int i = positionHotelVille.x; i < positionHotelVille.x + this.hotelVille.getLargeur(); i++){
            // Boucle pour parcourir les colonnes de l'hôtel de ville
            for(int j = positionHotelVille.y; j < positionHotelVille.y + this.hotelVille.getHauteur(); j++){
                // Définit l'objet bâtiment pour chaque case
                this.cases[i][j].setBatiment(this.hotelVille);
                // Indique que la case est occupée
                this.cases[i][j].setOccupee(true);
            }
        }
        this.selectBatiment.add(this.hotelVille);


        //Déclaration des cabanes :
        this.cabanes = new ArrayList<>();
        Point positioncabane = new Point((Grille.LARGEUR/2), (Grille.HAUTEUR/2));
        this.cabanes.add(new Batiment(positioncabane, "Cabane", 22, 21));
        // Définit la position de la première cabane
        positioncabane = new Point((Grille.LARGEUR/3) - (this.cabanes.get(0).getLargeur()/2), (Grille.HAUTEUR/3)- (this.cabanes.get(0).getHauteur()/2));
        this.cabanes.get(0).setPosition(positioncabane);
        for(int i = positioncabane.x; i < positioncabane.x + this.cabanes.get(0).getLargeur(); i++) {
            // Boucle pour parcourir les colonnes de la première cabane
            for (int j = positioncabane.y; j < positioncabane.y + this.cabanes.get(0).getHauteur(); j++) {
                // Définit l'objet bâtiment pour chaque case
                this.cases[i][j].setBatiment(this.cabanes.get(0));
                // Indique que la case est occupée
                this.cases[i][j].setOccupee(true);
            }
        }
        this.selectBatiment.add(this.cabanes.get(0));

        // Initialisation de la liste de défenses
        this.defenses = new ArrayList<>();
        // Initialisation de la position de la première défense à (0,0)
        Point positionDefense = new Point(0,0);
        // Ajout de la première défense à la liste avec les paramètres données
        this.defenses.add(new Defense(this, positionDefense, "Defense", 17,29, 5,40));
        // Ajout de la deuxième défense à la liste avec les paramètres données
        this.defenses.add(new Defense(this, positionDefense, "Defense", 17,29, 5,40));
        // Mise à jour de la position de la première défense
        positionDefense = new Point((2*Grille.LARGEUR/3) - (this.defenses.get(0).getLargeur()/2), (Grille.HAUTEUR/3) - (this.defenses.get(0).getHauteur()/2));;
        this.defenses.get(0).setPosition(positionDefense);
        // Mise à jour de la position de la deuxième défense
        positionDefense = new Point((Grille.LARGEUR/3) - (this.defenses.get(1).getLargeur()/2), (2*Grille.HAUTEUR/3) - (this.defenses.get(1).getHauteur()/2));;
        this.defenses.get(1).setPosition(positionDefense);
        // Mise à jour des cases associées aux défenses pour indiquer qu'elles sont occupées par des bâtiments
        for(int i = 0; i < this.defenses.size(); i++) {
            for (int i1 = this.defenses.get(i).getPosition().x; i1 < this.defenses.get(i).getPosition().x + this.defenses.get(i).getLargeur(); i1++) {
                for (int j = this.defenses.get(i).getPosition().y; j < this.defenses.get(i).getPosition().y + this.defenses.get(i).getHauteur(); j++) {
                    this.cases[i1][j].setBatiment(this.defenses.get(i));
                    this.cases[i1][j].setOccupee(true);
                }
            }
        }
        this.selectBatiment.add(this.defenses.get(0));
        this.selectBatiment.add(this.defenses.get(1));

        // Initialisation de la liste des potagers
        this.potagers = new ArrayList<>();
        // Définition de la position initiale pour le potager
        Point positionPotager = new Point(3*Grille.LARGEUR/4,3*Grille.HAUTEUR/4);
        // Ajout d'un nouveau potager à la liste avec des dimensions de 3 x 3
        this.potagers.add(new Potager(this, positionPotager));
        // Boucle pour définir les cases occupées par le potager
        for(int i = 0; i < this.potagers.size(); i++) {
            // Boucle pour parcourir les lignes du potager
            for (int i1 = this.potagers.get(i).getPosition().x; i1 < this.potagers.get(i).getPosition().x + Potager.largeur; i1++) {
                // Boucle pour parcourir les colonnes du potager
                for (int j = this.potagers.get(i).getPosition().y; j < this.potagers.get(i).getPosition().y + Potager.hauteur; j++) {
                    // Définition de la case actuelle comme occupée par un bâtiment (potager)
                    this.cases[i1][j].setPotager(this.potagers.get(i));
                    this.cases[i1][j].setOccupee(true);
                }
            }
        }

        // Déclaration des villageois
        // Initialisation de la liste des villageois
        this.villageois = villageois;
        // Définition du nombre initial de villageois en fonction du nombre de cabanes disponibles
        int nombreInitVillageois = this.cabanes.size();
        // Boucle pour ajouter autant de villageois que de cabanes disponibles
        for(int i = 0; i < nombreInitVillageois*2 ; i++){
            // Génération d'une position aléatoire pour chaque villageois
            Point positionVillageois = genererPoint();
            while(!this.positionPossible(positionVillageois.x, positionVillageois.y, "Villageois")){
                positionVillageois = genererPoint();
            }
            // Ajout du villageois à la liste de villageois
            this.villageois.add(new Villageois(positionVillageois.x, positionVillageois.y));
        }
        // Boucle pour définir la position de chaque villageois dans le tableau de cases
        for(int i = 0; i < this.villageois.size(); i++){
            int x = this.villageois.get(i).getPosition().x;
            int y = this.villageois.get(i).getPosition().y;
            for(int i1 = x; i1 < x + Villageois.largeur; i1++){
                for(int i2 = y; i2 < y + Villageois.hauteur; i2++){
                    this.cases[i1][i2].setVillageois(this.villageois.get(i));
                }
            }
        }

        this.monstres = new ArrayList<>(); // initialiser la liste des monstres
        this.argents = new ArrayList<>();

        this.potagerEmplacement = new Batiment(new Point(Arbre.LARGEUR,Arbre.HAUTEUR), "potagerEmplacement", Potager.largeur, Potager.hauteur);
        this.cabaneEmplacement = new Batiment(new Point(Arbre.LARGEUR,Arbre.HAUTEUR), "cabaneEmplacement", 22, 21);
        this.defenseEmplacement = new Batiment(new Point(Arbre.LARGEUR,Arbre.HAUTEUR), "defenseEmplacement", 17,29);
    }

    public boolean resteBatimentsADetruire(){
        for(int i = 0; i< this.defenses.size(); i++){
            if(!this.defenses.get(i).estDetruit()){
                return true;
            }
        }
        for(int i = 0; i< this.cabanes.size(); i++){
            if(!this.cabanes.get(i).estDetruit()){
                return true;
            }
        }
        if(!this.hotelVille.estDetruit()){
            return true;
        }
        return false;
    }

    //Méthode pour définir un changement et notifier les observateurs
    public void setChangedAndNotify() {
        setChanged(); //définit un changement
        notifyObservers(); //notifie les observateurs
    }

    //Méthode pour trouver le batiment le plus proche d'un certain point
    public Point batimentCible(int x, int y){
        Point res = null;
        ArrayList<Point> positionsBatiments = new ArrayList<>();
        if(!this.hotelVille.estDetruit()) {
            positionsBatiments.add(this.hotelVille.getPosition());
        }
        for(int i = 0; i < this.cabanes.size(); i++){ // Rajouter les positions des cabanes
            if(!this.cabanes.get(i).estDetruit()) {
                positionsBatiments.add(this.cabanes.get(i).getPosition());
            }
        }
        for(int i = 0; i < this.defenses.size(); i++){ // Rajouter les positions des cabanes
            if(!this.defenses.get(i).estDetruit()) {
                positionsBatiments.add(this.defenses.get(i).getPosition());
            }
        }
        double distanceMinimum = 100000000;
        for(int i = 0; i < positionsBatiments.size(); i++){
            int posX = positionsBatiments.get(i).x;
            int posY = positionsBatiments.get(i).y;
            double posXP = Math.pow(x-posX, 2);
            double posYP = Math.pow(y-posY,2);
            double tmp = Math.sqrt(posXP+posYP);
            if(tmp < distanceMinimum){
                distanceMinimum = tmp;
                res = positionsBatiments.get(i);
            }
        }
        return res;
    }

    public PairBatP EmplacementCible(int x, int y){
        Point cible = this.batimentCible(x, y);
        if(cible != null) {
            Batiment tmp = null;
            if (cible.x == this.hotelVille.getPosition().x && cible.y == this.hotelVille.getPosition().y) {
                tmp = this.hotelVille;
            } else {
                for (int i = 0; i < this.cabanes.size(); i++) {
                    if (cible.x == this.cabanes.get(i).getPosition().x && cible.y == this.cabanes.get(i).getPosition().y) {
                        tmp = this.cabanes.get(i);
                        break;
                    }
                }
                for (int i = 0; i < this.defenses.size(); i++) { // Rajouter les positions des cabanes
                    if (cible.x == this.defenses.get(i).getPosition().x && cible.y == this.defenses.get(i).getPosition().y) {
                        tmp = this.defenses.get(i);
                        break;
                    }
                }
            }
            ArrayList<Point> res = new ArrayList<>();

            //Verifier toutes les positions possibles vers le haut du batiment
            for (int i = cible.x; i < cible.x + tmp.getLargeur(); i++) {
                if (positionPossible(i, cible.y - Monstre.hauteur - 1, "Monstre")) {
                    res.add(new Point(i, cible.y - Monstre.hauteur - 1));
                }
            }

            //Verifier toutes les positions possibles vers le bas du batiment
            for (int i = cible.x; i < cible.x + tmp.getLargeur(); i++) {
                if (positionPossible(i, cible.y + tmp.getHauteur() + 1, "Monstre")) {
                    res.add(new Point(i, cible.y + tmp.getHauteur() + 1));
                }
            }

            //Verifier toutes les positions possibles vers la gauche du batiment
            for (int j = cible.y; j < cible.y + tmp.getHauteur(); j++) {
                if (positionPossible(cible.x - Monstre.largeur - 1, j, "Monstre")) {
                    res.add(new Point(cible.x - Monstre.largeur - 1, j));
                }
            }

            //Verifier toutes les positions possibles vers la droite du batiment
            for (int j = cible.y; j < cible.y + tmp.getHauteur(); j++) {
                if (positionPossible(cible.x + tmp.getLargeur() + 1, j, "Monstre")) {
                    res.add(new Point(cible.x + tmp.getLargeur() + 1, j));
                }
            }

            double distanceMinimum = 100000000;
            Point result = null;
            for (int i = 0; i < res.size(); i++) {
                int posX = res.get(i).x;
                int posY = res.get(i).y;
                double posXP = Math.pow(x - posX, 2);
                double posYP = Math.pow(y - posY, 2);
                double tmp2 = Math.sqrt(posXP + posYP);
                if (tmp2 < distanceMinimum) {
                    distanceMinimum = tmp2;
                    result = res.get(i);
                }
            }

            PairBatP resultat = new PairBatP(result, tmp);

            return resultat;
        }
        else{
            return null;
        }
    }

    public boolean tousLesBatimentsDetruits(){
        if(!this.hotelVille.estDetruit()){
            return false;
        }
        for(int i = 0; i < this.cabanes.size(); i++){
            if(!this.cabanes.get(i).estDetruit()){
                return false;
            }
        }
        for(int i = 0; i < this.defenses.size(); i++){
            if(!this.defenses.get(i).estDetruit()){
                return false;
            }
        }
        return true;
    }



    //Setters:

    //Méthode pour ajouter des monstres
    public void addMonstres(){
        if(!this.tousLesBatimentsDetruits()) {
            boolean fini = false;
            Point res = null;
            //Determiner la coordonnée d'apparition du monstre
            while (!fini) {
                int idArbre = genererInt(0, this.arbres.size() - 1);
                Point posA = this.arbres.get(idArbre).getPosition();
                if (posA.x == 0) {
                    if (posA.y == 0) {
                        res = new Point(Arbre.LARGEUR + 1, Arbre.HAUTEUR + 1);
                    } else if (posA.y == Grille.HAUTEUR - Arbre.HAUTEUR) {
                        res = new Point(Arbre.LARGEUR + 1, posA.y - Monstre.hauteur - 1);
                    } else {
                        res = new Point(Arbre.LARGEUR + 1, posA.y);
                    }
                } else if (posA.x == Grille.LARGEUR - Arbre.LARGEUR) {
                    if (posA.y == 0) {
                        res = new Point(posA.x - Monstre.largeur - 1, Arbre.HAUTEUR + 1);
                    } else if (posA.y == Grille.HAUTEUR - Arbre.HAUTEUR) {
                        res = new Point(posA.x - Monstre.largeur - 1, posA.y - Monstre.hauteur - 1);
                    } else {
                        res = new Point(posA.x - Monstre.largeur - 1, posA.y);
                    }
                } else {
                    if (posA.y == 0) {
                        res = new Point(posA.x, Arbre.HAUTEUR + 1);
                    } else {
                        res = new Point(posA.x, posA.y - Monstre.hauteur - 1);
                    }
                }
                if (this.positionPossible(res.x, res.y, "Monstre")) {
                    fini = true;
                }
            }
            PairBatP p = this.EmplacementCible(res.x, res.y);
            Point cible = p.getPoint();
            Batiment bat = p.getBatiment();
            this.monstres.add(new Monstre(this.hotelVille, res.x, res.y, cible, bat));
            int x = this.monstres.get(this.monstres.size() - 1).getPosition().x;
            int y = this.monstres.get(this.monstres.size() - 1).getPosition().y;
            for (int i1 = x; i1 < x + Monstre.largeur; i1++) {
                for (int i2 = y; i2 < y + Monstre.hauteur; i2++) {
                    this.cases[i1][i2].setMonstres(this.monstres.get(this.monstres.size() - 1));
                }
            }
        }
    }

    // Methode pour ajouter des cabanes
    public void addCabanes(Point p) {
        // Verification pour ne pas avoir plus de 5 cabanes
        Batiment cabane = new Batiment(p, "Cabane", this.cabanes.get(0).getLargeur(), this.cabanes.get(0).getHauteur());
        this.cabanes.add(cabane);
        this.selectBatiment.add(cabane);
        for(int i = p.x; i < p.x + this.cabanes.get(0).getLargeur(); i++){
            for(int j = p.y; j < p.y + this.cabanes.get(0).getHauteur(); j++){
                this.cases[i][j].setEmplacementBat(false);
                this.cases[i][j].setOccupee(true);
            }
        }
        Point v1 = new Point(p.x+this.cabanes.get(0).getLargeur()+1, p.y+this.cabanes.get(0).getHauteur());
        this.villageois.add(new Villageois(v1.x, v1.y));
        Point v2 = new Point(p.x-Villageois.largeur, p.y+this.cabanes.get(0).getHauteur());
        this.villageois.add(new Villageois(v2.x, v2.y));
        this.cabaneEmplacement.setPosition(new Point(Arbre.LARGEUR, Arbre.HAUTEUR));
    }

    // Methode pour ajouter des potager
    public void addPotagers(Point p) {
        // Verification pour ne pas avoir plus de 5 cabanes
        Potager potager = new Potager(this, p);
        this.potagers.add(potager);
        for(int i = p.x; i < p.x + Potager.largeur; i++){
            for(int j = p.y; j < p.y + Potager.hauteur; j++){
                this.cases[i][j].setEmplacementBat(false);
                this.cases[i][j].setOccupee(true);
            }
        }
        this.potagerEmplacement.setPosition(new Point(Arbre.LARGEUR, Arbre.HAUTEUR));
    }

    // Methode pour ajouter des défenses
    public void addDefenses(Point p) {
        // Verification pour ne pas avoir plus de 5 cabanes
        Defense defense = new Defense(this, p, "Defense",  this.defenses.get(0).getLargeur(),this.defenses.get(0).getHauteur(), this.defenses.get(0).getDegat(),this.defenses.get(0).getRayon());
        this.defenses.add(defense);
        this.selectBatiment.add(defense);
        for(int i = p.x; i < p.x + this.defenses.get(0).getLargeur(); i++){
            for(int j = p.y; j < p.y + this.defenses.get(0).getHauteur(); j++){
                this.cases[i][j].setEmplacementBat(false);
                this.cases[i][j].setOccupee(true);
            }
        }
        this.defenseEmplacement.setPosition(new Point(Arbre.LARGEUR, Arbre.HAUTEUR));
    }


    // Fonction pour générer un entier aléatoire entre une borne inférieure et une borne supérieure
    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        // Calcule l'entier aléatoire en ajoutant la borne inférieure
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;
    }
    public boolean positionPossible(int x, int y, String quiFaitAppel){
        boolean res = true;
        if("Villageois".equals(quiFaitAppel)) {
            for (int i = x; i < x + Villageois.largeur; i++) {
                res = res && !this.positionOccupee(i, y);
            }
            for (int j = y; j < y + Villageois.hauteur; j++) {
                res = res && !this.positionOccupee(x, j);
            }
        }
        else if("Monstre".equals(quiFaitAppel)){
            for (int i = x; i < x + Monstre.largeur; i++) {
                res = res && !this.positionOccupee(i, y);
            }
            for (int j = y; j < y + Monstre.hauteur; j++) {
                res = res && !this.positionOccupee(x, j);
            }
        }
        return res;
    }

    // Fonction pour vérifier si une position est occupée
    public boolean positionOccupee(int x, int y){
        return this.cases[x][y].isOccupee();
    }

    // Fonction pour générer un point aléatoire
    public Point genererPoint(){
        int x = genererInt(0, LARGEUR);
        int y = genererInt(0, HAUTEUR);
        // Boucle pour vérifier si la position générée est déjà occupée
        while(positionOccupee(x, y)){
            x = genererInt(0, LARGEUR);
            y = genererInt(0, HAUTEUR);
        }
        return new Point(x, y);
    }

    //Guetters:
    public ArrayList<Monstre> getMonstre() {
        return this.monstres;
    }

    public ArrayList<Potager> getPotagers() {
        return potagers;
    }
    public ArrayList<Villageois> getVillageois() {
        return this.villageois; //renvoie la liste des villageois
    }

    public Case[][] getCases() {
        return this.cases; //renvoie la grille de cases
    }

    public ArrayList<Arbre> getArbres() {
        return arbres;
    }

    public ArrayList<Batiment> getCabanes() {
        return cabanes; //renvoie la liste des cabanes
    }

    public ArrayList<Defense> getDefenses() {
        return defenses;
    }

    public Batiment getHotelVille() {
        return hotelVille;
    }

    public ArrayList<Argent> getArgents() {
        return argents;
    }

    public Batiment getCabaneEmplacement() {
        return cabaneEmplacement;
    }

    public Batiment getDefenseEmplacement() {
        return defenseEmplacement;
    }

    public Batiment getPotagerEmplacement() {
        return potagerEmplacement;
    }

    public ArrayList<Batiment> getSelectBatiment() {
        return selectBatiment;
    }



    public void setSelectBatiment(ArrayList<Batiment> selectBatiment) {
        this.selectBatiment = selectBatiment;
    }


}
