/**

 La classe Case représente une case de la grille du jeu. Elle peut contenir différents éléments comme un batiment,
 un potager, un villageois, un monstre ou de l'argent. Elle peut également être occupée ou non.
 Elle est observable et on notifie ses observateurs lorsqu'un élément est ajouté ou retiré de la case.
 */
package Model;
import Model.Entity.Monstre;
import Model.Entity.Villageois;
import Model.Potager.Potager;

import java.util.Observable;

public class Case extends Observable {

    private int x; //Coordonnée en abscisse
    private int y; //Coordonnée en ordonnée
    private boolean occupee;
    private boolean emplacementBat;

    private Batiment batiment;
    private Potager potager;
    private Villageois villageois;
    private Monstre monstre;
    private Argent argent;

    //Attributs pour A*
    private Case pere;
    private int cout;
    private int heuristique;

    /**
     * Constructeur de la classe Case. Initialise les attributs de la case à des valeurs par défaut.
     * @param x la coordonnée en abscisse de la case
     * @param y la coordonnée en ordonnée de la case
     */
    public Case(int x, int y){
        this.x = x;
        this.y = y;
        this.occupee = false;
        this.potager = null;
        this.villageois = null;
        this.batiment = null;
        this.monstre = null;
        this.argent = null;

        this.pere = null;
        this.cout = 0;
        this.heuristique = 0;
    }

    //Connaitre le type de batiment dans la case
    public String batimentType(){
        if(this.batiment != null){
            return this.batiment.getNom();
        }
        else{
            return null;
        }
    }

    //Savoir si un villageois est dans la case
    public boolean isVillageois() {
        return this.villageois != null && this.villageois.getFonction() =="Villageois";
    }

    public boolean isMonstre() {
        return this.monstre != null && this.monstre.getFonction() =="Monstre";
    }


    //Guetters :
    /**
     * Retourne le père de cette case dans l'arborescence de recherche de chemin.
     *
     * @return Le père de cette case.
     */
    public Case getPere() {
        return this.pere;
    }
    /**
     * Retourne le coût de la case.
     *
     * @return Le coût de la case.
     */
    public int getCout() {
        return this.cout;
    }
    /**
     * Retourne la valeur heuristique de la case.
     *
     * @return La valeur heuristique de la case.
     */

    public int getHeuristique() {
        return this.heuristique;
    }

    /**
     * Retourne la coordonnée X de la case.
     *
     * @return La coordonnée X de la case.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retourne la coordonnée Y de la case.
     *
     * @return La coordonnée Y de la case.
     */
    public int getY() {
        return this.y;
    }
    /**
     * Vérifie si la case est occupée.
     *
     * @return True si la case est occupée, false sinon.
     */
    public boolean isOccupee() {
        return this.occupee;
    }

    /**
     * Retourne l'argent présent sur la case.
     *
     * @return L'argent présent sur la case.
     */
    public Argent getArgent() {
        return argent;
    }
    /**
     * Vérifie si la case est un emplacement de bâtiment.
     *
     * @return True si la case est un emplacement de bâtiment, false sinon.
     */
    public boolean isEmplacementBat() {
        return emplacementBat;
    }

    /**
     * Vérifie si la case est un bâtiment.
     *
     * @return True si la case est un bâtiment, false sinon.
     */

    public boolean isBatiment() {
        return this.batiment != null;
    }
    /**
     * Retourne le villageois présent sur la case.
     *
     * @return Le villageois présent sur la case.
     */
    public Villageois getVillageois() {
        return villageois;
    }

    //Setters:
    /**
     * Modifie l'état d'occupation de la case.
     *
     * @param occupee True si la case est occupée, false sinon.
     */
    public void setOccupee(boolean occupee) {
        this.occupee = occupee;
    }
    /**
     * Modifie le bâtiment présent sur la case.
     *
     * @param batiment Le bâtiment à ajouter à la case.
     */

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }
    /**
     * Modifie le potager présent sur la case.
     *
     * @param potager Le potager à ajouter à la case.
     */
    public void setPotager(Potager potager) {
        this.potager = potager;
    }
    /**
     * Modifie la valeur heuristique de la case.
     *
     * @param heuristique La nouvelle valeur heuristique de la case.
     */
    public void setHeuristique(int heuristique) {
        this.heuristique = heuristique;
    }

    /**
     * Modifie l'argent présent sur la case.
     *
     * @param argent L'argent à ajouter à la case.
     */
    public void setArgent(Argent argent) {
        if(argent == null){
            this.argent = null;
            this.occupee = false;
        }
        else{
            this.argent = argent;
            this.occupee = true;
        }
        this.notifyObservers();
    }
    /**
     * Met à jour le coût associé à la case.
     * @param cout le nouveau coût à associer à la case
     */
    public void setCout(int cout) {
        this.cout = cout;
    }
    /**
     * Met à jour le père de la case.
     * @param pere la nouvelle case à associer comme père
     */

    public void setPere(Case pere) {
        this.pere = pere;
    }
    /**
     * Met à jour la disponibilité de l'emplacement de bâtiment sur la case.
     * @param emplacementBat la nouvelle disponibilité de l'emplacement de bâtiment sur la case
     */
    public void setEmplacementBat(boolean emplacementBat) {
        this.emplacementBat = emplacementBat;
    }

    /**
     * Met à jour le villageois associé à la case.
     * Si le villageois est null, la case devient libre.
     * @param villageois le nouveau villageois associé à la case
     */
    public void setVillageois(Villageois villageois) {
        if(villageois == null){
            this.villageois = null;
            this.occupee = false;
        }
        else{
            this.villageois = villageois;
            this.occupee = true;
        }
        this.notifyObservers();
    }

    /**
     * Met à jour le monstre associé à la case.
     * Si le monstre est null, la case devient libre.
     * @param monstre le nouveau monstre associé à la case
     */
    public void setMonstres(Monstre monstre) {
        if(monstre == null){
            this.monstre = null;
            this.occupee = false;
        }
        else{
            this.monstre = monstre;
            this.occupee = true;
        }
        this.notifyObservers();
    }



}
