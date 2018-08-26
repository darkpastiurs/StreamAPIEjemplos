package sv.edu.unab.main;

import sv.edu.unab.formularios.FrmStreamEjemplo;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frm = new JFrame("Estudiantes de la Botellita de Jerez");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setContentPane(new FrmStreamEjemplo().pnlRoot);
        frm.pack();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }

}
