import javax.swing.SwingUtilities;

public class Main {
   public static void main(String[] args) {
        // Ejecutar la aplicación en el hilo de despacho de eventos
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazInicio();
            }
        });
    }
}


