package Controler;

import Model.*;
import Model.Deplacements.AStar;
import Model.Entity.Monstre;
import Model.Entity.Villageois;
import Model.Potager.Potager;
import Utils.ActionVillageois;
import Utils.Plante;
import Utils.Potion;
import Vue.*;
import Vue.Grille.VueBatiment;
import Vue.Grille.VueDefense;
import Vue.Grille.VueJoueur;
import Vue.Grille.VuePotager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
/**
 * Cette classe est le contrôleur du jeu et gère les événements et les interactions entre les différents éléments du jeu.
 */

public class Control implements ActionListener, KeyListener {
    private final Game game;
    private VueConsole console;
    private final Affichage affichage;
    private int idPotager = 0;
    private int idVillageois = 0;
    private int idArgent = 0;
    private int idMonstre = 0;
    private int idInventaire = 0;
    private int idSelectBat = 0;
    private String action;
    private VueShop vueShop;
    private boolean shop;
    private VueTresorerie vueTresorerie;
    private VueInventaire inventaire;
    /**
     * Constructeur de la classe Control.
     * Il initialise une instance de la classe Game, une instance de la classe Affichage,
     * ajoute un KeyListener pour gérer les événements clavier et démarre un thread pour gérer la partie en temps réel.
     */
    public Control(){
        this.game = new Game();
        this.affichage = new Affichage(this.game.getGrille(), this);
        this.affichage.addKeyListener(this);
        this.shop = false;
        this.affichage.requestFocusInWindow();
        this.vueShop = this.affichage.getShop();
        JeuThread jeu  = new JeuThread(this.game, this);
        jeu.start();
    }
    /**
     * Méthode qui gère les événements liés aux boutons et aux menus.
     * Elle implémente l'interface ActionListener.
     * @param e L'événement qui a été déclenché.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.shop) {
            if ("Récolter".equals(e.getActionCommand())) {
                this.inventaire.setSelected(0);
                this.idInventaire = 0;
                this.inventaire.setSelected(false);
                this.inventaire.update();
                if (this.game.getGrille().getPotagers().size() > 0) {
                    Potager potager = this.game.getGrille().getPotagers().get(this.idPotager);
                    potager.setSelected(true);
                    this.action = "Recolter1";
                    this.console.printMessageRestant("Veuillez selectionner un potager");
                } else {
                    this.console.printMessage("Il n'y a aucun potager dans le village pour récolter");
                }
            }
            if ("Planter".equals(e.getActionCommand())) {
                this.inventaire.setSelected(0);
                this.idInventaire = 0;
                this.inventaire.setSelected(false);
                this.inventaire.update();
                if (this.game.getGrille().getPotagers().size() > 0) {
                    if(this.game.getInventaire().getCarottes() > 0 || this.game.getInventaire().getBaies() > 0 || this.game.getInventaire().getBetteraves() > 0){
                        this.action = "Planter1.1";
                        this.inventaire.setSelected(true);
                        this.inventaire.update();
                        this.console.printMessageRestant("Veuillez selectionner un légume à planter");
                    }
                    else{
                        this.console.printMessage("Vous n'avez aucune graine dans votre inventaire");
                    }
                } else {
                    this.console.printMessage("Il n'y a aucun potager dans le village pour planter");
                }
            }
            if ("Attaquer".equals(e.getActionCommand())) {
                this.inventaire.setSelected(0);
                this.idInventaire = 0;
                this.inventaire.setSelected(false);
                this.inventaire.update();
                if (this.game.getGrille().getMonstre().size() > 0) {
                    Monstre monstre = this.game.getGrille().getMonstre().get(this.idMonstre);
                    monstre.setSelected(true);
                    this.action = "Attaquer1";
                    this.console.printMessageRestant("Veuillez selectionner un monstre");
                } else {
                    this.console.printMessage("Il n'y a aucun monstre à attaquer");
                }
            }
            if ("Ramasser".equals(e.getActionCommand())) {
                this.inventaire.setSelected(0);
                this.idInventaire = 0;
                this.inventaire.setSelected(false);
                this.inventaire.update();
                if (this.game.getGrille().getArgents().size() > 0) {
                    Argent argent = this.game.getGrille().getArgents().get(this.idArgent);
                    argent.setSelected(true);
                    this.action = "Ramasser1";
                    this.console.printMessageRestant("Veuillez selectionner une pièce");
                } else {
                    this.console.printMessage("Il n'y a aucune pièce à ramasser");
                }
            }
            if ("Shop".equals(e.getActionCommand())) {
                this.inventaire.setSelected(0);
                this.idInventaire = 0;
                this.inventaire.setSelected(false);
                this.inventaire.update();
                this.console.printMessageSelecShop();
                this.action = "Shop";
                this.shop = true;
            }
            if ("Construire".equals(e.getActionCommand())) {
                this.action = "Construire";
                this.inventaire.setSelected(true);
                this.idInventaire = 0;
                this.inventaire.update();
                this.console.printMessageRestant("Veuillez selectionner un bâtiment ou une haie");
            }
            if ("Utiliser".equals(e.getActionCommand())) {
                this.action = "Utiliser";
                this.inventaire.setSelected(true);
                this.idInventaire = 0;
                this.inventaire.update();
                this.console.printMessageRestant("Veuillez selectionner une potion");
            }
            if ("Améliorer".equals(e.getActionCommand())) {
                this.inventaire.setSelected(0);
                this.idInventaire = 0;
                this.inventaire.setSelected(false);
                this.inventaire.update();
                this.action = "Améliorer";
                this.idSelectBat = 0;
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(true);
                this.console.printMessageRestant("Veuillez selectionner un bâtiment à améliorer");
            }
        }
        this.game.getGrille().notifyObservers();
    }
    /**

     Cette méthode est appelée lorsque l'utilisateur tape une touche sur le clavier.
     Elle ne fait rien dans cette implémentation.
     @param e l'événement lié à la touche pressée
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     Cette méthode est appelée lorsque l'utilisateur appuie sur une touche du clavier.
     Si la touche pressée est "Entrée", elle sélectionne le potager ou le monstre en fonction de l'action
     @param e l'événement lié à la touche pressée
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(("Planter1".equals(this.action) || "Recolter1".equals(this.action))) {
                Potager potager = this.game.getGrille().getPotagers().get(this.idPotager);
                potager.setSelected(false);
                this.console.printMessageRestant("Veuillez selectionner une villageois");
                Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                villageois.setSelected(true);

                if("Planter1".equals(this.action)) {
                    this.action = "Planter2";
                    if (this.idInventaire == 3) {
                        potager.setPlante(Plante.Carotte);
                        potager.getPlanter().setPlante(Plante.Carotte);
                        potager.getRecolter().setPlante(Plante.Carotte);
                    } else if (this.idInventaire == 4) {
                        potager.setPlante(Plante.Baies);
                        potager.getPlanter().setPlante(Plante.Baies);
                        potager.getRecolter().setPlante(Plante.Baies);
                    } else {
                        potager.setPlante(Plante.Betterave);
                        potager.getPlanter().setPlante(Plante.Betterave);
                        potager.getRecolter().setPlante(Plante.Betterave);
                    }
                    for(int i = 0; i < this.affichage.getGrille().getPotagers().size(); i++){
                        VuePotager vuePotager = this.affichage.getGrille().getPotagers().get(i);
                        if(vuePotager.getPotager() == potager){
                            vuePotager.update();
                            break;
                        }
                    }
                }
                else if("Recolter1".equals(this.action)){
                    this.action = "Recolter2";
                    if(potager.getPlante() == Plante.Carotte){
                        this.idInventaire = 3;
                    }
                    else if(potager.getPlante() == Plante.Baies){
                        this.idInventaire = 4;
                    }
                    else if(potager.getPlante() == Plante.Betterave){
                        this.idInventaire = 5;
                    }
                }
            }
            else if(("Planter2".equals(this.action) || "Recolter2".equals(this.action))) {
                if(!this.game.getGrille().getVillageois().get(this.idVillageois).isActionAFaire()) {
                    this.console.setTextNull();
                    Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                    villageois.setSelected(false);
                    villageois.setActionAFaire(true);
                    Point depart = new Point(villageois.getPosition().x, villageois.getPosition().y); // choisir un point de départ
                    Point destination = this.game.getGrille().getPotagers().get(this.idPotager).positionPourVillageois(); // choisir un point d'arrivée
                    AStar astar = new AStar(this.game.getGrille(), depart, destination, 0); // créer un objet AStar
                    if (astar.getThread() != null) {
                        astar.getThread().interrupt();
                    }
                    Thread thread = new Thread(astar);
                    thread.start();
                    astar.setThread(thread);
                    try {
                        thread.join(); // attendre que le calcul soit terminé
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    ArrayList<Case> chemin = astar.getChemin(); // récupérer le chemin calculé
                    thread.interrupt();
                    if ("Planter2".equals(this.action) && this.game.getGrille().getPotagers().get(this.idPotager).getEtatPotager() == 0) {
                        if (this.idInventaire == 3) {
                            this.game.getInventaire().removeCarottes(1);
                        } else if (this.idInventaire == 4) {
                            this.game.getInventaire().removeBaies(1);
                        } else {
                            this.game.getInventaire().removeBetteraves(1);
                        }
                        villageois.deplacerAction(this.game.getGrille(), chemin, this.idPotager, "Planter");
                        this.idInventaire = 0;
                        this.inventaire.update();
                    } else if ("Recolter2".equals(this.action) && this.game.getGrille().getPotagers().get(this.idPotager).getEtatPotager() == 12) {
                        villageois.deplacerAction(this.game.getGrille(), chemin, this.idPotager, "Recolter");
                        if (this.idInventaire == 3) {
                            this.game.getInventaire().addCarottes(3);
                        } else if (this.idInventaire == 4) {
                            this.game.getInventaire().addBaies(3);
                        } else {
                            this.game.getInventaire().addBetteraves(3);
                        }
                        this.idInventaire = 0;
                        this.inventaire.update();
                    }
                    else{
                        villageois.setActionAFaire(false);
                        if("Planter2".equals(this.action)){
                            this.console.printMessage("Le potager que vous avez selectionné est déjà plein");
                        }
                        else if("Recolter2".equals(this.action)){
                            this.console.printMessage("Le potager que vous avez selectionné n'est pas en état de récolte");
                        }
                    }
                }
                else{
                    Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                    villageois.setSelected(false);
                    //On peut rajouter dans la console pour dire que le villageois a qlq chose a faire
                    this.console.printMessage("Le villageois que vous avez sélectionné a déjà une action à effectuer");
                }
                this.action = null;
                this.idMonstre = 0;
                this.idPotager = 0;
                this.idVillageois = 0;
            }
            else if("Attaquer1".equals(this.action)){
                Monstre monstre = this.game.getGrille().getMonstre().get(this.idMonstre);
                monstre.setSelected(false);
                this.console.printMessageRestant("Veuillez selectionner une villageois");
                Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                villageois.setSelected(true);
                this.action = "Attaquer2";
            }
            else if("Attaquer2".equals(this.action) ){
                if(!this.game.getGrille().getVillageois().get(this.idVillageois).isActionAFaire()) {
                    this.console.setTextNull();
                    Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                    villageois.setSelected(false);
                    villageois.setActionAFaire(true);
                    Point depart = new Point(villageois.getPosition().x, villageois.getPosition().y); // choisir un point de départ
                    Point destination = this.game.getGrille().getMonstre().get(this.idMonstre).positionPourVillageois(); // choisir un point d'arrivée
                    AStar astar = new AStar(this.game.getGrille(), depart, destination, 0); // créer un objet AStar
                    if (astar.getThread() != null) {
                        astar.getThread().interrupt();
                    }
                    Thread thread = new Thread(astar);
                    thread.start();
                    astar.setThread(thread);
                    try {
                        thread.join(); // attendre que le calcul soit terminé
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    ArrayList<Case> chemin = astar.getChemin(); // récupérer le chemin calculé
                    villageois.setWalkAttack(ActionVillageois.walkAttack);
                    villageois.deplacerAction(this.game.getGrille(), chemin, this.idMonstre, "Attaquer");
                }
                else{
                    Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                    villageois.setSelected(false);
                    //On peut rajouter dans la console pour dire que le villageois a qlq chose a faire
                    this.console.printMessage("Le villageois que vous avez sélectionné a déjà une action à effectuer");

                }
                this.action = null;
                this.idMonstre = 0;
                this.idPotager = 0;
                this.idVillageois = 0;
            }
            else if("Ramasser1".equals(this.action) ){
                Argent argent = this.game.getGrille().getArgents().get(this.idArgent);
                argent.setSelected(false);
                this.console.printMessageRestant("Veuillez selectionner une villageois");
                Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                villageois.setSelected(true);
                this.action = "Ramasser2";
            }
            else if("Ramasser2".equals(this.action)){
                if(!this.game.getGrille().getVillageois().get(this.idVillageois).isActionAFaire()) {
                    this.console.setTextNull();
                    Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                    villageois.setSelected(false);
                    villageois.setActionAFaire(true);
                    Point depart = new Point(villageois.getPosition().x, villageois.getPosition().y); // choisir un point de départ
                    Point destination = this.game.getGrille().getArgents().get(this.idArgent).positionPourVillageois(); // choisir un point d'arrivée
                    AStar astar = new AStar(this.game.getGrille(), depart, destination, 0); // créer un objet AStar
                    if (astar.getThread() != null) {
                        astar.getThread().interrupt();
                    }
                    Thread thread = new Thread(astar);
                    thread.start();
                    astar.setThread(thread);
                    try {
                        thread.join(); // attendre que le calcul soit terminé
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    ArrayList<Case> chemin = astar.getChemin(); // récupérer le chemin calculé
                    villageois.deplacerAction(this.game.getGrille(), chemin, this.idArgent, "Ramasser");
                }
                else{
                    Villageois villageois = this.game.getGrille().getVillageois().get(this.idVillageois);
                    villageois.setSelected(false);
                    this.console.printMessage("Le villageois que vous avez sélectionné a déjà une action à effectuer");
                    //On peut rajouter dans la console pour dire que le villageois a qlq chose a faire
                }
                this.action = null;
                this.idMonstre = 0;
                this.idPotager = 0;
                this.idVillageois = 0;
                this.idArgent = 0;

            }
            if("Planter1.1".equals(this.action)){
                this.idInventaire = this.inventaire.getSelected();
                if(this.idInventaire != 3 && this.idInventaire != 4 && this.idInventaire != 5){
                    this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                }
                else{
                    if(this.idInventaire == 3 && this.game.getInventaire().getCarottes() == 0){
                        this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                    }
                    else if(this.idInventaire == 4 && this.game.getInventaire().getBaies() == 0){
                        this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                    }
                    else if(this.idInventaire == 5 && this.game.getInventaire().getBetteraves() == 0){
                        this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                    }
                    else {
                        this.inventaire.setSelected(false);
                        Potager potager = this.game.getGrille().getPotagers().get(this.idPotager);
                        potager.setSelected(true);
                        this.inventaire.update();
                        this.action = "Planter1";
                    }
                }
            }
            if("AcheterCabane".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                int pieces = this.game.getPieces();
                if(i == 1){
                    int newPieces = pieces - 1500;
                    this.game.setPieces(newPieces);
                    this.game.getInventaire().addCabanes(new Batiment(null,"Cabane", 22, 21));
                    this.vueTresorerie.update();
                    this.inventaire.update();
                    this.vueShop.shopAchat();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Acheter";
                }
                this.console.setChoixConfirmation(1);
            }
            if("AcheterDefense".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                int pieces = this.game.getPieces();
                if(i == 1){
                    int newPieces = pieces - 2500;
                    this.game.setPieces(newPieces);
                    this.game.getInventaire().addDefenses(new Defense(this.game.getGrille(), null, "Defense", 17,29, 5,40));
                    this.vueTresorerie.update();
                    this.inventaire.update();
                    this.vueShop.shopAchat();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Acheter";
                }
                this.console.setChoixConfirmation(1);
            }
            if("AcheterHaie".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                int pieces = this.game.getPieces();
                if(i == 1){
                    int newPieces = pieces - 1000;
                    this.game.setPieces(newPieces);
                    this.game.getInventaire().addHaies(new Potager(this.game.getGrille(), null));
                    this.vueTresorerie.update();
                    this.inventaire.update();
                    this.vueShop.shopAchat();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Acheter";
                }
                this.console.setChoixConfirmation(1);
            }
            if("AcheterCarottes".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                int pieces = this.game.getPieces();
                if(i == 1){
                    int newPieces = pieces - 100;
                    this.game.setPieces(newPieces);
                    this.game.getInventaire().addCarottes(3);
                    this.vueTresorerie.update();
                    this.inventaire.update();
                    this.vueShop.shopAchat();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Acheter";
                }
                this.console.setChoixConfirmation(1);
            }
            if("AcheterBaies".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                int pieces = this.game.getPieces();
                if(i == 1){
                    int newPieces = pieces - 200;
                    this.game.setPieces(newPieces);
                    this.game.getInventaire().addBaies(3);
                    this.vueTresorerie.update();
                    this.inventaire.update();
                    this.vueShop.shopAchat();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Acheter";
                }
                this.console.setChoixConfirmation(1);
            }
            if("AcheterBetteraves".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                int pieces = this.game.getPieces();
                if(i == 1){
                    int newPieces = pieces - 300;
                    this.game.setPieces(newPieces);
                    this.game.getInventaire().addBetteraves(3);
                    this.vueTresorerie.update();
                    this.inventaire.update();
                    this.vueShop.shopAchat();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Acheter";
                }
                this.console.setChoixConfirmation(1);
            }
            if("AcheterPotionCarottes".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                if(i == 1){
                    this.game.getInventaire().removeCarottes(30);
                    this.game.getInventaire().addPotionsCarottes(1);
                    this.inventaire.update();
                    this.vueShop.shopVente();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Vendre";
                }
                this.console.setChoixConfirmation(1);
            }
            if("AcheterPotionBaies".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                if(i == 1){
                    this.game.getInventaire().removeBaies(40);
                    this.game.getInventaire().addPotionsBaies(1);
                    this.inventaire.update();
                    this.vueShop.shopVente();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Vendre";
                }
                this.console.setChoixConfirmation(1);
            }
            if("AcheterPotionBetteraves".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                if(i == 1){
                    this.game.getInventaire().removeBetteraves(40);
                    this.game.getInventaire().addPotionsBetteraves(1);
                    this.inventaire.update();
                    this.vueShop.shopVente();
                    this.action = null;
                    this.console.setTextNull();
                    this.shop = false;
                }
                else{
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.action = "Vendre";
                }
                this.console.setChoixConfirmation(1);
            }
            if("Acheter".equals(this.action)){
                int i = this.vueShop.getSelectedIndexAchat();
                int pieces = this.game.getPieces();
                if(i == 0){
                    if(pieces >= 1500){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterCabane";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de pièces pour acheter une cabane</p>");
                    }
                }
                else if(i == 1){
                    if(pieces >= 2500){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterDefense";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de pièces pour acheter un bâtiment de défense</p>");
                    }
                }
                else if(i == 2){
                    if(pieces >= 1000){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterHaie";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de pièces pour acheter une haie</p>");
                    }

                }
                else if(i == 3){
                    if(pieces >= 100){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterCarottes";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de pièces pour acheter des graines de carottes</p>");
                    }
                }
                else if(i == 4){
                    if(pieces >= 200){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterBaies";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de pièces pour acheter des graines de baies</p>");
                    }
                }
                else if(i == 5){
                    if(pieces >= 300){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterBetteraves";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de pièces pour acheter des graines de betteraves</p>");
                    }
                }
            }
            if("Vendre".equals(this.action)){
                int i = this.vueShop.getSelectedIndexVente();
                if(i == 0){
                    if(this.game.getInventaire().getCarottes() >= 30){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterPotionCarottes";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de carottes pour acheter la potion</p>");
                    }
                }
                else if(i == 1){
                    if(this.game.getInventaire().getBaies() >= 40){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterPotionBaies";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de baies pour acheter la potion</p>");
                    }
                }
                else if(i == 2){
                    if(this.game.getInventaire().getBetteraves() >= 40){
                        this.console.printMessageConfirmation();
                        this.action = "AcheterPotionBetteraves";
                    }
                    else{
                        this.console.printMessageReturnShop("<p style='color:red';>Vous n'avez pas assez de betteraves pour acheter la potion</p>");
                    }
                }
            }
            if("Shop".equals(this.action)){
                int i = this.console.getChoixLabelShop();
                String label = this.console.getLabelShop()[i];
                this.console.setTextNull();
                if(label.equals("Acheter")){
                    this.action = "Acheter";
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter l'achat");
                    this.vueShop.shopAchat();
                    this.vueShop.updateAchat();
                }
                else if(label.equals("Vendre")){
                    this.action = "Vendre";
                    this.console.printMessageRestant("Clique sur le bouton d'Escape pour quitter la vente");
                    this.vueShop.shopVente();
                    this.vueShop.updateVente();
                }
                else if(label.equals("Quitter")){
                    this.console.setTextNull();
                    this.action = null;
                    this.shop = false;
                }
                this.console.setChoixLabelShop(0);
            }
            if("ConstruireCabane".equals(this.action)){
                Point p = this.game.getGrille().getCabaneEmplacement().getPosition();
                boolean possible = true;
                for(int i = p.x; i < p.x + this.game.getGrille().getCabaneEmplacement().getLargeur(); i++){
                    for(int j = p.y; j < p.y + this.game.getGrille().getCabaneEmplacement().getHauteur(); j++){
                        if(this.game.getGrille().getCases()[i][j].isOccupee()){
                            possible = false;
                            break;
                        }
                    }
                    if(!possible){
                        break;
                    }
                }
                if(possible){
                    this.game.getGrille().addCabanes(p);
                    int sizeVillageois = this.game.getGrille().getVillageois().size();
                    Villageois v1 = this.game.getGrille().getVillageois().get(sizeVillageois-2);
                    Villageois v2 = this.game.getGrille().getVillageois().get(sizeVillageois-1);
                    this.affichage.getGrille().getJoueurs().add(new VueJoueur(v1));
                    this.affichage.getGrille().getJoueurs().add(new VueJoueur(v2));
                    this.game.getInventaire().removeCabanes(0);
                    int i = this.game.getGrille().getCabanes().size();
                    this.affichage.getGrille().getBatiments().add(new VueBatiment(this.game.getGrille().getCabanes().get(i-1)));
                    this.affichage.getGrille().setRajouteBatiment(false);
                    this.action = null;
                    this.inventaire.update();
                    this.console.setTextNull();
                    this.inventaire.setSelected(0);
                }
                else{
                    this.console.printMessage("<p style='color:red';>L'emplacement que vous avez choisi est occupé !</p>");
                }
            }
            if("ConstruireDefense".equals(this.action)){
                Point p = this.game.getGrille().getDefenseEmplacement().getPosition();
                boolean possible = true;
                for(int i = p.x; i < p.x + this.game.getGrille().getDefenseEmplacement().getLargeur(); i++){
                    for(int j = p.y; j < p.y + this.game.getGrille().getDefenseEmplacement().getHauteur(); j++){
                        if(this.game.getGrille().getCases()[i][j].isOccupee()){
                            possible = false;
                            break;
                        }
                    }
                    if(!possible){
                        break;
                    }
                }
                if(possible){
                    this.game.getGrille().addDefenses(p);
                    int i = this.game.getGrille().getDefenses().size();
                    this.affichage.getGrille().getDefenses().add(new VueDefense(this.game.getGrille().getDefenses().get(i-1)));
                    this.game.getInventaire().removeDefenses(0);
                    this.affichage.getGrille().setRajouteBatiment(false);
                    this.action = null;
                    this.inventaire.update();
                    this.console.setTextNull();
                    this.inventaire.setSelected(0);
                }
                else{
                    this.console.printMessage("<p style='color:red';>L'emplacement que vous avez choisi est occupé !</p>");
                }
            }
            if("ConstruirePotager".equals(this.action)){
                Point p = this.game.getGrille().getPotagerEmplacement().getPosition();
                boolean possible = true;
                for(int i = p.x; i < p.x + this.game.getGrille().getPotagerEmplacement().getLargeur(); i++){
                    for(int j = p.y; j < p.y + this.game.getGrille().getPotagerEmplacement().getHauteur(); j++){
                        if(this.game.getGrille().getCases()[i][j].isOccupee()){
                            possible = false;
                            break;
                        }
                    }
                    if(!possible){
                        break;
                    }
                }
                if(possible){
                    this.game.getGrille().addPotagers(p);
                    int i = this.game.getGrille().getPotagers().size();
                    this.affichage.getGrille().getPotagers().add(new VuePotager(this.game.getGrille().getPotagers().get(i-1)));
                    this.game.getInventaire().removeHaies(0);
                    this.affichage.getGrille().setRajouteBatiment(false);
                    this.action = null;
                    this.console.setTextNull();
                    this.inventaire.update();
                    this.inventaire.setSelected(0);
                }
                else{
                    this.console.printMessage("<p style='color:red';>L'emplacement que vous avez choisi est occupé !</p>");
                }
            }
            if("Construire".equals(this.action)){
                int i = this.inventaire.getSelected();
                if(i != 0 && i != 1 && i != 2){
                    this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                }
                else{
                    if(i == 0){
                        if(this.game.getInventaire().getCabanes().size() > 0){
                            this.action = "ConstruireCabane";
                            this.affichage.getGrille().setRajouteBatiment(true);
                            this.inventaire.setSelected(false);
                            this.inventaire.update();
                            this.inventaire.setSelected(0);
                            this.console.printMessageRestant("Vous pouvre placer la cabane où vous souhaitez dans la grille");
                            int x = this.game.getGrille().getCabaneEmplacement().getPosition().x;
                            int y = this.game.getGrille().getCabaneEmplacement().getPosition().y;
                            for(int i1 = x; i1 < x + this.game.getGrille().getCabaneEmplacement().getLargeur(); i1++){
                                for(int i2 = y; i2 < y + this.game.getGrille().getCabaneEmplacement().getHauteur(); i2++){
                                    this.game.getGrille().getCases()[i1][i2].setEmplacementBat(true);
                                }
                            }
                        }
                        else{
                            this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                        }
                    }
                    else if(i == 1){
                        if(this.game.getInventaire().getDefenses().size() > 0){
                            this.action = "ConstruireDefense";
                            this.affichage.getGrille().setRajouteBatiment(true);
                            this.inventaire.setSelected(false);
                            this.inventaire.update();
                            this.inventaire.setSelected(0);
                            this.console.printMessageRestant("Vous pouvre placer le bâtiment de défense où vous souhaitez dans la grille");
                            int x = this.game.getGrille().getDefenseEmplacement().getPosition().x;
                            int y = this.game.getGrille().getDefenseEmplacement().getPosition().y;
                            for(int i1 = x; i1 < x + this.game.getGrille().getDefenseEmplacement().getLargeur(); i1++){
                                for(int i2 = y; i2 < y + this.game.getGrille().getDefenseEmplacement().getHauteur(); i2++){
                                    this.game.getGrille().getCases()[i1][i2].setEmplacementBat(true);
                                }
                            }
                        }
                        else{
                            this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                        }
                    }
                    else if(i == 2){
                        if(this.game.getInventaire().getHaies().size() > 0){
                            this.action = "ConstruirePotager";
                            this.affichage.getGrille().setRajouteBatiment(true);
                            this.inventaire.setSelected(false);
                            this.inventaire.update();
                            this.inventaire.setSelected(0);
                            this.console.printMessageRestant("Vous pouvre placer le potager où vous souhaitez dans la grille");
                            int x = this.game.getGrille().getCabaneEmplacement().getPosition().x;
                            int y = this.game.getGrille().getCabaneEmplacement().getPosition().y;
                            for(int i1 = x; i1 < x + Potager.largeur; i1++){
                                for(int i2 = y; i2 < y + Potager.hauteur; i2++){
                                    this.game.getGrille().getCases()[i1][i2].setEmplacementBat(true);
                                }
                            }

                        }
                        else{
                            this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                        }
                    }
                }
            }
            if("UtiliserPotionCarottes".equals(this.action)){
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(false);
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getPotion(Potion.Carotte);
                this.game.getInventaire().removePotionsCarottes(1);
                this.inventaire.update();
                this.idSelectBat = 0;
                this.action = null;
            }
            if("UtiliserPotionBaies".equals(this.action)){
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(false);
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getPotion(Potion.Baies);
                this.game.getInventaire().removePotionsBaies(1);
                this.inventaire.update();
                this.idSelectBat = 0;
                this.action = null;
            }
            if("UtiliserPotionBetteraves".equals(this.action)){
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(false);
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getPotion(Potion.Betterave);
                this.game.getInventaire().removePotionsBetteraves(1);
                this.inventaire.update();
                this.idSelectBat = 0;
                this.action = null;
            }
            if("Utiliser".equals(this.action)){
                int i = this.inventaire.getSelected();
                if(i != 6 && i != 7 && i != 8){
                    this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                }
                else{
                    if(i == 6){
                        if(this.game.getInventaire().getPotionsCarottes() > 0){
                            this.action = "UtiliserPotionCarottes";
                            this.inventaire.setSelected(false);
                            this.inventaire.update();
                            this.idSelectBat = 0;
                            this.inventaire.setSelected(this.idSelectBat);
                            this.game.getGrille().getSelectBatiment().get(0).setSelected(true);
                        }
                        else{
                            this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                        }
                    }
                    else if(i == 7){
                        if(this.game.getInventaire().getPotionsBaies() > 0){
                            this.action = "UtiliserPotionBaies";
                            this.inventaire.setSelected(false);
                            this.inventaire.update();
                            this.idSelectBat = 0;
                            this.inventaire.setSelected(this.idSelectBat);
                            this.game.getGrille().getSelectBatiment().get(0).setSelected(true);

                        }
                        else{
                            this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                        }
                    }
                    else if(i == 8){
                        if(this.game.getInventaire().getPotionsBetteraves() > 0){
                            this.action = "UtiliserPotionBetteraves";
                            this.inventaire.setSelected(false);
                            this.inventaire.update();
                            this.idSelectBat = 0;
                            this.inventaire.setSelected(this.idSelectBat);
                            this.game.getGrille().getSelectBatiment().get(0).setSelected(true);
                        }
                        else{
                            this.console.printMessage("Vous avez selectionné la mauvaise cellule");
                        }
                    }
                }
            }

            if("AméliorerBat".equals(this.action)){
                int pieces = this.game.getPieces();
                if(this.idSelectBat == 0){
                    Batiment hdv = this.game.getGrille().getSelectBatiment().get(this.idSelectBat);
                    if(this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getNiveau() == 1) {
                        if(pieces >= 3000){
                            int maxHp = hdv.getMaxHP();
                            this.game.setPieces(pieces-3000);
                            this.vueTresorerie.update();
                            hdv.setSelected(false);
                            hdv.setNiveau(2);
                            hdv.setMaxHP(maxHp + 100);
                            hdv.setHP(hdv.getMaxHP());
                            hdv.setAfficheHeathBar(false);
                            this.action = null;
                            this.console.printMessage("<p style='color:green';>Votre hôtel de ville est passé au niveau 3</p>");
                        }
                        else{
                            this.console.printMessage("<p style='color:red';>Vous n'avez pas assez de pièces pour l'amélioration !</p>");
                        }
                    }
                    else{
                        if(pieces >= 6000){
                            int maxHp = hdv.getMaxHP();
                            this.game.setPieces(pieces-6000);
                            this.vueTresorerie.update();
                            hdv.setSelected(false);
                            hdv.setNiveau(3);
                            hdv.setMaxHP(maxHp + 100);
                            hdv.setHP(hdv.getMaxHP());
                            hdv.setAfficheHeathBar(false);
                            this.action = null;
                            this.console.printMessage("<p style='color:green';>Votre hôtel de ville est passé au niveau 3</p>");
                        }
                        else{
                            this.console.printMessage("<p style='color:red';>Vous n'avez pas assez de pièces pour l'amélioration !</p>");
                        }
                    }
                }
                else{
                    Defense defense = null;
                    for(int i = 0; i < this.game.getGrille().getDefenses().size(); i++){
                        if(this.game.getGrille().getDefenses().get(i).isSelected()){
                            defense = this.game.getGrille().getDefenses().get(i);
                            break;
                        }
                    }
                    if(defense.getNiveau() == 1) {
                        if(pieces >= 4000){
                            int maxHp = defense.getMaxHP();
                            this.game.setPieces(pieces-4000);
                            this.vueTresorerie.update();
                            defense.setSelected(false);
                            defense.setNiveau(2);
                            defense.setMaxHP(maxHp + 100);
                            defense.setHP(defense.getMaxHP());
                            defense.setAfficheHeathBar(false);
                            this.action = null;
                            this.console.printMessage("<p style='color:green';>Votre bâtiment de défense est passé au niveau 2</p>");
                        }
                        else{
                            this.console.printMessage("<p style='color:red';>Vous n'avez pas assez de pièces pour l'amélioration !</p>");
                        }
                    }
                    else{
                        if(pieces >= 5000){
                            int maxHp = defense.getMaxHP();
                            this.game.setPieces(pieces-5000);
                            this.vueTresorerie.update();
                            defense.setSelected(false);
                            defense.setNiveau(3);
                            defense.setMaxHP(maxHp + 100);
                            defense.setHP(defense.getMaxHP());
                            defense.setAfficheHeathBar(false);
                            this.action = null;
                            this.console.printMessage("<p style='color:green';>Votre bâtiment de défense est passé au niveau 3</p>");
                        }
                        else{
                            this.console.printMessage("<p style='color:red';>Vous n'avez pas assez de pièces pour l'amélioration !</p>");
                        }
                    }
                }
            }
            if("Améliorer".equals(this.action)){
                if(this.idSelectBat == 0){ // Si c'est le premier batiment alors c'est l'hotel de ville
                    if(this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getNiveau() == 1) {
                        this.console.printMessageConfirmationAmelioration("L'amélioration de l'hôtel de ville au niveau 2 côute 3000 pièces");
                        this.console.setChoixConfirmation(1);
                        this.action = "AméliorerBat";
                    }
                    else if(this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getNiveau() == 2){
                        this.console.printMessageConfirmationAmelioration("L'amélioration de l'hôtel de ville au niveau 3 côute 6000 pièces");
                        this.console.setChoixConfirmation(1);
                        this.action = "AméliorerBat";
                    }
                    else{
                        this.console.printMessage("Votre hôtel de ville est déjà à son niveau max");
                    }
                }
                else{
                    if(this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getNom() == "Cabane"){
                        this.console.printMessage("Vous avez selectionné un mauvais bâtiment");
                    }
                    else{
                        if(this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getNiveau() == 1){
                            this.console.printMessageConfirmationAmelioration("L'amélioration de la défense au niveau 2 côute 4000 pièces");
                            this.console.setChoixConfirmation(1);
                            this.action = "AméliorerBat";
                        }
                        else if(this.game.getGrille().getSelectBatiment().get(this.idSelectBat).getNiveau() == 2){
                            this.console.printMessageConfirmationAmelioration("L'amélioration de la défense au niveau 3 côute 5000 pièces");
                            this.console.setChoixConfirmation(1);
                            this.action = "AméliorerBat";
                        }
                        else{
                            this.console.printMessage("Le bâtiment de défense est déjà à son niveau maximale");
                        }
                    }
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if("Planter1".equals(this.action) || "Recolter1".equals(this.action)){
                this.game.getGrille().getPotagers().get(this.idPotager).setSelected(false);
                if(this.idPotager == this.game.getGrille().getPotagers().size()-1){
                    this.idPotager = 0;
                }
                else{
                    this.idPotager++;
                }
                this.game.getGrille().getPotagers().get(this.idPotager).setSelected(true);
            }
            if("Planter2".equals(this.action) || "Recolter2".equals(this.action) || "Attaquer2".equals(this.action) || "Ramasser2".equals(this.action)){
                this.game.getVillageois().get(this.idVillageois).setSelected(false);
                if(this.idVillageois == this.game.getGrille().getVillageois().size()-1){
                    this.idVillageois = 0;
                }
                else{
                    this.idVillageois++;
                }
                this.game.getVillageois().get(this.idVillageois).setSelected(true);
            }
            if("Attaquer1".equals(this.action)){
                this.game.getGrille().getMonstre().get(this.idMonstre).setSelected(false);
                if(this.idMonstre == this.game.getGrille().getMonstre().size()-1){
                    this.idMonstre = 0;
                }
                else{
                    this.idMonstre++;
                }
                this.game.getGrille().getMonstre().get(this.idMonstre).setSelected(true);
            }
            if("Ramasser1".equals(this.action)){
                this.game.getGrille().getArgents().get(this.idArgent).setSelected(false);
                if(this.idArgent == this.game.getGrille().getArgents().size()-1){
                    this.idArgent = 0;
                }
                else{
                    this.idArgent++;
                }
                this.game.getGrille().getArgents().get(this.idArgent).setSelected(true);
            }
            if("Acheter".equals(this.action)){
                if(this.vueShop.getSelectedIndexAchat() == 2){
                    this.vueShop.setSelectedIndexAchat(0);
                }
                else if(this.vueShop.getSelectedIndexAchat() == 5){
                    this.vueShop.setSelectedIndexAchat(3);
                }
                else{
                    int i = this.vueShop.getSelectedIndexAchat() + 1;
                    this.vueShop.setSelectedIndexAchat(i);
                }
                this.vueShop.updateAchat();
            }
            if("Vendre".equals(this.action)){
                if(this.vueShop.getSelectedIndexVente() == 2){
                    this.vueShop.setSelectedIndexVente(0);
                }
                else{
                    int i = this.vueShop.getSelectedIndexVente() + 1;
                    this.vueShop.setSelectedIndexVente(i);
                }
                this.vueShop.updateVente();
            }
            if("Planter1.1".equals(this.action) || "Construire".equals(this.action) || "Utiliser".equals(this.action)){
                if(this.inventaire.getSelected() == 1){
                   this.inventaire.setSelected(0);
                }
                else if(this.inventaire.getSelected() == 3){
                    this.inventaire.setSelected(2);
                }
                else if(this.inventaire.getSelected() == 5){
                    this.inventaire.setSelected(4);
                }
                else if(this.inventaire.getSelected() == 7){
                    this.inventaire.setSelected(6);
                }
                else if(this.inventaire.getSelected() == 9){
                    this.inventaire.setSelected(8);
                }
                else{
                    int i = this.inventaire.getSelected();
                    this.inventaire.setSelected(i+1);
                }
                this.inventaire.update();
            }
            if("ConstruireCabane".equals(this.action)){
                int posX = this.game.getGrille().getCabaneEmplacement().getPosition().x;
                int posY = this.game.getGrille().getCabaneEmplacement().getPosition().y;
                int l = this.game.getGrille().getCabaneEmplacement().getLargeur();
                int h = this.game.getGrille().getCabaneEmplacement().getHauteur();
                if(posX + 1 < Grille.LARGEUR) {
                    for (int i1 = posX + l - 1; i1 >= posX; i1--) {
                        for (int i2 = posY + h - 1; i2 >= posY; i2--) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1 + 1][i2].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX + 1, posY);
                    this.game.getGrille().getCabaneEmplacement().setPosition(tmp);
                }
            }
            if("ConstruireDefense".equals(this.action)){
                int posX = this.game.getGrille().getDefenseEmplacement().getPosition().x;
                int posY = this.game.getGrille().getDefenseEmplacement().getPosition().y;
                int l = this.game.getGrille().getDefenseEmplacement().getLargeur();
                int h = this.game.getGrille().getDefenseEmplacement().getHauteur();
                if(posX + 1 < Grille.LARGEUR) {
                    for (int i1 = posX + l - 1; i1 >= posX; i1--) {
                        for (int i2 = posY + h - 1; i2 >= posY; i2--) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1 + 1][i2].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX + 1, posY);
                    this.game.getGrille().getDefenseEmplacement().setPosition(tmp);
                }
            }
            if("ConstruirePotager".equals(this.action)){
                int posX = this.game.getGrille().getPotagerEmplacement().getPosition().x;
                int posY = this.game.getGrille().getPotagerEmplacement().getPosition().y;
                int l = Potager.largeur;
                int h = Potager.hauteur;
                if(posX + 1 < Grille.LARGEUR) {
                    for (int i1 = posX + l - 1; i1 >= posX; i1--) {
                        for (int i2 = posY + h - 1; i2 >= posY; i2--) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1 + 1][i2].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX + 1, posY);
                    this.game.getGrille().getPotagerEmplacement().setPosition(tmp);
                }
            }
            if("UtiliserPotionCarottes".equals(this.action) || "UtiliserPotionBaies".equals(this.action) || "UtiliserPotionBetteraves".equals(this.action) || "Améliorer".equals(this.action)){
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(false);
                if(this.idSelectBat < this.game.getGrille().getSelectBatiment().size()-1){
                    this.idSelectBat++;
                    this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(true);
                }
                else{
                    this.idSelectBat = 0;
                    this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(true);
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if("Planter1".equals(this.action) || "Recolter1".equals(this.action)){
                this.game.getGrille().getPotagers().get(this.idPotager).setSelected(false);
                if(this.idPotager == 0){
                    this.idPotager = this.game.getGrille().getPotagers().size()-1;
                }
                else{
                    this.idPotager--;
                }
                this.game.getGrille().getPotagers().get(this.idPotager).setSelected(true);
            }
            if("Planter2".equals(this.action) || "Recolter2".equals(this.action) || "Attaquer2".equals(this.action)  || "Ramasser2".equals(this.action)){
                this.game.getVillageois().get(this.idVillageois).setSelected(false);
                if(this.idVillageois == 0){
                    this.idVillageois = this.game.getGrille().getVillageois().size()-1;
                }
                else{
                    this.idVillageois--;
                }
                this.game.getVillageois().get(this.idVillageois).setSelected(true);
            }
            if("Attaquer1".equals(this.action)){
                this.game.getGrille().getMonstre().get(this.idMonstre).setSelected(false);
                if(this.idMonstre == 0){
                    this.idMonstre = this.game.getGrille().getMonstre().size()-1;
                }
                else{
                    this.idMonstre--;
                }
                this.game.getGrille().getMonstre().get(this.idMonstre).setSelected(true);
            }
            if("Ramasser1".equals(this.action)){
                this.game.getGrille().getArgents().get(this.idArgent).setSelected(false);
                if(this.idArgent == 0){
                    this.idArgent = this.game.getGrille().getArgents().size()-1;
                }
                else{
                    this.idArgent--;
                }
                this.game.getGrille().getArgents().get(this.idArgent).setSelected(true);
            }
            if("Acheter".equals(this.action)){
                if(this.vueShop.getSelectedIndexAchat() == 0){
                    this.vueShop.setSelectedIndexAchat(2);
                }
                else if(this.vueShop.getSelectedIndexAchat() == 3){
                    this.vueShop.setSelectedIndexAchat(5);
                }
                else{
                    int i = this.vueShop.getSelectedIndexAchat() - 1;
                    this.vueShop.setSelectedIndexAchat(i);
                }
                this.vueShop.updateAchat();
            }
            if("Vendre".equals(this.action)){
                if(this.vueShop.getSelectedIndexVente() == 0){
                    this.vueShop.setSelectedIndexVente(2);
                }
                else{
                    int i = this.vueShop.getSelectedIndexVente() - 1;
                    this.vueShop.setSelectedIndexVente(i);
                }
                this.vueShop.updateVente();
            }
            if("Planter1.1".equals(this.action) || "Construire".equals(this.action) || "Utiliser".equals(this.action)){
                if(this.inventaire.getSelected() == 0){
                    this.inventaire.setSelected(1);
                }
                else if(this.inventaire.getSelected() == 2){
                    this.inventaire.setSelected(3);
                }
                else if(this.inventaire.getSelected() == 4){
                    this.inventaire.setSelected(5);
                }
                else if(this.inventaire.getSelected() == 6){
                    this.inventaire.setSelected(7);
                }
                else if(this.inventaire.getSelected() == 8){
                    this.inventaire.setSelected(9);
                }
                else{
                    int i = this.inventaire.getSelected();
                    this.inventaire.setSelected(i-1);
                }
                this.inventaire.update();
            }
            if("ConstruireCabane".equals(this.action)){
                int posX = this.game.getGrille().getCabaneEmplacement().getPosition().x;
                int posY = this.game.getGrille().getCabaneEmplacement().getPosition().y;
                int l = this.game.getGrille().getCabaneEmplacement().getLargeur();
                int h = this.game.getGrille().getCabaneEmplacement().getHauteur();
                if(posX - 1 >= 0) {
                    for (int i1 = posX; i1 < posX + l; i1++) {
                        for (int i2 = posY; i2 < posY + h; i2++) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1 - 1][i2].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX - 1, posY);
                    this.game.getGrille().getCabaneEmplacement().setPosition(tmp);
                }
            }
            if("ConstruireDefense".equals(this.action)){
                int posX = this.game.getGrille().getDefenseEmplacement().getPosition().x;
                int posY = this.game.getGrille().getDefenseEmplacement().getPosition().y;
                int l = this.game.getGrille().getDefenseEmplacement().getLargeur();
                int h = this.game.getGrille().getDefenseEmplacement().getHauteur();
                if(posX - 1 >= 0) {
                    for (int i1 = posX; i1 < posX + l; i1++) {
                        for (int i2 = posY; i2 < posY + h; i2++) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1 - 1][i2].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX - 1, posY);
                    this.game.getGrille().getDefenseEmplacement().setPosition(tmp);
                }
            }
            if("ConstruirePotager".equals(this.action)){
                int posX = this.game.getGrille().getPotagerEmplacement().getPosition().x;
                int posY = this.game.getGrille().getPotagerEmplacement().getPosition().y;
                int l = Potager.largeur;
                int h = Potager.hauteur;
                if(posX - 1 >= 0) {
                    for (int i1 = posX; i1 < posX + l; i1++) {
                        for (int i2 = posY; i2 < posY + h; i2++) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1 - 1][i2].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX - 1, posY);
                    this.game.getGrille().getPotagerEmplacement().setPosition(tmp);
                }
            }
            if("UtiliserPotionCarottes".equals(this.action) || "UtiliserPotionBaies".equals(this.action) || "UtiliserPotionBetteraves".equals(this.action) || "Améliorer".equals(this.action)){
                this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(false);
                if(this.idSelectBat > 0){
                    this.idSelectBat--;
                    this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(true);
                }
                else{
                    this.idSelectBat = this.game.getGrille().getSelectBatiment().size()-1;
                    this.game.getGrille().getSelectBatiment().get(this.idSelectBat).setSelected(true);
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if("Shop".equals(this.action)){
                int i = this.console.getChoixLabelShop();
                if(i == 0){
                    this.console.setChoixLabelShop(2);
                }
                else{
                    i--;
                    this.console.setChoixLabelShop(i);
                }
                this.console.printMessageSelecShop();

            }
            if("Acheter".equals(this.action)){
                int i = this.vueShop.getSelectedIndexAchat() + 3;
                int i2 = this.vueShop.getSelectedIndexAchat() - 3;
                if(this.vueShop.getSelectedIndexAchat() < 3){
                    this.vueShop.setSelectedIndexAchat(i);
                }
                else {
                    this.vueShop.setSelectedIndexAchat(i2);
                }
                this.vueShop.updateAchat();
            }
            if("Planter1.1".equals(this.action) || "Construire".equals(this.action) || "Utiliser".equals(this.action)){
                int i = this.inventaire.getSelected();
                if(i == 0){
                    this.inventaire.setSelected(8);
                }
                else if(i == 1){
                    this.inventaire.setSelected(9);
                }
                else{
                    this.inventaire.setSelected(i-2);
                }
                this.inventaire.update();
            }
            if("AcheterCabane".equals(this.action) || "AcheterDefense".equals(this.action) || "AcheterHaie".equals(this.action) || "AcheterCarottes".equals(this.action) || "AcheterBaies".equals(this.action) || "AcheterBetteraves".equals(this.action) || "AcheterPotionCarottes".equals(this.action) || "AcheterPotionBaies".equals(this.action) || "AcheterPotionBetteraves".equals(this.action) || "AméliorerBat".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                if(i == 2){
                    this.console.setChoixConfirmation(1);
                }
                else if(i == 1){
                    this.console.setChoixConfirmation(2);
                }
                this.console.printMessageConfirmation();
            }
            if("ConstruireCabane".equals(this.action)){
                int posX = this.game.getGrille().getCabaneEmplacement().getPosition().x;
                int posY = this.game.getGrille().getCabaneEmplacement().getPosition().y;
                int l = this.game.getGrille().getCabaneEmplacement().getLargeur();
                int h = this.game.getGrille().getCabaneEmplacement().getHauteur();
                if(posY - 1 >= 0) {
                    for (int i1 = posX; i1 < posX + l; i1++) {
                        for (int i2 = posY; i2 < posY + h; i2++) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1][i2 - 1].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX, posY - 1);
                    this.game.getGrille().getCabaneEmplacement().setPosition(tmp);
                }
            }
            if("ConstruireDefense".equals(this.action)){
                int posX = this.game.getGrille().getDefenseEmplacement().getPosition().x;
                int posY = this.game.getGrille().getDefenseEmplacement().getPosition().y;
                int l = this.game.getGrille().getDefenseEmplacement().getLargeur();
                int h = this.game.getGrille().getDefenseEmplacement().getHauteur();
                if(posY - 1 >= 0) {
                    for (int i1 = posX; i1 < posX + l; i1++) {
                        for (int i2 = posY; i2 < posY + h; i2++) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1][i2 - 1].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX, posY - 1);
                    this.game.getGrille().getDefenseEmplacement().setPosition(tmp);
                }
            }
            if("ConstruirePotager".equals(this.action)){
                int posX = this.game.getGrille().getPotagerEmplacement().getPosition().x;
                int posY = this.game.getGrille().getPotagerEmplacement().getPosition().y;
                int l = Potager.largeur;
                int h = Potager.hauteur;
                if(posY - 1 >= 0) {
                    for (int i1 = posX; i1 < posX + l; i1++) {
                        for (int i2 = posY; i2 < posY + h; i2++) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1][i2 - 1].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX, posY - 1);
                    this.game.getGrille().getPotagerEmplacement().setPosition(tmp);
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if("Shop".equals(this.action)){
                int i = this.console.getChoixLabelShop();
                if(i == 2){
                    this.console.setChoixLabelShop(0);
                }
                else{
                    i++;
                    this.console.setChoixLabelShop(i);
                }
                this.console.printMessageSelecShop();
            }
            if("Acheter".equals(this.action)){
                int i = this.vueShop.getSelectedIndexAchat() + 3;
                int i2 = this.vueShop.getSelectedIndexAchat() - 3;
                if(this.vueShop.getSelectedIndexAchat() < 3){
                    this.vueShop.setSelectedIndexAchat(i);
                }
                else {
                    this.vueShop.setSelectedIndexAchat(i2);
                }
                this.vueShop.updateAchat();
            }
            if("Planter1.1".equals(this.action) || "Construire".equals(this.action) || "Utiliser".equals(this.action)){
                int i = this.inventaire.getSelected();
                if(i == 8){
                    this.inventaire.setSelected(0);
                }
                else if(i == 9){
                    this.inventaire.setSelected(1);
                }
                else{
                    this.inventaire.setSelected(i+2);
                }
                this.inventaire.update();
            }
            if("AcheterCabane".equals(this.action) || "AcheterDefense".equals(this.action) || "AcheterHaie".equals(this.action) || "AcheterCarottes".equals(this.action) || "AcheterBaies".equals(this.action) || "AcheterBetteraves".equals(this.action) || "AcheterPotionCarottes".equals(this.action) || "AcheterPotionBaies".equals(this.action) || "AcheterPotionBetteraves".equals(this.action) || "AméliorerBat".equals(this.action)){
                int i = this.console.getChoixConfirmation();
                if(i == 1){
                    this.console.setChoixConfirmation(2);
                }
                else if(i == 2){
                    this.console.setChoixConfirmation(1);
                }
                this.console.printMessageConfirmation();
            }
            if("ConstruireCabane".equals(this.action)){
                int posX = this.game.getGrille().getCabaneEmplacement().getPosition().x;
                int posY = this.game.getGrille().getCabaneEmplacement().getPosition().y;
                int l = this.game.getGrille().getCabaneEmplacement().getLargeur();
                int h = this.game.getGrille().getCabaneEmplacement().getHauteur();
                if(posY + 1 < Grille.HAUTEUR) {
                    for (int i2 = posY + h - 1; i2 >= posY; i2--) {
                        for (int i1 = posX + l - 1; i1 >= posX; i1--) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1][i2 + 1].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX, posY + 1);
                    this.game.getGrille().getCabaneEmplacement().setPosition(tmp);
                }
            }
            if("ConstruireDefense".equals(this.action)){
                int posX = this.game.getGrille().getDefenseEmplacement().getPosition().x;
                int posY = this.game.getGrille().getDefenseEmplacement().getPosition().y;
                int l = this.game.getGrille().getDefenseEmplacement().getLargeur();
                int h = this.game.getGrille().getDefenseEmplacement().getHauteur();
                if(posY + 1 < Grille.HAUTEUR) {
                    for (int i2 = posY + h - 1; i2 >= posY; i2--) {
                        for (int i1 = posX + l - 1; i1 >= posX; i1--) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1][i2 + 1].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX, posY + 1);
                    this.game.getGrille().getDefenseEmplacement().setPosition(tmp);
                }
            }
            if("ConstruirePotager".equals(this.action)){
                int posX = this.game.getGrille().getPotagerEmplacement().getPosition().x;
                int posY = this.game.getGrille().getPotagerEmplacement().getPosition().y;
                int l = Potager.largeur;
                int h = Potager.hauteur;
                if(posY + 1 < Grille.HAUTEUR) {
                    for (int i2 = posY + h - 1; i2 >= posY; i2--) {
                        for (int i1 = posX + l - 1; i1 >= posX; i1--) {
                            this.game.getGrille().getCases()[i1][i2].setEmplacementBat(false);
                            this.game.getGrille().getCases()[i1][i2 + 1].setEmplacementBat(true);
                        }
                    }
                    Point tmp = new Point(posX, posY + 1);
                    this.game.getGrille().getPotagerEmplacement().setPosition(tmp);
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if("Acheter".equals(this.action)){
                this.console.setTextNull();
                this.vueShop.shopAchat();
                this.vueShop.setSelectedIndexAchat(0);
                this.action = null;
                this.shop = false;
            }
            if("Vendre".equals(this.action)){
                this.console.setTextNull();
                this.vueShop.shopVente();
                this.vueShop.setSelectedIndexVente(0);
                this.action = null;
                this.shop = false;
            }
        }
        this.game.getGrille().notifyObservers();
    }

    /**
     Méthode appelée lorsque la touche est relâchée.
     @param e L'événement de la touche relâchée.
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
    /**
     Getter pour le jeu.
     @return Le jeu actuel.
     */

    //Guetters :
    public Game getGame() {
        return game;
    }

    /**

     Getter pour l'affichage.
     @return L'affichage actuel.
     */
    public Affichage getAffichage() {
        return affichage;
    }
    /**

     Getter pour la vue de la trésorerie.
     @return La vue de la trésorerie.
     */
    public VueTresorerie getVueTresorerie() {
        return vueTresorerie;
    }
    /**

     Getter pour la vue de l'inventaire.
     @return La vue de l'inventaire.
     */
    public VueInventaire getInventaire() {
        return inventaire;
    }
    /**
     Setter pour modifier la vue de la console.
     @param console La vue de la console.
     */
    public void setConsole(VueConsole console) {
        this.console = console;
    }
    /**
     Setter qui modifie la vue de l'inventaire.
     @param inventaire La vue de l'inventaire.
     */
    public void setInventaire(VueInventaire inventaire) {
        this.inventaire = inventaire;
    }
    /**
     Modifie la vue de la trésorerie utilisée par cette classe.
     @param vueTresorerie la nouvelle vue de la trésorerie à utiliser
     */
    public void setTresorerie(VueTresorerie vueTresorerie){
        this.vueTresorerie = vueTresorerie;
    }
}
