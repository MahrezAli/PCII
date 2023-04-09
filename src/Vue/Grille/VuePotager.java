/**

 Cette classe représente la vue d'un potager dans l'interface graphique.
 Elle étend la classe JPanel et contient les images pour l'affichage du potager.
 */
package Vue.Grille;
import Model.Potager.Potager;
import Utils.Plante;
import Vue.VueCase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VuePotager extends JPanel {
    private Potager potager;
    private Plante plante;

    //Déclaration des images du portager en mode normal
    private BufferedImage[] n_potager;

    //Déclaration des images du portager en mode selectionne
    private BufferedImage[] potagerS;

    /**
     * Constructeur de la classe VuePotager.
     *
     * @param potager le potager à afficher dans la vue
     */

    public VuePotager(Potager potager){
        this.potager = potager;
        this.n_potager = new BufferedImage[16];
        this.potagerS = new BufferedImage[16];


        try {
            this.n_potager[0] = ImageIO.read(getClass().getClassLoader().getResource("Image/Planter/PlanterCarotte/normal/t" + 0 + ".png"));
            this.potagerS[0] = ImageIO.read(getClass().getClassLoader().getResource("Image/Planter/PlanterCarotte/Selectionne/t" + 0 + "S.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Méthode de dessin de la vue du potager.
     * Elle dessine le potager avec l'image correspondante en fonction de l'état et de la sélection du potager.
     *
     * @param g le graphique pour dessiner la vue
     */

    public void paintComponent(Graphics g) {
        if(this.potager.isSelected()){
            if (this.potager.getEtatPotager() == 0) {
                g.drawImage(this.potagerS[0], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 1) {
                g.drawImage(this.potagerS[1], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 2) {
                g.drawImage(this.potagerS[2], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 3) {
                g.drawImage(this.potagerS[3], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 4) {
                g.drawImage(this.potagerS[4], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 5) {
                g.drawImage(this.potagerS[5], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 6) {
                g.drawImage(this.potagerS[6], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 7) {
                g.drawImage(this.potagerS[7], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 8) {
                g.drawImage(this.potagerS[8], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 9) {
                g.drawImage(this.potagerS[9], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 10) {
                g.drawImage(this.potagerS[10], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 11) {
                g.drawImage(this.potagerS[11], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 12) {
                g.drawImage(this.potagerS[12], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 13) {
                g.drawImage(this.potagerS[13], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 14) {
                g.drawImage(this.potagerS[14], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 15) {
                g.drawImage(this.potagerS[15], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            }
        }
        else {
            if (this.potager.getEtatPotager() == 0) {
                g.drawImage(this.n_potager[0], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 1) {
                g.drawImage(this.n_potager[1], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 2) {
                g.drawImage(this.n_potager[2], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 3) {
                g.drawImage(this.n_potager[3], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 4) {
                g.drawImage(this.n_potager[4], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 5) {
                g.drawImage(this.n_potager[5], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 6) {
                g.drawImage(this.n_potager[6], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 7) {
                g.drawImage(this.n_potager[7], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 8) {
                g.drawImage(this.n_potager[8], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 9) {
                g.drawImage(this.n_potager[9], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 10) {
                g.drawImage(this.n_potager[10], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 11) {
                g.drawImage(this.n_potager[11], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 12) {
                g.drawImage(this.n_potager[12], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 13) {
                g.drawImage(this.n_potager[13], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 14) {
                g.drawImage(this.n_potager[14], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            } else if (this.potager.getEtatPotager() == 15) {
                g.drawImage(this.n_potager[15], this.potager.getPosition().x * VueCase.tailleCase, this.potager.getPosition().y * VueCase.tailleCase, null);
            }
        }
    }

    /**

     Cette méthode met à jour les images du potager en fonction de la plante qui y est plantée.
     Elle parcourt les tableaux d'images correspondant à la plante et les charge dans les attributs de la classe.
     @throws IOException en cas d'erreur lors du chargement des images
     */
    public void update(){
        this.plante = this.potager.getPlante();
        if(this.plante == Plante.Carotte){
            for(int i = 1;  i < 16; i++){
                try {
                    this.n_potager[i] = ImageIO.read(getClass().getClassLoader().getResource("Image/Planter/PlanterCarotte/normal/t" + i + ".png"));
                    this.potagerS[i] = ImageIO.read(getClass().getClassLoader().getResource("Image/Planter/PlanterCarotte/Selectionne/t" + i + "S.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(this.plante == Plante.Baies){
            for(int i = 1;  i < 16; i++){
                try {
                    this.n_potager[i] = ImageIO.read(getClass().getClassLoader().getResource("Image/Planter/PlanterBaies/normal/t" + i + ".png"));
                    this.potagerS[i] = ImageIO.read(getClass().getClassLoader().getResource("Image/Planter/PlanterBaies/Selectionne/t" + i + "S.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(this.plante == Plante.Betterave){
            for(int i = 1;  i < 16; i++){
                try {
                    this.n_potager[i] = ImageIO.read(getClass().getClassLoader().getResource("Image/Planter/PlanterBetterave/normal/t" + i + ".png"));
                    this.potagerS[i] = ImageIO.read(getClass().getClassLoader().getResource("Image/Planter/PlanterBetterave/Selectionne/t" + i + "S.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     méthode qui retourne l'objet Potager associé à cette instance de VuePotager.
     @return l'objet Potager associé à cette instance de VuePotager
     */
    public Potager getPotager() {
        return potager;
    }
}
