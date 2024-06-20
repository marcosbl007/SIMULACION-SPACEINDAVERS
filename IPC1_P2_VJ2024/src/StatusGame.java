import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.HashMap;

public class StatusGame implements Serializable {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final long serialVersionUID = 1L;
    private int timeRemaining;
    private int puntaje;
    private ArrayList<JLabel> enemigos;
    private HashMap<JLabel, Integer> puntosEnemigos;
    private JLabel nave;
    private ArrayList<Item> items;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public StatusGame(int timeRemaining, int puntaje, Enemigos enemigos, PlayerNave nave, ControlItems itemManager) {
        this.timeRemaining = timeRemaining;
        this.puntaje = puntaje;
        this.enemigos = enemigos.getEnemigos();
        this.puntosEnemigos = enemigos.getPuntosEnemigos();
        this.nave = nave.getLabel();
        this.items = itemManager.getItems();
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getTimeRemaining() {
        return timeRemaining;
    }
    
    public int getPuntaje() {
        return puntaje;
    }
    
    public ArrayList<JLabel> getEnemigos() {
        return enemigos;
    }
    
    public HashMap<JLabel, Integer> getPuntosEnemigos() {
        return puntosEnemigos;
    }
    
    public JLabel getNave() {
        return nave;
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
