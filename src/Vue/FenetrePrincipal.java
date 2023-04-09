package Vue;

import javax.swing.*;
import java.awt.*;
import Controler.Control;
import Model.Grille;

public class FenetrePrincipal extends JFrame {
    public JFrame fenetre; // Déclaration de la fenêtre d'affichage
    public Control control; // Déclaration du control
    private VueCommande commande; // Instance de la vue de la commande
    private VueConsole console; // Instance de la vue de la console

    public FenetrePrincipal() {
        // Initialisation de la fenêtre
        this.fenetre = new JFrame("Projet_PCII");
        // Initialisation du contrôleur
        this.control = new Control();
        // Définition du layout de la fenêtre
        this.fenetre.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Focus sur la fenêtre
        this.fenetre.requestFocus();

        // Ajout d'un JPanel blanc à gauche de l'inventaire pour centrer l'inventaire
        JPanel blank4 = new JPanel();
        blank4.setPreferredSize(new Dimension(20, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.fenetre.add(blank4, c);

        // Initialisation de la vue de l'inventaire et ajout à la fenêtre
        VueInventaire inventaire = new VueInventaire(this.control);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        this.fenetre.add(inventaire, c);
        // Enregistrement de la vue de l'inventaire dans le contrôleur
        this.control.setInventaire(inventaire);

        // Ajout d'un JPanel blanc à droite de l'inventaire pour centrer la grille de jeu
        JPanel blank5 = new JPanel();
        blank5.setPreferredSize(new Dimension(20, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        this.fenetre.add(blank5, c);

        // Ajout de la vue de la grille de jeu à la fenêtre
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        this.fenetre.add(this.control.getAffichage(), c);

        // Initialisation de la vue de la commande et ajout à la fenêtre
        this.commande = new VueCommande(this.control);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        this.fenetre.add(this.commande, c);

        // Ajout d'un JPanel blanc pour espacer les éléments
        JPanel blank = new JPanel();
        blank.setPreferredSize(new Dimension(5, 5));
        c.gridx = 4;
        c.gridy = 0;
        this.fenetre.add(blank);

        // Initialisation du conteneur de la vue de la trésorerie et de la console
        JPanel containerBorder = new JPanel();
        containerBorder.setLayout(new GridBagLayout());

        // Initialisation de la vue de la trésorerie et ajout au conteneur
        VueTresorerie vueTresorerie = new VueTresorerie(this.control.getGame());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        containerBorder.add(vueTresorerie, c);
        // Enregistrement de la vue de la trésorerie dans le contrôleur
        this.control.setTresorerie(vueTresorerie);
        //Crée une zone vide et l'ajoute dans le conteneur principal.
        JPanel blank2 = new JPanel();
        blank2.setPreferredSize(new Dimension(20, 120));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        containerBorder.add(blank2, c);
       // Crée une instance de VueConsole avec le contrôleur et l'ajoute dans le conteneur principal.

        this.console = new VueConsole(this.control);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        containerBorder.add(this.console, c);
        this.control.setConsole(this.console);
        //Crée une zone vide et l'ajoute dans le conteneur principal.
        JPanel blank3 = new JPanel();
        blank3.setPreferredSize(new Dimension(20, 200));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        containerBorder.add(blank3, c);
        //Crée un bouton "Shop", l'ajoute dans le conteneur principal et ajoute des écouteurs d'événements de contrôle.
        JButton shop = new JButton("Shop");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        containerBorder.add(shop, c);
        shop.addActionListener(this.control);
        shop.addKeyListener(this.control);
        //Ajoute le conteneur principal dans la fenêtre principale et définit ses paramètres d'affichage et de fermeture.
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridy = 0;

        this.fenetre.add(containerBorder, c);

        this.fenetre.pack();
        this.fenetre.setVisible(true);
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}

