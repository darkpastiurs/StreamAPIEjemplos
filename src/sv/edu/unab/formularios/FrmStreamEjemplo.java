package sv.edu.unab.formularios;

import sv.edu.unab.dominio.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class FrmStreamEjemplo extends JFrame {
    //<editor-fold defautlstate="collapsed" desc="Componentes">
    public JPanel pnlRoot;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JFormattedTextField ftxtFechaNacimiento;
    private JTextArea atxtDireccion;
    private JTextField txtGrado;
    private JTextField txtSeccion;
    private JComboBox cboGenero;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnConsultar;
    private JTable tblEstudiantes;
    //</editor-fold>
    private List<Estudiante> estudiantesModel;

    public FrmStreamEjemplo() {
        initComponents();
    }

    public void initComponents() {

        tblEstudiantes.setFillsViewportHeight(true);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        if (estudiantesModel == null) {
            estudiantesModel = new ArrayList<>();
        }
        estudiantesModel.add(new Estudiante(String.valueOf(new Random().nextInt()), "Sussan Gissele", "Batres", "Alvarenga", LocalDate.of(1999, 5, 25), "Paraíso", "6to", 'C', 'F'));
        estudiantesModel.add(new Estudiante(String.valueOf(new Random().nextInt()), "Marcos Alexanander", "Gómez", "Pérez", LocalDate.of(1997, 4, 26), "Sierpe", "8vo", 'C', 'M'));
        estudiantesModel.stream().forEach(estudiante -> {
            StringJoiner nombreCompleto = new StringJoiner(" ");
            nombreCompleto.add(estudiante.getApellidoPaterno());
            nombreCompleto.add(estudiante.getApellidoMaterno());
            nombreCompleto.add(estudiante.getNombre());
            modelo.addRow(new Object[]{
                    estudiante.getCodigo(),
                    nombreCompleto
            });
        });
        tblEstudiantes.setModel(modelo);
        //Agregando eventos a botones
        btnAgregar.addActionListener(evt -> {
            Estudiante estudiante = new Estudiante(String.valueOf(new Random().nextInt()));
            estudiante.setNombre(txtNombre.getText());
            estudiante.setApellidoPaterno(txtApellidoPaterno.getText());
            estudiante.setApellidoMaterno(txtApellidoMaterno.getText());
            DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            estudiante.setFechaNacimiento(LocalDate.parse(ftxtFechaNacimiento.getText(), dft));
            estudiante.setDireccion(atxtDireccion.getText());
            estudiante.setGrado(txtGrado.getText());
            estudiante.setSeccion(txtSeccion.getText().toUpperCase().charAt(0));
            estudiante.setGenero(cboGenero.getSelectedItem().toString().charAt(0));
        });
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            ftxtFechaNacimiento.setFormatterFactory(new DefaultFormatterFactory(mascara));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
