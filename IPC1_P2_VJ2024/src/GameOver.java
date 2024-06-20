import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JFrame implements ActionListener {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private JButton buttonInicio;
    private JLabel scoreValueLabel;
    private JTextField nicknameField;
    private int puntaje;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public GameOver(int puntaje) {
        this.puntaje = puntaje;
    
        setTitle("Interfaz con fondo GIF");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/imgs/gifLogin.gif"));
    
        Image image = gifIcon.getImage().getScaledInstance(500, 650, Image.SCALE_DEFAULT);
        gifIcon = new ImageIcon(image);
    
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
    
        buttonInicio = new JButton("OK!");
        buttonInicio.setBounds(90, 400, 300, 60); 
        buttonInicio.setContentAreaFilled(false);
        buttonInicio.setOpaque(false);
        buttonInicio.setBorderPainted(true);
        buttonInicio.setFocusPainted(false);
        buttonInicio.setFont(new Font("DePixel", Font.BOLD, 17));
        buttonInicio.setForeground(Color.WHITE);
        buttonInicio.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonInicio.addActionListener(this);
    
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("DePixel", Font.BOLD, 27));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(40, 250, 430, 50);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nicknameLabel = new JLabel("Nombre");
        nicknameLabel.setFont(new Font("DePixel", Font.BOLD, 17));
        nicknameLabel.setForeground(Color.WHITE);
        nicknameLabel.setBounds(40, 235, 250, 30);
    
        nicknameField = new JTextField();
        nicknameField.setFont(new Font("DePixel", Font.BOLD, 12));
        nicknameField.setBounds(250, 235, 200, 30);

        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(new Font("DePixel", Font.BOLD, 17));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(65, 320, 200, 30);

        scoreValueLabel = new JLabel(String.valueOf(puntaje));
        scoreValueLabel.setFont(new Font("DePixel", Font.BOLD, 17));
        scoreValueLabel.setForeground(Color.WHITE);
        scoreValueLabel.setBounds(230, 320, 250, 30);
    
        ImageIcon coinIcon = new ImageIcon(getClass().getResource("/imgs/coin.gif"));
        Image imageCoin = coinIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        coinIcon = new ImageIcon(imageCoin);
        JLabel coinLabel = new JLabel(coinIcon);
        coinLabel.setBounds(380, 320, 30, 30);
    
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 650));
    
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
    
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
    
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
    
        setVisible(true);
    
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverLabel.setVisible(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonInicio) {
            String nickname = nicknameField.getText();
            ControlScores.addScore(nickname, puntaje);
            InterfazInicio vtn_Inicio = new InterfazInicio();
            vtn_Inicio.setVisible(true);
            this.dispose();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
