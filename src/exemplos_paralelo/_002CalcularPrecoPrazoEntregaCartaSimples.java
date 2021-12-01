package exemplos_paralelo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import grid_paralelo.SuperClass;
import grid_paralelo.TLDriverFactory;

public class _002CalcularPrecoPrazoEntregaCartaSimples extends SuperClass{

	@Test
	public void _002_carta_simples() throws Exception {

		WebDriver driver = TLDriverFactory.getDriver();

		// TESTE
				acessarURL(driver); // ok
			    preencherUsuario2(driver); //ok
			    preencherSenha2(driver); //ok
			    //clicarBotao(driver); //ok
			   // pausar(3000);
			   
			    
	}
}
