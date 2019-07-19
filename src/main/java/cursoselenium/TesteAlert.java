package cursoselenium;

import static br.se.edgargueno.core.DriverFactory.getDriver;
import static br.se.edgargueno.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.se.edgargueno.core.DSL;

public class TesteAlert {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
				
	}
	@After
	public void Finaliza() {

		killDriver();
	}
	
	@Test
	public void devInterragirComAlertSimples() {
			
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceita(); 
		Assert.assertEquals("Alert Simples", texto);
		dsl.escrever("elementosForm:nome", texto);
	
	}
	
	@Test
	public void devInterragirComAlertConfirme() {
	
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());

	}
	
	@Test
	public void devInterragirComAlertPrompt() {

		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());

	}
			
}
