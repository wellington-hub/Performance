package ftor_testng;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**********************************************************************************************************************
 * TestUtils -> Classe responsável por conter o atributo driver, da interface
 * WebDriver (API do Selenium WebDriver) e objeto util (classe Utils.java);
 * 
 * -> Chamadas aos métodos de inicialização/término da instância do navegador e
 * método gravarTelaRQM que tem a função de adicionar imagem (evidência de
 * teste) ao campo ‘Capturas de Tela’ do log do RQM;
 * 
 * -> Contém as anotações @BeforeMethod (o que será executado antes do Caso de
 * Teste) e @AfterMethod (o que será executado após o Caso de Teste) do TestNG.
 **********************************************************************************************************************/
public class TestUtils {

	public static WebDriver driver;
	public static Utils util = new Utils();

	/*****************************************************************************************************************
	 *             
	 *  					     OBS: COMENTAR O METODO defineBrowser
	 *               		CASO NAO QUEIRA EXECUTAR ATRAVES DO TESTNG.XML " paralelo" 
	 *                              E DESCOMENTAR o METODO setUp
	 * 
	 * ****************************************************************************************************************/
	 
	@Parameters({ "browser", "platform", "version" })
//	@BeforeMethod
//	public static void defineBrowser(String browser, String platform, String version) {
//		DesiredCapabilities extraCapabilities = new DesiredCapabilities();
//		extraCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		extraCapabilities.setBrowserName(browser);
//
//		// Verifica a plataforma
//		if ("windows".equals(platform)) {
//			extraCapabilities.setPlatform(Platform.WINDOWS);
//		} else if ("linux".equals(platform)) {
//			extraCapabilities.setPlatform(Platform.LINUX);
//		} else if ("mac".equals(platform)) {
//			extraCapabilities.setPlatform(Platform.MAC);
//		}
//
//		// Verifica a versão do navegador
//		extraCapabilities.setVersion(version);
//
//		// Define o browser
//		switch (browser) {
//		case "firefox-portable":
//			Browser.executarComFirefoxPortable44();
//			break;
//
//		case "firefox52":
//			Browser.executarComFirefox52();
//			break;
//
//		case "firefox57":
//			Browser.executarComFirefox57();
//			break;
//
//		case "chrome54":
//			Browser.executarComChrome54();
//			break;
//
//		case "chrome70":
//			Browser.executarComChrome70();
//			break;
//
//		case "ie11":
//			Browser.executarComIE11();
//			break;
//
//		case "edge17":
//			Browser.executarComEdge17();
//			break;
//
//		case "phantomjs-2.11":
//			Browser.executarComPhantomJS_2_11();
//			break;
//
//		case "chrome70-headless":
//			Browser.executarComChrome70_Headless();
//			break;
//
//		case "chrome-portable":
//			Browser.executarComChromePortable();
//			break;
//		}
//	}
	
	/*****************************************************************************************************************
	 *             
	 *  					   OBS: DESCOMENTAR O METODO ABAIXO(setUp)
	 *               		CASO NAO QUEIRA EXECUTAR ATRAVES DO TESTNG.XML
	 *              			 E COMENTAR O METODO defineBrowser
	 * 
	 * ****************************************************************************************************************/
	 
	@BeforeMethod
	public void setUp() throws Exception {		
		//Browser.executarComFirefox57();
		Browser.executarComChrome70();
	}


	@AfterMethod
	public void tearDown() throws Exception {

		/*********************************************************
		 * -->Finaliza a gravação do relatório em vídeo;
		 * -->Adiciona imagem ao campo'Capturas de Tela' do log do RQM;
		 * -->Finaliza a instância do navegador
		 *********************************************************/
		ReportLog.finalizarGravacaoVideoExecucaoCasoTeste();
		util.gravarTelaRQM();
		//Browser.fecharInstancia();
	}
}
