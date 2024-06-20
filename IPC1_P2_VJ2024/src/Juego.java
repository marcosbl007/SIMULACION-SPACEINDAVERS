import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Juego extends JFrame {

    private JPanel bluePanel;
    private JLayeredPane layeredPane;
    private Nave nave;
    private Enemigos enemigos;
    private ItemManager itemManager;
    private JLabel timeValueLabel;
    private JLabel scoreValueLabel;
    private Timer timer;
    private int timeRemaining;
    private int puntaje;

    public Juego() {
        this(null);
    }

    public Juego(GameState estadoJuego) {
        // Configurar el JFrame
        setTitle("Space Invaders");
        setSize(1010, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);  // Usar layout nulo para posicionar los paneles manualmente

        // Crear el panel azul
        bluePanel = new JPanel();
        bluePanel.setBackground(new Color(25, 107, 189));
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

        if (estadoJuego == null) {
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

            timeRemaining = 90; // Tiempo inicial
            puntaje = 0; // Puntaje inicial
        } else {
            // Restaurar el estado del juego
            timeRemaining = estadoJuego.getTimeRemaining();
            puntaje = estadoJuego.getPuntaje();
            scoreValueLabel.setText(String.valueOf(puntaje));
            timeValueLabel.setText(String.valueOf(timeRemaining));

            // Restaurar enemigos
            enemigos = new Enemigos(layeredPane, estadoJuego.getEnemigos());
            for (JLabel enemigo : enemigos.getEnemigos()) {
                layeredPane.add(enemigo, JLayeredPane.PALETTE_LAYER);
            }

            // Restaurar ítems
            itemManager = new ItemManager(layeredPane, estadoJuego.getItems());
            itemManager.start();

            // Restaurar nave
            nave = new Nave(layeredPane, enemigos, itemManager, this, estadoJuego.getNave());
            // Asegurarse de que la nave tenga el icono configurado
            ImageIcon naveIcon = new ImageIcon(getClass().getResource("/imgs/player.png"));
            Image NaveImage = naveIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT); // Aumentar tamaño de los enemigos
            naveIcon = new ImageIcon(NaveImage);
            nave.getLabel().setIcon(naveIcon);
            layeredPane.add(nave.getLabel(), JLayeredPane.PALETTE_LAYER); // Añadir la nave restaurada al panel
        }

        // Añadir KeyListener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    regresarAlInicio();
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    serializarPartida();
                    regresarSerializar();
                    mostrarAlerta();
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
        // Abrir la pantalla de inicio
        GameOver vtn_Inicio = new GameOver(puntaje);
        vtn_Inicio.setVisible(true);
    }

    public void regresarSerializar() {
        finalizarJuego();
        // Cerrar la ventana actual
        dispose();
        // Abrir la pantalla de inicio
        InterfazInicio vtn_Inicio = new InterfazInicio();
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

    private void serializarPartida() {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_dd_MM_yyyy");
            String formattedDateTime = now.format(formatter);
            File dir = new File("Juegos");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filePath = Paths.get(dir.getAbsolutePath(), formattedDateTime + ".bin").toString();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(new GameState(timeRemaining, puntaje, enemigos, nave, itemManager));
                System.out.println("Se ha serializado tu partida.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta() {
        JOptionPane.showMessageDialog(this, "Tu partida ha sido guardada.", "Partida Guardada", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new InterfazInicio();
    }
}