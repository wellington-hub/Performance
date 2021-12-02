package ftor_testng;

import exemplos._001CalcularPrecoPrazoEntregaCartaRegistrada;
import exemplos._002CalcularPrecoPrazoEntregaCartaSimples;
import exemplos._003CompraDebitoAutomaticoVeja;
import exemplos._004CompraBoletoBancarioVeja;
import exemplos._005LoginOrgBlockFechadoWEB;

/***************************************************************************************
 * ConfigureExecution: Classe responsável por possuir os objetos, atributos e
 * métodos de configuração da execução dos casos de teste
 ***************************************************************************************/
public class ConfigureExecution {

	// Atributos
	public static String cCaso = "";
	public static int nLin = 0;
	public static int nLin2 = 0;

	// Objetos das classes de teste
	static ReportLog reportlog = new ReportLog();

	// Objetos das classes de teste
	static _001CalcularPrecoPrazoEntregaCartaRegistrada ct001 = new _001CalcularPrecoPrazoEntregaCartaRegistrada();
	static _002CalcularPrecoPrazoEntregaCartaSimples ct002 = new _002CalcularPrecoPrazoEntregaCartaSimples();
	static _003CompraDebitoAutomaticoVeja ct003 = new _003CompraDebitoAutomaticoVeja();
	static _004CompraBoletoBancarioVeja ct004 = new _004CompraBoletoBancarioVeja();
	static _005LoginOrgBlockFechadoWEB ct005 = new _005LoginOrgBlockFechadoWEB();
	

	/*********************************************************
	 * ->Seta o ID do Caso de Teste; 
	 * ->Ler o CSV; 
	 * ->Chama o método processarCasosTeste,referente ao Caso de Teste;
	 *********************************************************/
	public static void configurarExecucaoCasosTeste(String idCaso) throws Exception {
		switch (idCaso) {

		case "001":

			cCaso = idCaso;

			DataDriven.lerCSV();

			try {
				reportlog.apontarRelatorioHtml("_001CalcularPrecoPrazoEntregaCartaRegistrada");
				processarCasosTeste(idCaso);
			} catch (final Exception e) {

				throw new Exception();
			}

			break;
			
			
		case "002":

			cCaso = idCaso;

			DataDriven.lerCSV();

			try {
				reportlog.apontarRelatorioHtml("_002CalcularPrecoPrazoEntregaCartaSimples");
				processarCasosTeste(idCaso);
			} catch (final Exception e) {

				throw new Exception();
			}

			break;
			
		case "003":

			cCaso = idCaso;

			DataDriven.lerCSV();

			try {
				reportlog.apontarRelatorioHtml("_003CompraDebitoAutomaticoVeja ");
				processarCasosTeste(idCaso);
			} catch (final Exception e) {

				throw new Exception();
			}

			break;
			
		case "004":

			cCaso = idCaso;

			DataDriven.lerCSV();

			try {
				reportlog.apontarRelatorioHtml("_004CompraBoletoBancarioVeja");
				processarCasosTeste(idCaso);
			} catch (final Exception e) {

				throw new Exception();
			}

			break;
			
		case "005":

			cCaso = idCaso;

			DataDriven.lerCSV();

			try {
				reportlog.apontarRelatorioHtml("_005LoginOrgBlockFechadoWEB");
				processarCasosTeste(idCaso);
			} catch (final Exception e) {

				throw new Exception();
			}

			break;

		}
	}

	/****************************************************************************************
	 * ->Verifica a linha que será lida do .csv pelo Id do caso; 
	 * ->Executa método 'lerRegistro' (GET do Array) com os campos do .csv; 
	 * ->Inicia a gravação do relatório em vídeo;
	 * ->Realiza a chamada ao método de teste.
	 ****************************************************************************************/
	public static void processarCasosTeste(String idCaso) throws Exception {
		switch (idCaso) {

		case "001":

			// Verificação do id do caso
			nLin2 = DataDriven.aLinha.size() - 1;

			for (nLin = 1; nLin <= nLin2; nLin++)

			{
				if (cCaso.equalsIgnoreCase(DataDriven.aidCaso.get(nLin))) {
					DataDriven.nLin = nLin;
					DataDriven.nLin2 = nLin2;

					DataDriven.lerRegistro();

					// CHAMA MÉTODO DE TESTE
					ReportLog.iniciarGravacaoVideoExecucaoCasoTeste();
					ct001.test001CalcularPrecoPrazoEntregaCartaRegistrada();
					break;
				}
			}

			break;
			
			
		case "002":

			// Verificação do id do caso
			nLin2 = DataDriven.aLinha.size() - 1;

			for (nLin = 1; nLin <= nLin2; nLin++)

			{
				if (cCaso.equalsIgnoreCase(DataDriven.aidCaso.get(nLin))) {
					DataDriven.nLin = nLin;
					DataDriven.nLin2 = nLin2;

					DataDriven.lerRegistro();

					// CHAMA MÉTODO DE TESTE
					ReportLog.iniciarGravacaoVideoExecucaoCasoTeste();
					ct002.test002CalcularPrecoPrazoEntregaCartaSimples();
					break;
				}
			}

			break;
			
		case "003":

			// Verificação do id do caso
			nLin2 = DataDriven.aLinha.size() - 1;

			for (nLin = 1; nLin <= nLin2; nLin++)

			{
				if (cCaso.equalsIgnoreCase(DataDriven.aidCaso.get(nLin))) {
					DataDriven.nLin = nLin;
					DataDriven.nLin2 = nLin2;

					DataDriven.lerRegistro();

					// CHAMA MÉTODO DE TESTE
					ReportLog.iniciarGravacaoVideoExecucaoCasoTeste();
					ct003.test003CompraDebitoAutomaticoVeja ();
					break;
				}
			}

			break;
			
		case "004":

			// Verificação do id do caso
			nLin2 = DataDriven.aLinha.size() - 1;

			for (nLin = 1; nLin <= nLin2; nLin++)

			{
				if (cCaso.equalsIgnoreCase(DataDriven.aidCaso.get(nLin))) {
					DataDriven.nLin = nLin;
					DataDriven.nLin2 = nLin2;

					DataDriven.lerRegistro();

					// CHAMA MÉTODO DE TESTE
					ReportLog.iniciarGravacaoVideoExecucaoCasoTeste();
					ct004.test004CompraBoletoBancarioVeja();
					break;
				}
			}

			break;		
			
		case "005":

			// Verificação do id do caso
			nLin2 = DataDriven.aLinha.size() - 1;

			for (nLin = 1; nLin <= nLin2; nLin++)

			{
				if (cCaso.equalsIgnoreCase(DataDriven.aidCaso.get(nLin))) {
					DataDriven.nLin = nLin;
					DataDriven.nLin2 = nLin2;

					DataDriven.lerRegistro();

					// CHAMA MÉTODO DE TESTE
					ReportLog.iniciarGravacaoVideoExecucaoCasoTeste();
					ct005.test005LoginOrgBlockFechadoWEB();
					break;
				}
			}

			break;

		
		}
	}
}
