package br.se.edgargueno.test;

import static br.se.edgargueno.core.DriverFactory.getDriver;
import static br.se.edgargueno.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.se.edgargueno.core.DSL;

public class TesteCampoTreinamento {
		
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		
	}
	
	@After
	public void Finaliza() {
		killDriver();
	}
	
	@Test
	public void testeTextFild() {
		
		dsl.escrever("elementosForm:nome", "Edgar Gueno");
		Assert.assertEquals("Edgar Gueno", dsl.obterValorCampo("elementosForm:nome"));
				
	}
	
	public void deveVerificarValoresCombo(){
		
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
		
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		
		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected());

	}	
	
	@Test
	public void deveInteragirComCheckbox() {
	
		dsl.clicarRadio("elementosForm:comidaFavorita:1");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:1"));

	}	
	
	@Test
	public void deveIntegarirComCombo(){
		
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
		
	}
	@Test
	public void deveVerificarValorescombobox() {
		
		getDriver().manage().window().maximize();
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")){
				encontrou = true;
				
				break;
			}
		}
		
		Assert.assertTrue(encontrou);
        
	}	
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void deveInteragirBotoes() {
		dsl.clicarBotao("buttonSimple");
		WebElement botao = getDriver().findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
	}
	
	@Test
	public void deveInteragirLinks() {
				
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!",dsl.obterTexto("resultado"));
				
	}
	
	@Test
	public void deveBuscarTextoNaPagina() {
				
//		Assert.assertTrue(getDriver().findElement(By.tagName("body"))
//		.getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
		dsl.obterTexto(By.className("facilAchar")));

	}
	
}
