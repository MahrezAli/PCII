    /**
    Classe qui représente la vue d'affichage de la console dans le jeu.
        */
package Vue;
import java.awt.*;


import Controler.Control;

import javax.swing.*;
import javax.swing.border.Border;

public class VueConsole extends JPanel {
    private Control control;
    private JLabel label;
    private String[] labelShop;
    private String[] labelConfirmation;
    private int choixLabelShop;
    private int choixConfirmation;

    /**
     * Constructeur de la classe VueConsole.
     *
     * @param control l'objet Control à partir duquel on crée la vue.
     */
    public VueConsole(Control control) {
        this.control = control;
        this.labelShop = new String[3];
        this.labelConfirmation = new String[3];
        this.labelShop[0] = "Acheter";
        this.labelShop[1] = "Vendre";
        this.labelShop[2] = "Quitter";
        this.labelConfirmation[0] = "Êtes-vous sûr ?";
        this.labelConfirmation[1] = "OUI";
        this.labelConfirmation[2] = "NON";
        this.choixLabelShop = 0;
        this.choixConfirmation = 1;
        this.setBackground(Color.BLACK);
        this.label = new JLabel();
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setForeground(Color.WHITE);
        this.label.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.label.setPreferredSize(new Dimension(200, 100));

        // Ajouter une bordure blanche autour du JLabel
        this.label.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Ajouter le JLabel au JPanel
        this.add(label);

    }
    /**
     * Affiche un message dans la console qui indique qu'il faut retourner au shop.
     *
     * @param message le message à afficher.
     */

    public void printMessage(String message) {
        // Ajouter le texte au JLabel
        //System.out.println(message);
        this.label.setText("<html><body style='padding:5px;'><center>" + message + "</center></body></html>");
        int delayInSeconds = 8;
        // Planifier l'effacement du texte après un certain délai
        Timer timer = new Timer(delayInSeconds * 1000, e -> {
            this.label.setText("");
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Affiche les options du shop dans la console.
     */

    public void printMessageReturnShop(String message) {
        // Ajouter le texte au JLabel
        //System.out.println(message);
        this.label.setText("<html><body style='padding:5px;'><center>" + message + "</center></body></html>");
        int delayInSeconds = 8;
        // Planifier l'effacement du texte après un certain délai
        Timer timer = new Timer(delayInSeconds * 1000, e -> {
            this.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
        });
        timer.setRepeats(false);
        timer.start();
    }
    /**
     * Affiche les choix disponibles dans la boutique sous forme de texte avec une mise en forme spécifique.
     */
    public void printMessageSelecShop() {
        // Ajouter le texte au JLabel
        String message = "<html><body style='padding:5px;'><center>";
        // Ajouter chaque choix au JLabel avec une couleur d'arrière-plan pour différencier les choix
        for (int i = 0; i < this.labelShop.length; i++) {
            String choice = this.labelShop[i];
            message += "<p style='background-color: " + (this.choixLabelShop == i ? "white" : "black") + ";color : " + (this.choixLabelShop == i ? "black" : "white") + ";'>" + choice + "</p>";
        }
        message += "</center></body></html>";
        this.label.setText(message);
    }

    /**
     * Affiche un message de confirmation avec les choix possibles sous forme de texte avec une mise en forme spécifique.
     * @param m le message à afficher
     */
    public void printMessageConfirmationAmelioration(String m) {
        // Ajouter le texte au JLabel
        String message = "<html><body style='padding:5px;'><center>" + m;
        // Ajouter chaque choix au JLabel avec une couleur d'arrière-plan pour différencier les choix
        for (int i = 0; i < this.labelConfirmation.length; i++) {
            String choice = this.labelConfirmation[i];
            message += "<p style='background-color: " + (this.choixConfirmation == i ? "white" : "black") + ";color : " + (this.choixConfirmation == i ? "black" : "white") + ";'>" + choice + "</p>";
        }
        message += "</center></body></html>";
        this.label.setText(message);
    }
    /**
     * Affiche un message de confirmation avec les choix possibles sous forme de texte avec une mise en forme spécifique.
     */

    public void printMessageConfirmation() {
        // Ajouter le texte au JLabel
        String message = "<html><body style='padding:5px;'><center>";
        // Ajouter chaque choix au JLabel avec une couleur d'arrière-plan pour différencier les choix
        for (int i = 0; i < this.labelConfirmation.length; i++) {
            String choice = this.labelConfirmation[i];
            message += "<p style='background-color: " + (this.choixConfirmation == i ? "white" : "black") + ";color : " + (this.choixConfirmation == i ? "black" : "white") + ";'>" + choice + "</p>";
        }
        message += "</center></body></html>";
        this.label.setText(message);
    }
    /**

     Cette méthode affiche un message dans le JLabel de l'interface graphique.
     @param message Le message à afficher dans le JLabel.
     */
    public void printMessageRestant(String message) {
        this.label.setText("<html><body style='padding:5px;'><center>" + message + "</center></body></html>");
    }

    //Guetters:
    /**

     Getter pour la variable choixLabelShop.
     @return Le choix de l'utilisateur dans la boutique.
     */
    public int getChoixLabelShop() {
        return choixLabelShop;
    }
    /**

     Getter pour la variable choixConfirmation.
     @return Le choix de l'utilisateur lors d'une confirmation.
     */

    public int getChoixConfirmation() {
        return choixConfirmation;
    }
    /**

     Getter pour le tableau labelShop.
     @return Le tableau des choix de la boutique.
     */

    public String[] getLabelShop() {
        return labelShop;
    }
    /**

     Getter pour le tableau labelConfirmation.
     @return Le tableau des choix lors d'une confirmation.
     */

    public String[] getLabelConfirmation() {
        return labelConfirmation;
    }

    //Setters:

    /**
     Setter pour la variable choixLabelShop.
     @param choixLabelShop Le choix de l'utilisateur dans la boutique.
     */
    public void setChoixLabelShop(int choixLabelShop) {
        this.choixLabelShop = choixLabelShop;
    }

    /**
      méthode qui réinitialise le texte du JLabel à une chaîne vide.
     */
    public void setTextNull(){
        this.label.setText("");
    }

    /**

     Setter pour la variable choixConfirmation.
     @param choixConfirmation Le choix de l'utilisateur lors d'une confirmation.
     */
    public void setChoixConfirmation(int choixConfirmation) {
        this.choixConfirmation = choixConfirmation;
    }
}
