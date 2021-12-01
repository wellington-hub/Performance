package ftor_testng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.LogStatus;

/***********************************************************************************
 * Utils -> Classe respons�vel por conter atributos e m�todos auxiliares que
 * ser�o utilizados na implementa��o da superclasse.
 ***********************************************************************************/
public class Utils extends ReportLog {

	public static String userDir = System.getProperty("user.dir");
	public static String userName = System.getProperty("user.name");
	public static String osName = System.getProperty("os.name");
	public static String osVersion = System.getProperty("os.version");
	public static String osArchitecture = System.getProperty("os.arch");

	// Aceita o alerta (clica no bot�o OK).
	public void aceitarAlerta() throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logSucesso("Aceita o alert (clica no bot�o 'OK')");
		} catch (Exception e) {
			logErro("N�o foi poss�vel aceitar o alert", e);
		}
	}

	// Acessa � URL passada por par�metro.
	public void acessarURL(String url) throws Exception {
		try {
			driver.get(url);
			logSucesso("Acessa a URL " + url);
		} catch (Exception e) {
			logErro("N�o foi poss�vel acessar a URL " + url, e);
		}
	}

	/*
	 * Aguarda o alert ser exibido (wait din�mico). Quando o mesmo estiver
	 * vis�vel, o teste ir� prosseguir. O tempo limite � de 30 segundos.
	 */
	public void aguardarPorAlert() throws Exception {
		long tempoEspera = 30;
		try {
			WebDriverWait wait = new WebDriverWait(driver, tempoEspera);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			logErro("Timeout por aguardar pela presen�a do alert por " + tempoEspera + " segundos", e);
		}
	}

	/*
	 * Aguarda o elemento ser exibido (wait din�mico). Quando o mesmo estiver
	 * vis�vel, o teste ir� prosseguir. O tempo limite � de 30 segundos.
	 */
	public void aguardarPorElemento(By by) throws Exception {
		long tempoEspera = 60;
		try {
			WebDriverWait wait = new WebDriverWait(driver, tempoEspera);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			logErro("Timeout por aguardar pela presen�a do elemento " + by + " por " + tempoEspera + " segundos", e);
		}
	}

	// Altera a janela corrente do teste pelo t�tulo da p�gina passado por
	// par�metro.
	public void alterarJanelaPorTituloPagina(String titulo) throws Exception {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				if (driver.switchTo().window(winHandle).getTitle().equals(titulo)) {
					break;
				}
			}

			pausar(2000);
			logSucesso("Altera o foco para janela com o t�tulo " + titulo);
		} catch (Exception e) {
			logErro("N�o foi poss�vel alterar o foco para a janela com o t�tulo " + titulo, e);
		}
	}

	// Altera a janela corrente do teste pela URL da p�gina passada por
	// par�metro.
	public void alterarJanelaPorURL(String url) throws Exception {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				if (driver.switchTo().window(winHandle).getCurrentUrl().equals(url)) {
					break;
				}
			}

			pausar(2000);
			logSucesso("Altera o foco para janela com a URL " + url);
		} catch (Exception e) {
			logErro("N�o foi poss�vel alterar o foco para janela com a URL " + url, e);
		}
	}

	// Altera o foco para o frame 'ifrm'
	public void alterarFocoFrame() throws Exception {
		String frame = "ifrm";
		int frameZero = 0;

		try {
			driver.switchTo().frame(frame);
			logSucesso("Altera o foco para o frame " + frame);
		} catch (NoSuchElementException e) {
			driver.switchTo().frame(frameZero);
			logErro("N�o foi poss�vel alterar o foco para o frame " + frame + ",o foco foi alterado para o frame "
					+ frameZero, e);
		}
	}

	// Arrasta elemento e solta em um determinado local
	public void arrastarSoltarElemento(By elemento, By localFuturo) throws Exception {
		try {
			WebElement sourceLocator = driver.findElement(elemento);
			WebElement destinationLocator = driver.findElement(localFuturo);

			Actions actions = new Actions(driver);
			actions.dragAndDrop(sourceLocator, destinationLocator).build().perform();

			logSucesso("Arrasta elemento " + elemento + "e solta no local " + localFuturo);
		} catch (Exception e) {
			logErro("N�o foi poss�vel arrastar elemento " + elemento + "e soltar no local " + localFuturo, e);
		}
	}

	// Busca por um texto na p�gina atual
	public boolean buscarPorTextoNaPagina(String texto) throws Exception {
		try {
			driver.getPageSource().contains(texto);
			logSucesso("O texto " + texto + "foi encontrado na p�gina atual: " + driver.getCurrentUrl());
			return true;
		} catch (Exception e) {
			logErro("N�o foi poss�vel encontrar na p�gina atual " + driver.getCurrentUrl() + ", o texto " + texto, e);
			return false;
		}
	}

	/*
	 * M�todo para buscar valores na ComboBox de acordo com a op��o desejada.
	 * Com esse m�todo n�o importa a posi��o em que o valor encontra-se, a busca
	 * ser� feita pelo valor desejado na comboBox.
	 */
	public void buscarValoresNaComboBoxPorTexto(By cliqueSimplesNaCombo, By xpathDaListaElementos,
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
			logSucesso("Seleciona o valor " + valorDesejadoNaCombo + " na lista de elementos " + xpathDaListaElementos);
		} catch (Exception e) {
			logErro("N�o foi poss�vel selecionar o valor " + valorDesejadoNaCombo + " na lista de elementos "
					+ xpathDaListaElementos, e);
		}
	}

	/*
	 * M�todo para buscar valores na Grid/tabela de acordo com a op��o desejada.
	 * Com esse m�todo n�o importa a posi��o em que o valor encontra-se, a busca
	 * ser� feita pelo texto informado.
	 */
	public void buscarValoresNaGridPorTexto(By xpathDoGrid, String textoParaPesquisa) throws Exception {

		try {

			List<WebElement> elementos = driver.findElements((xpathDoGrid));

			for (WebElement elementosGrid : elementos) {

				if (elementosGrid.getText().contains(textoParaPesquisa)) {

					elementosGrid.click();
				}
			}
			logSucesso("Seleciona o valor: " + textoParaPesquisa + " na lista de elementos: " + xpathDoGrid);

		} catch (Exception e) {
			logErro("N�o foi poss�vel selecionar o valor: " + textoParaPesquisa + " na lista de elementos: "
					+ xpathDoGrid, e);
		}
	}

	// Clica no componente passado por par�metro.
	public void clicarElemento(By by, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(by);
			driver.findElement(by).click();
			logSucesso("Clica no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel clicar no elemento " + by, e);
		}
	}

	public void clicarElementoPorIdComandoJavaScript(By id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("document.getElementById(\'" + id + "\').click();");
		js.executeScript(stringBuilder.toString());
	}

	// Verifica se a propriedade �text� do componente em quest�o cont�m o texto
	// passado por par�metro.
	public void contemTextoPresentePorElemento(By by, String texto, String nomeElemento) throws Exception {

		try {
			AssertJUnit.assertTrue(driver.findElement(by).getText().contains(texto));
			logSucesso("Valida que o elemento " + nomeElemento + " cont�m em sua propriedade 'text' o texto " + texto);
		} catch (Exception e) {
			logErro("N�o foi poss�vel validar que o elemento " + by + " contenha em sua propriedade 'text' o texto "
					+ texto, e);
		}
	}

	// Deleta todos os cookies do navegador.
	public void deletarTodosCookies() {
		driver.manage().deleteAllCookies();
	}

	// N�o aceita o alerta (clica no bot�o Cancelar).
	public void dispensarAlerta() throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();

			logSucesso("Dispensa o alert (clica no bot�o 'Cancelar')");
		} catch (Exception e) {
			logErro("N�o foi poss�vel dispensar o alert", e);
		}
	}

	// Realiza duplo clique em um elemento
	public void duploCliqueElemento(By by, String nomeElemento) throws Exception {
		try {
			Actions action = new Actions(driver);
			WebElement element = driver.findElement(by);

			action.doubleClick(element).perform();

			logSucesso("Realiza duplo clique no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel realizar duplo clique no elemento " + by, e);
		}
	}

	// Verifica se o componente est� vis�vel. Propriedade �isDisplayed�.
	public boolean elementoEstaVisivel(By by) {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Verifica se o componente n�o est� vis�vel. Propriedade �isDisplayed�.
	public boolean elementoNaoEstaVisivel(By by) {
		try {
			AssertJUnit.assertFalse(driver.findElement(by).isDisplayed());
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Verifica se o elemento est� presente.
	public boolean elementoEstaPresente(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Verifica se o elemento n�o est� presente.
	public void elementoNaoEstaPresente(By by) throws Exception {
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
	public void escreverTexto(By by, String texto, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(texto);

			logSucesso("Preenche o campo de texto " + nomeElemento + " com o valor " + texto);
		} catch (Exception e) {
			logErro("N�o foi poss�vel preencher o campo de texto " + by, e);
		}
	}

	// Preenche campo de texto (componente) com texto passado por par�metro
	// utilizando comando javascript.
	public void escreverTextoComandoJavaScript(By by, String script, String texto, String nomeElemento)
			throws Exception {
		try {
			aguardarPorElemento(by);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script); // exemplo de script:
			// document.getElementById('form').style=''
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(texto);

			logSucesso("Preenche o campo de texto " + nomeElemento + " com o valor " + texto);
		} catch (Exception e) {
			logErro("N�o foi poss�vel preencher o campo de texto " + by, e);
		}
	}

	public void escreverTextoComNumeroRandomico(By by, String texto, String nomeElemento) throws Exception {
		String textoComNumeroRandomico = texto + gerarNumeroRandomico();
		try {
			aguardarPorElemento(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(textoComNumeroRandomico);

			logSucesso("Preenche o campo de texto " + nomeElemento + " com o valor " + textoComNumeroRandomico);
		} catch (Exception e) {
			logErro("N�o foi poss�vel preencher o campo de texto " + by, e);
		}
	}

	// Verifica se existe alerta sendo exibido no momento.
	public boolean existeAlerta() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void fecharAbaNavegador() {
		driver.close();
	}

	// Realiza file upload de um arquivo armazenado na pasta 'upload'
	public void fileUpload(By by, String arquivo, String nomeElemento) throws Exception {

		//String pathUpload = (userDir + "\\upload\\" + arquivo);
		try {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(arquivo);

			logSucesso("Realiza upload do arquivo " + arquivo + " no campo " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel realizar o upload do arquivo " + arquivo + " no campo " + by, e);
		}
	}

	// Retorna um n�mero rand�mico entre 0 e 100.
	public int gerarNumeroRandomico() {
		Random random = new Random();
		int x = random.nextInt(101);

		return x;
	}

	// Adiciona imagem ao campo 'Capturas de Tela' do log do RQM.
	public void gravarTelaRQM() {

		String cJpeg = DataDriven.nomeCaso + "_" + gerarDataHoraAtual() + "_" + Utils.obterNomeBrowser() + "_" +
		Utils.obterVersaoBrowser() + "_" + Utils.osName + ".jpg";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imageFileDir = System.getProperty("selenium.screenshot.dir");
		if (imageFileDir == null) {
			imageFileDir = System.getProperty("java.io.tmpdir");
		}

		try {
			FileUtils.copyFile(scrFile, new File(imageFileDir, cJpeg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Limpa o campo de texto.
	public void limparCampoTexto(By by, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(by);
			driver.findElement(by).clear();
			logSucesso("Limpa o campo de texto " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel limpar o campo de texto " + by, e);
		}
	}

	// Realiza a a��o 'Mouse Over' no elemento
	public void mouseOverNoElemento(By by) throws Exception {
		try {
			Actions actions = new Actions(driver);
			WebElement mouseHover = driver.findElement(by);
			actions.moveToElement(mouseHover).perform();

			logSucesso("Realiza a a��o 'Mouse Over' no elemento " + by);
		} catch (Exception e) {
			logErro("N�o foi poss�vel realizar a a��o 'Mouse Over' no elemento " + by, e);
		}
	}

	// Realiza a a��o 'Mouse Scroll' de cima para baixo na p�gina
	public void mouseScrollCimaParaBaixo() throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");

			logSucesso("Realiza a a��o 'Mouse Scroll' de cima para baixo na p�gina");
		} catch (Exception e) {
			logErro("N�o foi poss�vel realizar a a��o 'Mouse Scroll' de cima para baixo na p�gina", e);
		}
	}

	// Realiza a a��o 'Mouse Scroll' de baixo para cima na p�gina
	public void mouseScrollBaixoParaCima() throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-500)", "");

			logSucesso("Realiza a a��o 'Mouse Scroll' de baixo para cima na p�gina");
		} catch (Exception e) {
			logErro("N�o foi poss�vel realizar a a��o 'Mouse Scroll' de baixo para cima na p�gina", e);
		}
	}

	// Realiza a a��o 'Mouse Scroll' at� que o elemento seja visualizado
	public void mouseScrollBuscaElemento(By by) throws Exception {
		try {
			WebElement element = driver.findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			logSucesso("Realiza a a��o 'Mouse Scroll' at� que o elemento " + by + "seja visualizado");
		} catch (Exception e) {
			logErro("N�o foi poss�vel realizar a a��o 'Mouse Scroll' para visualizar o elemento " + by, e);
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
	public void pressionarTecla(By by, Keys keyboard) throws Exception {

		aguardarPorElemento(by);
		driver.findElement(by).sendKeys(keyboard);
	}

	// Realiza refresh na p�gina.
	public void refreshPagina() {
		driver.navigate().refresh();
	}

	/*
	 * Tira uma screenshot e a armazena na pasta ExtentReports com o nome
	 * passado por par�metro concatenando com �yyyyMMddHH�, no formato jpg.
	 */
	public static String screenShot(String fileName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String imagem = fileName + "_" + Utils.gerarDataHoraAtual() + "_" + Utils.obterNomeBrowser() + "_"
				+ Utils.obterVersaoBrowser() + "_" + Utils.osName + ".jpg";

		try {

			FileUtils.copyFile(scrFile, new File("saidas/ExtentReports/" + imagem), true);
			return imagem;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagem;
	}

	// Seleciona op��o em combobox pelo texto passado por par�metro.
	public void selecionarElementoPorTexto(By by, String texto, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(by);
			new Select(driver.findElement(by)).selectByVisibleText(texto);

			logSucesso("Seleciona o texto " + texto + " no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel selecionar o texto " + texto + " no elemento " + by, e);
		}
	}

	// Seleciona op��o em combobox pela propriedade value passada por par�metro.
	public void selecionarElementoPorValue(By by, String valor, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(by);
			new Select(driver.findElement(by)).selectByValue(valor);

			logSucesso("Seleciona o value " + valor + " no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel selecionar o value " + valor + " no elemento " + by, e);
		}
	}

	// Seleciona op��o em combobox pela propriedade index passada por par�metro.
	public void selecionarElementoPorIndex(By by, int index, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(by);
			new Select(driver.findElement(by)).selectByIndex(index);

			logSucesso("Seleciona o index " + index + " no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel selecionar o index " + index + " no elemento " + by, e);
		}
	}

	// Seleciona op��o em combobox pelo texto passado por par�metro utilizando
	// comando javascript.
	public void selecionarElementoPorTextoComandoJavaScript(By by, String script, String texto, String nomeElemento)
			throws Exception {
		try {
			pausar(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script); // exemplo de script:
										// document.getElementById('form').style=''
			new Select(driver.findElement(by)).selectByVisibleText(texto);

			logSucesso("Seleciona o texto " + texto + " no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel selecionar o texto " + texto + " no elemento " + by, e);
		}
	}

	// Seleciona op��o em combobox pela propriedade value passada por par�metro
	// utilizando comando javascript.
	public void selecionarElementoPorValueComandoJavaScript(By by, String script, String valor, String nomeElemento)
			throws Exception {
		try {
			pausar(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script); // exemplo de script:
										// document.getElementById('form').style=''
			new Select(driver.findElement(by)).selectByValue(valor);

			logSucesso("Seleciona o value " + valor + " no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel selecionar o value " + valor + " no elemento " + by, e);
		}
	}

	// Seleciona op��o em combobox pela propriedade index passada por par�metro
	// utilizando comando javascript.
	public void selecionarElementoPorIndexComandoJavaScript(By by, String script, int index, String nomeElemento)
			throws Exception {
		try {
			pausar(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script); // exemplo de script:
										// document.getElementById('form').style=''
			new Select(driver.findElement(by)).selectByIndex(index);

			logSucesso("Seleciona o index " + index + " no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("N�o foi poss�vel selecionar o index " + index + " no elemento " + by, e);
		}
	}

	// Verifica se o texto passado por par�metro n�o esta presente na
	// propriedade �text� do componente em quest�o.
	public void textoNaoPresentePorElemento(By by, String textoEsperado) throws Exception {
		try {
			AssertJUnit.assertFalse(driver.findElement(by).getText().contains(textoEsperado));
			logSucesso("Valida que o texto " + textoEsperado + " n�o esteja presente no elemento " + by);
		} catch (Exception e) {
			logErro("O texto " + textoEsperado + " est� presente na propriedade 'text' do elemento " + by, e);
		}
	}

	//// Verifica se o texto passado por par�metro esta presente na propriedade
	//// �text� do componente em quest�o.
	public void textoPresentePorElemento(By by, String textoEsperado) throws Exception {
		try {
			AssertJUnit.assertEquals(textoEsperado, driver.findElement(by).getText());
			logSucesso("Valida que o texto " + textoEsperado + " esteja presente no elemento " + by);
		} catch (Exception e) {
			logErro("O texto " + textoEsperado + " n�o est� presente na propriedade 'text' do elemento " + by, e);
		}
	}

	// Verifica se o componente em quest�o est� selecionado (checkbox por
	// exemplo).
	public void validarComponenteSelecionado(By by, String nomeElemento) throws Exception {
		try {
			AssertJUnit.assertTrue(driver.findElement(by).isSelected());
			logSucesso("Valida que o elemento " + by + " esteja selecionado");
		} catch (Exception e) {
			logErro("O elemento " + nomeElemento + " n�o est� selecionado", e);
		}
	}

	// Verifica se o componente em quest�o est� habilitado.
	public void validarComponenteHabilitado(By by, String nomeElemento) throws Exception {
		try {
			AssertJUnit.assertTrue(driver.findElement(by).isEnabled());
			logSucesso("Valida que o elemento " + by + " esteja habilitado");
		} catch (Exception e) {
			logErro("O elemento " + nomeElemento + " n�o est� habilitado", e);
		}
	}

	// Verifica se o componente em quest�o n�o est� selecionado (checkbox por
	// exemplo).
	public void validarComponenteNaoSelecionado(By by, String nomeElemento) throws Exception {
		try {
			AssertJUnit.assertFalse(driver.findElement(by).isSelected());
			logSucesso("Valida que o elemento " + by + " n�o esteja selecionado");
		} catch (Exception e) {
			logErro("O elemento " + nomeElemento + " est� selecionado", e);
		}
	}

	// Verifica se o componente em quest�o n�o est� habilitado.
	public void validarComponenteDesabilitado(By by, String nomeElemento) throws Exception {
		try {
			AssertJUnit.assertFalse(driver.findElement(by).isEnabled());
			logSucesso("Valida que o elemento " + by + " n�o esteja habilitado");
		} catch (Exception e) {
			logErro("O elemento " + nomeElemento + " est� habilitado", e);
		}
	}

	/**
	 * Ler dois arquivos de texto, verifica as diferen�as do primeiro para o
	 * segundo arquivo e do segundo para o primeiro arquivo e valida se o
	 * conte�do dos arquivos s�o iguais.
	 */
	public void validarExisteDiferencaEntreDoisArquivosTxt(String primeiroArquivo, String segundoArquivo)
			throws Exception {

		List<String> diferencaDoPrimeiroParaSegundo = new ArrayList<String>();
		List<String> diferencaDoSegundoParaPrimeiro = new ArrayList<String>();

		List<String> linhasA = Files.readAllLines(Paths.get(primeiroArquivo));
		List<String> linhasB = Files.readAllLines(Paths.get(segundoArquivo));

		diferencaDoPrimeiroParaSegundo.addAll(linhasA);
		diferencaDoPrimeiroParaSegundo.removeAll(linhasB);

		diferencaDoSegundoParaPrimeiro.addAll(linhasB);
		diferencaDoSegundoParaPrimeiro.removeAll(linhasA);

		/*
		 * System.out.println("\n");
		 * System.out.println("Diferen�a do primeiro para o segundo arquivo: " +
		 * diferencaDoPrimeiroParaSegundo);
		 * System.out.println("Diferen�a do segundo para o primeiro arquivo: " +
		 * diferencaDoSegundoParaPrimeiro); System.out.println("\n");
		 */

		if (linhasA.equals(linhasB)) {
			logSucesso("Os dois arquivos de texto s�o iguais.");
		}

		else {

			// Log4j
			Logger log = Logger.getLogger(getClass());
			log.error("Os dois arquivos de texto s�o diferentes. A diferen�a do primeiro para o segundo arquivo �: "
					+ diferencaDoPrimeiroParaSegundo + ", a diferen�a do segundo para o primeiro arquivo �: "
					+ diferencaDoSegundoParaPrimeiro);

			// (Extent Reports) -> Adiciona screenshot ao relat�rio
			String imagem = Utils.screenShot(DataDriven.nomeCaso);
			logger.log(LogStatus.FAIL,
					"Os dois arquivos de texto s�o diferentes. A diferen�a do primeiro para o segundo arquivo �: "
							+ diferencaDoPrimeiroParaSegundo + ", a diferen�a do segundo para o primeiro arquivo �: "
							+ diferencaDoSegundoParaPrimeiro + logger.addScreenCapture(imagem));

			// Finaliza o teste
			report.endTest(logger);

			// Acrescenta o arquivo HTML com todos os testes finalizados
			report.flush();

			// Adiciona o relat�rio .pdf de erro
			gerarRelatorioPDFErro();

			throw new Exception();
		}
	}

	// Valida se na URL atual cont�m o texto (trecho da URL).
	public void validarURLAtualContemString(String trechoURL) throws Exception {
		try {
			AssertJUnit.assertTrue(driver.getCurrentUrl().contains(trechoURL));
			logSucesso("Valida que a URL atual " + driver.getCurrentUrl() + " contenha o trecho " + trechoURL);
		} catch (Exception e) {
			logErro("A URL atual " + driver.getCurrentUrl() + " n�o cont�m o trecho " + trechoURL, e);
		}
	}

	// Retorna � tela anterior.
	public void voltarTelaAnterior() throws Exception {
		try {
			driver.navigate().back();
			logSucesso("Retorna � tela anterior. A URL atual agora � " + driver.getCurrentUrl());
		} catch (Exception e) {
			logErro("N�o foi poss�vel retornar � tela anterior. A URL atual � " + driver.getCurrentUrl(), e);
		}
	}

	// Loga no SISGR (usu�rio com acesso ao Sistema)
	public void logarSISGRUsuarioComAcesso() throws Exception {

		try {
			acessarURL(DataDriven.url);
			/*
			 * escreverTexto(By.id("usuario"), DataDriven.usuario, "Usuario");
			 * escreverTexto(By.id("senha"), DataDriven.senha, "Senha");
			 */
			clicarElemento(By.cssSelector("a > img[name=\"ok\"]"), "Bot�o OK");
			elementoEstaPresente(By.linkText(DataDriven.nomeSistema));

			logSucesso("Login no SISGR com sucesso, usu�rio com acesso ao sistema " + DataDriven.nomeSistema);
		} catch (Exception e) {
			logErro("N�o foi poss�vel validar o acesso ao SISGR, sistema " + DataDriven.nomeSistema
					+ "com este usu�rio/senha", e);
		}
	}

	// Loga no SISGR (usu�rio sem acesso ao Sistema)
	public void logarSISGRUsuarioSemAcesso() throws Exception {

		try {
			acessarURL(DataDriven.url);
			/*
			 * escreverTexto(By.id("usuario"), DataDriven.usuario, "Usuario");
			 * escreverTexto(By.id("senha"), DataDriven.senha, "Senha");
			 */
			clicarElemento(By.cssSelector("a > img[name=\"ok\"]"), "Bot�o OK");
			elementoNaoEstaPresente(By.linkText(DataDriven.nomeSistema));

			logSucesso("Login no SISGR com sucesso, usu�rio sem acesso ao sistema " + DataDriven.nomeSistema);
		} catch (Exception e) {
			logErro("N�o foi poss�vel validar o acesso ao SISGR com usu�rio sem permiss�o ao sistema "
					+ DataDriven.nomeSistema, e);
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

	public void validarExisteDiferencaEntreDoisArquivosXmls(String primeiroXML, String segundoXML) throws Exception {

		FileInputStream xml1 = new FileInputStream(primeiroXML);
		FileInputStream xml2 = new FileInputStream(segundoXML);

		BufferedReader source = new BufferedReader(new InputStreamReader(xml1));
		BufferedReader target = new BufferedReader(new InputStreamReader(xml2));

		XMLUnit.setIgnoreWhitespace(true);

		List<?> diferencas = comparaXML(source, target);

		// showing differences found in two xml files

		int totalDiferencas = diferencas.size();

		if (totalDiferencas == 0) {
			logSucesso("Os dois arquivos xml s�o iguais.");

			printaDiferencasEntreXMLs(diferencas);
		}

		else {

			// Log4j
			Logger log = Logger.getLogger(getClass());
			log.error("Os dois arquivos xml s�o diferentes. As diferen�as entre eles s�o:" + diferencas);

			// (Extent Reports) -> Adiciona screenshot ao relat�rio
			String imagem = Utils.screenShot(DataDriven.nomeCaso);
			logger.log(LogStatus.FAIL, "Os dois arquivos xml s�o diferentes. As diferen�as entre eles s�o:" + diferencas
					+ logger.addScreenCapture(imagem));

			printaDiferencasEntreXMLs(diferencas);

			// Finaliza o teste
			report.endTest(logger);

			// Acrescenta o arquivo HTML com todos os testes finalizados
			report.flush();

			// Adiciona o relat�rio .pdf de erro
			gerarRelatorioPDFErro();

			throw new Exception();
		}
	}

	public static List<?> comparaXML(Reader source, Reader target) throws SAXException, IOException {

		Diff xmlDiff = new Diff(source, target);

		DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
		return detailXmlDiff.getAllDifferences();
	}

	public static void printaDiferencasEntreXMLs(List<?> diferencas) {

		int totalDiferencas = diferencas.size();

		System.out.println("===============================");

		System.out.println("Total de diferen�as entre os arquivos xml: " + totalDiferencas);

		System.out.println("================================");

		for (Object diferenca : diferencas) {

			System.out.println(diferenca);
		}
	}

	public static String obterVersaoBrowser() {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		Object strBrowserVersion = capabilities.getVersion();
		if (strBrowserVersion == "") {
			strBrowserVersion = capabilities.getCapability("browserVersion");
		} else {
			return (String) strBrowserVersion;
		}

		return (String) strBrowserVersion;
	}

	public static String obterNomeBrowser() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();

		return browserName;
	}
}
