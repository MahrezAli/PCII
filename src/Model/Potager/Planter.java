package Model.Potager;

import Utils.Plante;
import java.awt.*;

public class Planter implements Runnable {
    private int etatPotager;
    private Plante plante;
    private Point position;
    public static final int largeur = 20;
    public static final int hauteur = 20;
    private boolean isSelected;
    private int nb;
    private Potager potager;
    private Thread thread;  // Ajout de la variable d'instance pour stocker la référence à l'objet Thread correspondant

    /**
     * Constructeur de la classe Planter.
     *
     * @param potager le potager dans lequel l'objet Planter est créé
     * @param p la position de l'objet Planter
     */
    public Planter(Potager potager, Point p) {
        this.position = p;
        this.potager = potager;
        this.etatPotager = 0;
        this.isSelected = false;
    }

    /**
     * Méthode pour démarrer le thread et stocker la référence à l'objet Thread correspondant.
     */
    public void demarrerThread() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Méthode pour arrêter le thread.
     */    public void arreterThread() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }
    /**
     * Méthode pour planter une plante.
     *
     * @param p la plante à planter
     */
    public void planter(Plante p){
        this.plante = p;
        this.etatPotager = 1;
    }


    /**
     * La méthode exécutée par le thread.
     */
    @Override
    public void run() {
        while (this.nb == 0) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            this.potager.setEtatPotager(this.etatPotager);
            if (this.etatPotager == 3) {
                this.nb = 1;
            } else {
                this.etatPotager++;
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
     * Retourne l'état du potager.
     *
     * @return l'état du potager
     */
    public int getEtatPotager() {
        return etatPotager;
    }
    /**
     * Retourne le nombre de plantes plantées.
     *
     * @return le nombre de plantes plantées
     */
    public int getNb() {
        return nb;
    }
    /**
     * Retourne la position de l'objet Planter.
     *
     * @return la position de l'objet Planter
     */

    public Point getPosition() {
        return position;
    }

    /**
     * Retourne la plante à planter.
     *
     * @return la plante à planter
     */
    public Plante getPlante() {
        return plante;
    }

    /**
     * Retourne la référence à l'objet Thread correspondant.
     *
     * @return la référence à l'objet Thread correspondant
     */
    public Thread getThread() {
        return thread;
    }

    /**
     Retourne si le potager est sélectionné ou non.
     @return true si le potager est sélectionné, false sinon
     */

    public boolean isSelected() {
        return isSelected;
    }

    //Setters:
    /**
     Modifie l'état du potager.
     @param etatPotager le nouvel état du potager
     */
    public void setEtatPotager(int etatPotager) {
        this.etatPotager = etatPotager;
    }

    /**
     Modifie la plante à planter.
     @param plante la nouvelle plante à planter
     */
    public void setPlante(Plante plante) {
        this.plante = plante;
    }

    /**
     Modifie le nombre de fois que le potager a été planté ou récolté.
     @param nb le nouveau nombre de fois que le potager a été planté ou récolté
     */
    public void setNb(int nb) {
        this.nb = nb;
    }

    /**
     Modifie la référence à l'objet Thread correspondant.
     @param thread la nouvelle référence à l'objet Thread correspondant
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     Modifie si le potager est sélectionné ou non.
     @param selected true si le potager est sélectionné, false sinon
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
