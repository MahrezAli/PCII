/**  classe qui s'execute en arrière plan comme thread et qui gère la gestion des événements du jeu, tels que le
 *  déplacement des personnages, l'ajout et la suppression de monstres et
 *  d'argent, ainsi que la mise à jour des défenses.
 */
package Controler;

import Model.Argent;
import Model.Defense;
import Model.Entity.Monstre;
import Model.Game;
import Utils.PairBatP;
import Vue.Grille.VueArgent;
import Vue.Grille.VueMonstre;
import Vue.VueTresorerie;

import java.awt.*;
import java.util.Random;

public class JeuThread extends Thread {
    private Game game;
    private Control control;
    private VueTresorerie vueTresorerie;
    /**
     * Constructeur de la classe JeuThread
     * @param game instance de la classe Game représentant le jeu
     * @param control instance de la classe Control représentant le contrôleur
     */
    public JeuThread(Game game, Control control) {
        this.game = game;
        this.control = control;
    }

    /**
     * Méthode qui lance le jeu dans un thread
     */
    @Override
    public void run() {
        int tempo = 0;
        while(true) {
            if(this.vueTresorerie == null){
                this.vueTresorerie = this.control.getVueTresorerie();
            }
            //Faire déplacer les villageois de manière au hasard
            for(int i =  0; i < this.game.getVillageois().size(); i++){
                if(!this.game.getVillageois().get(i).isActionAFaire() && !this.game.getVillageois().get(i).isMoving()) {
                    this.game.getVillageois().get(i).setMoving(true);
                    this.game.getVillageois().get(i).deplacer(this.game.getGrille());
                }
            }

            //Ajouter un monstre à chaque tempo
            if(tempo == 250){
                this.game.getGrille().addMonstres();
                Monstre monster = this.game.getGrille().getMonstre().get(this.game.getGrille().getMonstre().size()-1);
                this.control.getAffichage().getGrille().addMonstres(new VueMonstre(monster));
                monster.deplacer(this.game.getGrille());
                tempo = 0;
            }
            else {
                tempo++;
            }
            //Supprimer les monstres morts
            for(int i = 0; i < this.game.getGrille().getMonstre().size(); i++){
                Monstre monstre = this.game.getGrille().getMonstre().get(i);
                if((monstre != null && monstre.estMort()) || monstre == null){
                    int x = monstre.getPosition().x;
                    int y = monstre.getPosition().y;
                    int idVueMonstre = -1;
                    for(int s = 0;  s < this.control.getAffichage().getGrille().getMonstres().size(); s++){
                        if(this.control.getAffichage().getGrille().getMonstres().get(s).getMonstre() == monstre){
                            idVueMonstre = s;
                            break;
                        }
                    }
                    this.control.getAffichage().getGrille().removeMonstre(idVueMonstre);
                    this.game.getGrille().getMonstre().remove(monstre);
                    for(int i1 = x; i1 < x + Monstre.largeur; i1++){
                        for(int i2 = y; i2 < y + Monstre.hauteur; i2++){
                            this.game.getGrille().getCases()[i1][i2].setMonstres(null);
                        }
                    }
                    this.game.getGrille().getMonstre().remove(monstre);

                    //Ajouter la pièce
                    Argent argent = new Argent(this.game.getGrille(), new Point(x, y));
                    this.game.getGrille().getArgents().add(argent);
                    this.control.getAffichage().getGrille().addArgent(new VueArgent(argent));
                    for(int i1 = x; i1 < x + Argent.largeur; i1++){
                        for(int i2 = y; i2 < y + Argent.hauteur; i2++){
                            this.game.getGrille().getCases()[i1][i2].setArgent(argent);
                        }
                    }
                    if (argent.getThread() != null) {
                        argent.getThread().interrupt();
                    }
                    Thread t = new Thread(argent);
                    t.start();
                    argent.setThread(t);

                }
            }

            //Supprimer l'argent qui a été ramassé
            for(int i = 0; i < this.game.getGrille().getArgents().size(); i++){
                Argent argent = this.game.getGrille().getArgents().get(i);
                if(argent.isEstRamasse()){
                    int x = argent.getPosition().x;
                    int y = argent.getPosition().y;
                    int idVueArgent = -1;
                    for(int s = 0;  s < this.control.getAffichage().getGrille().getArgents().size(); s++){
                        if(this.control.getAffichage().getGrille().getArgents().get(s).getArgent() == argent){
                            idVueArgent = s;
                            break;
                        }
                    }
                    this.control.getAffichage().getGrille().removeArgent(idVueArgent);
                    this.game.getGrille().getArgents().remove(argent);
                    for(int i1 = x; i1 < x + Argent.largeur; i1++){
                        for(int i2 = y; i2 < y + Argent.hauteur; i2++){
                            this.game.getGrille().getCases()[i1][i2].setArgent(null);
                        }
                    }
                    this.game.getGrille().getArgents().remove(argent);
                    int valeur = Argent.valeur + this.game.getPieces();
                    this.game.setPieces(valeur);
                    this.vueTresorerie.update();
                }
            }

            //Lancer les tours de défenses si un monstre est dans leurs rayon
            for(int i = 0; i < this.game.getGrille().getDefenses().size(); i++){
                Defense defense = this.game.getGrille().getDefenses().get(i);
                if(!defense.estDetruit() && defense.getMonstre() == null) {
                    defense.verifieMonstreDansRayon();
                    if (defense.getMonstre() != null && !defense.isEnAttaque()) {
                        defense.setEnAttaque(true);
                        if (defense.getThread() != null) {
                            defense.getThread().interrupt();
                        }
                        Thread t = new Thread(defense);
                        t.start();
                        defense.setThread(t);
                    }
                }
            }

            //Rafraichir les places occupées
            for(int i = 0; i < this.game.getGrille().getMonstre().size(); i++){
                int posX = this.game.getGrille().getMonstre().get(i).getPosition().x;
                int posY = this.game.getGrille().getMonstre().get(i).getPosition().y;
                for(int i1 = posX; i1 < posX + Monstre.largeur; i1++){
                    for(int i2 = posY; i2 < posY + Monstre.hauteur; i2++){
                        this.game.getGrille().getCases()[i1][i2].setMonstres(this.game.getGrille().getMonstre().get(i));
                    }
                }
            }

            //Donner de nouvelles cibles aux monstres qui ont détruits un batiment
            if(this.game.getGrille().resteBatimentsADetruire()) {
                for (int i = 0; i < this.game.getGrille().getMonstre().size(); i++) {
                    Monstre monster = this.game.getGrille().getMonstre().get(i);
                    if (monster.getBatiment().estDetruit()) {
                        PairBatP tmp = this.game.getGrille().EmplacementCible(monster.getPosition().x, monster.getPosition().y);
                        monster.setCible(tmp.getPoint());
                        monster.setBatiment(tmp.getBatiment());
                        monster.deplacer(this.game.getGrille());
                    }
                }
            }

            // Attendre un certain temps secondes
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    /**
     Fonction qi génère un nombre entier aléatoire dans un intervalle donné
     @param borneInf La borne inférieure de l'intervalle
     @param borneSup La borne supérieure de l'intervalle
     @return un nombre entier aléatoire compris dans l'intervalle [borneInf, borneSup[
     */
    private int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }
}
