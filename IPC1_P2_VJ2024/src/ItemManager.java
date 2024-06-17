import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class ItemManager extends Thread {

    private JLayeredPane panel;
    private ArrayList<Item> items;
    private Random random;
    private boolean running = true;  // Bandera de ejecución
    
    public ItemManager(JLayeredPane panel) {
        this.panel = panel;
        items = new ArrayList<>();
        random = new Random();
    }
    
    public void crearItem() {
        int x = panel.getWidth();
        int y = random.nextInt(panel.getHeight() - 50) + 50; // Evitar que aparezcan en la parte azul
        int speed = random.nextInt(3) + 1;
    
        Item.ItemType[] types = Item.ItemType.values();
        Item item = new Item(types[random.nextInt(types.length)], x, y, speed);
    
        items.add(item);
        panel.add(item, JLayeredPane.PALETTE_LAYER);
        new Thread(item).start();
    }
    
    @Override
    public void run() {
        while (running) {
            crearItem();
            try {
                Thread.sleep(6000); // Aparición cada 3 segundos, ajustable según necesidad
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
    
    public void finalizar() {
        running = false;
        for (Item item : items) {
            item.finalizar();  // Llamar al método finalizar() en lugar de stop()
        }
    }
}
