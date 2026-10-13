package vista;

import controlador.InscripcionController;
import modelo.Curso;
import modelo.FichaPago;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/** Vista: ventana de inscripción en clases (Swing). */
public class VentanaInscripcion extends JFrame {
    private InscripcionController controlador;

    private final DefaultListModel<Curso> modeloDisponibles = new DefaultListModel<>();
    private final DefaultListModel<Curso> modeloInscritos = new DefaultListModel<>();
    private final JList<Curso> listaDisponibles = new JList<>(modeloDisponibles);
    private final JList<Curso> listaInscritos = new JList<>(modeloInscritos);
    private final JLabel lblTotal = new JLabel("Total a pagar: $0.00");
    private final JButton btnInscribir = new JButton("Inscribir curso >>");
    private final JButton btnFinalizar = new JButton("Finalizar inscripción");

    public VentanaInscripcion() {
        super("Inscripción en clases");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(760, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel izquierda = new JPanel(new BorderLayout(5, 5));
        izquierda.setBorder(BorderFactory.createTitledBorder("Cursos disponibles"));
        izquierda.add(new JScrollPane(listaDisponibles), BorderLayout.CENTER);
        izquierda.add(btnInscribir, BorderLayout.SOUTH);

        JPanel derecha = new JPanel(new BorderLayout(5, 5));
        derecha.setBorder(BorderFactory.createTitledBorder("Cursos inscritos"));
        derecha.add(new JScrollPane(listaInscritos), BorderLayout.CENTER);
        lblTotal.setFont(lblTotal.getFont().deriveFont(Font.BOLD, 15f));
        derecha.add(lblTotal, BorderLayout.SOUTH);

        JPanel centro = new JPanel(new GridLayout(1, 2, 10, 0));
        centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        centro.add(izquierda);
        centro.add(derecha);

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sur.add(btnFinalizar);

        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);

        btnInscribir.addActionListener(e -> controlador.inscribirCurso(listaDisponibles.getSelectedValue()));
        btnFinalizar.addActionListener(e -> controlador.finalizarInscripcion());
        listaDisponibles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) controlador.inscribirCurso(listaDisponibles.getSelectedValue());
            }
        });
    }

    public void setControlador(InscripcionController controlador) {
        this.controlador = controlador;
    }

    public void mostrarListas(List<Curso> disponibles, List<Curso> inscritos, double total) {
        modeloDisponibles.clear();
        disponibles.forEach(modeloDisponibles::addElement);
        modeloInscritos.clear();
        inscritos.forEach(modeloInscritos::addElement);
        lblTotal.setText(String.format("Total a pagar: $%,.2f", total));
    }

    public void mostrarFicha(FichaPago ficha) {
        btnInscribir.setEnabled(false);
        btnFinalizar.setEnabled(false);
        JTextArea area = new JTextArea(ficha.toTexto());
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Ficha de pago", JOptionPane.PLAIN_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}
