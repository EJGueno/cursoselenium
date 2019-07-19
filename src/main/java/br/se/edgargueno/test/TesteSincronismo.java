package br.se.edgargueno.test;

import static br.se.edgargueno.core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.se.edgargueno.core.DSL;

public class TesteSincronismo {

	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void Finaliza() {

//		.quit();
	}
	@Test
	public void deveUltiplizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escrever("novoCampo", "Deu Certo?");
	}
	
	@Test
	public void deveUltiplizarEsperaInplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escrever("novoCampo", "Deu Certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUltiplizarEsperaExplicita() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu Certo?");
	}
	
}
