package exemplos_paralelo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import grid_paralelo.SuperClass;
import grid_paralelo.TLDriverFactory;

public class _001CalcularPrecoPrazoEntregaCartaRegistrada extends SuperClass{

	@Test
	public void _001_carta_registrada() throws Exception {

		WebDriver driver = TLDriverFactory.getDriver();

		 // TESTE
		acessarURL(driver); // ok
//	    preencherUsuario(driver); //ok
//	    preencherSenha(driver); //ok
//	    clicarBotao(driver);
//	    pausar(3000);

	}
	
	
}
