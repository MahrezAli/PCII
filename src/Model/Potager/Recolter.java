/**
 * Classe représentant un objet Recolter qui permet de récolter les plantes dans un Potager.
 */
package Model.Potager;

import Utils.Plante;

import java.awt.*;

public class Recolter implements Runnable {
    private int etatPotager;
    private Plante plante;
    private Point position;
    public static final int largeur = 20;
    public static final int hauteur = 20;
    private boolean isSelected;
    private Potager potager;

    private int nb;
    private Thread thread;  // Ajout de la variable d'instance pour stocker la référence à l'objet Thread correspondant


    /**
     * Constructeur pour initialiser un objet Recolter.
     * @param potager le potager dans lequel l'objet Recolter est placé.
     * @param p la position de l'objet Recolter.
     */    public Recolter(Potager potager, Point p) {
        this.position = p;
        this.potager = potager;
        this.etatPotager = 12;
        this.isSelected = false;
    }

    /**
     * Méthode permettant de démarrer le thread associé à l'objet Recolter.
     */
    public void demarrerThread() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Méthode permettant d'arrêter le thread associé à l'objet Recolter.
     */    public void arreterThread() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }
    /**
     * Méthode permettant de planter une plante dans le Potager associé à l'objet Recolter.
     * @param p la plante à planter.
     */
    public void planter(Plante p){
        this.plante = p;
        this.etatPotager = 1;
    }

    /**
     * Méthode associée au thread pour faire évoluer l'état du Potager associé à l'objet Recolter.
     */

    @Override
    public void run() {
        while (this.nb == 0) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            this.potager.setEtatPotager(this.etatPotager);
            if (this.etatPotager == 15) {
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
        this.etatPotager = 0;
        this.potager.setEtatPotager(this.etatPotager);
    }


    /**
     * Cette méthode permet de récupérer l'état du Potager associé à l'objet Recolter.
     * @return L'état du Potager.
     */
    public int getEtatPotager() {
        return etatPotager;
    }
    /**
     * Cette méthode permet de récupérer le nombre de récoltes effectuées par l'objet Recolter.
     * @return Le nombre de récoltes effectuées.
     */
    public int getNb() {
        return nb;
    }
    /**
     * Cette méthode permet de récupérer la position de l'objet Recolter.
     * @return La position de l'objet Recolter.
     */
    public Point getPosition() {
        return position;
    }
    /**
     * Cette méthode permet de récupérer la Plante associée à l'objet Recolter.
     * @return La Plante associée à l'objet Recolter.
     */
    public Plante getPlante() {
        return plante;
    }
    /**
     * Cette méthode permet de récupérer la référence à l'objet Thread correspondant à l'objet Recolter.
     * @return La référence à l'objet Thread correspondant à l'objet Recolter.
     */

    public Thread getThread() {
        return thread;
    }
    /**
     * Cette méthode permet de savoir si l'objet Recolter est sélectionné.
     * @return Un booléen indiquant si l'objet Recolter est sélectionné.
     */

    public boolean isSelected() {
        return isSelected;
    }

    //Setters:
    /**
     * Cette méthode permet de définir l'état du Potager associé à l'objet Recolter.
     * @param etatPotager Le nouvel état du Potager.
     */
    public void setEtatPotager(int etatPotager) {
        this.etatPotager = etatPotager;
    }

    /**
     * Cette méthode permet de définir la Plante associée à l'objet Recolter.
     * @param plante La nouvelle Plante à associer à l'objet Recolter.
     */
    public void setPlante(Plante plante) {
        this.plante = plante;
    }
    /**
     * Cette méthode permet de définir le nombre de récoltes effectuées par l'objet Recolter.
     * @param nb Le nouveau nombre de récoltes effectuées.
     */
    public void setNb(int nb) {
        this.nb = nb;
    }
    /**
     * Cette méthode permet de définir la référence à l'objet Thread correspondant à l'objet Recolter.
     * @param thread La nouvelle référence à l'objet Thread correspondant à l'objet Recolter.
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }
    /**
     * Cette méthode permet de définir si l'objet Recolter est sélectionné.
     * @param selected Un booléen indiquant si l'objet Recolter doit séleectionné ou non
     * */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

