import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private int timeRemaining;
    private int puntaje;
    private ArrayList<JLabel> enemigos;
    private JLabel nave;
    private ArrayList<Item> items;

    public GameState(int timeRemaining, int puntaje, Enemigos enemigos, Nave nave, ItemManager itemManager) {
        this.timeRemaining = timeRemaining;
        this.puntaje = puntaje;
        this.enemigos = enemigos.getEnemigos();
        this.nave = nave.getLabel();
        this.items = itemManager.getItems();
    }
    
    public int getTimeRemaining() {
        return timeRemaining;
    }
    
    public int getPuntaje() {
        return puntaje;
    }
    
    public ArrayList<JLabel> getEnemigos() {
        return enemigos;
    }
    
    public JLabel getNave() {
        return nave;
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
}