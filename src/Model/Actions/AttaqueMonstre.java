/**
Classe qui gère l'attaque des monstres sur les bâtiments
 */
package Model.Actions;

import Model.Batiment;
import Model.Defense;
import Model.Entity.Monstre;
import Model.Entity.Villageois;
import Utils.ActionVillageois;

public class AttaqueMonstre implements Runnable{
    private Monstre monstre;
    private Batiment batiment;
    private Thread thread;
    /**
     * Constructeur de la classe AttaqueMonstre.
     *
     * @param monstre L'instance de Monstre qui effectue l'attaque.
     * @param batiment L'instance de Batiment qui subit l'attaque.
     */
    public AttaqueMonstre(Monstre monstre, Batiment batiment){
        this.monstre = monstre;
        this.batiment = batiment;
    }

    /**
     * Exécute le thread de l'attaque effectuée par le monstre sur un bâtiment.
     * Le monstre attaque le bâtiment jusqu'à ce que soit lui, soit le bâtiment soit détruit.
     */
    @Override
    public void run() {
        int tempo = 0;
        while(!this.monstre.estMort() && !this.batiment.estDetruit()){
            if(tempo == 3){
                this.monstre.setWalkEtat();
                this.batiment.subitDegat(this.monstre.getDegats());
                tempo = 0;
            }
            else{
                tempo++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.monstre.setWalkEtat();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    //Guetter:
    /**
     * Retourne le thread de l'attaque.
     * @return Le thread de l'attaque.
     */
    public Thread getThread() {
        return thread;
    }

    //Setter:
    /**
     * Definiss le thread de l'attaque.
     * @param thread Le thread à assigner.
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }


}
