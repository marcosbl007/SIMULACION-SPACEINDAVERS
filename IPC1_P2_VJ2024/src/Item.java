import javax.swing.*;
import java.awt.*;

public class Item extends JLabel implements Runnable {

    public enum ItemType {
        AUMENTO_TIEMPO,
        PUNTOS_EXTRA,
        DISMINUCION_TIEMPO,
        PENALIZACION
    }

    private ItemType type;
    private int speed;

    public Item(ItemType type, int x, int y, int speed) {
        this.type = type;
        this.speed = speed;
        setBounds(x, y, 30, 30);  // Tamaño del hitbox de los ítems

        // Asignar icono basado en el tipo de ítem
        switch (type) {
            case AUMENTO_TIEMPO:
                setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/relojboni.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
                break;
            case PUNTOS_EXTRA:
                setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/moneda.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
                break;
            case DISMINUCION_TIEMPO:
                setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/calaca.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
                break;
            case PENALIZACION:
                setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/deadpool.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
                break;
        }
    }

    public ItemType getType() {
        return type;
    }

    public void mover() {
        setLocation(getX() - speed, getY());
    }

    @Override
    public void run() {
        while (getX() > -50) {
            mover();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}