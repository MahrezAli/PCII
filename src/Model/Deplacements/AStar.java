/**

 Cette classe implémente l'algorithme A* pour trouver le chemin le plus court entre deux points sur une grille.
 */
package Model.Deplacements;

import Model.Arbre;
import Model.Case;
import Model.Entity.Monstre;
import Model.Grille;
import Model.Entity.Villageois;
import Utils.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AStar implements Runnable {
    private Grille grille; // la grille sur laquelle l'algorithme A* est exécuté
    private Point depart; // le point de départ de l'algorithme A*
    private Point destination; // le point d'arrivée de l'algorithme A*
    private ArrayList<Case> chemin; // le chemin trouvé par l'algorithme A*
    private Thread thread; // la référence à l'objet Thread correspondant
    private int faitAppel; // 0 pour le villageois et 1 pour le monstre

    /**
     * Constructeur de la classe AStar.
     * @param grille la grille sur laquelle l'algorithme A* est exécuté
     * @param depart le point de départ de l'algorithme A*
     * @param destination le point d'arrivée de l'algorithme A*
     * @param quiFaitAppel l'entier indiquant qui appelle l'algorithme A* (0 pour le villageois, 1 pour le monstre)
     */

    public AStar(Grille grille, Point depart, Point destination, int quiFaitAppel) {
        this.grille = grille;
        this.depart = depart;
        this.faitAppel = quiFaitAppel;
        if(this.faitAppel == 0) {
            if (Grille.LARGEUR - Arbre.LARGEUR - Villageois.largeur < destination.x) {
                if (Grille.HAUTEUR - Arbre.HAUTEUR - Villageois.hauteur < destination.y) {
                    this.destination = new Point(Grille.LARGEUR-Arbre.LARGEUR-Villageois.largeur, Grille.HAUTEUR-Arbre.HAUTEUR-Villageois.hauteur);
                } else if(destination.y < Arbre.HAUTEUR){
                    this.destination = new Point(Grille.LARGEUR-Arbre.LARGEUR-Villageois.largeur, Arbre.HAUTEUR);
                }
                else{
                    this.destination = new Point(Grille.LARGEUR-Arbre.LARGEUR-Villageois.largeur, destination.y);
                }
            } else if(Arbre.LARGEUR > destination.x){
                if (Grille.HAUTEUR - Arbre.HAUTEUR - Villageois.hauteur < destination.y) {
                    this.destination = new Point(Arbre.LARGEUR, Grille.HAUTEUR-Arbre.HAUTEUR-Villageois.hauteur);
                } else if(destination.y < Arbre.HAUTEUR){
                    this.destination = new Point(Arbre.LARGEUR, Arbre.HAUTEUR);
                }
                else{
                    this.destination = new Point(Arbre.LARGEUR, destination.y);
                }
            }
            else{
                if (Grille.HAUTEUR - Arbre.HAUTEUR - Villageois.hauteur < destination.y) {
                    this.destination = new Point(destination.x, Grille.HAUTEUR-Arbre.HAUTEUR-Villageois.hauteur);
                } else if(destination.y < Arbre.HAUTEUR){
                    this.destination = new Point(destination.x, Arbre.HAUTEUR);
                }
                else{
                    this.destination = destination;
                }
            }
        }
        else if(this.faitAppel == 1){
            if (Grille.LARGEUR - Arbre.LARGEUR - Monstre.largeur < destination.x) {
                if (Grille.HAUTEUR - Arbre.HAUTEUR - Monstre.hauteur < destination.y) {
                    this.destination = new Point(Grille.LARGEUR-Arbre.LARGEUR-Monstre.largeur, Grille.HAUTEUR-Arbre.HAUTEUR-Monstre.hauteur);
                } else if(destination.y < Arbre.HAUTEUR){
                    this.destination = new Point(Grille.LARGEUR-Arbre.LARGEUR-Monstre.largeur, Arbre.HAUTEUR);
                }
                else{
                    this.destination = new Point(Grille.LARGEUR-Arbre.LARGEUR-Monstre.largeur, destination.y);
                }
            } else if(Arbre.LARGEUR > destination.x){
                if (Grille.HAUTEUR - Arbre.HAUTEUR - Monstre.hauteur < destination.y) {
                    this.destination = new Point(Arbre.LARGEUR, Grille.HAUTEUR-Arbre.HAUTEUR-Monstre.hauteur);
                } else if(destination.y < Arbre.HAUTEUR){
                    this.destination = new Point(Arbre.LARGEUR, Arbre.HAUTEUR);
                }
                else{
                    this.destination = new Point(Arbre.LARGEUR, destination.y);
                }
            }
            else{
                if (Grille.HAUTEUR - Arbre.HAUTEUR - Monstre.hauteur < destination.y) {
                    this.destination = new Point(destination.x, Grille.HAUTEUR-Arbre.HAUTEUR-Monstre.hauteur);
                } else if(destination.y < Arbre.HAUTEUR){
                    this.destination = new Point(destination.x, Arbre.HAUTEUR);
                }
                else{
                    this.destination = destination;
                }
            }
        }
        else{
            this.destination = null;
        }
        this.chemin = new ArrayList<>();
    }

    /**

     Renvoie le chemin calculé par l'algorithme A*.
     @return le chemin calculé
     */
    public ArrayList<Case> getChemin() {
        return this.chemin;
    }

    /**
     Démarre l'algorithme A* pour calculer le chemin entre le point de départ et le point de destination.
     Cette méthode est appelée par l'objet Thread correspondant.
     */
    @Override
    public void run() {
        Case depart = this.grille.getCases()[this.depart.x][this.depart.y];
        Case destination = this.grille.getCases()[this.destination.x][this.destination.y];
        HashSet<Case> ouverts = new HashSet<>();
        HashSet<Case> fermes = new HashSet<>();
        ouverts.add(depart);
        while (!ouverts.isEmpty()) {
            Case courante = getCaseAvecCoutMinimum(ouverts);
            if (courante.equals(destination)) {
                ArrayList<Case> chemins = new ArrayList<>();
                chemins.add(courante);
                while (courante.getPere() != null) {
                    courante = courante.getPere();
                    chemins.add(0, courante);
                }
                this.chemin = chemins;
                //rafraichissement cases pour A*
                for(int i = 0; i < Grille.LARGEUR; i++){
                    for(int j = 0; j < Grille.HAUTEUR; j++){
                        this.grille.getCases()[i][j].setCout(0);
                        this.grille.getCases()[i][j].setHeuristique(0);
                        this.grille.getCases()[i][j].setPere(null);
                    }
                }
                return;
            }

            ouverts.remove(courante);
            fermes.add(courante);

            for (Case voisin : getVoisinsDeCase(courante)) {
                if (!fermes.contains(voisin)) {
                    int nouveauCout = courante.getCout() + getCoutEntreCases(voisin);

                    if (nouveauCout < voisin.getCout() || !ouverts.contains(voisin)) {
                        voisin.setCout(nouveauCout);
                        voisin.setHeuristique(getHeuristiqueEntreCases(voisin, destination));
                        voisin.setPere(courante);

                        if (!ouverts.contains(voisin)) {
                            ouverts.add(voisin);
                        }
                    }
                }
            }
        }
        //rafraichissement cases pour A*
        for(int i = 0; i < Grille.LARGEUR; i++){
            for(int j = 0; j < Grille.HAUTEUR; j++){
                this.grille.getCases()[i][j].setCout(0);
                this.grille.getCases()[i][j].setHeuristique(0);
                this.grille.getCases()[i][j].setPere(null);
            }
        }
    }

    /**

     Récupère le nœud ayant le coût minimum parmi un ensemble de nœuds.
     @param noeuds l'ensemble de nœuds
     @return le nœud ayant le coût minimum
     */
    private Case getCaseAvecCoutMinimum(Set<Case> noeuds) {
        Case noeudMinimum = null;
        int coutMinimum = Integer.MAX_VALUE;

        for (Case noeud : noeuds) {
            int cout = noeud.getCout() + noeud.getHeuristique();

            if (cout < coutMinimum) {
                noeudMinimum = noeud;
                coutMinimum = cout;
            }
        }

        return noeudMinimum;
    }
    /**
     Calcule le coût pour se déplacer d'une case à une autre.
     @param arrivee la case d'arrivée
     @return le coût pour se déplacer d'une case à l'autre
     */

    private int getCoutEntreCases(Case arrivee) {
        int cout = 1;

        if (this.grille.positionOccupee(arrivee.getX(), arrivee.getY())){
            cout = Integer.MAX_VALUE;
        }
        return cout;
    }
    /**
     * Retourne l'heuristique entre deux cases.
     *
     * @param depart la case voisine à atteindre.
     * @param arrivee la case destination.
     * @return l'heuristique entre les deux cases.
     */
    private int getHeuristiqueEntreCases(Case depart, Case arrivee) {
        int dx = Math.abs(depart.getX() - arrivee.getX());
        int dy = Math.abs(depart.getY() - arrivee.getY());
        return dx + dy;
    }
    /**
     * Retourne la liste des cases voisines accessibles à partir de la case courante.
     *
     * @param caseCourante  la case à partir de laquelle chercher les voisins.
     * @return la liste des voisins accessibles.
     */
    private ArrayList<Case> getVoisinsDeCase(Case caseCourante) {
        ArrayList<Case> voisins = new ArrayList<>();

        Point point = new Point(caseCourante.getX(), caseCourante.getY());

        if (point.x > 0 && positionPossible(point.x, point.y, Direction.left)) {
            Case voisinGauche = grille.getCases()[point.x - 1][point.y];
            voisins.add(voisinGauche);
        }
        if (point.x < Grille.LARGEUR - 1 && positionPossible(point.x, point.y, Direction.right)) {
            Case voisinDroite = grille.getCases()[point.x + 1][point.y];
            voisins.add(voisinDroite);
        }

        if (point.y > 0 && positionPossible(point.x, point.y, Direction.down)) {
            Case voisinBas = grille.getCases()[point.x][point.y - 1];
            voisins.add(voisinBas);
        }

        if (point.y < Grille.HAUTEUR - 1 && positionPossible(point.x, point.y, Direction.up)) {
            Case voisinHaut = grille.getCases()[point.x][point.y + 1];
            voisins.add(voisinHaut);
        }
        return voisins;
    }
    /**Vérifie si la position x,y dans la direction d est possible pour le villageois ou le monstre.
    @param x coordonnée x de la position à vérifier
    @param y coordonnée y de la position à vérifier
    @param d la direction dans laquelle vérifier si la position est possible
    @return true si la position est libre, false sinon.*/
    private boolean positionPossible(int x, int y, Direction d){
        boolean res = true;
        if(this.faitAppel == 0) {
            if (d == Direction.up) {
                for (int i = x; i < x + Villageois.largeur; i++) {
                    res = res && !this.grille.positionOccupee(i, y + Villageois.hauteur + 1);
                }
            } else if (d == Direction.down) {
                for (int i = x; i < x + Villageois.largeur; i++) {
                    res = res && !this.grille.positionOccupee(i, y - 2);
                }
            } else if (d == Direction.left) {
                for (int j = y; j < y + Villageois.hauteur; j++) {
                    res = res && !this.grille.positionOccupee(x - 1, j);
                }
            }
            else if (d == Direction.right) {
                for (int j = y; j < y + Villageois.hauteur; j++) {
                    res = res && !this.grille.positionOccupee(x + Villageois.largeur + 1, j);
                }
            }
        }
        else if(this.faitAppel == 1){
            if (d == Direction.up) {
                for (int i = x; i < x + Monstre.largeur; i++) {
                    res = res && !this.grille.positionOccupee(i, y + Monstre.hauteur + 1);
                }
            } else if (d == Direction.down) {
                for (int i = x; i < x + Monstre.largeur; i++) {
                    res = res && !this.grille.positionOccupee(i, y - 2);
                }
            } else if (d == Direction.left) {
                for (int j = y; j < y + Monstre.hauteur; j++) {
                    res = res && !this.grille.positionOccupee(x - 1, j);
                }
            }
            else if (d == Direction.right) {
                for (int j = y; j < y + Monstre.hauteur; j++) {
                    res = res && !this.grille.positionOccupee(x + Monstre.largeur + 1, j);
                }
            }
        }
        else{
            res = false;
        }
        return res;
    }
    /** Renvoie l'objet Thread de l'instance
    @return l'objet Thread associé à l'instance de AStar.*/

    public Thread getThread() {
        return thread;
    }

    /**Définit l'objet Thread associé à l'instance de AStar.
    @param thread l'objet Thread à associer à l'instance de AStar.*/

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}