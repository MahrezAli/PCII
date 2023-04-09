/**

 Cette classe représente la vue d'un joueur. Elle étend JPanel.
 Elle est utilisée pour afficher l'image du joueur sur la grille.
 */

package Vue.Grille;

import Model.Entity.Entite;
import Utils.ActionVillageois;
import Utils.Direction;
import Vue.VueCase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class VueJoueur extends JPanel{
    private Entite joueur;
    //Déclaration des images du villageois en mode 'walk' état : normal
    private BufferedImage up1; //Image 1 : up1
    private BufferedImage up2; //Image 1 : up2
    private BufferedImage up3; //Image 1 : up3
    private BufferedImage up4; //Image 1 : up4
    private BufferedImage down1; //Image 1 : down1
    private BufferedImage down2; //Image 1 : down2
    private BufferedImage down3; //Image 1 : down3
    private BufferedImage down4; //Image 1 : down4
    private BufferedImage left1; //Image 1 : left1
    private BufferedImage left2; //Image 1 : left2
    private BufferedImage left3; //Image 1 : left3
    private BufferedImage left4; //Image 1 : left4
    private BufferedImage right1; //Image 1 : right1
    private BufferedImage right2; //Image 1 : right2
    private BufferedImage right3; //Image 1 : right3
    private BufferedImage right4; //Image 1 : right4

    //Déclaration des images du villageois en mode 'walk' état : sélectionné
    private BufferedImage up1S; //Image 1 : up1
    private BufferedImage up2S; //Image 1 : up2
    private BufferedImage up3S; //Image 1 : up3
    private BufferedImage up4S; //Image 1 : up4
    private BufferedImage down1S; //Image 1 : down1
    private BufferedImage down2S; //Image 1 : down2
    private BufferedImage down3S; //Image 1 : down3
    private BufferedImage down4S; //Image 1 : down4
    private BufferedImage left1S; //Image 1 : left1
    private BufferedImage left2S; //Image 1 : left2
    private BufferedImage left3S; //Image 1 : left3
    private BufferedImage left4S; //Image 1 : left4
    private BufferedImage right1S; //Image 1 : right1
    private BufferedImage right2S; //Image 1 : right2
    private BufferedImage right3S; //Image 1 : right3
    private BufferedImage right4S; //Image 1 : right4

    //Déclaration des images du villageois en mode 'walk Attak'
    private BufferedImage up1WA; //Image 1 : up1
    private BufferedImage up2WA; //Image 1 : up2
    private BufferedImage up3WA; //Image 1 : up3
    private BufferedImage up4WA; //Image 1 : up4
    private BufferedImage down1WA; //Image 1 : down1
    private BufferedImage down2WA; //Image 1 : down2
    private BufferedImage down3WA; //Image 1 : down3
    private BufferedImage down4WA; //Image 1 : down4
    private BufferedImage left1WA; //Image 1 : left1
    private BufferedImage left2WA; //Image 1 : left2
    private BufferedImage left3WA; //Image 1 : left3
    private BufferedImage left4WA; //Image 1 : left4
    private BufferedImage right1WA; //Image 1 : right1
    private BufferedImage right2WA; //Image 1 : right2
    private BufferedImage right3WA; //Image 1 : right3
    private BufferedImage right4WA; //Image 1 : right4

    //Déclaration des images du villageois en mode 'Attak'
    private BufferedImage up1A; //Image 1 : up1
    private BufferedImage up2A; //Image 1 : up2
    private BufferedImage up3A; //Image 1 : up3
    private BufferedImage up4A; //Image 1 : up4
    private BufferedImage down1A; //Image 1 : down1
    private BufferedImage down2A; //Image 1 : down2
    private BufferedImage down3A; //Image 1 : down3
    private BufferedImage down4A; //Image 1 : down4
    private BufferedImage left1A; //Image 1 : left1
    private BufferedImage left2A; //Image 1 : left2
    private BufferedImage left3A; //Image 1 : left3
    private BufferedImage left4A; //Image 1 : left4
    private BufferedImage right1A; //Image 1 : right1
    private BufferedImage right2A; //Image 1 : right2
    private BufferedImage right3A; //Image 1 : right3
    private BufferedImage right4A; //Image 1 : right4





    //Constructeur :
    public VueJoueur(Entite joueur){
        this.joueur = joueur;
        try {
            //Initialisation des images du villageois en mode 'walk' état : normal
            this.up1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/up1.png"));
            this.up2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/up2.png"));
            this.up3 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/up3.png"));
            this.up4 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/up4.png"));
            this.down1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/down1.png"));
            this.down2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/down2.png"));
            this.down3 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/down3.png"));
            this.down4 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/down4.png"));
            this.left1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/left1.png"));
            this.left2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/left2.png"));
            this.left3 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/left3.png"));
            this.left4 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/left4.png"));
            this.right1 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/right1.png"));
            this.right2 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/right2.png"));
            this.right3 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/right3.png"));
            this.right4 = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walk/right4.png"));
            //Initialisation des images du villageois en mode 'walk' état : sélectionné
            this.up1S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/up1.png"));
            this.up2S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/up2.png"));
            this.up3S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/up3.png"));
            this.up4S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/up4.png"));
            this.down1S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/down1.png"));
            this.down2S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/down2.png"));
            this.down3S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/down3.png"));
            this.down4S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/down4.png"));
            this.left1S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/left1.png"));
            this.left2S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/left2.png"));
            this.left3S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/left3.png"));
            this.left4S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/left4.png"));
            this.right1S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/right1.png"));
            this.right2S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/right2.png"));
            this.right3S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/right3.png"));
            this.right4S = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkSelected/right4.png"));
            //Initialisation des images du villageois en mode 'walk Attak'
            this.up1WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/up1.png"));
            this.up2WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/up2.png"));
            this.up3WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/up3.png"));
            this.up4WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/up4.png"));
            this.down1WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/down1.png"));
            this.down2WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/down2.png"));
            this.down3WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/down3.png"));
            this.down4WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/down4.png"));
            this.left1WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/left1.png"));
            this.left2WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/left2.png"));
            this.left3WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/left3.png"));
            this.left4WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/left4.png"));
            this.right1WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/right1.png"));
            this.right2WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/right2.png"));
            this.right3WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/right3.png"));
            this.right4WA = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/walkAttack/right4.png"));
            //Initialisation des images du villageois en mode 'walk Attak'
            this.up1A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/up1.png"));
            this.up2A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/up2.png"));
            this.up3A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/up3.png"));
            this.up4A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/up4.png"));
            this.down1A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/down1.png"));
            this.down2A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/down2.png"));
            this.down3A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/down3.png"));
            this.down4A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/down4.png"));
            this.left1A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/left1.png"));
            this.left2A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/left2.png"));
            this.left3A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/left3.png"));
            this.left4A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/left4.png"));
            this.right1A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/right1.png"));
            this.right2A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/right2.png"));
            this.right3A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/right3.png"));
            this.right4A = ImageIO.read(getClass().getClassLoader().getResource("Image/Villageois/Attack/right4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    Méthode qui permet de dessiner le joueur sur le JPanel en fonction de son état et de sa direction.
    @param g l'le Graphics qui permet de dessiner sur le JPanel
    */
    public void paintComponent(Graphics g) {
        if(this.joueur.isSelected()){
            if(this.joueur.getDirection() == Direction.down){
                if(this.joueur.getEtatWalk() == 1){
                    g.drawImage(this.up1S, this.joueur.getPosition().x* VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 2){
                    g.drawImage(this.up2S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 3){
                    g.drawImage(this.up3S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 4){
                    g.drawImage(this.up4S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
            }
            else if(this.joueur.getDirection() == Direction.up){
                if(this.joueur.getEtatWalk() == 1){
                    g.drawImage(this.down1S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 2){
                    g.drawImage(this.down2S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 3){
                    g.drawImage(this.down3S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 4){
                    g.drawImage(this.down4S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
            }
            else if(this.joueur.getDirection() == Direction.left){
                if(this.joueur.getEtatWalk() == 1){
                    g.drawImage(this.left1S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 2){
                    g.drawImage(this.left2S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 3){
                    g.drawImage(this.left3S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 4){
                    g.drawImage(this.left4S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
            }
            else if(this.joueur.getDirection() == Direction.right){
                if(this.joueur.getEtatWalk() == 1){
                    g.drawImage(this.right1S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 2){
                    g.drawImage(this.right2S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 3){
                    g.drawImage(this.right3S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
                else if(this.joueur.getEtatWalk() == 4){
                    g.drawImage(this.right4S, this.joueur.getPosition().x*VueCase.tailleCase, this.joueur.getPosition().y*VueCase.tailleCase, null);
                }
            }
        }
        else{ //Le villageois n'est pas séléctionnée
            if(this.joueur.getWalkAttack() == ActionVillageois.normal) { //Le villageois marche normalement
                if (this.joueur.getDirection() == Direction.down) {
                    if (this.joueur.getEtatWalk() == 1) {
                        g.drawImage(this.up1, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 2) {
                        g.drawImage(this.up2, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 3) {
                        g.drawImage(this.up3, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 4) {
                        g.drawImage(this.up4, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.up) {
                    if (this.joueur.getEtatWalk() == 1) {
                        g.drawImage(this.down1, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 2) {
                        g.drawImage(this.down2, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 3) {
                        g.drawImage(this.down3, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 4) {
                        g.drawImage(this.down4, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.left) {
                    if (this.joueur.getEtatWalk() == 1) {
                        g.drawImage(this.left1, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 2) {
                        g.drawImage(this.left2, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 3) {
                        g.drawImage(this.left3, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 4) {
                        g.drawImage(this.left4, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.right) {
                    if (this.joueur.getEtatWalk() == 1) {
                        g.drawImage(this.right1, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 2) {
                        g.drawImage(this.right2, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 3) {
                        g.drawImage(this.right3, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 4) {
                        g.drawImage(this.right4, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                }
            }
            else if(this.joueur.getWalkAttack() == ActionVillageois.walkAttack){ //Le villageois est entrain de marcher l'arme à la main
                if (this.joueur.getDirection() == Direction.down) {
                    if (this.joueur.getEtatWalk() == 1) {
                        g.drawImage(this.up1WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 2) {
                        g.drawImage(this.up2WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 3) {
                        g.drawImage(this.up3WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 4) {
                        g.drawImage(this.up4WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.up) {
                    if (this.joueur.getEtatWalk() == 1) {
                        g.drawImage(this.down1WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 2) {
                        g.drawImage(this.down2WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 3) {
                        g.drawImage(this.down3WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 4) {
                        g.drawImage(this.down4WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.left) {
                    if (this.joueur.getEtatWalk() == 1) {
                        g.drawImage(this.left1WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 2) {
                        g.drawImage(this.left2WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 3) {
                        g.drawImage(this.left3WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 4) {
                        g.drawImage(this.left4WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.right) {
                    if (this.joueur.getEtatWalk() == 1) {
                        g.drawImage(this.right1WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 2) {
                        g.drawImage(this.right2WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 3) {
                        g.drawImage(this.right3WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatWalk() == 4) {
                        g.drawImage(this.right4WA, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                }
            }
            else if(this.joueur.getWalkAttack() == ActionVillageois.attack){ // Le villageois est entrain d'attaquer avec l'épée
                if (this.joueur.getDirection() == Direction.down) {
                    if (this.joueur.getEtatAttack() == 1) {
                        g.drawImage(this.up1A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 2) {
                        g.drawImage(this.up2A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 3) {
                        g.drawImage(this.up3A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 4) {
                        g.drawImage(this.up4A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.up) {
                    if (this.joueur.getEtatAttack() == 1) {
                        g.drawImage(this.down1A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 2) {
                        g.drawImage(this.down2A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 3) {
                        g.drawImage(this.down3A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 4) {
                        g.drawImage(this.down4A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.left) {
                    if (this.joueur.getEtatAttack() == 1) {
                        g.drawImage(this.left1A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 2) {
                        g.drawImage(this.left2A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 3) {
                        g.drawImage(this.left3A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 4) {
                        g.drawImage(this.left4A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                } else if (this.joueur.getDirection() == Direction.right) {
                    if (this.joueur.getEtatAttack() == 1) {
                        g.drawImage(this.right1A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 2) {
                        g.drawImage(this.right2A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 3) {
                        g.drawImage(this.right3A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    } else if (this.joueur.getEtatAttack() == 4) {
                        g.drawImage(this.right4A, this.joueur.getPosition().x * VueCase.tailleCase, this.joueur.getPosition().y * VueCase.tailleCase, null);
                    }
                }
            }
        }
    }


    /**
     Cette méthode permet de récupérer l'entité du joueur associée à cette vue.
     @return l'entité du joueur associée à cette vue.
     */
    public Entite getJoueur() {
        return joueur;
    }
}
