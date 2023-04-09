package Model;

import Model.Entity.Villageois;
import Model.Potager.Potager;
import Utils.Plante;
import Utils.Potion;

import java.awt.*;
import java.util.ArrayList;

/**
 * Constructeur de la classe Game.
 * Initialise la liste des villageois, le nombre de pièces et la grille.
 */
public class Game {

    private Grille grille; // une instance de la grille
    private ArrayList<Villageois> villageois; // liste des villageois
    private int pieces;
    private Inventaire inventaire;
    //Combien l'utilisateur possède pour chaque objet

    /**
     * Retourne l'instance de la grille associée à ce jeu.
     *
     * @return l'instance de la grille associée à ce jeu.
     */
    public Game(){
        this.villageois = new ArrayList<>(); // initialiser la liste des villageois
        this.pieces = 1000;
        this.grille = new Grille(this.villageois); // initialiser la grille avec la liste des villageois
        this.inventaire = new Inventaire(this);
    }

    //Getters:
    public Grille getGrille() {
        return this.grille;
    }
    /**
             Retourne la liste des villageois présents dans le jeu.
             * @return la liste des villageois présents dans le jeu.
            */
    public ArrayList<Villageois> getVillageois() {
        return this.villageois;
    }
    /**
     * Retourne le nombre de pièces que le joueur possède.
     *
     * @return le nombre de pièces que le joueur possède.
     */
    public int getPieces() {
        return pieces;
    }
    /**
     * Retourne l'inventaire du joueur.
     *
     * @return l'inventaire du joueur.
     */
    public Inventaire getInventaire() {
        return inventaire;
    }

    //Setters:
    /**
     * Met à jour le nombre de pièces que le joueur possède.
     *
     * @param pieces le nouveau nombre de pièces.
     */

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
}
