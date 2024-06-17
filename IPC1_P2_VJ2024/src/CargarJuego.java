import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CargarJuego extends JFrame implements ActionListener {

    private JButton buttonInicio, buttonBuscarJuego, buttonCargarJuego;

    public CargarJuego() {
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
        titleLabel.setBounds(107, 100, 290, 70);
        titleLabel.setOpaque(false);
        
        JLabel titleLabel2 = new JLabel("INVADERS");
        titleLabel2.setFont(new Font("Games Italic", Font.BOLD, 80));
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setBounds(50, 170, 400, 70);
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
    
        buttonBuscarJuego = new JButton("BUSCAR JUEGO");
        buttonBuscarJuego.setBounds(75, 320, 350, 70);  // Posición y tamaño del botón
        buttonBuscarJuego.setContentAreaFilled(false);
        buttonBuscarJuego.setOpaque(false);
        buttonBuscarJuego.setBorderPainted(true);
        buttonBuscarJuego.setFocusPainted(false);
        buttonBuscarJuego.setFont(new Font("DePixel", Font.BOLD, 17));
        buttonBuscarJuego.setForeground(Color.WHITE);
        buttonBuscarJuego.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonBuscarJuego.addActionListener(this);
    
    
        buttonCargarJuego = new JButton("CARGAR JUEGO");
        buttonCargarJuego.setBounds(75, 440, 350, 70);  // Posición y tamaño del botón
        buttonCargarJuego.setContentAreaFilled(false);
        buttonCargarJuego.setOpaque(false);
        buttonCargarJuego.setBorderPainted(true);
        buttonCargarJuego.setFocusPainted(false);
        buttonCargarJuego.setFont(new Font("DePixel", Font.BOLD, 17));
        buttonCargarJuego.setForeground(Color.WHITE);
        buttonCargarJuego.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonCargarJuego.addActionListener(this);
    
    
        // Crear un JLayeredPane para superponer el botón sobre el GIF
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 650));
    
        // Añadir el JLabel y el botón al JLayeredPane
        gifLabel.setBounds(0, 0, 500, 650);
        layeredPane.add(gifLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonInicio, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonCargarJuego, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonBuscarJuego, JLayeredPane.PALETTE_LAYER);
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
