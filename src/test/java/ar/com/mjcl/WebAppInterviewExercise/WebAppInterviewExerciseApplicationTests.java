package ar.com.mjcl.WebAppInterviewExercise;

import ar.com.mjcl.WebAppInterviewExercise.model.IntegrationResponse;
import ar.com.mjcl.WebAppInterviewExercise.service.IntegrationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith( SpringRunner.class )
@SpringBootTest
class WebAppInterviewExerciseApplicationTests {

	@Autowired
	private IntegrationService integrationService;

	//Test de integracion con un cuit dado
	@Test
	public void testIntegration(){
		final String cuitForTest = "20-26157300-9";
		IntegrationResponse results = integrationService.getDatosNomina(cuitForTest);
		assertThat(results.getResponseCode()).isEqualTo(200);
	}
}
