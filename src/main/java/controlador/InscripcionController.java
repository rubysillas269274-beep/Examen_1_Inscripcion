package controlador;

import modelo.Curso;
import modelo.FichaPago;
import modelo.Inscripcion;
import vista.VentanaInscripcion;

/** Controlador: coordina la vista y el modelo. */
public class InscripcionController {
    private final Inscripcion modelo;
    private final VentanaInscripcion vista;

    public InscripcionController(Inscripcion modelo, VentanaInscripcion vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    /** Muestra la lista de cursos y los inscritos (ninguno al arranque). */
    public void iniciar() {
        actualizarVista();
        vista.setVisible(true);
    }

    public void inscribirCurso(Curso curso) {
        if (curso == null) {
            vista.mostrarMensaje("Seleccione un curso de la lista.");
            return;
        }
        modelo.inscribir(curso);
        actualizarVista();
    }

    public void finalizarInscripcion() {
        if (modelo.getInscritos().isEmpty()) {
            vista.mostrarMensaje("Debe inscribir al menos un curso.");
            return;
        }
        FichaPago ficha = modelo.generarFichaPago();
        vista.mostrarFicha(ficha);
    }

    private void actualizarVista() {
        vista.mostrarListas(modelo.getDisponibles(), modelo.getInscritos(), modelo.calcularTotal());
    }
}
