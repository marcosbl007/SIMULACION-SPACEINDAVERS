import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JFrame implements ActionListener {

    private JButton buttonInicio;
    private JLabel scoreValueLabel;
    private JTextField nicknameField;
    private int puntaje;
    
    public GameOver(int puntaje) {
        this.puntaje = puntaje;
    
        // Configurar el JFrame
        setTitle("Interfaz con fondo GIF");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        // Cargar el GIF
        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/imgs/gifLogin.gif"));
    
        // Redimensionar el GIF
        Image image = gifIcon.getImage().getScaledInstance(500, 650, Image.SCALE_DEFAULT);
        gifIcon = new ImageIcon(image);
    
        // Crear un JLabel con el GIF redimensionado
        JLabel gifLabel = new JLabel(gifIcon);
    
        JLabel titleLabel = new JLabel("SPACE");
        titleLabel.setFont(new Font("Games Italic", Font.BOLD, 80));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(107, 40, 290, 70);
        titleLabel.setOpaque(false);
    
        JLabel titleLabel2 = new JLabel("INVADERS");
        titleLabel2.setFont(new Font("Games Italic", Font.BOLD, 80));
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setBounds(50, 110, 400, 70);
        titleLabel2.setOpaque(false);
    
        // Crear el botón
        buttonInicio = new JButton("OK!");
        buttonInicio.setBounds(90, 400, 300, 60);  // Posición y tamaño del botón
        buttonInicio.setContentAreaFilled(false);
        buttonInicio.setOpaque(false);
        buttonInicio.setBorderPainted(true);
        buttonInicio.setFocusPainted(false);
        buttonInicio.setFont(new Font("DePixel", Font.BOLD, 17));
        buttonInicio.setForeground(Color.WHITE);
        buttonInicio.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonInicio.addActionListener(this);
    
        // Crear el JLabel "GAME OVER"
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("DePixel", Font.BOLD, 27));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(40, 250, 430, 50);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        // Crear el JLabel para el apodo
        JLabel nicknameLabel = new JLabel("Nombre");
        nicknameLabel.setFont(new Font("DePixel", Font.BOLD, 17));
        nicknameLabel.setForeground(Color.WHITE);
        nicknameLabel.setBounds(40, 235, 250, 30);
    
        // Crear el JTextField para ingresar el apodo
        nicknameField = new JTextField();
        nicknameField.setFont(new Font("DePixel", Font.BOLD, 12));
        nicknameField.setBounds(250, 235, 200, 30);
    
        // Crear el JLabel para el puntaje
        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(new Font("DePixel", Font.BOLD, 17));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(65, 320, 200, 30);
    
        // Crear el JLabel para mostrar el puntaje
        scoreValueLabel = new JLabel(String.valueOf(puntaje)); // Establecer el puntaje pasado
        scoreValueLabel.setFont(new Font("DePixel", Font.BOLD, 17));
        scoreValueLabel.setForeground(Color.WHITE);
        scoreValueLabel.setBounds(230, 320, 250, 30);
    
        // Cargar el GIF de la moneda
        ImageIcon coinIcon = new ImageIcon(getClass().getResource("/imgs/coin.gif"));
        Image imageCoin = coinIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        coinIcon = new ImageIcon(imageCoin);
        JLabel coinLabel = new JLabel(coinIcon);
        coinLabel.setBounds(380, 320, 30, 30); // Ajustar según sea necesario
    
        // Crear un JLayeredPane para superponer el botón sobre el GIF
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 650));
    
        // Añadir el JLabel y el botón al JLayeredPane
        gifLabel.setBounds(0, 0, 500, 650);
        layeredPane.add(gifLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonInicio, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(titleLabel2, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(gameOverLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(nicknameLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(nicknameField, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(scoreLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(scoreValueLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(coinLabel, JLayeredPane.PALETTE_LAYER);
    
        // Añadir el JLayeredPane al JFrame
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
    
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
    
        // Hacer visible la ventana
        setVisible(true);
    
        // Crear un Timer para ocultar el "GAME OVER" después de 2 segundos
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverLabel.setVisible(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonInicio) {
            String nickname = nicknameField.getText();
            ScoreManager.addScore(nickname, puntaje);
            InterfazInicio vtn_Inicio = new InterfazInicio();
            vtn_Inicio.setVisible(true);
            this.dispose();
        }
    }
}
