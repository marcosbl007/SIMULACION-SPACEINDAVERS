import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Nave extends Thread implements KeyListener {

    private JLabel naveLabel;
    private JLayeredPane panel;
    private ArrayList<JLabel> disparos;
    private Enemigos enemigos;
    private ItemManager itemManager;
    private int naveY = 250;
    private boolean upPressed, downPressed, spacePressed;
    private Juego juego;
    private boolean running = true;
    
    public Nave(JLayeredPane panel, Enemigos enemigos, ItemManager itemManager, Juego juego) {
        this.panel = panel;
        this.enemigos = enemigos;
        this.itemManager = itemManager;
        this.juego = juego;
        disparos = new ArrayList<>();
    
        ImageIcon naveIcon = new ImageIcon(getClass().getResource("/imgs/player.png"));
        Image NaveImage = naveIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT); // Aumentar tamaño de los enemigos
        naveIcon = new ImageIcon(NaveImage);
        naveLabel = new JLabel(naveIcon);
        naveLabel.setBounds(50, naveY, 50, 50);
    }
    
    public Nave(JLayeredPane panel, Enemigos enemigos, ItemManager itemManager, Juego juego, JLabel nave) {
        this.panel = panel;
        this.enemigos = enemigos;
        this.itemManager = itemManager;
        this.juego = juego;
        disparos = new ArrayList<>();
        naveLabel = nave;
        naveY = nave.getY();
    }
    
    public JLabel getLabel() {
        return naveLabel;
    }
    
    private void detectarColisiones() {
        ArrayList<JLabel> disparosAEliminar = new ArrayList<>();
        ArrayList<JLabel> enemigosAEliminar = new ArrayList<>();
    
        for (JLabel disparo : disparos) {
            for (JLabel enemigo : enemigos.getEnemigos()) {
                if (disparo.getBounds().intersects(enemigo.getBounds())) {
                    enemigos.reducirImpactos(enemigo);
                    if (enemigos.getImpactosRestantes(enemigo) <= 0) {
                        mostrarExplosion(enemigo);
                        enemigosAEliminar.add(enemigo);
                        juego.sumarPuntos(enemigos.getPuntos(enemigo)); // Sumar puntos al eliminar el enemigo
                    }
                    disparosAEliminar.add(disparo);
                }
            }
        }
    
        disparos.removeAll(disparosAEliminar);
        for (JLabel disparo : disparosAEliminar) {
            panel.remove(disparo);
        }
    
        for (JLabel enemigo : enemigosAEliminar) {
            enemigos.eliminarEnemigo(enemigo);
        }
    
        // Verificar si el jugador ha eliminado a todos los enemigos
        juego.verificarEstadoJuego();
    }
    
    private void detectarColisionesConItems() {
        ArrayList<Item> itemsAEliminar = new ArrayList<>();
    
        for (Item item : itemManager.getItems()) {
            if (naveLabel.getBounds().intersects(item.getBounds())) {
                switch (item.getType()) {
                    case AUMENTO_TIEMPO:
                        juego.aumentarTiempo(10);
                        break;
                    case PUNTOS_EXTRA:
                        juego.sumarPuntos(10);
                        break;
                    case DISMINUCION_TIEMPO:
                        juego.reducirTiempo(10);
                        break;
                    case PENALIZACION:
                        juego.restarPuntos(10);
                        break;
                }
                itemsAEliminar.add(item);
            }
        }
    
        for (Item item : itemsAEliminar) {
            itemManager.getItems().remove(item);
            panel.remove(item);
        }
    }
    
    private void detectarColisionesConNave() {
        for (JLabel enemigo : enemigos.getEnemigos()) {
            if (naveLabel.getBounds().intersects(enemigo.getBounds())) {
                juego.regresarAlInicio();
                break;
            }
        }
    }
    
    private void mostrarExplosion(JLabel enemigo) {
        ImageIcon explosionIcon = new ImageIcon(getClass().getResource("/imgs/explosion.png"));
        Image explosionImage = explosionIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        explosionIcon = new ImageIcon(explosionImage);
    
        JLabel explosionLabel = new JLabel(explosionIcon);
        explosionLabel.setBounds(enemigo.getBounds());
        panel.add(explosionLabel, JLayeredPane.PALETTE_LAYER);
    
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(explosionLabel);
                panel.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    public void moverDisparos() {
        ArrayList<JLabel> disparosAEliminar = new ArrayList<>();
        for (JLabel disparo : disparos) {
            disparo.setLocation(disparo.getX() + 10, disparo.getY());
            if (disparo.getX() > 1000) {
                panel.remove(disparo);
                disparosAEliminar.add(disparo);
            }
        }
        disparos.removeAll(disparosAEliminar);
    }
    
    @Override
    public void run() {
        while (running) {
            if (upPressed && naveY > 0) {
                naveY -= 5;
                naveLabel.setLocation(50, naveY);
            }
            if (downPressed && naveY < 450) {
                naveY += 5;
                naveLabel.setLocation(50, naveY);
            }
            if (spacePressed) {
                ImageIcon balaIcon = new ImageIcon(getClass().getResource("/imgs/bala.png"));
                Image balaImage = balaIcon.getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT);
                balaIcon = new ImageIcon(balaImage);
                JLabel disparo = new JLabel(balaIcon);
                disparo.setBounds(100, naveY + 20, 20, 10);
                panel.add(disparo, JLayeredPane.PALETTE_LAYER);
                disparos.add(disparo);
                spacePressed = false;
            }
            moverDisparos();
            detectarColisiones();
            detectarColisionesConItems();  // Detectar colisiones con ítems
            detectarColisionesConNave();  // Detectar colisiones de la nave con los enemigos
            panel.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // No implementado
    }
    
    public void finalizar() {
        running = false;
    }
}
