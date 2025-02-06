package Main;

import java.util.Objects;

public class libro {
    private String titulo;
    private String autor;
    private String usuario;
    private boolean disponibilidad;
    private int fecha;

    public libro() {
    }

    public libro(String titulo, String autor, String usuario, boolean isponibilidad, int fecha) {
        this.titulo = titulo;
        this.autor = autor;
        this.usuario = usuario;
        this.isponibilidad = isponibilidad;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", usuario='" + usuario + '\'' +
                ", isponibilidad=" + isponibilidad +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        libro libro = (libro) o;
        return isIsponibilidad() == libro.isIsponibilidad() && getFecha() == libro.getFecha() && Objects.equals(getTitulo(), libro.getTitulo()) && Objects.equals(getAutor(), libro.getAutor()) && Objects.equals(getUsuario(), libro.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getAutor(), getUsuario(), isIsponibilidad(), getFecha());
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

    public boolean isIsponibilidad() {
        return isponibilidad;
    }

    public void setIsponibilidad(boolean isponibilidad) {
        this.isponibilidad = isponibilidad;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }
}
