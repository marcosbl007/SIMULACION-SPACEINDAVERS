import javax.swing.*;

public class Enemigo {

    private JLabel label;
    private int x, y;
    private int hitsRequired;
    private int hitsTaken = 0;
    
    public Enemigo(ImageIcon icon, int x, int y, int hitsRequired) {
        this.x = x;
        this.y = y;
        this.hitsRequired = hitsRequired;
        label = new JLabel(icon);
        label.setBounds(x, y, 60, 60);
    }
    
    public JLabel getLabel() {
        return label;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getHitsRequired() {
        return hitsRequired;
    }
    
    public int getHitsTaken() {
        return hitsTaken;
    }
    
    public void takeHit() {
        hitsTaken++;
    }
    
    public boolean isDestroyed() {
        return hitsTaken >= hitsRequired;
    }
    
    public void setIcon(ImageIcon icon) {
        label.setIcon(icon);
    }
}
