package sv.edu.unab.main;

import sv.edu.unab.formularios.FrmStreamEjemplo;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        //Obteniendo resoluciones de pantalla
        Dimension resolucionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //Ajustando tamaño del formulario
        Dimension ajusteTamaño = new Dimension(resolucionPantalla.width/2,resolucionPantalla.height - 100);
        JFrame frm = new JFrame("Estudiantes de la Botellita de Jerez");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setContentPane(new FrmStreamEjemplo().pnlRoot);
        frm.setPreferredSize(ajusteTamaño);
        frm.setResizable(false);
        frm.pack();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }

}
