package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** Entidad: ficha de pago generada al finalizar la inscripción. */
public class FichaPago {
    private final List<Curso> cursos;
    private final double total;
    private final LocalDateTime fecha;

    public FichaPago(List<Curso> cursos, double total) {
        this.cursos = new ArrayList<>(cursos);
        this.total = total;
        this.fecha = LocalDateTime.now();
    }

    public List<Curso> getCursos() { return cursos; }
    public double getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }

    /** Representación en texto de la ficha para mostrarla en pantalla. */
    public String toTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== FICHA DE PAGO ==========\n");
        sb.append("Fecha: ").append(fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n\n");
        sb.append(String.format("%-28s %12s%n", "Curso", "Costo"));
        sb.append("-----------------------------------------\n");
        for (Curso c : cursos) {
            sb.append(String.format("%-28s %12s%n", c.getNombre(), String.format("$%,.2f", c.getCosto())));
        }
        sb.append("-----------------------------------------\n");
        sb.append(String.format("%-28s %12s%n", "TOTAL A PAGAR", String.format("$%,.2f", total)));
        return sb.toString();
    }
}
