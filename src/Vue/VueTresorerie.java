/**
 * VueTresorerie est la classe qui affiche le nombre de pièces possédées par le joueur.
 * Elle hérite de JPanel pour pouvoir être ajoutée à la fenêtre principale.
 */
package Vue;

import Model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VueTresorerie extends JPanel {
    private Game game;
    private JLabel label;
    /**
     * Constructeur de la classe VueTresorerie.
     *
     * @param game le modèle du jeu contenant les informations sur la trésorerie
     */
    public VueTresorerie(Game game){
        this.game = game;
        // Création du label pour afficher le nombre de pièces
        this.label = new JLabel();
        this.label.setForeground(Color.WHITE);
        this.printMessageRestant(Integer.toString(this.game.getPieces()));
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(50, 50));
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.BLACK);
        container.setForeground(Color.WHITE);
        container.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.YELLOW));
        container.add(imageLabel,BorderLayout.WEST);
        container.add(label, BorderLayout.CENTER);
        this.add(container, BorderLayout.NORTH);
        this.setVisible(true);
        this.setFocusable(true);
        this.setOpaque(false);
    }
    /**
     * Met à jour le label pour afficher le nombre de pièces actualisé.
     */

    public void update(){
        this.label.removeAll();
        this.printMessageRestant(Integer.toString(this.game.getPieces()));
        this.label.revalidate();
    }
    /**
     * Affiche un message dans le label de la vue.
     *
     * @param message le message à afficher
     */
    private void printMessageRestant(String message) {
        this.label.setForeground(Color.WHITE);
        this.label.setText(message);
    }
}
