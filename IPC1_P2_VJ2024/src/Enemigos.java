import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Image;
import java.io.Serializable;

public class Enemigos extends Thread implements Serializable {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final long serialVersionUID = 1L;
    private ArrayList<JLabel> enemigos;
    private HashMap<JLabel, Integer> impactosRestantes;
    private HashMap<JLabel, Integer> puntosEnemigos;
    private JLayeredPane panel;
    private int dy = 2;
    private boolean running = true;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Enemigos(JLayeredPane panel) {
        this.panel = panel;
        enemigos = new ArrayList<>();
        impactosRestantes = new HashMap<>();
        puntosEnemigos = new HashMap<>();
        crearEnemigos();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Enemigos(JLayeredPane panel, ArrayList<JLabel> enemigos, HashMap<JLabel, Integer> puntosEnemigos) {
        this.panel = panel;
        this.enemigos = enemigos;
        this.puntosEnemigos = puntosEnemigos != null ? puntosEnemigos : new HashMap<>();
        impactosRestantes = new HashMap<>();
        for (JLabel enemigo : enemigos) {
            impactosRestantes.put(enemigo, 2);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void crearEnemigos() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {

                ImageIcon enemigoIcon;
                int impactos = 2;
                int puntos = 10; 

                if (j == 0) {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo1.png"));
                    impactos = 2; 
                    puntos = 10;
                } else if (j == 1 || j == 2) {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo2.png"));
                    impactos = 3; 
                    puntos = 20;
                } else {
                    enemigoIcon = new ImageIcon(getClass().getResource("/imgs/enemigo3.png"));
                    impactos = 4; 
                    puntos = 30;
                }

                Image enemigoImage = enemigoIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); 
                enemigoIcon = new ImageIcon(enemigoImage);

                JLabel enemigo = new JLabel(enemigoIcon);
                enemigo.setBounds(700 + j * 50, 50 + i * 50, 40, 40); 
                enemigos.add(enemigo);
                impactosRestantes.put(enemigo, impactos);
                puntosEnemigos.put(enemigo, puntos);
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<JLabel> getEnemigos() {
        return enemigos;
    }

    public int getImpactosRestantes(JLabel enemigo) {
        return impactosRestantes.getOrDefault(enemigo, 0);
    }

    public void reducirImpactos(JLabel enemigo) {
        impactosRestantes.put(enemigo, impactosRestantes.getOrDefault(enemigo, 1) - 1);
    }

    public int getPuntos(JLabel enemigo) {
        return puntosEnemigos.getOrDefault(enemigo, 0);
    }

    public void eliminarEnemigo(JLabel enemigo) {
        enemigos.remove(enemigo);
        impactosRestantes.remove(enemigo);
        puntosEnemigos.remove(enemigo);
        panel.remove(enemigo);
    }

    public HashMap<JLabel, Integer> getPuntosEnemigos() {
        return puntosEnemigos;
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
