/**
 * La classe VueCase représente l'interface graphique d'une case du jeu.
 * Elle implémente l'interface Observer pour être notifiée des changements de l'état de la case observée.
 */

package Vue;



import Model.Case;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueCase extends JPanel implements Observer {
    private JPanel caseJoueur;
    private Case caseID;
    public static final int tailleCase = 2;
    /**
     * Constructeur de la classe VueCase.
     *
     * @param caseID L'objet Case correspondant à cette case.
     */
    public VueCase(Case caseID) {
        this.caseID = caseID;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        this.caseID.addObserver(this);
        JPanel panelBas = new JPanel(new GridLayout());
        this.add(panelBas, BorderLayout.SOUTH);
        this.caseJoueur = new JPanel();
        this.setPreferredSize(new Dimension(tailleCase, tailleCase));
    }
    /**
     * Méthode qui dessine la case.
     *
     * @param g L'objet Graphics utilisé pour dessiner la case.
     */

    public void paintComponent(Graphics g) {
        super.paint(g);
        if(this.caseID.isEmplacementBat()){
            if(this.caseID.isOccupee()){
                g.setColor(Color.RED);
            }
            else{
                g.setColor(Color.YELLOW);
            }
        }
        else{
            //Couleur transparante
            Color c = new Color(0,0,1,1);
            g.setColor(c);
        }
        g.fillRect(this.caseID.getX()*tailleCase, this.caseID.getY()*tailleCase , tailleCase, tailleCase);
    }
    /**
     * Méthode appelée lorsqu'un objet observé notifie un changement d'état.
     * La méthode redessine la case.
     *
     * @param o L'objet observé.
     * @param arg L'argument passé lors de la notification.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
