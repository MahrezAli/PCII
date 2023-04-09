package Model;

import Model.Potager.Potager;

import java.util.ArrayList;

public class Inventaire {
    private Game game; // instance de la classe Game
    private ArrayList<Potager> haies; // liste des Potagers
    private ArrayList<Batiment> cabanes; // liste des Cabanes
    private ArrayList<Defense> defenses; // liste des Défenses
    private int carottes; // quantité de carottes
    private int baies; // quantité de baies
    private int betteraves; // quantité de betteraves
    private int potionsCarottes; // quantité de potions de carottes
    private int potionsBaies; // quantité de potions de baies
    private int potionsBetteraves; // quantité de potions de betteraves
    /**
     * Constructeur de la classe Inventaire qui initialise toutes les listes à vide et les compteurs à zéro.
     * @param game une instance de la classe Game pour accéder aux autres éléments du jeu.
     */
    public Inventaire(Game game){
        this.game = game;
        this.haies = new ArrayList<>();
        this.cabanes = new ArrayList<>();
        this.defenses = new ArrayList<>();
        this.carottes = 0;
        this.baies = 0;
        this.betteraves = 0;
        this.potionsCarottes = 0;
        this.potionsBaies = 0;
        this.potionsBetteraves = 0;
    }

    //Guetters:

    /**
     * Renvoie la liste des cabanes du joueur.
     * @return la liste des cabanes du joueur
     */
    public ArrayList<Batiment> getCabanes() {
        return cabanes;
    }
    /**
     * Renvoie la liste des défenses du joueur.
     * @return la liste des défenses du joueur
     */

    public ArrayList<Defense> getDefenses() {
        return defenses;
    }
    /**
     * Renvoie la liste des haies du joueur.
     * @return la liste des haies du joueur
     */
    public ArrayList<Potager> getHaies() {
        return haies;
    }
    /**
     * Renvoie le nombre de carottes que le joueur possède.
     * @return le nombre de carottes que le joueur possède
     */

    public int getCarottes() {
        return carottes;
    }
    /**
     * Renvoie le nombre de baies que le joueur possède.
     * @return le nombre de baies que le joueur possède
     */
    public int getBaies() {
        return baies;
    }
    /**
     * Renvoie le nombre de betteraves que le joueur possède.
     * @return le nombre de betteraves que le joueur possède
     */

    public int getBetteraves() {
        return betteraves;
    }
    /**
     * Renvoie le nombre de potions de baies que le joueur possède.
     * @return le nombre de potions de baies que le joueur possède
     */

    public int getPotionsBaies() {
        return potionsBaies;
    }
    /**
     * Renvoie le nombre de potions de carottes que le joueur possède.
     * @return le nombre de potions de carottes que le joueur possède
     */

    public int getPotionsCarottes() {
        return potionsCarottes;
    }
    /**
     * Renvoie le nombre de potions de betteraves que le joueur possède.
     * @return le nombre de potions de betteraves que le joueur possède
     */

    public int getPotionsBetteraves() {
        return potionsBetteraves;
    }

    //Setters:
    /**
     * Ajoute une cabane à la liste des cabanes.
     * @param cabanes Instance de la classe Batiment à ajouter.
     */
    public void addCabanes(Batiment cabanes) {
        this.cabanes.add(cabanes);
    }
    /**
     * Ajoute une défense à la liste des défenses.
     * @param defenses Instance de la classe Defense à ajouter.
     */
    public void addDefenses(Defense defenses) {
        this.defenses.add(defenses);
    }
    /**
     * Ajoute un potager à la liste des potagers.
     * @param haies Instance de la classe Potager à ajouter.
     */

    public void addHaies(Potager haies) {
        this.haies.add(haies);
    }
    /**
     * Ajoute un nombre de carottes à la quantité de carottes.
     * @param carottes Nombre de carottes à ajouter.
     */
    public void addCarottes(int carottes) {
        this.carottes +=  carottes;
    }
    /**
     * Ajoute un nombre de betteraves à la quantité de betteraves.
     * @param betteraves Nombre de betteraves à ajouter.
     */

    public void addBetteraves(int betteraves) {
        this.betteraves +=  betteraves;
    }
    /**

     Ajoute des baies à l'inventaire.
     @param baies la quantité de baies à ajouter.
     */

    public void addBaies(int baies) {
        this.baies +=  baies;
    }
    /**

     Ajoute potion de baies à l'inventaire.
     @param potionsBaies la quantité de baies à ajouter.
     */
    public void addPotionsBaies(int potionsBaies) {
        this.potionsBaies +=  potionsBaies;
    }
    /**

     Ajoute potion de betterave à l'inventaire.
     @param potionsBetteraves la quantité de baies à ajouter.
     */

    public void addPotionsBetteraves(int potionsBetteraves) {
        this.potionsBetteraves += potionsBetteraves;
    }
    /**

     Ajoute des potions de carottes à l'inventaire.
     @param potionsCarottes la quantité de potions de carottes à ajouter.
     */

    public void addPotionsCarottes(int potionsCarottes) {
        this.potionsCarottes += potionsCarottes;
    }
    /**

     Retire une certaine quantité de cabanes de l'inventaire.
     @param cabanes la quantité de cabanes à retirer.
     */

    public void removeCabanes(int cabanes) {
        this.cabanes.remove(cabanes);
    }
    /**

     Retire une certaine quantité de défenses de l'inventaire.
     @param defenses la quantité de défenses à retirer.
     */

    public void removeDefenses(int defenses) {
        this.defenses.remove(defenses);
    }
    /**

     Retire une certaine quantité de haies de l'inventaire.
     @param haies la quantité de haies à retirer.
     */

    public void removeHaies(int haies) {
        this.haies.remove(haies);
    }
    /**

     Retire une certaine quantité de carottes de l'inventaire.
     @param carottes la quantité de haies à retirer.
     */

    public void removeCarottes(int carottes) {
        this.carottes -= carottes;
    }
    /**

     Retire une certaine quantité de betteravess de l'inventaire.
     @param betteraves la quantité de haies à retirer.
     */

    public void removeBetteraves(int betteraves) {
        this.betteraves -= betteraves;
    }
    /**

     Retire une certaine quantité de baies de l'inventaire.
     @param baies la quantité de haies à retirer.
     */

    public void removeBaies(int baies) {
        this.baies -= baies;
    }
    /**

     Retire une certaine quantité de potion de baies de l'inventaire.
     @param potionsBaies la quantité de haies à retirer.
     */
    public void removePotionsBaies(int potionsBaies) {
        this.potionsBaies -= potionsBaies;
    }
    /**

     Retire une certaine quantité de potion de betteriaves de l'inventaire.
     @param potionsBetteraves la quantité de haies à retirer.
     */

    public void removePotionsBetteraves(int potionsBetteraves) {
        this.potionsBetteraves -= potionsBetteraves;
    }
    /**

     Retire une certaine quantité de potions de carottes de l'inventaire.
     @param potionsCarottes la quantité de haies à retirer.
     */
    public void removePotionsCarottes(int potionsCarottes) {
        this.potionsCarottes -= potionsCarottes;
    }
}
