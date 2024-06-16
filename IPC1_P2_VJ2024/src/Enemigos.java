import javax.swing.*;

import java.awt.Image;
import java.util.ArrayList;

public class Enemigos extends Thread {

    private ArrayList<JLabel> enemigos;
    private JLayeredPane panel;
    private int dy = 2;  // Velocidad vertical
    private boolean movingDown = true;  // Indica si los enemigos se están moviendo hacia abajo

    public Enemigos(JLayeredPane panel) {
        this.panel = panel;
        enemigos = new ArrayList<>();
        crearEnemigos();
    }

    private void crearEnemigos() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                
                ImageIcon enemigoIcon;
                if (j == 0) {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo1.png"));
                } else if (j == 1 || j == 2) {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo2.png"));
                } else {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo3.png"));
                }
    
                Image enemigoImage = enemigoIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); // Aumentar tamaño de los enemigos
                enemigoIcon = new ImageIcon(enemigoImage);
                
                JLabel enemigo = new JLabel(enemigoIcon);
                enemigo.setBounds(700 + j * 50, 50 + i * 50, 40, 40); // Ajustar la posición y tamaño de los enemigos
                enemigos.add(enemigo);

            }
        }
    }

    public ArrayList<JLabel> getEnemigos() {
        return enemigos;
    }

    public void moverEnemigos() {
        for (JLabel enemigo : enemigos) {
            enemigo.setLocation(enemigo.getX(), enemigo.getY() + dy);
            if (enemigo.getY() >= panel.getHeight() - 65 || enemigo.getY() <= 0) {
                dy = -dy;  // Cambiar de dirección
                for (JLabel en : enemigos) {
                    en.setLocation(en.getX() - 40, en.getY());  // Mover los enemigos a la izquierda
                }
                break;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            moverEnemigos();
            panel.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}