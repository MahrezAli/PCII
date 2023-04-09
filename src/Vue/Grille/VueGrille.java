/**

 La classe VueGrille représente la vue de la grille de jeu. Elle est un JPanel et observe la grille.
 Elle contient des vues pour les joueurs, les potagers, les arbres, les défenses, les monstres, les bâtiments et les pièces d'argent.
 Elle possède également un booléen rajouteBatiment qui est vrai si le joueur est en train d'ajouter un bâtiment.
 */
package Vue.Grille;

import Model.Arbre;
import Model.Grille;
import Vue.VueCase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VueGrille extends JPanel implements Observer {
    private Grille grille;
    private final static int TAILLE = 10;
    private BufferedImage background1;
    private VueCase[][] vueCases;
    private ArrayList<VueJoueur> joueurs;
    private ArrayList<VuePotager> potagers;
    private ArrayList<VueArbre> arbres;
    private ArrayList<VueDefense> defenses;
    private ArrayList<VueMonstre> monstres;
    private ArrayList<VueBatiment> batiments;
    private ArrayList<VueArgent> argents;
    private boolean rajouteBatiment;

    /**
     * Constructeur de la classe VueGrille.
     * @param grille La grille de jeu à observer.
     */
    public VueGrille(Grille grille) {
        this.grille = grille;
        this.rajouteBatiment = false;
        this.grille.addObserver(this);

        try {
            this.background1 = ImageIO.read(getClass().getClassLoader().getResource("Image/background1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.vueCases = new VueCase[Grille.LARGEUR][Grille.HAUTEUR];
        this.joueurs = new ArrayList<>();
        this.potagers = new ArrayList<>();
        this.arbres = new ArrayList<>();
        this.defenses = new ArrayList<>();
        this.monstres = new ArrayList<>();
        this.batiments = new ArrayList<>();
        this.argents = new ArrayList<>();

        //Initialisation de la vue des cases
        for (int i = 0; i < Grille.LARGEUR; i++) {
            for (int j = 0; j < Grille.HAUTEUR; j++) {
                this.vueCases[i][j] = new VueCase(this.grille.getCases()[i][j]);
            }
        }

        //Initialisation de la vue pour les joueurs
        for(int i = 0; i < grille.getVillageois().size(); i++){
            this.joueurs.add(new VueJoueur(grille.getVillageois().get(i)));
        }

        //Initialisation de la vue pour les potagers
        for(int i = 0; i < grille.getPotagers().size(); i++){
            this.potagers.add(new VuePotager(grille.getPotagers().get(i)));
        }

        //Initialisation de la vue pour les arbres
        for(int i = 0; i < grille.getArbres().size(); i++){
            this.arbres.add(new VueArbre(grille.getArbres().get(i)));
        }

        //Initialisation de la vue pour les défenses
        for(int i = 0; i < grille.getDefenses().size(); i++){
            this.defenses.add(new VueDefense(grille.getDefenses().get(i)));
        }

        //Initialisation de la vue pour les défenses
        for(int i = 0; i < grille.getMonstre().size(); i++){
            this.monstres.add(new VueMonstre(grille.getMonstre().get(i)));
        }

        this.batiments.add(new VueBatiment(grille.getHotelVille()));
        //Initialisation de la vue pour les batiments
        for(int i = 0; i < grille.getCabanes().size(); i++){
            this.batiments.add(new VueBatiment(grille.getCabanes().get(i)));
        }



        Dimension dim = new Dimension(TAILLE*Grille.LARGEUR*16, TAILLE*Grille.HAUTEUR*10);
        this.setPreferredSize(dim);
        this.setOpaque(false); //Définir un fond transparant
    }
    /**
     On redéfinit de la méthode paintComponent de JPanel pour dessiner les différents éléments de la grille.
     Elle dessine les arbres, les bâtiments, les potagers, les joueurs, les défenses, les monstres et l'argent.
     Si le mode rajouteBatiment est activé, elle dessine également les cases vides pour les bâtiments.
     @param g Le Graphics pour dessiner les éléments
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Dessiner le fond d'écran
        for(int x = Arbre.LARGEUR; x < Grille.LARGEUR - Arbre.LARGEUR; x += Arbre.LARGEUR) {
            for(int y = Arbre.HAUTEUR; y < Grille.HAUTEUR - Arbre.HAUTEUR; y += Arbre.HAUTEUR) {
                g.drawImage(this.background1, x*VueCase.tailleCase, y*VueCase.tailleCase, this);
            }
        }
        for(int i = 0; i < this.arbres.size(); i++){
            this.arbres.get(i).paintComponent(g);
        }
        for(int i = 0; i < this.batiments.size(); i++){
            this.batiments.get(i).paintComponent(g);
        }
        for(int i = 0; i < this.potagers.size(); i++){
            this.potagers.get(i).paintComponent(g);
        }
        for(int i = 0; i < this.joueurs.size(); i++){
            this.joueurs.get(i).paintComponent(g);
        }
        for(int i = 0; i < this.defenses.size(); i++){
            this.defenses.get(i).paintComponent(g);
        }
        for(int i = 0; i < this.monstres.size(); i++){
            this.monstres.get(i).paintComponent(g);
        }
        for(int i = 0; i< this.argents.size(); i++){
            this.argents.get(i).paintComponent(g);
        }
        if(this.rajouteBatiment){
            for(int i = 0; i < Grille.LARGEUR; i++){
                for(int j = 0; j < Grille.HAUTEUR; j++){
                    this.vueCases[i][j].paintComponent(g);
                }
            }
        }
    }

    /**
     On redéfinit de la méthode update de l'interface Observer.
     Elle redessine la grille à chaque mise à jour de l'observable.
     @param o L'observable
     @param arg L'objet transmis par l'observable
     */
    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    //Guetters:

    /**
     Getter pour l'attribut rajouteBatiment.
     @return true si le mode rajouteBatiment est activé, false sinon
     */
    public boolean isRajouteBatiment() {
        return rajouteBatiment;
    }
    /**
     Getter pour la liste des vues pour les potagers.
     @return La liste des vues pour les potagers
     */
    public ArrayList<VuePotager> getPotagers() {
        return potagers;
    }

     /**
     Getter pour la liste des vues pour les défenses.
     @return La liste des vues pour les défenses
     */
    public ArrayList<VueDefense> getDefenses() {
        return defenses;
    }
    /**

     Getter pour la liste des vues pour les joueurs.
     @return La liste des vues pour les joueurs
     */

    public ArrayList<VueJoueur> getJoueurs() {
        return joueurs;
    }
    /**

     Getter pour la liste des batiment pour les joueurs.
     @return La liste des batiment
     */

    public ArrayList<VueBatiment> getBatiments() {
        return batiments;
    }
    /**

     Getter pour la liste des monstres sur la grille.
     @return La liste des monstres sur la grille
     */
    public ArrayList<VueMonstre> getMonstres() {
        return monstres;
    }
    /**

     Getter pour la liste des pièces pour les joueurs.
     @return La liste des pièces pour les joueurs
     */

    public ArrayList<VueArgent> getArgents() {
        return argents;
    }

    //Setters:

    /**
     * Définit si on est en train de rajouter un bâtiment.
     * @param rajouteBatiment true si on est en train de rajouter un bâtiment, false sinon
     */
    public void setRajouteBatiment(boolean rajouteBatiment) {
        this.rajouteBatiment = rajouteBatiment;
    }
    /**
     * Ajoute un monstre à la liste des monstres à afficher.
     * @param monstre le monstre à ajouter
     */

    public void addMonstres(VueMonstre monstre) {
        this.monstres.add(monstre);
    }
    /**
     * Ajoute une pièce d'argent à la liste des pièces d'argent à afficher.
     * @param argent la pièce d'argent à ajouter
     */
    public void addArgent(VueArgent argent){
        this.argents.add(argent);
    }
    /**
     * Supprime un monstre de la liste des monstres à afficher.
     * @param monstre l'indice du monstre à supprimer
     */

    public void removeMonstre(int monstre){
        this.monstres.remove(monstre);
    }
    /**
     * Supprime une pièce d'argent de la liste des pièces d'argent à afficher.
     * @param argent l'indice de la pièce d'argent à supprimer
     */

    public void removeArgent(int argent){
        this.argents.remove(argent);
    }


}






