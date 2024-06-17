import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class top5 extends JFrame implements ActionListener {
    
    private JButton buttonInicio;

    public top5() {
        // Configurar el JFrame
        setTitle("Interfaz con fondo GIF");
        setSize(500, 650);
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
        titleLabel.setBounds(107, 80, 290, 70);
        titleLabel.setOpaque(false);
        
        JLabel titleLabel2 = new JLabel("INVADERS");
        titleLabel2.setFont(new Font("Games Italic", Font.BOLD, 80));
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setBounds(50, 150, 400, 70);
        titleLabel2.setOpaque(false);
    
        // Crear el botón
        buttonInicio = new JButton("< REGRESAR");
        buttonInicio.setBounds(0, 0, 250, 70);  // Posición y tamaño del botón
        buttonInicio.setContentAreaFilled(false);
        buttonInicio.setOpaque(false);
        buttonInicio.setBorderPainted(false);
        buttonInicio.setFocusPainted(false);
        buttonInicio.setBorder(BorderFactory.createEmptyBorder());
        buttonInicio.setFont(new Font("DePixel", Font.BOLD, 13));
        buttonInicio.setForeground(Color.WHITE);
        buttonInicio.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonInicio.addActionListener(this);
    
        ImageIcon trophyIcon = new ImageIcon(getClass().getResource("/imgs/trofeo.png"));
        Image trophyImage = trophyIcon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        trophyIcon = new ImageIcon(trophyImage);
        JLabel trophyLabel = new JLabel(trophyIcon);
        trophyLabel.setBounds(70, 230, 350, 350);  // Ajustar el tamaño y la posición del trofeo
    
        // Crear los labels transparentes para los top scores
        JLabel[] topLabels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            topLabels[i] = new JLabel("-");
            topLabels[i].setFont(new Font("DePixel", Font.BOLD, 20));
            topLabels[i].setForeground(Color.WHITE);
            topLabels[i].setBounds(20, 270 + i * 60, 450, 50);
            topLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
    
        // Obtener y mostrar los puntajes más altos
        ArrayList<ScoreManager.Score> scores = ScoreManager.getScores();
        for (int i = 0; i < scores.size() && i < topLabels.length; i++) {
            topLabels[i].setText(scores.get(i).toString());
        }
    
        // Crear un JLayeredPane para superponer el botón sobre el GIF
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 650));
    
        // Añadir el JLabel y el botón al JLayeredPane
        gifLabel.setBounds(0, 0, 500, 650);
        layeredPane.add(gifLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(trophyLabel, JLayeredPane.PALETTE_LAYER); // Nivel de capa para el trofeo
        for (JLabel topLabel : topLabels) {
            layeredPane.add(topLabel, JLayeredPane.MODAL_LAYER); // Añadir los labels de los top scores
        }
        layeredPane.add(buttonInicio, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(titleLabel2, JLayeredPane.PALETTE_LAYER);
    
        // Añadir el JLayeredPane al JFrame
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
    
        this.setResizable(false);  
        this.setLocationRelativeTo(null);
        this.setUndecorated(true); 
    
        // Hacer visible la ventana
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonInicio) {
            InterfazInicio vtn_Juego = new InterfazInicio();
            this.dispose();
        }
    }
    
    public static void main(String[] args) {
        new InterfazInicio();
    }
}

