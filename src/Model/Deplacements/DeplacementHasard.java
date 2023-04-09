/**
 Classe qui gère le déplacement aléatoire d'un villageois
 */
package Model.Deplacements;

import Model.Grille;
import Model.Entity.Villageois;
import Utils.Direction;

import java.awt.*;
import java.util.Random;

public class DeplacementHasard extends Thread{
    //Attribut qui contient l'objet Villageois qui va se déplacer
    private Villageois villageois;
    //Attribut qui contient l'objet Grille sur laquelle les déplacements se font
    private Grille grille;
    //Attribut pour verrouiller le déplacement
    private int lockDeplacement;

    private int lockEtatSelectionnee;

    /**
     * Constructeur qui initialise les attributs
     * @param v l'objet Villageois à déplacer
     * @param grille l'objet Grille sur laquelle se font les déplacements
     */
    public DeplacementHasard(Villageois v, Grille grille){
        this.grille = grille;
        this.villageois = v;
        this.lockDeplacement = 0;
    }
    /**
     * Boucle principale de la classe qui gère le déplacement aléatoire du villageois
     */

    @Override
    public void run() {
        super.run();
        while (!this.villageois.isActionAFaire()) {
            changeDirection();
            // Récupère la position en X du villageois
            int posX = this.villageois.getPosition().x;
            // Récupère la position en Y du villageois
            int posY = this.villageois.getPosition().y;
            // Si la direction du villageois est "vers le haut" et qu'il y a de la place disponible en Y+1
            if(this.villageois.getDirection() == Direction.up && posY + Villageois.hauteur + 1 < Grille.HAUTEUR && positionPossible(posX, posY)){
                for(int i2 = posY + Villageois.hauteur - 1; i2 >= posY; i2--){
                    for(int i1 = posX + Villageois.largeur - 1; i1 >= posX; i1--){
                        this.grille.getCases()[i1][i2].setVillageois(null);
                        this.grille.getCases()[i1][i2].setOccupee(false);
                        this.grille.getCases()[i1][i2+1].setVillageois(this.villageois);
                        this.grille.getCases()[i1][i2+1].setOccupee(true);
                    }
                }
                Point tmp = new Point(posX, posY+1);
                this.villageois.setPosition(tmp);
            }
            // Si la direction du villageois est "vers le bas" et qu'il y a de la place disponible en Y-1
            else if(this.villageois.getDirection() == Direction.down && posY - 1 >= 0 && positionPossible(posX, posY)){
                for(int i1 = posX; i1 < posX + Villageois.largeur; i1++){
                    for(int i2 = posY; i2 < posY + Villageois.hauteur; i2++){
                        this.grille.getCases()[i1][i2].setVillageois(null);
                        this.grille.getCases()[i1][i2].setOccupee(false);
                        this.grille.getCases()[i1][i2-1].setVillageois(this.villageois);
                        this.grille.getCases()[i1][i2-1].setOccupee(true);
                    }
                }
                Point tmp = new Point(posX, posY-1);
                this.villageois.setPosition(tmp);
            }
            // Si la direction du villageois est "vers la gauche" et qu'il y a de la place disponible en X-1
            else if(this.villageois.getDirection() == Direction.left && posX - 1 >= 0 && positionPossible(posX, posY)){
                for(int i1 = posX; i1 < posX + Villageois.largeur; i1++){
                    for(int i2 = posY; i2 < posY + Villageois.hauteur; i2++){
                        this.grille.getCases()[i1][i2].setVillageois(null);
                        this.grille.getCases()[i1][i2].setOccupee(false);
                        this.grille.getCases()[i1-1][i2].setVillageois(this.villageois);
                        this.grille.getCases()[i1-1][i2].setOccupee(true);
                    }
                }
                Point tmp = new Point(posX - 1, posY);
                this.villageois.setPosition(tmp);
            }
            // Si la direction du villageois est "vers la droite" et qu'il y a de la place disponible en X+1
            else if(this.villageois.getDirection() == Direction.right && posX + Villageois.largeur + 1 < Grille.LARGEUR && positionPossible(posX, posY)){
                for(int i1 = posX + Villageois.largeur-1; i1 >= posX; i1--){
                    for(int i2 = posY + Villageois.hauteur - 1; i2 >= posY; i2--){
                        this.grille.getCases()[i1][i2].setVillageois(null);
                        this.grille.getCases()[i1][i2].setOccupee(false);
                        this.grille.getCases()[i1+1][i2].setVillageois(this.villageois);
                        this.grille.getCases()[i1+1][i2].setOccupee(true);
                    }
                }
                Point tmp = new Point(posX + 1, posY);
                this.villageois.setPosition(tmp);
            }
            this.villageois.setWalkEtat();
            this.lockEtatSelectionnee++;
            this.grille.setChangedAndNotify();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.villageois.setMoving(false);
    }

    /**
     * Fonction pour changer la direction d'un villaegois
     */
    private void changeDirection(){
        Random random = new Random();
        //Génération d'un entier aléatoire entre 1 et 100
        int i = random.nextInt(100) + 1;
        this.lockDeplacement++;
        //Si le lock de déplacement est égal à 5
        if(this.lockDeplacement == 10) {
            //Si i est inférieur ou égal à 25
            if (i <= 25) {
                //Définir la direction en haut
                this.villageois.setDirection(Direction.up);
            }
            //Si i est supérieur à 25 et inférieur ou égal à 50
            if (i > 25 && i <= 50) {
                //Définir la direction en bas
                this.villageois.setDirection(Direction.down);
            }
            //Si i est supérieur à 50 et inférieur ou égal à 75
            if (i > 50 && i <= 75) {
                //Définir la direction à gauche
                this.villageois.setDirection(Direction.left);
            }
            //Si i est supérieur à 75
            if (i > 75) {
                //Définir la direction à droite
                this.villageois.setDirection(Direction.right);
            }
            //Remettre le lock de déplacement à 0
            this.lockDeplacement = 0;
        }
    }
    /**
     Methode qui vérifie si le déplacement d'un villageois dans une certaine direction est possible
     @param x La coordonnée x de la position actuelle du villageois
     @param y La coordonnée y de la position actuelle du villageois
     @return true si le déplacement est possible, false sinon
     */
    private boolean positionPossible(int x, int y){
        boolean res = true;
        if(this.villageois.getDirection() == Direction.up){
            for(int i = x; i < x + Villageois.largeur; i++){
                res = res && !this.grille.positionOccupee(i,y + Villageois.hauteur+1);
            }
        }
        else if(this.villageois.getDirection() == Direction.down){
            for(int i = x; i < x + Villageois.largeur; i++){
                res = res && !this.grille.positionOccupee(i,y-2);
            }
        }
        else if(this.villageois.getDirection() == Direction.left){
             for(int j = y; j < y + Villageois.hauteur; j++){
                 res = res && !this.grille.positionOccupee(x-1,j);
             }
        }
         else if(this.villageois.getDirection() == Direction.right){
             for(int j = y; j < y + Villageois.hauteur; j++){
                 res = res && !this.grille.positionOccupee(x+Villageois.largeur+1,j);
             }
        }
        return res;
    }
}
