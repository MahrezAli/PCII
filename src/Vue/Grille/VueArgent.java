/**
 Classe pour représenter la vue d'une pièce d'argent sur la grille de jeu.
 */
package Vue.Grille;

import Model.Arbre;
import Model.Argent;
import Vue.VueCase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VueArgent extends JPanel {
    private Argent argent;
    //Déclaration des images des pièces d'argent à l'état normal
    private BufferedImage coin1; //Image : argent à l'état 1
    private BufferedImage coin2; //Image : argent à l'état 2
    private BufferedImage coin3; //Image : argent à l'état 3
    private BufferedImage coin4; //Image : argent à l'état 4
    private BufferedImage coin5; //Image : argent à l'état 5
    private BufferedImage coin6; //Image : argent à l'état 6
    private BufferedImage coin7; //Image : argent à l'état 7
    private BufferedImage coin8; //Image : argent à l'état 8

    //Déclaration des images des pièces d'argent à l'état séléctionné
    private BufferedImage coin1S; //Image : argent à l'état 1
    private BufferedImage coin2S; //Image : argent à l'état 2
    private BufferedImage coin3S; //Image : argent à l'état 3
    private BufferedImage coin4S; //Image : argent à l'état 4
    private BufferedImage coin5S; //Image : argent à l'état 5
    private BufferedImage coin6S; //Image : argent à l'état 6
    private BufferedImage coin7S; //Image : argent à l'état 7
    private BufferedImage coin8S; //Image : argent à l'état 8

    /**
     * Constructeur de la classe VueArgent.
     *
     * @param argent l'argent à représenter dans la vue.
     */
    public VueArgent(Argent argent) {
        this.argent = argent;
        try {
            //Initialisation des images des pièces à l'état normal
            this.coin1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/normal/coin1.png"));
            this.coin2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/normal/coin2.png"));
            this.coin3 = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/normal/coin3.png"));
            this.coin4 = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/normal/coin4.png"));
            this.coin5 = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/normal/coin5.png"));
            this.coin6 = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/normal/coin6.png"));
            this.coin7 = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/normal/coin7.png"));
            this.coin8 = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/normal/coin8.png"));

            //Initialisation des images des pièces à l'état selectionné
            this.coin1S = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Selected/coin1.png"));
            this.coin2S = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Selected/coin2.png"));
            this.coin3S = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Selected/coin3.png"));
            this.coin4S = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Selected/coin4.png"));
            this.coin5S = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Selected/coin5.png"));
            this.coin6S = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Selected/coin6.png"));
            this.coin7S = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Selected/coin7.png"));
            this.coin8S = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Selected/coin8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Redéfinition de la méthode paintComponent de JPanel.
     * Elle permet de dessiner la pièce d'argent sur le plateau de jeu en fonction de son état (visible ou non).
     *
     * @param g Graphics.
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (this.argent.isSelected()) {
            if (this.argent.getEtat() == 1) {
                g.drawImage(this.coin1S, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 2) {
                g.drawImage(this.coin2S, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 3) {
                g.drawImage(this.coin3S, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 4) {
                g.drawImage(this.coin4S, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 5) {
                g.drawImage(this.coin5S, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 6) {
                g.drawImage(this.coin6S, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 7) {
                g.drawImage(this.coin7S, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 8) {
                g.drawImage(this.coin8S, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            }
        } else {
            if (this.argent.getEtat() == 1) {
                g.drawImage(this.coin1, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 2) {
                g.drawImage(this.coin2, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 3) {
                g.drawImage(this.coin3, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 4) {
                g.drawImage(this.coin4, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 5) {
                g.drawImage(this.coin5, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 6) {
                g.drawImage(this.coin6, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 7) {
                g.drawImage(this.coin7, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            } else if (this.argent.getEtat() == 8) {
                g.drawImage(this.coin8, this.argent.getPosition().x * VueCase.tailleCase, this.argent.getPosition().y * VueCase.tailleCase, null);
            }
        }
    }

    /**
     * Renvoie l'objet Argent associé à cette vue.
     *
     * @return l'objet Argent associé à cette vue.
     */
    public Argent getArgent() {
        return this.argent;
    }
}
