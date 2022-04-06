package ar.com.mjcl.WebAppInterviewExercise;

import ar.com.mjcl.WebAppInterviewExercise.model.DatoNomina;
import ar.com.mjcl.WebAppInterviewExercise.model.IntegrationResponse;
import ar.com.mjcl.WebAppInterviewExercise.service.IntegrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebAppInterviewExerciseApplicationTests {
	@Autowired
	private IntegrationService integrationService;

	//Test de integracion con un JWT
	@Test
	public void testIntegration(){
		final String cuitForTest = "";
		IntegrationResponse results = integrationService.getDatosNomina(cuitForTest);

	}
}
