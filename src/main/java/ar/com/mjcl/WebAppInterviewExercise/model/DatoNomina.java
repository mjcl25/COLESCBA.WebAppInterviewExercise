package ar.com.mjcl.WebAppInterviewExercise.model;

public class DatoNomina {

    private String nombreDato;
    private String valorDato;

    public DatoNomina(final String nombreDato, final String valorDato) {
        this.nombreDato = nombreDato;
        this.valorDato = valorDato;
    }

    public String getNombreDato() {
        return nombreDato;
    }

    public void setNombreDato(String nombreDato) {
        this.nombreDato = nombreDato;
    }

    public String getValorDato() {
        return valorDato;
    }

    public void setValorDato(String valorDato) {
        this.valorDato = valorDato;
    }
}
