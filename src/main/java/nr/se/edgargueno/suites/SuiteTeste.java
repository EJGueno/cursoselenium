package nr.se.edgargueno.suites;
import static br.se.edgargueno.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.se.edgargueno.test.TesteCadastro;
import br.se.edgargueno.test.TesteRegraCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegraCadastro.class
})
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {
		killDriver();
	}

}
