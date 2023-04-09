/**
 Cette classe représente l'affichage du jeu, qui contient la grille et la boutique.
 */
package Vue;

import Model.Grille;
import Controler.Control;
import Vue.Grille.VueGrille;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel {
    public static final int FENETRE_HAUTEUR = 520;
    public static final int FENETRE_LARGEUR = 520;
    private VueGrille grille;
    private VueShop shop;
    private Control control;
    private JLayeredPane layeredPane;

    /**
     * Constructeur de la classe Affichage, qui prend une grille et un contrôleur en paramètres.
     * @param grille La grille du jeu à afficher.
     * @param control Le contrôleur pour la communication avec la vue et le modèle.
     */
    public Affichage(Grille grille, Control control){
        this.control = control;
        this.grille = new VueGrille(grille);
        this.shop = new VueShop(this.control);
        this.layeredPane = new JLayeredPane();
        this.setPreferredSize(new Dimension(FENETRE_LARGEUR, FENETRE_HAUTEUR));
        this.layeredPane.setPreferredSize(new Dimension(FENETRE_LARGEUR, FENETRE_HAUTEUR));
        //this.setLayout(new BorderLayout());
        this.grille.setBounds(0,0,FENETRE_LARGEUR, FENETRE_HAUTEUR);
        this.layeredPane.add(this.grille, Integer.valueOf(0));
        this.shop.setBounds((FENETRE_LARGEUR/2) -200,(FENETRE_HAUTEUR/2)-200,400, 400);
        this.layeredPane.add(this.shop, Integer.valueOf(1));

        // Ajouter la couche au conteneur parent
        this.add(this.layeredPane);
        this.setFocusable(true);
    }

    //Getters:
    /**
     * Getter pour la vue de la grille du jeu.
     * @return La vue de la grille.
     */
    public VueGrille getGrille() {
        return grille;
    }

    /**
     * Getter pour la vue de la boutique.
     * @return La vue de la boutique.
     */
    public VueShop getShop() {
        return shop;
    }

}
