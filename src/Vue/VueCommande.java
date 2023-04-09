/**
 Cette classe représente la vue des commandes disponibles pour le joueur.
 */
package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import Model.Grille;
import Controler.Control;

import java.awt.*;

public class VueCommande extends JPanel {
    Control control;
    /**
     * Constructeur de la classe VueCommande.
     * @param control le controler associé à la vue.
     */
    public VueCommande(Control control){

        //Définition du layout en GridBagLayout
        this.setLayout(new GridBagLayout());

        //Création de contraintes GridBagConstraints pour définir l'emplacement des boutons dans le layout
        GridBagConstraints c = new GridBagConstraints();

        //Définition de la taille de la vue de commande
        this.setSize(400,100);

        //Création des boutons de commande
        JButton planter = new JButton("Planter");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(planter, c);

        JButton recolter = new JButton("Récolter");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.add(recolter, c);

        JButton attaquer = new JButton("Attaquer");
        c.gridx = 1;
        c.gridy = 0;
        this.add(attaquer,c);

        JButton ramasser = new JButton("Ramasser");
        c.gridx = 1;
        c.gridy = 1;
        this.add(ramasser,c);

        JButton construire = new JButton("Construire");
        c.gridx = 2;
        c.gridy = 0;
        this.add(construire,c);

        JButton utiliser = new JButton("Utiliser");
        c.gridx = 2;
        c.gridy = 1;
        this.add(utiliser,c);

        JButton ameliorer = new JButton("Améliorer");
        c.gridx = 1;
        c.gridy = 2;
        this.add(ameliorer,c);


        /*try {
            Image img = ImageIO.read(getClass().getResource("/Image/Button/recolter.png"));
            recolter.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println(ex);
        }*/
        //Association de la commande à la classe Control pour la gestion des événements
        this.control = control;

        //Association des événements pour chaque bouton
        planter.addActionListener(this.control);
        planter.addKeyListener(this.control);
        recolter.addActionListener(this.control);
        recolter.addKeyListener(this.control);
        attaquer.addActionListener(this.control);
        attaquer.addKeyListener(this.control);
        ramasser.addActionListener(this.control);
        ramasser.addKeyListener(this.control);
        construire.addActionListener(this.control);
        construire.addKeyListener(this.control);
        utiliser.addActionListener(this.control);
        utiliser.addKeyListener(this.control);
        ameliorer.addActionListener(this.control);
        ameliorer.addKeyListener(this.control);
    }


}
