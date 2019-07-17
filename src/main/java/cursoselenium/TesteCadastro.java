package cursoselenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
				
	}
	
	@After
	public void Finaliza() {

		driver.quit();
	}

	@Test
	public void deveCadastrar() {
		
		dsl.escrever("elementosForm:nome", "Edgar");
		dsl.escrever("elementosForm:sobrenome", "Gueno");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Edgar"));
		Assert.assertEquals("Sobrenome: Gueno", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino",dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao",dsl.obterTexto("descEsportes"));
		
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
			
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
			
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		
		dsl.escrever("elementosForm:nome","Nome qualquer");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
			
	}
	@Test
	public void deveValidarSexoObrigatorio() {
		
		dsl.escrever("elementosForm:nome", "Nome qualquer");
		dsl.escrever("elementosForm:sobrenome", "Sobrenome qualquer");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
			
	}
	@Test
	public void deveValidarComidaVegetariana() {
		
		dsl.escrever("elementosForm:nome", "Nome qualquer");
		dsl.escrever("elementosForm:sobrenome", "Sobrenome qualquer");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
	
		
		dsl.escrever("elementosForm:nome", "Nome qualquer");
		dsl.escrever("elementosForm:sobrenome", "Sobrenome qualquer");
		dsl.clicarBotao("elementosForm:sexo:1");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");		
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());

	}
}
