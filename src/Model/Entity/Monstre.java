package Model.Entity;

import Model.Batiment;
import Model.Case;
import Model.Deplacements.AStar;
import Model.Deplacements.DeplacementMonstre;
import Model.Grille;
import Utils.Direction;
import Utils.PairBatP;

import java.awt.*;
import java.util.ArrayList;

public class Monstre extends Entite {
    private int degats;  // le nombre de dégâts infligés par le monstre
    private int hp;  // les points de vie restants du monstre
    private boolean getAttacked;  // booléen qui indique si le monstre subit une attaque
    private Point cible;  // la cible actuelle du monstre
    private Batiment batiment;  // le bâtiment associé au monstre
    private Grille grille;  // la grille où évolue le monstre
    private Direction directionVillageois;  // la direction du villageois ciblé par le monstre
    private boolean afficheHeathBar;  // booléen qui indique si la barre de vie doit être affichée
    public static final int largeur = 12;
    public static final int hauteur = 10;;

    //Constructeur:
    /**
     * Initialise un nouveau monstre
     *
     * @param hdv le bâtiment de la mairie
     * @param x la position x du monstre
     * @param y la position y du monstre
     * @param cible la position de la cible du monstre
     * @param bat le bâtiment cible du monstre
     */
    public Monstre(Batiment hdv, int x, int y, Point cible, Batiment bat){
        super(x, y, "Monstre");
        if(hdv.getNiveau() == 1){
            // initialise les dégâts et les points de vie en fonction du niveau du HDV associé

            this.degats = 5;
            this.hp = 100;
        }
        else if(hdv.getNiveau() == 2){
            this.degats = 10;
            this.hp = 120;
        }
        else{
            this.degats = 15;
            this.hp = 150;
        }
        this.cible = cible;
        this.getAttacked = false;
        this.afficheHeathBar = false;
        this.batiment = bat;
    }

