/**

 Classe qui hérite de JPanel et qui permet de créer l'interface graphique de l'inventaire du joueur, qui affiche les différents types de ressources
 qu'il a dans son inventaire. Cette vue est mise à jour à chaque fois que le joueur ajoute ou enlève une ressource de son inventaire.
 */
package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import Controler.Control;
import Model.Game;
import Model.Inventaire;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VueInventaire extends JPanel {
    private Control control;
    private Game game;
    private Inventaire inventaire;
    private JPanel[] panneaux;
    private JPanel panel;
    private int selected;
    private boolean isSelected;

    /**
     * Constructeur de la classe VueInventaire.
     * Il initialise les différentes variables de la classe et crée l'interface graphique de l'inventaire du joueur.
     *
     * @param control : un objet Control qui permet de communiquer avec le contrôleur du jeu.
     */
    public VueInventaire(Control control) {
        // Initialisation des variables
        this.control = control;
        this.game = this.control.getGame();
        this.inventaire = this.game.getInventaire();
        this.isSelected = false;
        this.selected = 0;

        // Mise en place de la vue
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(104, 274));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Ajout du titre "Inventaire"
        JPanel titre = new JPanel();
        titre.setLayout(new BorderLayout());
        titre.setBackground(new Color(65, 43, 43));
        titre.setPreferredSize(new Dimension(105, 20));
        JLabel label = new JLabel();
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setText("      Inventaire");
        titre.add(label, BorderLayout.CENTER);

        // Ajout du panel contenant les items de l'inventaire
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.panneaux = new JPanel[10];
        GridBagConstraints c = new GridBagConstraints();
        int cmpt = 0;
        for (int i = 0; i < 10; i++) {
            this.panneaux[i] = new JPanel();
            this.panneaux[i].setLayout(new BorderLayout());
            this.panneaux[i].setPreferredSize(new Dimension(50, 50));
            this.panneaux[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            this.panneaux[i].setBackground(Color.GRAY);
            c.fill = GridBagConstraints.HORIZONTAL;
            if (i % 2 == 0) {
                c.gridx = 0;
                c.gridy = cmpt;
            } else {
                c.gridx = 1;
                c.gridy = cmpt;
                cmpt += 1;
            }
            this.panel.add(this.panneaux[i], c);
        }
        this.panel.setBackground(Color.GRAY);

        // Affichage de la vue
        this.setVisible(true);
        this.add(titre, BorderLayout.NORTH);
        this.add(this.panel, BorderLayout.CENTER);
        this.setFocusable(true);
        this.setOpaque(false);
    }

    /**
     * Cette méthode met à jour l'interface graphique de l'inventaire du joueur en fonction des ressources qu'il possède.
     * Elle est appelée à chaque fois que le joueur ajoute ou enlève une ressource de son inventaire.
     */

    public void update() {
        GridBagConstraints c = new GridBagConstraints();
        int cmpt = 0;
        Color c2 = new Color(246, 204, 44, 200);
        for (int i = 0; i < this.panneaux.length; i++) {
            this.panneaux[i].removeAll();
            if (i % 2 == 0) {
                c.gridx = 0;
                c.gridy = cmpt;
            } else {
                c.gridx = 1;
                c.gridy = cmpt;
                cmpt += 1;
            }
            if (this.selected == i && this.isSelected) {
                this.panneaux[i].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, c2));
            } else {
                this.panneaux[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
            this.panel.add(this.panneaux[i], c);
        }
        cmpt = 0;
        for (int i = 0; i < this.panneaux.length; i++) {
            if (i % 2 == 0) {
                c.gridx = 0;
                c.gridy = cmpt;
            } else {
                c.gridx = 1;
                c.gridy = cmpt;
                cmpt += 1;
            }
            int index = i + 1;
            if (i == 0) {
                if (this.inventaire.getCabanes().size() > 0) { // Pannel 0
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getCabanes().size() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getCabanes().size());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            } else if (i == 1) {
                if (this.inventaire.getDefenses().size() > 0) { // Pannal 1
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getDefenses().size() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getDefenses().size());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            } else if (i == 2) {
                if (this.inventaire.getHaies().size() > 0) { // Pannal 2
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getHaies().size() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getHaies().size());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            } else if (i == 3) {
                if (this.inventaire.getCarottes() > 0) { // Pannal 3
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getCarottes() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getCarottes());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            } else if (i == 4) {
                if (this.inventaire.getBaies() > 0) { // Pannal 4
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getBaies() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getBaies());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            } else if (i == 5) {
                if (this.inventaire.getBetteraves() > 0) { // Pannal 5
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getBetteraves() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getBetteraves());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            } else if (i == 6) {
                if (this.inventaire.getPotionsCarottes() > 0) { // Pannal 6
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getPotionsCarottes() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getPotionsCarottes());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            } else if (i == 7) {
                if (this.inventaire.getPotionsBaies() > 0) { // Pannal 7
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getPotionsBaies() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getPotionsBaies());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            } else if (i == 8) {
                if (this.inventaire.getPotionsBetteraves() > 0) { // Pannal 8
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getClassLoader().getResource("Image/Ressource/Inventaire/shop" + index + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.inventaire.getPotionsBetteraves() > 1) {
                        // Créer un JPanel pour contenir l'image et le texte
                        JPanel imagePanel = new JPanel(new BorderLayout());
                        imagePanel.setBackground(Color.GRAY);
                        String text = Integer.toString(this.inventaire.getPotionsBetteraves());
                        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                        imagePanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
                        imagePanel.add(textLabel, BorderLayout.SOUTH);

                        // Ajouter le JPanel contenant l'image et le texte à la vue
                        this.panneaux[i].add(imagePanel, BorderLayout.CENTER);
                    } else {
                        // Ajouter seulement l'image à la vue
                        JLabel imageLabel = new JLabel(new ImageIcon(image));
                        this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
                    }
                }
                this.panneaux[i].setBackground(Color.GRAY);
                this.panneaux[i].revalidate();
                this.panel.add(this.panneaux[i], c);
            }
        }
        this.panel.revalidate();

    }

    /**
     * Renvoie la valeur actuelle de la variable "selected".
     *
     * @return la valeur actuelle de "selected"
     */
    public int getSelected() {
        return selected;
    }

    /**
     * Indique si la case est sélectionnée ou non.
     *
     * @return true si la case est sélectionnée, false sinon
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Définit si la case est sélectionnée ou non.
     *
     * @param selected true si la case est sélectionnée, false sinon
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Définit la valeur de la variable "selected".
     *
     * @param selected la nouvelle valeur de "selected"
     */
    public void setSelected(int selected) {
        this.selected = selected;
    }
}