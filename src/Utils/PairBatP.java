/**
 La classe PairBatP représente une paire (Point, Batiment).
 */
package Utils;
import Model.Batiment;

import java.awt.*;

/**
 * Constructeur de la classe PairBatP.
 * @param  point point associé à la paire.
 * @param bat le bâtiment associé à la paire.
 */

public class PairBatP {
    private Batiment batiment;
    private Point point;

    public PairBatP(Point result, Batiment bat){
        this.point = result;
        this.batiment = bat;
    }

    //Guetters:
    public Point getPoint() {
        return point;
    }

    public Batiment getBatiment() {
        return batiment;
    }
}
