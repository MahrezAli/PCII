/**

 La classe Villageois représente une entité villageois du jeu.
 Elle hérite de la classe Entite et implémente les méthodes de déplacement.
 */
package Model.Entity;

import Model.Case;
import Model.Deplacements.DeplacementHasard;
import Model.Deplacements.DeplacementVillageois;
import Model.Grille;

import java.util.ArrayList;

public class Villageois extends Entite {
    //Attribut indiquant si le villageois a une action à faire

    private boolean actionAFaire;
    // degat du villageois
    private int degat;
    //La largeur du joueur
    public static final int largeur = 5;
    //La hauteur du joueur
    public static final int hauteur = 8;;
    /**
     * Constructeur de la classe Villageois.
     * Initialise les coordonnées et le type du villageois ainsi que l'action à faire et la puissance du villageois.
     * @param x Coordonnée X du villageois
     * @param y Coordonnée Y du villageois
     */
    public Villageois(int x, int y){
        super(x, y, "Villageois");
        this.actionAFaire = false;
        this.degat = 5;
    }
    /**
     * Méthode qui modifie l'état de marche du villageois.
     */

    public void setWalkEtat(){
        if(this.getEtatWalk() == 4){
            this.setEtatWalk(1);
        }
        else{
            int i = this.getEtatWalk() + 1;
            this.setEtatWalk(i);
        }
    }
    /**
     * Méthode pour déplace le villageois de manière aléatoire sur la grille.
     * @param grille Grille sur laquelle le villageois doit se déplacer.
     */
    public void deplacer(Grille grille){
        DeplacementHasard deplacement = new DeplacementHasard(this, grille);
        deplacement.start();
    }
    /**
     * Méthode pour déplacer le villageois en suivant un chemin prédéfini.
     * @param grille Grille sur laquelle le villageois doit se déplacer.
     * @param chemin Chemin prédéfini que le villageois doit suivre.
     * @param id Identifiant de l'objet associé à l'action à effectuer (ex: identifiant du potager à planter).
     * @param action Action à effectuer (Planter, Recolter, Attaquer, Ramasser).
     */

    public void deplacerAction(Grille grille, ArrayList<Case> chemin, int id, String action){
        DeplacementVillageois deplacementAStar = new DeplacementVillageois(this, grille, chemin, id, action);
        if (deplacementAStar.getThread() != null) {
            deplacementAStar.getThread().interrupt();
        }
        Thread thread = new Thread(deplacementAStar);
        thread.start();
        deplacementAStar.setThread(thread);
    }

    //Guetters:
    /**
     * Getter pour l'attribut degat.
     * @return Puissance du villageois.
     */
    public int getDegat() {
        return degat;
    }

    public boolean isActionAFaire() {
        return actionAFaire;
    }

    /**
     * Getter pour l'attribut actionAFaire.
     * @return Vrai si le villageois a une action à faire, faux sinon.
     */
    public void setActionAFaire(boolean actionAFaire) {
        this.actionAFaire = actionAFaire;
    }

}
