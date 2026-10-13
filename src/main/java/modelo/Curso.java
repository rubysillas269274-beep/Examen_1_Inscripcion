package modelo;

/** Entidad: un curso con su nombre y costo. */
public class Curso {
    private final String nombre;
    private final double costo;

    public Curso(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public String getNombre() { return nombre; }
    public double getCosto() { return costo; }

    @Override
    public String toString() {
        return String.format("%s  -  $%,.2f", nombre, costo);
    }
}
