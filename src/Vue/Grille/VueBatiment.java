/**

 La classe VueBatiment représente la vue graphique d'un bâtiment sur la grille de jeu.
 Elle étend la classe JPanel et implémente l'interface Observer pour recevoir des notifications des changements
 de l'état du bâtiment observé.
 */
package Vue.Grille;

import Model.Batiment;
import Vue.VueCase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class VueBatiment extends JPanel implements Observer {
    private Batiment batiment;
    private int maxHp;
//Images représentant les différents états du bâtiment

    private BufferedImage cabane;
    private BufferedImage hdv;
    private BufferedImage cabaneS;
    private BufferedImage hdvS;
    private BufferedImage healthBar1; //Image : barre de santé état 1
    private BufferedImage healthBar2; //Image : barre de santé état 2
    /**
     * Constructeur de la classe VueBatiment.
     * Initialise les attributs de la classe avec les valeurs du bâtiment observé et charge les images nécessaires.
     *
     * @param batiment Le bâtiment observé.
     */
    public VueBatiment(Batiment batiment){
        this.batiment = batiment;
        this.maxHp = this.batiment.getHP();
        this.batiment.addObserver(this);
        try {
            this.cabane = ImageIO.read(getClass().getClassLoader().getResource("Image/Batiment/cabane.png"));
            this.hdv = ImageIO.read(getClass().getClassLoader().getResource("Image/Batiment/HDV.png"));
            this.cabaneS = ImageIO.read(getClass().getClassLoader().getResource("Image/Batiment/cabaneS.png"));
            this.hdvS = ImageIO.read(getClass().getClassLoader().getResource("Image/Batiment/HDVS.png"));
            this.healthBar1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Health/health_bar_batiment.png"));
            this.healthBar2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Health/health_bar_batiment2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Redéfinition de la méthode paintComponent de la classe JPanel pour dessiner les éléments de la vue.
     * Affiche l'image de la barre de santé si le bâtiment est sélectionné.
     * Affiche l'image du bâtiment correspondant à son état.
     *
     * @param g L'objet Graphics utilisé pour dessiner les éléments.
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if(this.batiment.isAfficheHeathBar()){
            g.drawImage(this.healthBar2, (this.batiment.getPosition().x-3)* VueCase.tailleCase, (this.batiment.getPosition().y-4)*VueCase.tailleCase, null);
            if(this.batiment.getHP() > 0) {
                float HB = (float) this.batiment.getHP() / this.maxHp;
                int newWidth = (int) (this.healthBar1.getWidth() * HB);
                if(newWidth > 0) {
                    BufferedImage resizedImage = new BufferedImage(newWidth, this.healthBar1.getHeight(), this.healthBar1.getType());
                    Graphics2D g2 = resizedImage.createGraphics();
                    g2.drawImage(this.healthBar1, 0, 0, newWidth, this.healthBar1.getHeight(), null);
                    g2.dispose();
                    // Utiliser l'image redimensionnée pour afficher la barre de santé
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.drawImage(resizedImage, (this.batiment.getPosition().x - 3) * VueCase.tailleCase, (this.batiment.getPosition().y - 4) * VueCase.tailleCase, null);
                }
            }
        }
        if("Cabane".equals(this.batiment.getNom())){
            if(this.batiment.isSelected()){
                g.drawImage(this.cabaneS, this.batiment.getPosition().x*VueCase.tailleCase, this.batiment.getPosition().y*VueCase.tailleCase, null);
            }
            else{
                g.drawImage(this.cabane, this.batiment.getPosition().x*VueCase.tailleCase, this.batiment.getPosition().y*VueCase.tailleCase, null);
            }
        }
        else if("HotelVille".equals(this.batiment.getNom())){
            if(this.batiment.isSelected()){
                g.drawImage(this.hdvS, this.batiment.getPosition().x*VueCase.tailleCase, this.batiment.getPosition().y*VueCase.tailleCase, null);
            }
            else{
                g.drawImage(this.hdv, this.batiment.getPosition().x*VueCase.tailleCase, this.batiment.getPosition().y*VueCase.tailleCase, null);
            }
        }
    }
    /**

     Cette méthode est appelée lorsqu'un observateur détecte un changement chez l'objet observé.
     Elle demande alors à ce que le composant soit redessiné pour refléter les changements.
     @param o l'objet observé
     @param arg l'argument passé lors de la notification, s'il y en a un
     */

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
