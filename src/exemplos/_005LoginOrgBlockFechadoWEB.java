/****
 * Script Name	 : <b> ScriptLoginOrgBlockFechadoWeb</b>
 * Generated	 : <b>19/11/2021 07:00</b>
 * Description	 : Functional Test Script
 * Original Host : Win7 version xx Build xxx (s)
 *  
 * @since  2021/11/19
 * @author Wellington Medeiros.....
 * 
 *
 * 
 */

package exemplos;
import org.testng.annotations.Test;
import ftor_testng.AbrilUtils;
import ftor_testng.ConfigureExecution;

public class _005LoginOrgBlockFechadoWEB extends AbrilUtils {

	// Caso de Teste
	@Test
	public void testar001() throws Exception {

		ConfigureExecution.configurarExecucaoCasosTeste("005");
	}

	// Método de Teste
	public void test005LoginOrgBlockFechadoWEB() throws Exception {

		
		/**********************
		 * Teste
		 ********************/
		acessarURLFechada();
		clicarSouAssinate();
		preencherUser();
		preencherSenha();
		logar();
		AceitoLGPD();
		// validarAcessoMateriaFechada();
		// clicarSair();

		// clicarMenu();
		// validarUser();

		/************************************************
		 * -->Adiciona imagem ao relatório ExtentReports; -->Finaliza o
		 * relatório.
		 ************************************************/
		adicionaImagemLogSucessoRelatorio("Evidência de Teste:");

	}
}
