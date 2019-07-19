package br.se.edgargueno.test;
import static br.se.edgargueno.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.se.edgargueno.core.BaseTest;
import br.se.edgargueno.page.CampoTreinamentoPage;


public class TesteCadastro extends BaseTest {
	
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();

	}

	@Test
	public void deveCadastrar() {
		page.setNome("Edgar");
		page.setSobrenome("Gueno");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolariedade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Edgar"));
		Assert.assertEquals("Sobrenome: Gueno", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: mestrado", page.obterEscolariedadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.obterEsporteCadastro());
		
	}
	
}
