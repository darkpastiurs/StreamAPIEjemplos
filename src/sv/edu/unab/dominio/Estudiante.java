package sv.edu.unab.dominio;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Estudiante {
    //<editor-fold desc="Propiedades">
    private String codigo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String grado;
    private Character seccion;
    private Character genero;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Estudiante() {
    }

    public Estudiante(String codigo) {
        this.codigo = codigo;
    }

    public Estudiante(String codigo, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String direccion, String grado, Character seccion, Character genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.grado = grado;
        this.seccion = seccion;
        this.genero = genero;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public Character getSeccion() {
        return seccion;
    }

    public void setSeccion(Character seccion) {
        this.seccion = seccion;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estudiante)) return false;

        Estudiante that = (Estudiante) o;

        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return codigo;
    }
}
