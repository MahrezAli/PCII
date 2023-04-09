/**
 VueMonstre gère l'affichage d'un Monstre sur la grille de jeu.
 Elle étend la classe JPanel et utilise des images pour afficher le monstre et sa barre de vie.
 La classe est utilisée pour afficher les Monstres lorsqu'ils sont sur la grille de jeu.
 */
package Vue.Grille;

import Model.Entity.Monstre;
import Vue.VueCase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VueMonstre extends JPanel {
    private Monstre monstre;
    private int maxHp;
    //Déclaration des images du monstre
    private BufferedImage slimeVert1; //Image : monstre vert état 1
    private BufferedImage slimeVert2; //Image : monstre vert état 2
    private BufferedImage slimeRouge1; //Image : monstre rouge état 1
    private BufferedImage slimeRouge2; //Image : monstre rouge état 2
    private BufferedImage slimeJaune1; //Image : monstre rouge état 1
    private BufferedImage slimeJaune2; //Image : monstre rouge état 2
    private BufferedImage healthBar1; //Image : barre de santé état 1
    private BufferedImage healthBar2; //Image : barre de santé état 2

    /**
     * Constructeur de la classe VueMonstre.
     * @param monstre le Monstre à afficher
     */
    public VueMonstre(Monstre monstre){
        this.monstre = monstre;
        this.maxHp = this.monstre.getHp();
        try {
            //Initialisation des images du monstre
            this.slimeVert1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Monstre/greenslime1.png"));
            this.slimeVert2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Monstre/greenslime2.png"));
            this.slimeRouge1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Monstre/redslime1.png"));
            this.slimeRouge2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Monstre/redslime2.png"));
            this.slimeJaune1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Monstre/slimeYellow1.png"));
            this.slimeJaune2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Monstre/slimeYellow2.png"));
            this.healthBar1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Health/health_bar_entity.png"));
            this.healthBar2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Health/health_bar_entity2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Méthode paintComponent qui dessine le Monstre sur la grille de jeu.
     * @param g le graphic utilisé pour le dessin
     */
    public void paintComponent(Graphics g) {
        if(this.monstre.getAfficheHeathBar()){
            g.drawImage(this.healthBar2, (this.monstre.getPosition().x-3)* VueCase.tailleCase, (this.monstre.getPosition().y-3)*VueCase.tailleCase, null);
            if(this.monstre.getHp() > 0) {
                float HB = (float) this.monstre.getHp() / this.maxHp;
                int newWidth = (int) (this.healthBar1.getWidth() * HB);
                BufferedImage resizedImage = new BufferedImage(newWidth, this.healthBar1.getHeight(), this.healthBar1.getType());
                Graphics2D g2 = resizedImage.createGraphics();
                g2.drawImage(this.healthBar1, 0, 0, newWidth, this.healthBar1.getHeight(), null);
                g2.dispose();
                // Utiliser l'image redimensionnée pour afficher la barre de santé
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(resizedImage, (this.monstre.getPosition().x - 3) * VueCase.tailleCase, (this.monstre.getPosition().y - 3) * VueCase.tailleCase, null);
            }
        }
        if(!this.monstre.subitAttaque()){
            if(this.monstre.isSelected()){
                if(this.monstre.getEtatWalk() == 1){
                    g.drawImage(this.slimeJaune1, this.monstre.getPosition().x*VueCase.tailleCase, this.monstre.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.monstre.getEtatWalk() == 2){
                    g.drawImage(this.slimeJaune2, this.monstre.getPosition().x*VueCase.tailleCase, this.monstre.getPosition().y*VueCase.tailleCase, null);
                }

            }else{
                if(this.monstre.getEtatWalk() == 1){
                    g.drawImage(this.slimeVert1, this.monstre.getPosition().x*VueCase.tailleCase, this.monstre.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.monstre.getEtatWalk() == 2){
                    g.drawImage(this.slimeVert2, this.monstre.getPosition().x*VueCase.tailleCase, this.monstre.getPosition().y*VueCase.tailleCase, null);
                }
            }
        }
        else{
            if(this.monstre.getEtatWalk() == 1){
                g.drawImage(this.slimeRouge1, this.monstre.getPosition().x*VueCase.tailleCase, this.monstre.getPosition().y*VueCase.tailleCase, null);
            }
            else if(this.monstre.getEtatWalk() == 2){
                g.drawImage(this.slimeRouge2, this.monstre.getPosition().x*VueCase.tailleCase, this.monstre.getPosition().y*VueCase.tailleCase, null);
            }
        }
    }
    /**
     * Renvoie l'objet Monstre associé à cette vue.
     * @return l'objet Monstre
     */
    public Monstre getMonstre() {
        return monstre;
    }
}
