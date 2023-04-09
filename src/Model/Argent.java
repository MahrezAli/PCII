package Model;

import Model.Entity.Villageois;
import Utils.Direction;

import javax.swing.text.Position;
import java.awt.*;

public class Argent implements Runnable{
    /**
     * La position de l'argent sur la grille.
     */
    private Point position;
    /**
     * La grille sur laquelle on trouve l'argent.
     */
    private Grille grille;
    /**
     * état de l'argent.
     */
    private int etat;
    /**
     *  thread pour  l'animation de l'argent.
     */
    private Thread thread;
    /**
     * Indique si l'argent a été sélectionne par le jouer.
     */
    private boolean selected;
    /**
     * Indique si l'argent a été ramassée par un villageois.
     */
    private boolean estRamasse;
    /**
     * La valeur de d une piece.
     */
    public final static int valeur = 50;
    /**
     * La direction du villageois qui ramasse l'argent.
     */
    private Direction directionVillageois;

    /**
     * La largeur d'une piece.
     */
    public static final int largeur = 10;

    /**
     * La hauteur d'une piece
     */
    public static final int hauteur = 15;

    /**
     * Constructeur de la classe Argent.
     *
     * @param grille   la grille sur laquelle se trouve l'argent.
     * @param position la position de l'argent sur la grille.
     */
    public Argent(Grille grille, Point position){
        this.position = position;
        this.grille = grille;
        this.etat = 1;
        this.selected = false;
        this.estRamasse = false;
    }
    /**
     * Retourne une position aléatoire pour un villageois qui ramasse l'argent.
     *
     * @return une position aléatoire pour un villageois qui ramasse l'argent.
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
     * Vérifie si une position est possible pour un villageois
     * @param x l'abscisse de la position à vérifier
     * @param y l'ordonnée de la position à vérifier
     * @return true si la position est possible, false sinon
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
    /**
     * Implémente la méthode run() de l'interface Runnable pour le thread.
     * Incrémente l'état de l'objet Arbre en boucle.
     */
    @Override
    public void run() {
        while(true){

            if(this.etat == 8){
                this.etat = 1;
            }
            else{
                this.etat++;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retourne la position de l'objet Arbre.
     * @return la position de l'objet Arbre
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Retourne l'état actuel de l'objet Arbre.
     * @return l'état actuel de l'objet Arbre
     */
    public int getEtat() {
        return etat;
    }

    /**
     * Retourne si l'objet Arbre est sélectionné ou non.
     * @return true si l'objet Arbre est sélectionné, false sinon
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Retourne si l'objet Arbre a été ramassé ou non.
     * @return true si l'objet Arbre a été ramassé, false sinon
     */
    public boolean isEstRamasse() {
        return estRamasse;
    }

    /**
     * Retourne le thread associé à l'objet Arbre.
     * @return le thread associé à l'objet Arbre
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Modifie la position de l'objet Arbre.
     * @param position la nouvelle position de l'objet Arbre
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Modifie si l'objet Arbre est sélectionné ou non.
     * @param selected true si l'objet Arbre doit être sélectionné, false sinon
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Modifie l'état actuel de l'objet Arbre.
     * @param etat le nouvel état de l'objet Arbre
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     * Modifie si l'objet Arbre a été ramassé ou non.
     * @param estRamasse true si l'objet Arbre a été ramassé, false sinon
     */
    public void setEstRamasse(boolean estRamasse) {
        this.estRamasse = estRamasse;
    }

    /**
     * Modifie le thread associé à l'objet Arbre.
     * @param thread le nouveau thread associé à l'objet Arbre
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    } }