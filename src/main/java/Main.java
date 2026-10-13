import controlador.InscripcionController;
import modelo.Inscripcion;
import vista.VentanaInscripcion;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inscripcion modelo = new Inscripcion();
            VentanaInscripcion vista = new VentanaInscripcion();
            new InscripcionController(modelo, vista).iniciar();
        });
    }
}
