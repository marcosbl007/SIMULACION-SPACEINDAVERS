import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Nave extends Thread implements KeyListener {

    private JLabel naveLabel;
    private JLayeredPane panel;
    private ArrayList<JLabel> disparos;
    private int naveY = 250;
    private boolean upPressed, downPressed, spacePressed;

    public Nave(JLayeredPane panel) {
        this.panel = panel;
        disparos = new ArrayList<>();

        ImageIcon naveIcon = new ImageIcon(getClass().getResource("/imgs/player.png"));
        Image NaveImage = naveIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT); // Aumentar tamaño de los enemigos
        naveIcon = new ImageIcon(NaveImage);
        naveLabel = new JLabel(naveIcon);
        naveLabel.setBounds(50, naveY, 50, 50);
    }

    public JLabel getLabel() {
        return naveLabel;
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
        while (true) {
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
}