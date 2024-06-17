import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Juego extends JFrame {

    private JPanel bluePanel;
    private JLayeredPane layeredPane;
    private Nave nave;
    private Enemigos enemigos;
    private ItemManager itemManager;
    private JLabel timeValueLabel;
    private JLabel scoreValueLabel;
    private Timer timer;
    private int timeRemaining = 90;
    private int puntaje = 0;  // Atributo para almacenar el puntaje

    public Juego() {
        // Configurar el JFrame
        setTitle("Space Invaders");
        setSize(1010, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);  // Usar layout nulo para posicionar los paneles manualmente

        // Crear el panel azul
        bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(0, 0, 1000, 100);  // Ajustar el tamaño y la posición del panel azul
        bluePanel.setLayout(null);

        // Añadir elementos al panel azul
        // Cargar imagen de moneda
        ImageIcon coinIcon = new ImageIcon(getClass().getResource("/imgs/coin.gif"));
        Image imageCoin = coinIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        coinIcon = new ImageIcon(imageCoin);
        JLabel coinLabel = new JLabel(coinIcon);
        coinLabel.setBounds(215, 20, 50, 50);  // Ajustar el tamaño y la posición de la moneda
        bluePanel.add(coinLabel);

        // Añadir label de puntaje
        JLabel scoreLabel = new JLabel("SCORE");
        scoreLabel.setForeground(Color.RED);
        scoreLabel.setFont(new Font("DePixel", Font.BOLD, 13));
        scoreLabel.setBounds(280, 30, 120, 20);
        bluePanel.add(scoreLabel);

        // Añadir label del puntaje
        scoreValueLabel = new JLabel("0");
        scoreValueLabel.setForeground(Color.WHITE);
        scoreValueLabel.setFont(new Font("DePixel", Font.BOLD, 13));
        scoreValueLabel.setBounds(290, 50, 130, 20);
        bluePanel.add(scoreValueLabel);

        // Cargar imagen del reloj
        ImageIcon clockIcon = new ImageIcon(getClass().getResource("/imgs/relojtiempo.png"));
        Image clockimage = clockIcon.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);
        clockIcon = new ImageIcon(clockimage);
        JLabel clockLabel = new JLabel(clockIcon);
        clockLabel.setBounds(585, 20, 50, 50);  // Ajustar el tamaño y la posición del reloj
        bluePanel.add(clockLabel);

        // Añadir label de tiempo
        JLabel timeLabel = new JLabel("TIME (S)");
        timeLabel.setForeground(Color.CYAN);
        timeLabel.setFont(new Font("DePixel", Font.BOLD, 13));
        timeLabel.setBounds(650, 30, 170, 20);
        bluePanel.add(timeLabel);

        // Añadir label del tiempo
        timeValueLabel = new JLabel("90");
        timeValueLabel.setForeground(Color.WHITE);
        timeValueLabel.setFont(new Font("DePixel", Font.BOLD, 13));
        timeValueLabel.setBounds(685, 50, 50, 20);
        bluePanel.add(timeValueLabel);

        // Crear el JLayeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 100, 1000, 500);

        // Crear el panel del GIF
        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/imgs/gifEstrella.gif"));
        Image image = gifIcon.getImage().getScaledInstance(1000, 500, Image.SCALE_DEFAULT);
        gifIcon = new ImageIcon(image);
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setBounds(0, 0, 1000, 500);
        layeredPane.add(gifLabel, JLayeredPane.DEFAULT_LAYER);

        // Añadir los paneles al JFrame
        add(bluePanel);
        add(layeredPane);

        // Crear y añadir los enemigos
        enemigos = new Enemigos(layeredPane);
        for (JLabel enemigo : enemigos.getEnemigos()) {
            layeredPane.add(enemigo, JLayeredPane.PALETTE_LAYER);
        }

        // Crear y añadir el gestor de ítems
        itemManager = new ItemManager(layeredPane);
        itemManager.start();

        // Crear y añadir la nave
        nave = new Nave(layeredPane, enemigos, itemManager, this);
        layeredPane.add(nave.getLabel(), JLayeredPane.PALETTE_LAYER);

        // Añadir KeyListener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    regresarAlInicio();
                }
            }
        });
        addKeyListener(nave);
        setFocusable(true);

        // Inicializar el timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timeValueLabel.setText(String.valueOf(timeRemaining));
                if (timeRemaining <= 0) {
                    regresarAlInicio();
                }
            }
        });
        timer.start();

        // Hacer visible la ventana
        setVisible(true);

        // Iniciar hilos
        nave.start();
        enemigos.start();
    }

    public void regresarAlInicio() {
        finalizarJuego();
        // Cerrar la ventana actual
        dispose();
        // Abrir la pantalla de Game Over y pasar el puntaje actual
        GameOver vtn_Inicio = new GameOver(puntaje);
        vtn_Inicio.setVisible(true);
    }

    public void verificarEstadoJuego() {
        if (enemigos.getEnemigos().isEmpty()) {
            regresarAlInicio();
        }
    }

    public void finalizarJuego() {
        nave.finalizar();
        enemigos.finalizar();
        itemManager.finalizar();
        timer.stop();
    }

    public void sumarPuntos(int puntos) {
        puntaje += puntos;
        scoreValueLabel.setText(String.valueOf(puntaje));
    }

    public void restarPuntos(int puntos) {
        puntaje -= puntos;
        scoreValueLabel.setText(String.valueOf(puntaje));
    }

    public void aumentarTiempo(int segundos) {
        timeRemaining += segundos;
        timeValueLabel.setText(String.valueOf(timeRemaining));
    }

    public void reducirTiempo(int segundos) {
        timeRemaining -= segundos;
        timeValueLabel.setText(String.valueOf(timeRemaining));
    }
}

