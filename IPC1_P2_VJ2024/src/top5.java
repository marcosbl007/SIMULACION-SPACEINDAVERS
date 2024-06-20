import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class top5 extends JFrame implements ActionListener {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private JButton buttonInicio;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public top5() {

        setTitle("Interfaz con fondo GIF");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/imgs/gifLogin.gif"));
        Image image = gifIcon.getImage().getScaledInstance(500, 650, Image.SCALE_DEFAULT);
        gifIcon = new ImageIcon(image);
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
    
        buttonInicio = new JButton("< REGRESAR");
        buttonInicio.setBounds(0, 0, 250, 70);
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
        trophyLabel.setBounds(70, 230, 350, 350);

        JLabel[] topLabels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            topLabels[i] = new JLabel("-");
            topLabels[i].setFont(new Font("DePixel", Font.BOLD, 20));
            topLabels[i].setForeground(Color.WHITE);
            topLabels[i].setBounds(20, 270 + i * 60, 450, 50);
            topLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
    
        ArrayList<ControlScores.Score> scores = ControlScores.getScores();
        for (int i = 0; i < scores.size() && i < topLabels.length; i++) {
            topLabels[i].setText(scores.get(i).toString());
        }

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 650));
    
        gifLabel.setBounds(0, 0, 500, 650);
        layeredPane.add(gifLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(trophyLabel, JLayeredPane.PALETTE_LAYER);
        for (JLabel topLabel : topLabels) {
            layeredPane.add(topLabel, JLayeredPane.MODAL_LAYER);
        }
        layeredPane.add(buttonInicio, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(titleLabel2, JLayeredPane.PALETTE_LAYER);
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
    
        this.setResizable(false);  
        this.setLocationRelativeTo(null);
        this.setUndecorated(true); 
        setVisible(true);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonInicio) {
            InterfazInicio vtn_Inicio = new InterfazInicio();
            this.dispose();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
