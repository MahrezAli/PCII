/**

 La classe Arbre hérite de la classe Batiment et représente un objet de type arbre dans le jeu.
 */
package Model;

import java.awt.*;
import java.util.Random;

public class Arbre extends Batiment {
    /**
     * La largeur de l'arbre.
     */
    public final static int LARGEUR = 20;

    /**
     * La hauteur de l'arbre.
     */
    public final static int HAUTEUR = 20;

    /**
     * Un boolean représentant l'état de l'arbre.
     */
    private boolean NB;

    /**
     * Constructeur de la classe Arbre.
     *
     * @param p   Point correspondant à la position de l'arbre.
     * @param nom Nom de l'arbre.
     */
    public Arbre(Point p, String nom) {
        super(p, nom, LARGEUR, HAUTEUR);
        Random rd = new Random();
        this.NB = rd.nextBoolean();
    }

//Guetters:

    /**
     * Getter pour l'attribut NB.
     *
     * @return boolean représentant l'état de l'arbre.
     */
    public boolean getNB() {
        return NB;
    }
}
