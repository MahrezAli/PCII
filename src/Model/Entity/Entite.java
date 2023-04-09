/**

  Classe abstraite qui représente une entité du joueur.
 */
package Model.Entity;


import Utils.ActionVillageois;
import Utils.Direction;

import java.awt.*;

public abstract class Entite {
    //Position actuelle du joueur dans l'espace de jeu
    private Point position;
    //Le score du joueur
    private int score;
    //Booléen indiquant si le joueur est en train de se déplacer
    private boolean isMoving;
    private ActionVillageois walkAttack;
    //Fonction du joueur
    private String fonction;
    //Direction dans laquelle se déplace le joueur
    private Direction direction;
    private int etatAttack;
    private boolean isSelected;
    private int etatWalk;

    /**
     Constructeur permettant de créer une nouvelle entité à une position donnée (x, y) avec une fonction donnée.
     @param x la coordonnée x de la position de l'entité
     @param y la coordonnée y de la position de l'entité
     @param fonction la fonction de l'entité
     */
    public Entite(int x, int y, String fonction){
        this.position = new Point(x, y);
        this.score = 100;
        this.fonction = fonction;
        this.direction = Direction.up;
        this.isSelected = false;
        this.etatWalk = 1;
        this.walkAttack = ActionVillageois.normal;
    }



    //Getters:
/**
 Retourne l'état de l'attaque de l'entité.
            @return l'état de l'attaque de l'entité
            */

    public int getEtatAttack() {
        return etatAttack;
    }

    /**

     Retourne la position actuelle du joueyr
     @return la position du joeur
     */    public Point getPosition() {
        return position;
    }

    /**
     @return la fonction actuelle du joueur
     */    public String getFonction() {
        return fonction;
    }
    /**
     @return Retourne vrai si le joueur est en train de se déplacer, faux sinon
     */
    public boolean isMoving() {
        return isMoving;
    }
    /**
     @return la direction dans laquelle se déplace le joueur
     */
    public Direction getDirection() {
        return direction;
    }
    /**
     @return retourne le score du joueur

     */
    public int getScore() {
        return score;
    }
    /**
    Retourne si le joueur est sélectionné ou non.
            @return vrai si le joueur est sélectionné, faux sinon
    */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     Retourne l'état de la marche du joueur.
     @return l'état de la marche du joueur
     */
    public int getEtatWalk() {
        return etatWalk;
    }
    /**
     Retourne l'action de marche ou d'attaque en cours du joueur.
     @return l'action de marche ou d'attaque en cours du joueur
     */
    public ActionVillageois getWalkAttack() {
        return walkAttack;
    }

    //Setters:
    /**
     Définit l'état d'attaque du joueur.
     @param etatAttack l'état d'attaque du joueur
     */
    public void setEtatAttack(int etatAttack) {
        this.etatAttack = etatAttack;
    }

    /**
     Définit la position du joueur.
     @param position la position du joueur
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     Définit si le joueur est en train de se déplacer ou non.
     @param moving un booléen indiquant si le joueur est en train de se déplacer
     */    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    /**
     Définit la direction dans laquelle se déplace le joueur.
     @param direction la direction dans laquelle se déplace le joueur
     */    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     Définit le score du joueur.
     @param score le score du joueur
     */    public void setScore(int score) {
        this.score = score;
    }
    /**
     Définit si le joueur est sélectionné ou non.
     @param selected un booléen indiquant si le joueur est sélectionné
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    /**
     Définit l'état de marche du joueur.
     @param etatWalk l'état de marche du joueur
     */
    public void setEtatWalk(int etatWalk) {
        this.etatWalk = etatWalk;
    }
    /**
     Définit l'action de marche/attaque du joueur.
     @param walkAttack l'action de marche/attaque du joueur
     */
    public void setWalkAttack(ActionVillageois walkAttack) {
        this.walkAttack = walkAttack;
    }
}
