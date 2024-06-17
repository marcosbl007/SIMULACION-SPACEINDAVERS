import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazInicio extends JFrame implements ActionListener {

    private JButton buttonInicio, buttonCargar, buttonTOP5, buttonExit;

    public InterfazInicio() {
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
        buttonInicio = new JButton("NUEVO JUEGO");
        buttonInicio.setBounds(78, 300, 350, 70);  // Posición y tamaño del botón
        buttonInicio.setContentAreaFilled(false);
        buttonInicio.setOpaque(false);
        buttonInicio.setBorderPainted(true);
        buttonInicio.setFocusPainted(false);
        buttonInicio.setFont(new Font("DePixel", Font.BOLD, 17));
        buttonInicio.setForeground(Color.WHITE);
        buttonInicio.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonInicio.addActionListener(this);


        buttonCargar = new JButton("CARGAR JUEGO");
        buttonCargar.setBounds(78, 380, 350, 70);  // Posición y tamaño del botón
        buttonCargar.setContentAreaFilled(false);
        buttonCargar.setOpaque(false);
        buttonCargar.setBorderPainted(true);
        buttonCargar.setFocusPainted(false);
        buttonCargar.setFont(new Font("DePixel", Font.BOLD, 17));
        buttonCargar.setForeground(Color.WHITE);
        buttonCargar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonCargar.addActionListener(this);


        buttonTOP5 = new JButton("TOP 5");
        buttonTOP5.setBounds(78, 460, 350, 70);  // Posición y tamaño del botón
        buttonTOP5.setContentAreaFilled(false);
        buttonTOP5.setOpaque(false);
        buttonTOP5.setBorderPainted(true);
        buttonTOP5.setFocusPainted(false);
        buttonTOP5.setFont(new Font("DePixel", Font.BOLD, 17));
        buttonTOP5.setForeground(Color.WHITE);
        buttonTOP5.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonTOP5.addActionListener(this);


        buttonExit = new JButton("SALIR");
        buttonExit.setBounds(78, 540, 350, 70);  // Posición y tamaño del botón
        buttonExit.setContentAreaFilled(false);
        buttonExit.setOpaque(false);
        buttonExit.setBorderPainted(true);
        buttonExit.setFocusPainted(false);
        buttonExit.setFont(new Font("DePixel", Font.BOLD, 17));
        buttonExit.setForeground(Color.WHITE);
        buttonExit.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonExit.addActionListener(this);


        // Crear un JLayeredPane para superponer el botón sobre el GIF
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 650));

        // Añadir el JLabel y el botón al JLayeredPane
        gifLabel.setBounds(0, 0, 500, 650);
        layeredPane.add(gifLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonInicio, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonCargar, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonTOP5, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonExit, JLayeredPane.PALETTE_LAYER);
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
            Juego vtn_Juego = new Juego();
            this.dispose();
        }else if (e.getSource() == buttonCargar) {
            CargarJuego vtnCargarJuego = new CargarJuego();
            this.dispose();
        }else if (e.getSource() == buttonTOP5) {
            top5 vtn_top5 = new top5();
            this.dispose();
        }else if (e.getSource() == buttonExit) {

        int option = JOptionPane.showConfirmDialog(this, "Esta seguro que desea salir?", "Exit", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            this.dispose();
                }
        }
    }
}