    /**
     * Déplace le monstre sur la grille donnée
     *
     * @param grille la grille sur laquelle déplacer le monstre
     */
    public void deplacer(Grille grille){
        this.grille = grille;
        ArrayList<Case> chemin = new ArrayList<>();
        int i = 0;
        while(chemin.isEmpty() && this.cible != null) {
            if(i > 0){
                PairBatP tmp = grille.EmplacementCible(this.getPosition().x, this.getPosition().y);
                if(tmp != null) {
                    this.cible = tmp.getPoint();
                    this.batiment = tmp.getBatiment();
                }
                else{
                    break;
                }
            }
            if(this.estMort() || i == 5){
                break;
            }
            i++;
            // calcule le chemin optimal pour atteindre la cible actuelle

            AStar astar = new AStar(grille, this.getPosition(), this.cible, 1); // créer un objet AStar
            if (astar.getThread() != null) {
                astar.getThread().interrupt();
            }
            Thread thread = new Thread(astar);
            thread.start();
            astar.setThread(thread);
            try {
                thread.join(); // attendre que le calcul soit terminé
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            chemin = astar.getChemin(); // récupérer le chemin calculé
        }
        if(!this.estMort() && !chemin.isEmpty()) {
            DeplacementMonstre deplacementMonstre = new DeplacementMonstre(this, grille, chemin);
            if (deplacementMonstre.getThread() != null) {
                deplacementMonstre.getThread().interrupt();
            }
            Thread thread = new Thread(deplacementMonstre);
            thread.start();
            deplacementMonstre.setThread(thread);
        }
    }
    /**

     Cette méthode vérifie si le monstre est mort ou non en vérifiant si sa valeur de points de vie (hp) est inférieure ou égale à 0.
     @return true si le monstre est mort, sinon false.
     */
    public boolean estMort(){
        return this.hp <= 0;
    }
    /**
     Fonction qui permet de changer l'état de marche du monstre en alternant entre deux valeurs (1 et 2).
     */
    public void setWalkEtat(){
        if(this.getEtatWalk() == 2){
            this.setEtatWalk(1);
        }
        else{
            this.setEtatWalk(2);
        }
    }
    /**
     Méthode poour vérifier si le monstre est attaqué.
     @return true si le monstre est attaqué, sinon false.
     */
    public boolean subitAttaque(){
        return this.getAttacked;
    }
    /**
     Cette méthode permet au monstre de subir une attaque en déduisant la valeur de points de dégâts (deg) de sa valeur de points de vie (hp).
     Elle active également l'affichage de la barre de vie.
     @param deg la valeur de points de dégâts subis par le monstre.
     */
    public void subitDegat(int deg) {
        this.afficheHeathBar = true;
        this.hp -= deg;
        this.getAttacked = true;
    }
    /**

     Méthode qui retourne une position possible aléatoire pour le villageois.
     On choisit  aléatoirement une direction parmi quatre directions possibles (haut, bas, gauche, droite),
     puis calcule une position en fonction de la position courante du monstre, de la taille de celui-ci et de la taille du villageois.
     On vérifie ensuite si la position est occupée ou non avant de la retourner.
     @return une position possible aléatoire pour le villageois.
     */

    public Point positionPourVillageois(){
        Point res = null;
        boolean fini = false;
        int x = this.getPosition().x;
        int y = this.getPosition().y;

        while(!fini) {
            int random = (int) Math.floor(Math.random() * 4 + 1);
            if (random == 1) { // Donner une position vers le haut
                res = new Point((x + (largeur/2)) - (Villageois.largeur/2), y - Villageois.hauteur-1);
                this.directionVillageois = Direction.up;
            }
            else if (random == 2) { // Donner une position vers le bas
                res = new Point((x + (largeur/2)) - (Villageois.largeur/2), y+hauteur+1);
                this.directionVillageois = Direction.down;
            }
            else if (random == 3) { // Donner une position vers la gauche
                res = new Point(x-Villageois.largeur-1, y + (Villageois.hauteur /2));
                this.directionVillageois = Direction.right;
            }
            else if (random == 4) { // Donner une position vers la droite
                res = new Point((x+largeur+1), y + (Villageois.hauteur/2));
                this.directionVillageois = Direction.left;
            }
            if(res != null && positionPossible(res.x, res.y)){
                fini = true;
            }
        }
        return res;
    }
    /**

     Cette méthode vérifie si une position donnée est libre, c'est-à-dire si aucun villageois n'occupe cette case.
     @param x la coordonnée x de la position à vérifier
     @param y la coordonnée y de la position à vérifier
     @return vrai si la position est libre, faux sinon
     */
    private boolean positionPossible(int x, int y){
        boolean res = true;
        for(int i = x; i < x + Villageois.largeur; i++){
            for(int j = y; j < y + Villageois.hauteur; j++){
                res = res && !this.grille.positionOccupee(i,j);
            }
        }
        return res;
    }

    //Guetters:
    /**

     Renvoie la cible actuelle du villageois.
     @return la cible actuelle du villageois
     */

    public Point getCible() {
        return cible;
    }
    /**

     Cette méthode renvoie la direction actuelle du villageois.
     @return la direction actuelle du villageois
     */
    public Direction getDirectionVillageois() {
        return directionVillageois;
    }
    /**

     Cette méthode renvoie le bâtiment associé au villageois.
     @return le bâtiment associé au villageois
     */
    public Batiment getBatiment() {
        return batiment;
    }
    /**

     Cette méthode renvoie vrai si la barre de vie du villageois doit être affichée, faux sinon.
     @return vrai si la barre de vie du villageois doit être affichée, faux sinon
     */
    public boolean getAfficheHeathBar(){
        return this.afficheHeathBar;
    }
    /**

     Cette méthode renvoie les points de vie actuels du villageois.
     @return les points de vie actuels du villageois
     */
    public int getHp() {
        return hp;
    }
    /**

     Cette méthode renvoie les dégâts infligés par le villageois.
     @return les dégâts infligés par le villageois
     */
    public int getDegats() {
        return degats;
    }

    //Setters:
    /**

     La méthode modifie le bâtiment associé au villageois.
     @param batiment le nouveau bâtiment associé au villageois
     */
    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }
    /**

     Cette méthode modifie la cible actuelle du villageois.
     @param cible la nouvelle cible du villageois
     */
    public void setCible(Point cible) {
        this.cible = cible;
    }
    /**

     Cette méthode modifie l'état d'attaque du villageois.
     @param getAttacked le nouvel état d'attaque du villageois
     */
    public void setGetAttacked(boolean getAttacked) {
        this.getAttacked = getAttacked;
    }
    /**
     Cette méthode modifie l'affichage de la barre de vie du villageois.
     @param afficheHeathBar le nouvel affichage de la barre de vie du villageois
     */
    public void setAfficheHeathBar(boolean afficheHeathBar) {
        this.afficheHeathBar = afficheHeathBar;
    }
}
