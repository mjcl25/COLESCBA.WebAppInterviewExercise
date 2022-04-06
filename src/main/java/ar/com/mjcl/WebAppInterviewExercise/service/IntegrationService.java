package ar.com.mjcl.WebAppInterviewExercise.service;

import ar.com.mjcl.WebAppInterviewExercise.configuration.IntegrationConfiguration;
import ar.com.mjcl.WebAppInterviewExercise.model.DatoNomina;
import ar.com.mjcl.WebAppInterviewExercise.model.IntegrationResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class IntegrationService {

    @Autowired
    private IntegrationConfiguration integrationConfiguration;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Genera el token para la autentificacion contra el WebService utilizando la configuracion de claims.
     * @return token en formato String.
     */
    public String getToken(){
        Algorithm algorithm = Algorithm.HMAC256(integrationConfiguration.getTokenSecret());
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.MINUTE, 2);
        Date expDate = calendar.getTime();
        final String token = JWT.create()
                .withIssuer(integrationConfiguration.getTokenIssuer()) //iss
                .withIssuedAt(currentDate) //iat
                .withExpiresAt(expDate) //exp
                .withAudience("nomina-escribanos-ws")  //aud
                .withSubject("examen-tecnico") //sub
                .withClaim("role", Arrays.asList(
                        "EXTERNOS"
                ))
                .sign(algorithm);
        return token;
    }

    /**
     * Realiza la integracion con el WebService solicitado para buscar las nominas del CUIT recibido.
     * @param cuit valor de CUIT a utilizar en la integracion
     * @return Objeto IntegrationResponse que contiene la respuesta, mensaje y listado de datos de nomina de la integracion.
     */
    public IntegrationResponse getDatosNomina(final String cuit){
        final String token = getToken();
        final String url = integrationConfiguration.getIntegrationUrl() + "/" + cuit.replace("-", "");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity request = new HttpEntity(headers);
        try{
            ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            if ((response.getStatusCode() != HttpStatus.OK)) {
                return null;
            }
            final String stringResponse = response.getBody();
            final List<DatoNomina> nominaList = parseResponse(stringResponse);
            if(nominaList.size() > 0) {
                return new IntegrationResponse(200, "Obtenido correctamente", nominaList);
            }
            return new IntegrationResponse(200, "No se encuentran resultados", null);
        } catch(HttpClientErrorException ex){
            return new IntegrationResponse(ex.getStatusCode().value(), ex.getResponseBodyAsString(), null);
        } catch (Exception ex) {
            return new IntegrationResponse(0, "Error al consultar WebService", null);
        }
    }

    /**
     * Metodo que realiza la conversion de la respuesta del WebService a un listado de DatoNomina. Se da por sentado que si
     *  la respuesta tiene contenido tendra valores correctos.
     * @param response Representacion en String de la respuesta del WebService
     * @return Listado de datos de nomina.
     */
    private List<DatoNomina> parseResponse(final String response) {
        List<DatoNomina> result = new ArrayList<>();
        final JsonObject jo = getResponseAsJsonObject(response);
        if(jo != null && !jo.entrySet().isEmpty()){
            for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
                DatoNomina dato = new DatoNomina(entry.getKey(), jo.get(entry.getKey()).getAsString());
                result.add(dato);
            }
        }
        return result;
    }

    /**
     * Realiza la conversion de la respuesta en String a JsonObject
     * @param response Representacion en String de la respuesta del WebService
     * @return Representacion en JsonObject de la respuesta del Webservice.
     */
    private JsonObject getResponseAsJsonObject(final String response) {
        try{
            return new Gson().fromJson(response, JsonObject.class);
        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
