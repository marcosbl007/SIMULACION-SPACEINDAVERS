import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Enemigos extends Thread {

    private ArrayList<JLabel> enemigos;
    private HashMap<JLabel, Integer> impactosRestantes;
    private HashMap<JLabel, Integer> puntosEnemigos;
    private JLayeredPane panel;
    private int dy = 2;  // Velocidad vertical
    private boolean running = true;  // Bandera de ejecución
    
    public Enemigos(JLayeredPane panel) {
        this.panel = panel;
        enemigos = new ArrayList<>();
        impactosRestantes = new HashMap<>();
        puntosEnemigos = new HashMap<>();
        crearEnemigos();
    }
    
    private void crearEnemigos() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                
                ImageIcon enemigoIcon;
                int impactos = 2; // Por defecto, los enemigos de la primera columna necesitan 2 impactos
                int puntos = 10;  // Puntos para el tipo de enemigo 1
    
                if (j == 0) {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo1.png"));
                    impactos = 2;  // Enemigos de la columna 1
                    puntos = 10;
                } else if (j == 1 || j == 2) {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo2.png"));
                    impactos = 3;  // Enemigos de las columnas 2 y 3
                    puntos = 20;
                } else {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo3.png"));
                    impactos = 4;  // Enemigos de las columnas 4 y 5
                    puntos = 30;
                }
    
                Image enemigoImage = enemigoIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); // Aumentar tamaño de los enemigos
                enemigoIcon = new ImageIcon(enemigoImage);
                
                JLabel enemigo = new JLabel(enemigoIcon);
                enemigo.setBounds(700 + j * 50, 50 + i * 50, 40, 40); // Ajustar la posición y tamaño de los enemigos
                enemigos.add(enemigo);
                impactosRestantes.put(enemigo, impactos);
                puntosEnemigos.put(enemigo, puntos);
            }
        }
    }
    
    public ArrayList<JLabel> getEnemigos() {
        return enemigos;
    }
    
    public int getImpactosRestantes(JLabel enemigo) {
        return impactosRestantes.get(enemigo);
    }
    
    public void reducirImpactos(JLabel enemigo) {
        impactosRestantes.put(enemigo, impactosRestantes.get(enemigo) - 1);
    }
    
    public int getPuntos(JLabel enemigo) {
        return puntosEnemigos.get(enemigo);
    }
    
    public void eliminarEnemigo(JLabel enemigo) {
        enemigos.remove(enemigo);
        impactosRestantes.remove(enemigo);
        puntosEnemigos.remove(enemigo);
        panel.remove(enemigo);
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
    
    public void finalizar() {
        running = false;
    }
    
    @Override
    public void run() {
        while (running) {
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
