package sv.edu.unab.formularios;

import sv.edu.unab.dominio.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.logging.SimpleFormatter;

public class FrmStreamEjemplo {
   //<editor-fold defautlstate="collapsed" desc="Componentes">
    public JPanel pnlRoot;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JFormattedTextField ftxtFechaNacimiento;
    private JTextArea atxtDireccion;
    private JTextField textField1;
    private JTextField textField2;
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

    public void initComponents(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        if(estudiantesModel == null){
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
        //Formato de ftxt
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            ftxtFechaNacimiento = new JFormattedTextField(mascara);
            //Agregando eventos a botones
            btnAgregar.addActionListener(evt -> {
                Estudiante estudiante = new Estudiante(String.valueOf(new Random().nextInt()));
                estudiante.setNombre(txtNombre.getText());
                estudiante.setApellidoPaterno(txtApellidoPaterno.getText());
                estudiante.setApellidoMaterno(txtApellidoMaterno.getText());
                estudiante.setFechaNacimiento(LocalDate.parse(ftxtFechaNacimiento.getText()));
                System.out.println(estudiante.getFechaNacimiento());

            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
