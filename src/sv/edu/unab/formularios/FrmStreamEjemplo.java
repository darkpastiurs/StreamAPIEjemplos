package sv.edu.unab.formularios;

import sv.edu.unab.dominio.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class FrmStreamEjemplo {
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
    private JLabel lblEstudianteMenor;
    private JLabel lblEstudianteMayor;
    private JLabel lblPromedioEdad;
    //</editor-fold>
    private List<Estudiante> estudiantesModel;

    public FrmStreamEjemplo() {
        initComponents();
    }

    public void initComponents() {
        tblEstudiantes.setFillsViewportHeight(true);
        if (estudiantesModel == null) {
            estudiantesModel = new ArrayList<>();
        }
        estudiantesModel.add(new Estudiante(String.valueOf(new Random().nextInt()), "Sussan Gissele", "Batres", "Alvarenga", LocalDate.of(1999, 5, 25), "Paraíso", "6to", 'C', 'F'));
        estudiantesModel.add(new Estudiante(String.valueOf(new Random().nextInt()), "Marcos Alexanander", "Gómez", "Pérez", LocalDate.of(1997, 4, 26), "Sierpe", "8vo", 'C', 'M'));
        actualizarDatos(estudiantesModel);
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
            estudiantesModel.add(estudiante);
            limpiarComponentes();
            actualizarDatos(estudiantesModel);
        });

        btnConsultar.addActionListener(evt -> {
            List<Estudiante> estudiantesFiltrados = estudiantesModel.stream().filter(e -> {
                boolean encontrado = e.getNombre().toUpperCase().contains(txtNombre.getText().toUpperCase());
                if(!txtApellidoPaterno.getText().isEmpty()){
                    encontrado = encontrado && e.getApellidoPaterno().toUpperCase().contains(txtApellidoPaterno.getText().toUpperCase());
                }
                if(!txtGrado.getText().isEmpty()){
                    encontrado = encontrado && e.getGrado().contains(txtGrado.getText());
                }
                return encontrado;
            }).collect(Collectors.toList());
            actualizarDatos(estudiantesFiltrados);
        });
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            ftxtFechaNacimiento.setFormatterFactory(new DefaultFormatterFactory(mascara));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void actualizarDatos(List<Estudiante> listado){
        reiniciarJTable(tblEstudiantes);
        Estudiante edadMenor = listado.stream().min((e1, e2) -> {
            Long edad1 = e1.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            Long edad2 = e2.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            return edad1.compareTo(edad2);
        }).get();
        Estudiante edadMayor = listado.stream().max((e1, e2) -> {
            Long edad1 = e1.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            Long edad2 = e2.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            return edad1.compareTo(edad2);
        }).get();
        lblEstudianteMenor.setText(edadMenor.getNombre());
        lblEstudianteMayor.setText(edadMayor.getNombre());
        BigDecimal edadPromedio = listado.stream()
                .map(e -> {
                    Long edad = e.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
                    return new BigDecimal(edad);
                })
                .reduce((e1, e2) -> (e1.add(e2)).divide(new BigDecimal(2))).get();
        edadPromedio.setScale(2,BigDecimal.ROUND_HALF_UP);
        lblPromedioEdad.setText(edadPromedio.toString());
        DefaultTableModel modelo  = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Edad");
        modelo.addColumn("Curso");
        listado.stream().forEach(estudiante -> {
            StringJoiner nombreCompleto = new StringJoiner(" ");
            nombreCompleto.add(estudiante.getApellidoPaterno());
            nombreCompleto.add(estudiante.getApellidoMaterno());
            nombreCompleto.add(estudiante.getNombre());
            StringJoiner curso = new StringJoiner(" ");
            curso.add(estudiante.getGrado());
            curso.add(estudiante.getSeccion().toString());
            modelo.addRow(new Object[]{
                    estudiante,
                    nombreCompleto,
                    estudiante.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS),
                    curso
            });
        });
        tblEstudiantes.setModel(modelo);
    }

    private void reiniciarJTable(JTable jTable){
        DefaultTableModel modelo = (DefaultTableModel) jTable.getModel();
        while (modelo.getRowCount() > 0){
            modelo.removeRow(0);
        }
    }

    private void limpiarComponentes(){
        txtNombre.setText(null);
        txtApellidoPaterno.setText(null);
        txtApellidoMaterno.setText(null);
        ftxtFechaNacimiento.setValue(null);
        atxtDireccion.setText(null);
        txtGrado.setText(null);
        txtSeccion.setText(null);
        cboGenero.setSelectedIndex(0);
    }
}
