package exemplos;

import org.testng.annotations.Test;

import ftor_testng.CalculoPrecoPrazoUtils;
import ftor_testng.ConfigureExecution;

public class _001CalcularPrecoPrazoEntregaCartaRegistrada extends CalculoPrecoPrazoUtils {

	// Caso de Teste
	@Test
	public void testar001() throws Exception {
		
		ConfigureExecution.configurarExecucaoCasosTeste("001");
	}

	// Método de Teste
	public void test001CalcularPrecoPrazoEntregaCartaRegistrada() throws Exception {

		/**********************
		 * Teste
		 ********************/	
		acessarURL();
		preencherUsuario();
		preencherSenha();
		clicarBotao();
		pausar(3000);
		


		/************************************************
		 * -->Adiciona imagem ao relatório ExtentReports;
		 * -->Finaliza o relatório.
		 ************************************************/
		adicionaImagemLogSucessoRelatorio("Evidência de Teste:");
	}
	
	
}
