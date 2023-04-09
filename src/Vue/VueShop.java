/**

 Cette classe VueShop est une JPanel qui représente l'interface du magasin.

 Elle permet d'afficher les éléments à vendre et à acheter, ainsi que les labels correspondants.

 Elle possède deux parties: une partie pour l'achat et une autre pour la vente.

 Pour chaque partie, elle utilise des panneaux, un JSplitPane, des labels et des images pour afficher les éléments.

 Elle permet également de mettre à jour les affichages des éléments lorsqu'il y a des changements dans les données.

 */
package Vue;

import Controler.Control;
import Model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VueShop extends JPanel {
    private Game game;
    //Les labels pour l'interface du shop coté achat
    private JPanel achat;
    private JPanel[] panneaux;
    private JPanel vide;
    private JPanel pannel;
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JLabel labelAchat;
    private JSplitPane splitPane;
    private int selectedIndexAchat;

    //Les labels pour l'interface du shop coté vente
    private JPanel vente;
    private JPanel[] panneauxVente;
    private JPanel videVente;
    private JPanel pannelVente;
    private JLabel labelVente;
    private JSplitPane splitPaneVente;
    private int selectedIndexVente;

    public VueShop(Control control){
        this.game = control.getGame();
        this.achat = new JPanel();
        this.vente = new JPanel();
        this.selectedIndexAchat = 0;
        this.selectedIndexVente = 0;
        this.achat.setLayout(new BorderLayout());
        this.vente.setLayout(new BorderLayout());
        Color c = new Color(0,0,0, 200);
        this.achat.setBackground(c);
        this.vente.setBackground(c);
        this.achat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.vente.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.achat.setPreferredSize(new Dimension(300,320));
        this.vente.setPreferredSize(new Dimension(300,240));

        // Créer et ajouter les panneaux à la partie haute du JSplitPane
        this.pannel = new JPanel();
        this.pannel.setLayout(new GridLayout(2,3));
        this.panneaux = new JPanel[6];
        for (int i = 0; i < 6; i++) {
            this.panneaux[i] = new JPanel();
            this.panneaux[i].setLayout(new BorderLayout());
            this.panneaux[i].setPreferredSize(new Dimension(50, 50));
            this.panneaux[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            this.panneaux[i].setBackground(c);
            // charger l'image à partir d'un fichier
            BufferedImage image = null;
            int index = i+1;
            try {
                image = ImageIO.read(getClass().getClassLoader().getResource("Image/Shop/Achat/shop" + index + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // créer un label pour afficher l'image
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            // ajouter le label au panneau
            this.panneaux[i].add(imageLabel, BorderLayout.CENTER);
            this.panneaux[i].setBackground(c);
            this.pannel.add(this.panneaux[i]);
        }
        this.pannel.setBackground(c);

        this.vide = new JPanel();
        this.vide.setLayout(new BorderLayout());
        this.vide.setPreferredSize(new Dimension(300, 100));
        this.vide.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.vide.setBackground(c);
        this.labelAchat = new JLabel();
        this.labelAchat.setVerticalAlignment(JLabel.CENTER);
        this.labelAchat.setHorizontalAlignment(JLabel.CENTER);
        this.labelAchat.setForeground(Color.WHITE);
        this.labelAchat.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.labelAchat.setPreferredSize(new Dimension(300, 100));
        this.remplireLabelAchat();
        this.vide.add(this.labelAchat);


        this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, this.pannel, this.vide);
        this.splitPane.setDividerSize(1);
        this.splitPane.setDividerLocation(180);
        this.splitPane.setBackground(c);
        this.top = new JPanel();
        this.top.setPreferredSize(new Dimension(20,20));
        this.top.setBackground(c);
        this.right = new JPanel();
        this.right.setPreferredSize(new Dimension(25,20));
        this.right.setBackground(c);
        this.bottom = new JPanel();
        this.bottom.setPreferredSize(new Dimension(25,20));
        this.bottom.setBackground(c);
        this.left = new JPanel();
        this.left.setPreferredSize(new Dimension(20,20));
        this.left.setBackground(c);
        this.achat.add(top, BorderLayout.NORTH);
        this.achat.add(bottom, BorderLayout.SOUTH);
        this.achat.add(left, BorderLayout.WEST);
        this.achat.add(right, BorderLayout.EAST);
        this.achat.add(splitPane, BorderLayout.CENTER);


        // Créer et ajouter les panneaux à la partie haute du JSplitPane
        this.pannelVente = new JPanel();
        this.pannelVente.setLayout(new GridLayout(1,3));
        this.panneauxVente = new JPanel[3];
        for (int i = 0; i < 3; i++) {
            this.panneauxVente[i] = new JPanel();
            this.panneauxVente[i].setLayout(new BorderLayout());
            this.panneauxVente[i].setPreferredSize(new Dimension(50, 50));
            this.panneauxVente[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            this.panneauxVente[i].setBackground(c);
            // charger l'image à partir d'un fichier
            BufferedImage image = null;
            int index = i+1;
            try {
                image = ImageIO.read(getClass().getClassLoader().getResource("Image/Shop/Vente/shop" + index + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // créer un label pour afficher l'image
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            // ajouter le label au panneau
            this.panneauxVente[i].add(imageLabel, BorderLayout.CENTER);
            this.panneauxVente[i].setBackground(c);
            this.pannelVente.add(this.panneauxVente[i]);
        }
        this.pannelVente.setBackground(c);

        this.videVente = new JPanel();
        this.videVente.setLayout(new BorderLayout());
        this.videVente.setPreferredSize(new Dimension(300, 120));
        this.videVente.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.videVente.setBackground(c);
        this.labelVente = new JLabel();
        this.labelVente.setVerticalAlignment(JLabel.CENTER);
        this.labelVente.setHorizontalAlignment(JLabel.CENTER);
        this.labelVente.setForeground(Color.WHITE);
        this.labelVente.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.labelVente.setPreferredSize(new Dimension(300, 120));
        this.remplireLabelVente();
        this.videVente.add(this.labelVente);

        this.splitPaneVente = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, this.pannelVente, this.videVente);
        this.splitPaneVente.setDividerSize(1);
        this.splitPaneVente.setDividerLocation(80);
        this.splitPaneVente.setBackground(c);
        this.vente.add(top, BorderLayout.NORTH);
        this.vente.add(bottom, BorderLayout.SOUTH);
        this.vente.add(left, BorderLayout.WEST);
        this.vente.add(right, BorderLayout.EAST);
        this.vente.add(splitPaneVente, BorderLayout.CENTER);


        this.achat.setVisible(false);
        this.vente.setVisible(false);
        this.add(this.achat);
        this.add(this.vente);
        this.setFocusable(true);
        this.setOpaque(false);
    }

    public void updateAchat(){
        this.achat.removeAll();
        this.pannel.removeAll();
        this.splitPane.removeAll();
        this.vide.removeAll();
        Color c = new Color(0,0,0, 200);
        Color c2 = new Color(246, 204, 44, 200);
        for(int i = 0; i < this.panneaux.length; i++){
            if(this.selectedIndexAchat == i){
                this.panneaux[i].setBorder(BorderFactory.createMatteBorder(5,5,5,5, c2));
            }
            else{
                this.panneaux[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
            this.pannel.add(this.panneaux[i]);
        }
        this.remplireLabelAchat();
        this.vide.add(this.labelAchat);
        this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, this.pannel, this.vide);
        this.splitPane.setDividerSize(1);
        this.splitPane.setDividerLocation(180);
        this.splitPane.setBackground(c);
        this.achat.add(top, BorderLayout.NORTH);
        this.achat.add(bottom, BorderLayout.SOUTH);
        this.achat.add(left, BorderLayout.WEST);
        this.achat.add(right, BorderLayout.EAST);
        this.achat.add(splitPane, BorderLayout.CENTER);
        this.pannel.revalidate();
        this.splitPane.revalidate();
        this.achat.revalidate();
        this.vide.revalidate();
    }

    public void updateVente(){
        this.vente.removeAll();
        this.pannelVente.removeAll();
        this.splitPaneVente.removeAll();
        this.videVente.removeAll();
        Color c = new Color(0,0,0, 200);
        Color c2 = new Color(246, 204, 44, 200);
        for(int i = 0; i < this.panneauxVente.length; i++){
            if(this.selectedIndexVente == i){
                this.panneauxVente[i].setBorder(BorderFactory.createMatteBorder(5,5,5,5, c2));
            }
            else{
                this.panneauxVente[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
            this.pannelVente.add(this.panneauxVente[i]);
        }
        this.remplireLabelVente();
        this.videVente.add(this.labelVente);
        this.splitPaneVente = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, this.pannelVente, this.videVente);
        this.splitPaneVente.setDividerSize(1);
        this.splitPaneVente.setDividerLocation(80);
        this.splitPaneVente.setBackground(c);
        this.vente.add(top, BorderLayout.NORTH);
        this.vente.add(bottom, BorderLayout.SOUTH);
        this.vente.add(left, BorderLayout.WEST);
        this.vente.add(right, BorderLayout.EAST);
        this.vente.add(splitPaneVente, BorderLayout.CENTER);
        this.pannelVente.revalidate();
        this.splitPaneVente.revalidate();
        this.vente.revalidate();
        this.videVente.revalidate();
    }

    private void remplireLabelAchat(){
        if(this.selectedIndexAchat == 0){
            if(this.game.getPieces() < 1500){
                printMessageRestant("<h3>Cabane</h3><p>Elle va permettre d'augmenter le nombre de villageois.</p><p style='color:red';> Prix : 1500 pièces </p>");
            }
            else{
                printMessageRestant("<h3>Cabane</h3><p>Elle va permettre d'augmenter le nombre de villageois.</p><p style='color:yellow';> Prix : 1500 pièces </p>");
            }
        }
        else if(this.selectedIndexAchat == 1){
            if(this.game.getPieces() < 2500) {
                printMessageRestant("<h3>Défense</h3><p>C'est un bâtiment qui va aider à défendre le village.</p><p style='color:red';> Prix : 2500 pièces </p>");
            }
            else{
                printMessageRestant("<h3>Défense</h3><p>C'est un bâtiment qui va aider à défendre le village.</p><p style='color:yellow';> Prix : 2500 pièces </p>");
            }
        }
        else if(this.selectedIndexAchat == 2){
            if(this.game.getPieces() < 1000){
                printMessageRestant("<h3>Haie</h3><p>C'est un outil qui va permettre de rajouter un potager.</p><p style='color:red';> Prix : 1000 pièces </p>");
            }
            else{
                printMessageRestant("<h3>Haie</h3><p>C'est un outil qui va permettre de rajouter un potager.</p><p style='color:yellow';> Prix : 1000 pièces </p>");
            }
        }
        else if(this.selectedIndexAchat == 3) {
            if (this.game.getPieces() < 100) {
                printMessageRestant("<h3>Carotte</h3><p>Des graines pour planter des carottes dans le potager.</p><p style='color:red';> Prix : 100 pièces </p>");
            } else {
                printMessageRestant("<h3>Carotte</h3><p>Des graines pour planter des carottes dans le potager.</p><p style='color:yellow';> Prix : 100 pièces </p>");
            }
        }
        else if(this.selectedIndexAchat == 4){
            if(this.game.getPieces() < 200){
                printMessageRestant("<h3>Baies</h3><p>Des graines pour planter des baies dans le potager.</p><p style='color:red';> Prix : 200 pièces </p>");
            }
            else{
                printMessageRestant("<h3>Baies</h3><p>Des graines pour planter des baies dans le potager.</p><p style='color:yellow';> Prix : 200 pièces </p>");
            }
        }
        else if(this.selectedIndexAchat == 5){
            if(this.game.getPieces() < 300){
                printMessageRestant("<h3>Betterave</h3><p>Des graines pour planter des betteraves dans le potager.</p><p style='color:red';> Prix : 300 pièces </p>");
            }
            else{
                printMessageRestant("<h3>Betterave</h3><p>Des graines pour planter des betteraves dans le potager.</p><p style='color:yellow';> Prix : 300 pièces </p>");
            }
        }
    }

    private void remplireLabelVente(){
        if(this.selectedIndexVente == 0){
            if(this.game.getInventaire().getCarottes() < 30){
                printMessageRestantVente("<h3>Potion de carotte</h3><p>Produite à partir de carottes, elle va permettre de réparer 20% des points de vie des bâtiments.</p><p style='color:red';> Prix : 30 carottes </p>");
            }
            else{
                printMessageRestantVente("<h3>Potion de carotte</h3><p>Produite à partir de carottes, elle va permettre de réparer 20% des points de vie des bâtiments.</p><p style='color:yellow';> Prix : 30 carottes </p>");
            }
        }
        else if(this.selectedIndexVente == 1){
            if(this.game.getInventaire().getBaies() < 40) {
                printMessageRestantVente("<h3>Potion de baies</h3><p>Produite à partir de baies, elle va permettre de réparer 50% des points de vie des bâtiments.</p><p style='color:red';> Prix : 40 baies </p>");
            }
            else{
                printMessageRestantVente("<h3>Potion de baies</h3><p>Produite à partir de baies, elle va permettre de réparer 50% des points de vie des bâtiments.</p><p style='color:yellow';> Prix : 40 baies </p>");
            }
        }
        else if(this.selectedIndexVente == 2){
            if(this.game.getInventaire().getBetteraves() < 40){
                printMessageRestantVente("<h3>Potion de betterave</h3><p>Produite à partir de betteraves, elle va permettre de réparer 100% des points de vie des bâtiments.</p><p style='color:red';> Prix : 40 betteraves </p>");
            }
            else{
                printMessageRestantVente("<h3>Potion de betterave</h3><p>Produite à partir de betteraves, elle va permettre de réparer 100% des points de vie des bâtiments.</p><p style='color:yellow';> Prix : 40 betteraves </p>");
            }
        }
    }

    private void printMessageRestant(String message) {
        this.labelAchat.setText("");
        this.labelAchat.setText("<html><body style='padding:5px;'><center>" + message + "</center></body></html>");
    }

    private void printMessageRestantVente(String message) {
        this.labelVente.setText("");
        this.labelVente.setText("<html><body style='padding:5px;'><center>" + message + "</center></body></html>");
    }



    public void shopVente(){
        if(this.vente.isVisible()){
            this.vente.setVisible(false);
        }
        else{
            this.vente.setVisible(true);
        }
    }

    public void shopAchat(){
        if(this.achat.isVisible()){
            this.achat.setVisible(false);
        }
        else{
            this.achat.setVisible(true);
        }
    }

    //Guetters:

    public int getSelectedIndexAchat() {
        return selectedIndexAchat;
    }

    public int getSelectedIndexVente() {
        return selectedIndexVente;
    }

    //Setters:

    public void setSelectedIndexAchat(int selectedIndexAchat) {
        this.selectedIndexAchat = selectedIndexAchat;
    }

    public void setSelectedIndexVente(int selectedIndexVente) {
        this.selectedIndexVente = selectedIndexVente;
    }
}
