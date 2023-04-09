/**

 Classe qui représente un bâtiment dans le jeu.
 Un bâtiment peut être construit par le joueur pour effectuer des actions telles que la production de ressources.
 Il possède des points de vie (HP) et une position sur la carte.
 Cette classe étend Observable, pour permettre l'observation du bâtiment par les classes qui en ont besoin.
 */
package Model;

import Utils.Potion;

import java.awt.*;
import java.util.Observable;

public class Batiment extends Observable {
    private int HP;
    private int maxHP;
    private int etat;
    private Point position;
    private int largeur;
    private int hauteur;
    private int niveau;
    private String nom;
    private boolean isSelected;
    private boolean afficheHeathBar;

    /**
     * Constructeur de la classe Batiment.
     *
     * @param p      La position du bâtiment sur la carte.
     * @param nom    Le nom du bâtiment.
     * @param l      La largeur du bâtiment.
     * @param h      La hauteur du bâtiment.
     */
    public Batiment(Point p, String nom, int l, int h){
        this.niveau  = 1;
        this.position = p;
        this.HP = 300;
        this.maxHP = 300;
        this.nom = nom;
        this.largeur = l;
        this.hauteur = h;
        this.isSelected = false;
        this.afficheHeathBar = false;
    }

    //Guetters :
    /**
     * Renvoie les points de vie actuels du bâtiment.
     *
     * @return Les points de vie actuels du bâtiment.
     */
    public int getHP() {
        return this.HP;
    }
    /**
     * Renvoie la position du bâtiment sur la carte.
     *
     * @return La position du bâtiment sur la carte.
     */
    public void getPotion(Potion potion){
        if(potion == Potion.Carotte){
            int x = this.maxHP*20/100;
            if(this.HP + x > this.maxHP){
                this.HP = maxHP;
                this.afficheHeathBar = false;
            }
            else{
                this.HP += x;
            }
        }
        else if(potion == Potion.Baies){
            int x = this.maxHP*50/100;
            if(this.HP + x > this.maxHP){
                this.HP = maxHP;
                this.afficheHeathBar = false;
            }
            else{
                this.HP += x;
            }
        }
        else if(potion == Potion.Betterave){
            this.HP = this.maxHP;
            this.afficheHeathBar = false;
        }
    }

    public int getMaxHP() {
        return maxHP;
    }
    /**
     * Renvoie la largeur du bâtiment.
     *
     * @return La largeur du bâtiment.
     */

    public int getLargeur() {
        return largeur;
    }
    /**
     * Renvoie la hauteur du bâtiment.
     *
     * @return La hauteur du bâtiment.
     */
    public int getHauteur() {
        return hauteur;
    }
    /**
     * Renvoie le nom du bâtiment.
     *
     * @return Le nom du bâtiment.
     */
    public String getNom() {
        return nom;
    }
    /**
     * Retourne si la barre de vie doit être affichée.
     *
     * @return true si la barre de vie doit être affichée, false sinon.
     */
    public boolean isAfficheHeathBar() {
        return afficheHeathBar;
    }
    /**
     * Retourne la position du bâtiment sur la carte.
     *
     * @return la position du bâtiment sur la carte.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Retourne si le bâtiment est détruit ou non (son HP est égal ou inférieur à 0).
     *
     * @return true si le bâtiment est détruit, false sinon.
     */    public boolean estDetruit(){
        return this.HP <= 0;
    }

    /**
     * Réduit les HP du bâtiment en fonction des dégâts subis et active l'affichage de la barre de vie.
     *
     * @param deg la quantité de dégâts subis par le bâtiment.
     */

    public void subitDegat(int deg) {
        this.afficheHeathBar = true;
        this.HP -= deg;
    }
    /**
     * Indique si le bâtiment est sélectionné ou non.
     *
     * @return true si le bâtiment est sélectionné, false sinon.
     */
    public boolean isSelected() {
        return isSelected;
    }
    /**
     * Renvoie le niveau actuel du bâtiment.
     *
     * @return Le niveau actuel du bâtiment.
     */
    public int getNiveau() {
        return niveau;
    }

    //Setters:
    /**
     * Définit si le batiment est sélectionné ou non.
     * @param selected un booléen qui est vrai si le batiment est sélectionné, faux sinon
     */

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Définit si la barre de vie du batiment doit être affichée ou non.
     * @param afficheHeathBar un booléen qui est vrai si la barre de vie doit être affichée, faux sinon
     */
    public void setAfficheHeathBar(boolean afficheHeathBar) {
        this.afficheHeathBar = afficheHeathBar;
    }
    /**
     * Définit la position du batiment.
     * @param position un objet Point représentant la position du batiment
     */
    public void setPosition(Point position) {
        this.position = position;
    }
    /**
     * Définit le niveau du batiment.
     * @param niveau un entier représentant le niveau du batiment
     */
    public void setNiveau(int niveau){
        this.niveau = niveau;
    }
    /**
     * Définit les points de vie du batiment.
     * @param HP un entier représentant les points de vie du batiment
     */
    public void setHP(int HP) {
        this.HP = HP;
    }
    /**
     * Définit les points de vie maximum du batiment.
     * @param maxHP un entier représentant les points de vie maximum du batiment
     */
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    /**
     * Définit la hauteur du batiment.
     * @param hauteur un entier représentant la hauteur du batiment
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
    /**
     * Définit la largeur du batiment.
     * @param largeur un entier représentant la largeur du batiment
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
