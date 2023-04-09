/**

 La classe Potager représente le potager de notre jeu.
 Celui ci contient une plante et deux types de villageois : un qui plante la plante et l'autre qui la récolte.
 Le potager peut être sélectionné ou non, et sa position est déterminée par une coordonnée x et y.
 Le potager est aussi capable de fournir une position pour un villageois qui veut récolter la plante.
 */

package Model.Potager;

import Model.Grille;
import Model.Entity.Villageois;
import Utils.Direction;
import Utils.Plante;

import java.awt.*;

public class Potager implements Runnable {
    private int etatPotager;
    private Plante plante;
    private Point position;
    public static final int largeur = 20;
    public static final int hauteur = 20;
    private boolean isSelected;
    private Recolter recolter;
    private Planter planter;
    private Grille grille;
    private Direction directionVillageois;

    private int etatThread;
    private int nb;
    private Thread thread;  // Ajout de la variable d'instance pour stocker la référence à l'objet Thread correspondant

/**

 Constructeur de la classe Potager.
 @param grille la grille sur laquelle se trouve le potager
 @param p la position du potager
 */
    public Potager(Grille grille, Point p) {
        this.position = p;
        this.grille = grille;
        this.planter = new Planter(this, p);
        this.recolter = new Recolter(this, p);
        this.etatPotager = this.planter.getEtatPotager();
        this.etatThread = 0;
        this.isSelected = false;
    }
    /**

     Méthode qui permet de planter une plante dans le potager.
     @param p la plante à planter
     */

    public void planter(Plante p){
        this.plante = p;
        this.etatPotager = 1;
    }
    /**

     Méthode qui retourne une position pour un villageois qui veut récolter la plante.
     @return la position pour le villageois
     */

    public Point positionPourVillageois(){
        Point res = null;
        boolean fini = false;
        int x = this.position.x;
        int y = this.position.y;

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

 Méthode qui vérifie si une position donnée est possible pour un villageois.
 @param x la coordonnée x de la position
 @param y la coordonnée y de la position
 @return true si la position est possible, false sinon
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

     Méthode run() de l'interface Runnable, qui permet de faire évoluer le potager avec le temps.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            if (this.etatPotager <= 12 && this.nb == 0) {
                if (this.etatThread == 3) {
                    try {
                        Thread.sleep(25000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    this.etatThread = 0;
                } else {
                    if (this.etatPotager == 12) {
                        this.nb = 1;
                    } else {
                        this.etatPotager++;
                    }
                    this.etatThread++;
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    //Guetters:
    /**
     Méthode qui retourne l'état actuel du potager.
     @return l'état actuel du potager
     */
    public int getEtatPotager() {
        return etatPotager;
    }
    /**

     Méthode qui retourne les plantes du potager.
     @return l'objet Planter du potager
     */

    public Planter getPlanter() {
        return planter;
    }
    /**

     Méthode qui retourne l'objet la récolte du potager.
     @return l'objet Recolter du potager
     */

    public Recolter getRecolter() {
        return recolter;
    }
    /**

     Méthode qui retourne le nombre de récoltes effectuées dans le potager.
     @return le nombre de récoltes effectuées
     */
    public int getNb() {
        return nb;
    }
    /**
     Méthode qui retourne la position du potager.
     @return la position du potager
     */

    public Point getPosition() {
        return position;
    }
    /**
     Méthode qui retourne la plante actuelle du potager.
     @return la plante actuelle du potager
     */
    public Plante getPlante() {
        return plante;
    }
    /**
     Méthode qui retourne le thread de la classe Potager.
     @return le thread de la classe Potager
     */
    public Thread getThread() {
        return thread;
    }
    /**
     Méthode qui indique si le potager est sélectionné ou non.
     @return true si le potager est sélectionné, false sinon
     */
    public boolean isSelected() {
        return isSelected;
    }
    /**
     Méthode qui retourne la direction du villageois qui récolte la plante.
     @return la direction du villageois
     */

    public Direction getDirectionVillageois() {
        return directionVillageois;
    }

    //Setters:
    /**
     Méthode pour modifier l'état du potager.
     @param etatPotager le nouvel état du potager
     */
    public void setEtatPotager(int etatPotager) {
        this.etatPotager = etatPotager;
    }
    /**
     Méthode qui permet de modifier la plante du potager.
     @param plante la nouvelle plante du potager
     */

    public void setPlante(Plante plante) {
        this.plante = plante;
    }
    /**
     Méthode qui permet de modifier le nombre de récoltes effectuées dans le potager.
     @param nb le nouveau nombre de récoltes effectuées
     */
    public void setNb(int nb) {
        this.nb = nb;
    }
    /**

     Méthode qui permet de modifier le thread de la classe Potager.
     @param thread le nouveau thread de la classe Potager
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }
    /**

     Méthode qui permet de modifier l'état du thread du potager.
     @param etatThread le nouvel état du thread du potager
     */
    public void setEtatThread(int etatThread) {
        this.etatThread = etatThread;
    }
    /**
     Méthode qui permet de modifier la selection du potager.
     @param selected le nouvel état du potager
     */

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
