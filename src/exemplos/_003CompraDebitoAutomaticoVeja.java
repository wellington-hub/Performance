/****
 * * Script Name	 : <b> Script Teste de compra (Monitoramento em Produção) </b>
 * Generated	 : <b>30/11/2021 07:00</b>
 * Description	 : Functional Test Script
 * Original Host : Win7 version xx Build xxx (s)
 *  
 * @since  2021/11/30
 * @author Wellington Medeiros...
 * 
 * 
 *  Eu como PO
 *  Quero validar a compra através de débito bancário no checkout dos sites da Abril.
 *  Para garantir que o processo de vendas esta funcionando normalmente

 *  Notas

 *	Realizar o acesso ao checkout através da página /ofertas em qualquer um dos sites com venda se assinaturas:

 *	Veja, Veja SP, Veja RIO, Veja Saúde, Super, Claudia, QR, Placar, VC SA, VC RH e GE

 *	A compra deve ser feita utilizando a palavra “teste” no nome ou no e-mail para que não seja 
 *	contabilizada pelo time de monetização.
 * 
 * 
 * 
 * 
 */

package exemplos;

import org.testng.annotations.Test;

import ftor_testng.AbrilUtils;
import ftor_testng.ConfigureExecution;

public class _003CompraDebitoAutomaticoVeja extends AbrilUtils {

	// Caso de Teste
	@Test
	public void testar003() throws Exception {

		ConfigureExecution.configurarExecucaoCasosTeste("003");
	}

	// Método de Teste
	public void test003CompraDebitoAutomaticoVeja() throws Exception {

		/**********************
		 * Teste
		 ********************/
		/**********************
		 * Teste
		 ********************/
		acessarUrlProd();
		AceitoLGPD();
		VerOfertasVeja();				
		Assine();
		EscolhaPlano();// default Anual
		DadosIdentificaçao();
		EscolhaDebitoConta();
		PreencheDebitoConta();
		FecharCompra();

		// validação //

		/************************************************
		 * -->Adiciona imagem ao relatório ExtentReports; -->Finaliza o
		 * relatório.
		 ************************************************/
		adicionaImagemLogSucessoRelatorio("Evidência de Teste:");

	}
}
