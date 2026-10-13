package modelo;

import java.util.ArrayList;
import java.util.List;

/** Entidad principal del modelo: administra cursos disponibles e inscritos. */
public class Inscripcion {
    private final List<Curso> disponibles = new ArrayList<>();
    private final List<Curso> inscritos = new ArrayList<>();

    public Inscripcion() {
        disponibles.add(new Curso("Programación en Java", 1800));
        disponibles.add(new Curso("Bases de Datos", 1500));
        disponibles.add(new Curso("Desarrollo Web", 1650));
        disponibles.add(new Curso("Redes de Computadoras", 1400));
        disponibles.add(new Curso("Inglés Técnico", 900));
        disponibles.add(new Curso("Estructuras de Datos", 1750));
        disponibles.add(new Curso("Ingeniería de Software", 2000));
    }

    public List<Curso> getDisponibles() { return new ArrayList<>(disponibles); }
    public List<Curso> getInscritos() { return new ArrayList<>(inscritos); }

    /** Quita el curso de la lista de disponibles y lo agrega a los inscritos. */
    public void inscribir(Curso curso) {
        if (disponibles.remove(curso)) {
            inscritos.add(curso);
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Curso c : inscritos) total += c.getCosto();
        return total;
    }

    public FichaPago generarFichaPago() {
        return new FichaPago(inscritos, calcularTotal());
    }
}
