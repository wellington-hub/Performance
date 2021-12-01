package grid_paralelo;


import java.io.File;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


/***********************************************************************************
 * Utils -> Classe respons�vel por conter atributos e m�todos auxiliares que
 * ser�o utilizados na implementa��o da superclasse.
 ***********************************************************************************/
public class Utils {

	public static String userDir = System.getProperty("user.dir");
	public static String userName = System.getProperty("user.name");
	public static String osName = System.getProperty("os.name");
	public static String osVersion = System.getProperty("os.version");
	public static String osArchitecture = System.getProperty("os.arch");

	// Aceita o alerta (clica no bot�o OK).
	public void aceitarAlerta(WebDriver driver) throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
		}
	}

	// Acessa � URL passada por par�metro.
	public void acessarURL(WebDriver driver, String url) throws Exception {
		try {
			driver.get(url);
		} catch (Exception e) {
		}
	}

	/*
	 * Aguarda o alert ser exibido (wait din�mico). Quando o mesmo estiver
	 * vis�vel, o teste ir� prosseguir. O tempo limite � de 30 segundos.
	 */
	public void aguardarPorAlert(WebDriver driver) throws Exception {
		long tempoEspera = 30;
		try {
			WebDriverWait wait = new WebDriverWait(driver, tempoEspera);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
		}
	}

	/*
	 * Aguarda o elemento ser exibido (wait din�mico). Quando o mesmo estiver
	 * vis�vel, o teste ir� prosseguir. O tempo limite � de 30 segundos.
	 */
	public void aguardarPorElemento(WebDriver driver, By by) throws Exception {
		long tempoEspera = 30;
		try {
			WebDriverWait wait = new WebDriverWait(driver, tempoEspera);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
		}
	}

	// Altera a janela corrente do teste pelo t�tulo da p�gina passado por
	// par�metro.
	public void alterarJanelaPorTituloPagina(WebDriver driver, String titulo) throws Exception {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				if (driver.switchTo().window(winHandle).getTitle().equals(titulo)) {
					break;
				}
			}

			pausar(2000);
		} catch (Exception e) {
		}
	}

	// Altera a janela corrente do teste pela URL da p�gina passada por
	// par�metro.
	public void alterarJanelaPorURL(WebDriver driver, String url) throws Exception {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				if (driver.switchTo().window(winHandle).getCurrentUrl().equals(url)) {
					break;
				}
			}

			pausar(2000);
		} catch (Exception e) {
		}
	}

	// Altera o foco para o frame 'ifrm'
	public void alterarFocoFrame(WebDriver driver) throws Exception {
		String frame = "ifrm";
		int frameZero = 0;

		try {
			driver.switchTo().frame(frame);
		} catch (NoSuchElementException e) {
			driver.switchTo().frame(frameZero);
		}
	}

	// Arrasta elemento e solta em um determinado local
	public void arrastarSoltarElemento(WebDriver driver, By elemento, By localFuturo) throws Exception {
		try {
			WebElement sourceLocator = driver.findElement(elemento);
			WebElement destinationLocator = driver.findElement(localFuturo);

			Actions actions = new Actions(driver);
			actions.dragAndDrop(sourceLocator, destinationLocator).build().perform();
		} catch (Exception e) {
		}
	}

	// Busca por um texto na p�gina atual
	public boolean buscarPorTextoNaPagina(WebDriver driver, String texto) throws Exception {
		try {
			driver.getPageSource().contains(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * M�todo para buscar valores na ComboBox de acordo com a op��o desejada.
	 * Com esse m�todo n�o importa a posi��o em que o valor encontra-se, a busca
	 * ser� feita pelo valor desejado na comboBox.
	 */
	public void buscarValoresNaComboBoxPorTexto(WebDriver driver, By cliqueSimplesNaCombo, By xpathDaListaElementos,
			String valorDesejadoNaCombo) throws Exception {

		try {

			WebElement findElement = driver.findElement((cliqueSimplesNaCombo));
			findElement.click();

			List<WebElement> elementos = driver.findElements((xpathDaListaElementos));

			for (WebElement elementosGrid : elementos) {

				if (elementosGrid.getText().equals(valorDesejadoNaCombo)) {

					elementosGrid.click();
				}
			}
		} catch (Exception e) {
		}
	}

	/*
	 * M�todo para buscar valores na Grid/tabela de acordo com a op��o desejada.
	 * Com esse m�todo n�o importa a posi��o em que o valor encontra-se, a busca
	 * ser� feita pelo texto informado.
	 */
	public void buscarValoresNaGridPorTexto(WebDriver driver, By xpathDoGrid, String textoParaPesquisa)
			throws Exception {

		try {

			List<WebElement> elementos = driver.findElements((xpathDoGrid));

			for (WebElement elementosGrid : elementos) {

				if (elementosGrid.getText().contains(textoParaPesquisa)) {

					elementosGrid.click();
				}
			}
		} catch (Exception e) {
		}
	}

	// Clica no componente passado por par�metro.
	public void clicarElemento(WebDriver driver, By by, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(driver, by);
			driver.findElement(by).click();
		} catch (Exception e) {
		}
	}

	public void clicarElementoPorIdComandoJavaScript(WebDriver driver, By id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("document.getElementById(\'" + id + "\').click();");
		js.executeScript(stringBuilder.toString());
	}

	// Verifica se a propriedade �text� do componente em quest�o cont�m o texto
	// passado por par�metro.
	public void contemTextoPresentePorElemento(WebDriver driver, By by, String texto, String nomeElemento)
			throws Exception {

		try {
			AssertJUnit.assertTrue(driver.findElement(by).getText().contains(texto));
		} catch (Exception e) {
		}
	}

	// Deleta todos os cookies do navegador.
	public void deletarTodosCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	// N�o aceita o alerta (clica no bot�o Cancelar).
	public void dispensarAlerta(WebDriver driver) throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {
		}
	}

	// Realiza duplo clique em um elemento
	public void duploCliqueElemento(WebDriver driver, By by, String nomeElemento) throws Exception {
		try {
			Actions action = new Actions(driver);
			WebElement element = driver.findElement(by);

			action.doubleClick(element).perform();
		} catch (Exception e) {
		}
	}

	// Verifica se o componente est� vis�vel. Propriedade �isDisplayed�.
	public boolean elementoEstaVisivel(WebDriver driver, By by) {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Verifica se o componente n�o est� vis�vel. Propriedade �isDisplayed�.
	public boolean elementoNaoEstaVisivel(WebDriver driver, By by) {
		try {
			AssertJUnit.assertFalse(driver.findElement(by).isDisplayed());
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Verifica se o elemento est� presente.
	public boolean elementoEstaPresente(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Verifica se o elemento n�o est� presente.
	public void elementoNaoEstaPresente(WebDriver driver, By by) throws Exception {
		try {
			boolean notPresent = ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by)).apply(driver);
			AssertJUnit.assertTrue(notPresent);
		} catch (Exception e) {
		}
	}

	// Verifica se elemento possui uma determinada classe
	public boolean elementoPossuiClasse(WebElement element, String classe) {
		String classes = element.getAttribute("class");
		for (String c : classes.split(" ")) {
			if (c.equals(classe)) {
				return true;
			}
		}
		return false;
	}

	// Preenche campo de texto (componente) com texto passado por par�metro.
	public void escreverTexto(WebDriver driver, By by, String texto, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(driver, by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(texto);
		} catch (Exception e) {
		}
	}

	// Preenche campo de texto (componente) com texto passado por par�metro
	// utilizando comando javascript.
	public void escreverTextoComandoJavaScript(WebDriver driver, By by, String script, String texto,
			String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(driver, by);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script); // exemplo de script:
			// document.getElementById('form').style=''
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(texto);
		} catch (Exception e) {
		}
	}

	public void escreverTextoComNumeroRandomico(WebDriver driver, By by, String texto, String nomeElemento)
			throws Exception {
		String textoComNumeroRandomico = texto + gerarNumeroRandomico();
		try {
			aguardarPorElemento(driver, by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(textoComNumeroRandomico);
		} catch (Exception e) {
		}
	}

	// Verifica se existe alerta sendo exibido no momento.
	public boolean existeAlerta(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void fecharAbaNavegador(WebDriver driver) {
		driver.close();
	}

	// Realiza file upload de um arquivo armazenado na pasta 'upload'
	public void fileUpload(WebDriver driver, By by, String arquivo, String nomeElemento) throws Exception {

		String pathUpload = (userDir + "\\upload\\" + arquivo);
		try {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(pathUpload);
		} catch (Exception e) {
		}
	}

	// Retorna um n�mero rand�mico entre 0 e 100.
	public int gerarNumeroRandomico() {
		Random random = new Random();
		int x = random.nextInt(101);

		return x;
	}

	// Limpa o campo de texto.
	public void limparCampoTexto(WebDriver driver, By by, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(driver, by);
			driver.findElement(by).clear();
		} catch (Exception e) {
		}
	}

	// Realiza a a��o 'Mouse Over' no elemento
	public void mouseOverNoElemento(WebDriver driver, By by) throws Exception {
		try {
			Actions actions = new Actions(driver);
			WebElement mouseHover = driver.findElement(by);
			actions.moveToElement(mouseHover).perform();
		} catch (Exception e) {
		}
	}

	// Realiza a a��o 'Mouse Scroll' de cima para baixo na p�gina
	public void mouseScrollCimaParaBaixo(WebDriver driver) throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		} catch (Exception e) {
		}
	}

	// Realiza a a��o 'Mouse Scroll' de baixo para cima na p�gina
	public void mouseScrollBaixoParaCima(WebDriver driver) throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-500)", "");
		} catch (Exception e) {
		}
	}

	// Realiza a a��o 'Mouse Scroll' at� que o elemento seja visualizado
	public void mouseScrollBuscaElemento(WebDriver driver, By by) throws Exception {
		try {
			WebElement element = driver.findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
		}
	}

	// Pausa a execu��o do teste de acordo com o tempo (milissegundos) passado
	// por par�metro.
	public void pausar(int tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Pressiona tecla de teclado passando por par�metro.
	public void pressionarTecla(WebDriver driver, By by, Keys keyboard) throws Exception {

		aguardarPorElemento(driver, by);
		driver.findElement(by).sendKeys(keyboard);
	}

	// Realiza refresh na p�gina.
	public void refreshPagina(WebDriver driver) {
		driver.navigate().refresh();
	}

	/*
	 * Tira uma screenshot e a armazena na pasta ExtentReports com o nome
	 * passado por par�metro concatenando com �yyyyMMddHH�, no formato jpg.
	 */
	public static String screenShot(WebDriver driver, String fileName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String imagem = fileName + "_" + Utils.gerarDataHoraAtual() + "_" + Utils.obterNomeBrowser(driver) + "_"
				+ Utils.obterVersaoBrowser(driver) + "_" + Utils.osName + ".jpg";

		try {

			FileUtils.copyFile(scrFile, new File("ExtentReports/" + imagem), true);
			return imagem;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagem;
	}

	// Seleciona op��o em combobox pelo texto passado por par�metro.
	public void selecionarElementoPorTexto(WebDriver driver, By by, String texto, String nomeElemento)
			throws Exception {
		try {
			aguardarPorElemento(driver, by);
			new Select(driver.findElement(by)).selectByVisibleText(texto);
		} catch (Exception e) {
		}
	}

	// Seleciona op��o em combobox pela propriedade value passada por par�metro.
	public void selecionarElementoPorValue(WebDriver driver, By by, String valor, String nomeElemento)
			throws Exception {
		try {
			aguardarPorElemento(driver, by);
			new Select(driver.findElement(by)).selectByValue(valor);
		} catch (Exception e) {
		}
	}

	// Seleciona op��o em combobox pela propriedade index passada por par�metro.
	public void selecionarElementoPorIndex(WebDriver driver, By by, int index, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(driver, by);
			new Select(driver.findElement(by)).selectByIndex(index);
		} catch (Exception e) {
		}
	}

	// Seleciona op��o em combobox pelo texto passado por par�metro utilizando
	// comando javascript.
	public void selecionarElementoPorTextoComandoJavaScript(WebDriver driver, By by, String script, String texto,
			String nomeElemento) throws Exception {
		try {
			pausar(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script); // exemplo de script:
										// document.getElementById('form').style=''
			new Select(driver.findElement(by)).selectByVisibleText(texto);
		} catch (Exception e) {
		}
	}

	// Seleciona op��o em combobox pela propriedade value passada por par�metro
	// utilizando comando javascript.
	public void selecionarElementoPorValueComandoJavaScript(WebDriver driver, By by, String script, String valor,
			String nomeElemento) throws Exception {
		try {
			pausar(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script); // exemplo de script:
										// document.getElementById('form').style=''
			new Select(driver.findElement(by)).selectByValue(valor);
		} catch (Exception e) {
		}
	}

	// Seleciona op��o em combobox pela propriedade index passada por par�metro
	// utilizando comando javascript.
	public void selecionarElementoPorIndexComandoJavaScript(WebDriver driver, By by, String script, int index,
			String nomeElemento) throws Exception {
		try {
			pausar(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script); // exemplo de script:
										// document.getElementById('form').style=''
			new Select(driver.findElement(by)).selectByIndex(index);
		} catch (Exception e) {
		}
	}

	// Verifica se o texto passado por par�metro n�o esta presente na
	// propriedade �text� do componente em quest�o.
	public void textoNaoPresentePorElemento(WebDriver driver, By by, String textoEsperado) throws Exception {
		try {
			AssertJUnit.assertFalse(driver.findElement(by).getText().contains(textoEsperado));
		} catch (Exception e) {
		}
	}

	//// Verifica se o texto passado por par�metro esta presente na propriedade
	//// �text� do componente em quest�o.
	public void textoPresentePorElemento(WebDriver driver, By by, String textoEsperado) throws Exception {
		try {
			AssertJUnit.assertEquals(textoEsperado, driver.findElement(by).getText());
		} catch (Exception e) {
		}
	}

	// Verifica se o componente em quest�o est� selecionado (checkbox por
	// exemplo).
	public void validarComponenteSelecionado(WebDriver driver, By by, String nomeElemento) throws Exception {
		try {
			AssertJUnit.assertTrue(driver.findElement(by).isSelected());
		} catch (Exception e) {
		}
	}

	// Verifica se o componente em quest�o est� habilitado.
	public void validarComponenteHabilitado(WebDriver driver, By by, String nomeElemento) throws Exception {
		try {
			AssertJUnit.assertTrue(driver.findElement(by).isEnabled());
		} catch (Exception e) {
		}
	}

	// Verifica se o componente em quest�o n�o est� selecionado (checkbox por
	// exemplo).
	public void validarComponenteNaoSelecionado(WebDriver driver, By by, String nomeElemento) throws Exception {
		try {
			AssertJUnit.assertFalse(driver.findElement(by).isSelected());
		} catch (Exception e) {
		}
	}

	// Verifica se o componente em quest�o n�o est� habilitado.
	public void validarComponenteDesabilitado(WebDriver driver, By by, String nomeElemento) throws Exception {
		try {
			AssertJUnit.assertFalse(driver.findElement(by).isEnabled());
		} catch (Exception e) {
		}
	}

	// Valida se na URL atual cont�m o texto (trecho da URL).
	public void validarURLAtualContemString(WebDriver driver, String trechoURL) throws Exception {
		try {
			AssertJUnit.assertTrue(driver.getCurrentUrl().contains(trechoURL));
		} catch (Exception e) {
		}
	}

	// Retorna � tela anterior.
	public void voltarTelaAnterior(WebDriver driver) throws Exception {
		try {
			driver.navigate().back();
		} catch (Exception e) {
		}
	}

	public static String gerarDataHoraAtual() {
		// Configura objeto do tipo Date com o padr�o yyyyMMdd
		String cData = "yyyyMMdd";
		DateFormat formataData = new SimpleDateFormat("yyyyMMddHH");
		Date dDate = new Date();
		cData = formataData.format(dDate);

		return cData;
	}

	public static String obterVersaoBrowser(WebDriver driver) {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		Object strBrowserVersion = capabilities.getVersion();
		if (strBrowserVersion == "") {
			strBrowserVersion = capabilities.getCapability("browserVersion");
		} else {
			return (String) strBrowserVersion;
		}

		return (String) strBrowserVersion;
	}

	public static String obterNomeBrowser(WebDriver driver) {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();

		return browserName;
	}
	
	public static void printaLogThread(String mensagem)
	{
		System.out.println(
				mensagem + "(thread " + Thread.currentThread().getId() + ")");
	}
	
	
	
    
}
