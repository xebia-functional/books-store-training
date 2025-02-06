package Main;

import java.time.LocalDate;
import java.util.Objects;

public class libro {
    private String titulo;
    private String autor;
    private String usuario;
    private boolean disponibilidad;
    private LocalDate fecha;

    public libro() {
    }

    public libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.usuario = "";
        this.disponibilidad = true;
    }

    @Override
    public String toString() {
        return "libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", usuario='" + usuario + '\'' +
                ", disponibilidad=" + disponibilidad+
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        libro libro = (libro) o;
        return isDisponibilidad() == libro.isDisponibilidad() && getFecha() == libro.getFecha() && Objects.equals(getTitulo(), libro.getTitulo()) && Objects.equals(getAutor(), libro.getAutor()) && Objects.equals(getUsuario(), libro.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getAutor(), getUsuario(), isDisponibilidad(), getFecha());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
