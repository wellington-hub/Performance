package exemplos;

import org.testng.annotations.Test;

import ftor_testng.CalculoPrecoPrazoUtils;
import ftor_testng.ConfigureExecution;

public class _002CalcularPrecoPrazoEntregaCartaSimples extends CalculoPrecoPrazoUtils {
	
	// Caso de Teste
	@Test
	public void testar002() throws Exception {

		ConfigureExecution.configurarExecucaoCasosTeste("002");
	}

	// M�todo de Teste
	public void test002CalcularPrecoPrazoEntregaCartaSimples() throws Exception {

		/**********************
		 * Teste
		 ********************/	
		acessarURL();
		preencherUsuario();
		//preencherSenha();
		//clicarBotao();
		//pausar(3000);
		
		
		/************************************************
		 * -->Adiciona imagem ao relat�rio ExtentReports; 
		 * -->Finaliza o relat�rio.
		 ************************************************/
		adicionaImagemLogSucessoRelatorio("Evid�ncia de Teste:");
	}
}