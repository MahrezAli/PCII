/**

 Cette classe représente l'action d'un monstre qui attaque un villageois.
 */
package Model.Actions;
import Model.Entity.Monstre;
import Model.Entity.Villageois;
import Utils.ActionVillageois;

public class AttaqueVillageois implements Runnable{
    private Monstre monstre; // le monstre qui attaque le villageois
    private int etatAttaque; // l'état de l'attaque actuelle
    private Villageois villageois; // le villageois attaqué
    private Thread thread; // le thread de l'action

    /**
     * Constructeur de l'action d'attaque d'un villageois.
     * @param villageois le villageois attaqué
     * @param monstre le monstre qui attaque
     */
    public AttaqueVillageois(Villageois villageois, Monstre monstre){
        this.etatAttaque = 1;
        this.villageois = villageois;
        this.monstre = monstre;
        this.villageois.setEtatAttack(this.etatAttaque);
    }

    /**
     * Exécute l'action d'attaque d'un villageois.
     */
    @Override
    public void run() {
        int tempo = 0;
        int tempoMonstre = 2;
        while(!this.monstre.estMort()){

            if(tempo == 1){
                if(this.etatAttaque == 4){
                    this.etatAttaque = 1;
                }
                else{
                    this.etatAttaque++;
                }
                if(tempoMonstre == 3) {
                    this.monstre.subitDegat(this.villageois.getDegat());
                    tempoMonstre = 0;
                }
                tempo = 0;
                this.villageois.setEtatAttack(this.etatAttaque);
            }
            else{
                this.monstre.setGetAttacked(false);
                tempo++;
                tempoMonstre++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.monstre = null;
        //Remettre le villageois en état normal
        this.villageois.setWalkAttack(ActionVillageois.normal);
        this.villageois.setActionAFaire(false);
    }

    /**
     * Renvoie le thread de l'action d'attaque d'un villageois.
     * @return le thread de l'action
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Modifie l'état d'attaque actuel.
     * @param etatAttaque le nouvel état d'attaque
     */
    public void setEtatAttaque(int etatAttaque) {
        this.etatAttaque = etatAttaque;
    }

    /**
     * Modifie le thread de l'action d'attaque d'un villageois.
     * @param thread le nouveau thread de l'action
     */

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
