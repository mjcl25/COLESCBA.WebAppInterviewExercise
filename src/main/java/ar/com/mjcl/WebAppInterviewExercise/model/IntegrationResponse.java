package ar.com.mjcl.WebAppInterviewExercise.model;

import java.util.List;

public class IntegrationResponse {

    private final List<DatoNomina> nominaList;
    private final int responseCode;
    private final String responseMessage;

    public IntegrationResponse (final int responseCode, final String responseMessage, final List<DatoNomina> nominaList) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.nominaList = nominaList;
    }

    public List<DatoNomina> getNominaList() {
        return nominaList;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
