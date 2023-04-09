package Model;

import Model.Entity.Monstre;

import java.awt.*;

public class Defense extends Batiment implements Runnable {
    private int degat;
    private int rayon;
    private int etatWeapon;
    private Grille grille;
    private Monstre monstre;
    private boolean enAttaque;
    private Point positionMonstre;

    private Thread thread;  // Ajout de la variable d'instance pour stocker la référence à l'objet Thread correspondant


    /**
     * Constructeur de la classe Case. Initialise les attributs de la case à des valeurs par défaut.
     * @param x la coordonnée en abscisse de la case
     * @param y la coordonnée en ordonnée de la case
     */
    public Defense(Grille grille, Point p, String nom, int l, int h, int d, int r) {
        super(p, nom, l, h);
        this.degat = d;
        this.rayon = r;
        this.etatWeapon = 1;
        this.grille = grille;
        this.monstre = null;
        this.enAttaque = false;
        this.positionMonstre = null;
    }
    /**
     * Renvoie le type de batiment présent sur la case.
     * @return le nom du batiment s'il y en a un, null sinon.
     */
    public void verifieMonstreDansRayon(){
        for(int i = 0; i < this.grille.getMonstre().size(); i++){
            //Coordonnée du centre de l'objet monstre
            int xM = this.grille.getMonstre().get(i).getPosition().x + (Monstre.largeur / 2);
            int yM = this.grille.getMonstre().get(i).getPosition().y + (Monstre.hauteur / 2);
            //Coordonnée du centre de la tour de défense
            int xB = this.getPosition().x + (this.getLargeur()/2);
            int yB = this.getPosition().y + (this.getHauteur()/2);
            //Calculer la distance
            double posXP = Math.pow(xM - xB, 2);
            double posYP = Math.pow(yM - yB, 2);
            double distance = Math.sqrt(posXP + posYP);
            if(distance <= this.rayon){
                this.monstre = this.grille.getMonstre().get(i);
                this.positionMonstre = this.grille.getMonstre().get(i).getPosition();
            }
        }
    }
    /**
     * Lance la défense en tant que thread.
     */
    @Override
    public void run() {
        int tempo = 0;
        int tempoMonstre = 2;
        while(!this.estDetruit() && this.monstre != null && !this.monstre.estMort()){
            if(tempo == 1){
                if(this.etatWeapon == 6){
                    this.etatWeapon = 1;
                }
                else{
                    this.etatWeapon++;
                }
                if(tempoMonstre == 3) {
                    this.monstre.subitDegat(this.degat);
                    tempoMonstre = 0;
                }
                tempo = 0;
            }
            else{
                this.monstre.setGetAttacked(false);
                tempo++;
                tempoMonstre++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.monstre = null;
        this.enAttaque = false;
    }

/**
 * Renvoie l'état d'attaque de la défense.
 * @return True si la défense est en train d'attaquer **/
public boolean isEnAttaque() {
        return enAttaque;
    }
    /**

     Cette méthode retourne l'objet Thread correspondant à cette instance de défense.
     @return l'objet Thread correspondant à cette instance de défense
     */
    public Thread getThread() {
        return thread;
    }
    /**

     Cette méthode retourne la portée de la défense.
     @return la portée de la défense
     */

    public int getRayon() {
        return rayon;
    }
    /**

     Cette méthode retourne la valeur des dégâts de la défense.
     @return la valeur des dégâts de la défense
     */

    public int getDegat() {
        return degat;
    }
    /**

     Cette méthode retourne l'état de l'arme de la défense.
     @return l'état de l'arme de la défense
     */

    public int getEtatWeapon() {
        return etatWeapon;
    }
    /**

     Cette méthode retourne le monstre actuellement ciblé par la défense.
     @return le monstre actuellement ciblé par la défense
     */

    public Monstre getMonstre(){
        return this.monstre;
    }
    /**

     Cette méthode retourne la position du monstre actuellement ciblé par la défense.
     @return la position du monstre actuellement ciblé par la défense
     */
    public Point getPositionMonstre() {
        return positionMonstre;
    }

    //Setters:
    /**

     Cette méthode permet de changer le monstre ciblé par la défense.
     @param monstre le nouveau monstre ciblé par la défense
     */

    public void setMonstre(Monstre monstre) {
        this.monstre = monstre;
    }
    /**

     Cette méthode permet de changer la valeur des dégâts de la défense.
     @param degat la nouvelle valeur des dégâts de la défense
     */
    public void setDegat(int degat) {
        this.degat = degat;
    }
    /**

     Cette méthode permet de changer l'objet Thread correspondant à cette instance de défense.
     @param thread le nouvel objet Thread correspondant à cette instance de défense
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }
    /**

     Cette méthode permet de faire évoluer la défense d'un certain niveau.
     @param niveau le niveau à atteindre pour la défense
     */

    public void lvlUP(int niveau) {
        if(niveau == 2){
            this.setHauteur(30);
            this.setDegat(this.getDegat()+15);
        }
        else if(niveau == 3){
            this.setHauteur(33);
            this.setDegat(this.getDegat()+30);
        }
        this.setNiveau(niveau);
        for (int i = this.getPosition().x; i < this.getPosition().x + this.getLargeur(); i++) {
            for (int j = this.getPosition().y; j < this.getPosition().y + this.getHauteur(); j++) {
                this.grille.getCases()[i][j].setBatiment(this);
                this.grille.getCases()[i][j].setOccupee(true);
            }
        }
        this.grille.notifyObservers();
    }
    /**

     Cette méthode permet de mettre la défense en état d'attaque ou de désactiver son état d'attaque.
     @param enAttaque l'état d'attaque de la défense (true si en attaque, false sinon)
     */

    public void setEnAttaque(boolean enAttaque) {
        this.enAttaque = enAttaque;
    }

}
