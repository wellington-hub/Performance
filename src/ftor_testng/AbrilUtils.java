package ftor_testng;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*********************************************************************************
 * Superclasse -> Classe responsável pela implementação dos métodos referentes à
 * tela de resultado da consulta por preço e prazo de entrega que posteriormente
 * serão chamados dentro dos casos de teste.
 *********************************************************************************/
public class AbrilUtils extends Utils {

	public void acessarURL() throws Exception {
		acessarURL(DataDriven.url);
	}

	public void urlpreProd() throws Exception {
		acessarURL(DataDriven.urlpreProd);
	}

	public void acessarURLFechada() throws Exception {

		acessarURL(DataDriven.urlFechada);
	}

	public void acessarUrlProd() throws Exception {
		acessarURL(DataDriven.urlProd);
	}

	public void clicarEntrar() throws Exception {

		clicarElemento(By.id("login"), "Entrar");
		pausar(2000);
	}

	public void clicarSouAssinate() throws Exception {

		// scrol
		mouseScrollCimaParaBaixo();
		pausar(2000);
		textoPresentePorElemento(By.xpath("//*[@id='injected-paywall_info']/a"), ("Se já é assinante, entre aqui."));
		pausar(2000);
		clicarElemento(By.xpath("//*[@id='injected-paywall_info']/a"), "Entre aqui.");
		pausar(2000);

	}

	public void preencherUser() throws Exception {

		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("asi_screen"))); // Frame

		escreverTexto((By.xpath("//*[@id='email']")), DataDriven.usuario, "Usuario");
		pausar(2000);

	}

	public void preencherSenha() throws Exception {

		escreverTexto(By.id("senha"), DataDriven.senha, "Senha");
		pausar(2000);

	}

	public void logar() throws Exception {

		driver.findElement(By.id("login")).click();
	}

	public void clicarMenu() throws Exception {

		driver.findElement(By.xpath("/html/body/header/div/div/div[1]/button/span")).click();
	}

	public void clicarSair() throws Exception {

		driver.findElement(By.xpath("//*[@id='login']")).click();

	}

	public void validarUser() throws Exception {

		textoPresentePorElemento(By.id("userName"), ("Olá, QA"));
	}

	// abri nova aba

	public void Navegar1() throws Exception {

		acessarURL(DataDriven.url1);
		pausar(2000);
		clicarElemento(By.id("aceitoLGPD"), "Aceito Cookies");
		pausar(2000);
		clicarElemento(By.id("close-piano-notice"), "close-piano-notice");
	}

	public void Explorar1() throws Exception {
		// dispensarAlerta();
		clicarElemento(By.xpath("//*[@id='menu-item-3323949']/a/span"), "Politica");
		pausar(2000);
		mouseScrollCimaParaBaixo();
		clicarElemento(By.xpath("//*[@id='menu-item-3323946']/a/span"), "radar economico");
		pausar(2000);
		mouseScrollBaixoParaCima();
	}

	public void Navegar2() throws Exception {
		acessarURL(DataDriven.url2);
		pausar(2000);

		// clicarElemento(By.id("aceitoLGPD"), "Aceito Cookies");
		// pausar(2000);
		// clicarElemento(By.id("close-piano-notice"),"close-piano-notice");
	}

	public void Explorar2() throws Exception {
		clicarElemento(By.xpath("//*[@id='menu-item-3323948']/a/span"), "Economia");
		pausar(2000);
		mouseScrollCimaParaBaixo();

	}

	public void Navegar3() throws Exception {
		acessarURL(DataDriven.url3);
		pausar(2000);
		// clicarElemento(By.id("aceitoLGPD"), "Aceito Cookies");
		// pausar(2000);
		// clicarElemento(By.id("close-piano-notice"),"close-piano-notice");
	}

	public void Explorar3() throws Exception {
		clicarElemento(By.xpath("//*[@id='menu-item-3323945']/a/span"), "RADAR");
		pausar(2000);
		mouseScrollCimaParaBaixo();

	}

	public void autenticacaoGmail() throws Exception {

		acessarURL(DataDriven.urlEnvio);
		pausar(2000);
		driver.findElement(By.xpath("/html/body/header/div/aside/div/nav/ul/li[2]/a")).click();// Entrar
		pausar(2000);

		driver.findElement(By.id("i0116")).click();
		driver.findElement(By.id("i0116")).clear();
		driver.findElement(By.id("i0116")).sendKeys("qqaautomatizado2021@hotmail.com");
		driver.findElement(By.id("idSIButton9")).click();
		driver.findElement(By.name("passwd")).clear();
		pausar(2000);
		driver.findElement(By.name("passwd")).sendKeys("We34873793092");
		pausar(2000);
		driver.findElement(By.id("idSIButton9")).click();
		pausar(2000);
		driver.findElement(By.xpath("//*[@id='KmsiCheckboxField']")).click();
		pausar(2000);
		driver.findElement(By.id("idSIButton9")).click();

		pausar(2000);

	}

	public void escolhaAnexo() throws Exception {

		driver.findElement(By
				.xpath("//div[@id='ReadingPaneContainerId']/div/div/div/div/div[3]/div[2]/div[2]/div/div/div/div/div/div/div/button/span/i"))
				.click();
		driver.findElement(By.xpath("//div[@id='id__427-menu']/div/ul/li[6]/button/div/span")).click();

		// fileUpload(By.xpath("//*[@id='id__439-menu']/div/ul/li[2]/button/div"),"_002LogarGmail_2021092518_chrome_94.0.4606.61_Windows
		// 10","Relatorios");
		// UPLOAD ARQUIVO

	}

	public void Send() throws Exception {
		// driver.findElement(By.xpath("//span[@id = 'id__416' and (text() =
		// 'Enviar' or . = 'Enviar')]")).click(); //Enviando

	}

	public void acessarURLAMP() throws Exception {
		acessarURL(DataDriven.urlAMP);
	}

	public void inserirURL() throws Exception {

		driver.findElement(By.cssSelector("div.JGptt:nth-child(2) > input:nth-child(1)")).sendKeys(
				"https://veja.abril.com.br/economia/previsao-para-inflacao-sobe-pela-20a-semana-consecutiva-e-chega-a-711/amp");
		pausar(2000);

		// fechar pop
		// fazTAB(By.cssSelector("div.JGptt:nth-child(2) >
		// input:nth-child(1)"));
		// fazTAB(By.cssSelector("div.JGptt:nth-child(2) >
		// input:nth-child(1)"));
		// pausar(2000);

		// TESTAR URL
		driver.findElement(By.cssSelector("#AtnjWb>c-wiz>div.KlI29c.fmjis>div>span>span>div")).click();

		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("asi_screen"))); // Frame

		// FECHAR CAPTION
		alterarJanelaPorURL("https://search.google.com/test/amp");
	}

	public void visualizarResultados() throws Exception {
		driver.findElement(By
				.xpath("//*[@id='yDmH0d']/c-wiz[3]/c-wiz/div/div[2]/c-wiz/div/div[3]/c-wiz/c-wiz/div/span/div[2]/div/div/div[1]/div/div[1]/span/div/div/div[2]/c-wiz/div/span/div/span/span/div/span[2]"))
				.click();

	}

	public void AceitoLGPD() throws Exception {

		clicarElemento(By.id("aceitoLGPD"), "Aceito Cookies");

	}

	public void VerOfertas() throws Exception {

		mouseScrollCimaParaBaixo();
		pausar(2000);
		aguardarPorElemento(By.xpath("//*[@id='abril_barra_assine_widget-10']/section/div/div/div[4]/a[2]"));
		pausar(2000);

		// *[@id="abril_barra_assine_widget-6"]/section/div/div/div[4]/a[2]
		// *[@id="abril_barra_assine_widget-6"]/section/div/div/div[1]/a[2]
		driver.findElement(By.xpath("//*[@id='abril_barra_assine_widget-10']/section/div/div/div[4]/a[2]")).click();
	}

	public void VerOfertasVeja() throws Exception {

		mouseScrollCimaParaBaixo();
		pausar(4000);
		aguardarPorElemento(By.xpath("//*[@id='abril_barra_assine_widget-6']/section/div/div/div[1]/a[2]"));
		pausar(4000);
		driver.findElement(By.xpath("//*[@id='abril_barra_assine_widget-6']/section/div/div/div[1]/a[2]")).click();
		pausar(4000);
	}

	public void Assine() throws Exception {

		aguardarPorElemento(By.xpath("//*[@id='new-paywall-parent-cards']/div[4]/div/div[6]/a/button"));
		pausar(4000);
		driver.findElement(By.xpath("//*[@id='new-paywall-parent-cards']/div[4]/div/div[6]/a/button")).click();
	}

	public void EscolhaPlano() throws Exception {

	}

	public void DadosIdentificaçao() throws Exception {
		escreverTexto((By.id("identificacao_nome_completo")), DataDriven.identificacaonomecompleto,
				"Iserir Nome Completo");
		pausar(2000);

		escreverTexto((By.id("identificacao_email")), DataDriven.identificacaoemail, "Inserir Email");
		pausar(6000);
		aguardarPorElemento(By.id("identificacao_telefone"));
		driver.findElement(By.id("identificacao_telefone")).sendKeys("211234-5678");
		//escreverTexto((By.id("identificacao_telefone")), DataDriven.identificacaotelefone, "Inserir Tel");
		pausar(4000);

	}

	public void DadosEntrega() throws Exception {

		escreverTexto((By.id("endereco_cep")), DataDriven.enderecocep, "Endereco cep");
		pausar(2000);

		escreverTexto((By.id("endereco_logradouro")), DataDriven.enderecologradouro, "Endereco logradouro");
		pausar(2000);

		escreverTexto((By.id("endereco_numero")), DataDriven.endereconumero, "Endereco numero");
		pausar(2000);

		escreverTexto((By.id("endereco_complemento")), DataDriven.enderecocomplemento, "Endereco complemento");
		pausar(2000);

		escreverTexto((By.id("endereco_bairro")), DataDriven.enderecobairro, "Endereco bairro");
		pausar(2000);

		// seleionar combobox
		selecionarElementoPorTexto(By.id("endereco_uf"), "RJ", "RJ");
		// escreverTexto((By.id("endereco_uf")), DataDriven.enderecouf,
		// "Endereco uf");

		escreverTexto((By.id("endereco_cidade")), DataDriven.enderecocidade, "Endereco cidade");
		pausar(2000);
	}

	public void Pagamento() throws Exception {
		escreverTexto((By.id("pagamento_cartao_credito_nome")), DataDriven.pagamentocartaocreditonome,
				"Inserir Nome Cartao 1");
		pausar(2000);
		// fazTAB(By.id("pagamento_cartao_credito_nome"));
		pausar(2000);

		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("js-iframe-input input-field"))); // Frame
		escreverTexto((By.xpath("//*[@id='encryptedCardNumber']")), DataDriven.CardNumber1, "Inserir Numero Cartao 1");
		// pausar(2000);

		// *[@id="component-container-cartao"]/div/div/div[2]/div/div[1]/label/span[2]/span/iframe

		// v1 by containing name, probably the safest option
		// WebElement iframe_by_name_contains =
		// driver.findElement(By.xpath("//iframe[contains(@name,'__privateStripeFrame')]"));
		// driver.switchTo().frame(iframe_by_name_contains);

		// v2 by name - might not be goood if the 2445 is dynamic
		// WebElement iframe_by_name =
		// driver.findElement(By.xpath("//iframe[@name='__privateStripeFrame2445']"));
		// driver.switchTo().frame(iframe_by_name);

		// by title - might not be good in case that there are locales and the
		// title is translated
		// WebElement iframe_by_title =
		// driver.findElement(By.xpath("//iframe[@title='Secure card number
		// input frame']"));
		// driver.switchTo().frame(iframe_by_title);

		// WebElement iframe_by_title =
		// driver.findElement(By.id("encryptedCardNumber"));
		// driver.switchTo().frame(iframe_by_title);
		// pausar(2000);

		// new WebDriverWait(driver,
		// 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("js-iframe")));
		// // Frame
		// teste 1
		// escreverTextoComandoJavaScript(By by, String script, String texto,
		// String nomeElemento)
		// escreverTextoComandoJavaScript(By.className("adyen-checkout__input
		// adyen-checkout__input--large adyen-checkout__card__cardNumber__input
		// sMjS8HCbKiP5yR9Td9ZgQ"),"document.getElementById('component-container-cartao').style=''","5591293622120470","Número
		// do cartão");

		// teste2
		// escreverTextoComandoJavaScript(By.cssSelector("component-container-cartao>div>div>div.adyen-checkout__card-input__form._3jXHyitM6npsG088lgDkjo>div>div.adyen-checkout__field.adyen-checkout__field--cardNumber>label>span.adyen-checkout__input-wrapper>span"),"document.getElementById('form').style=''","5591293622120470","Número
		// do cartão");

		// teste 3

		// escreverTexto((By.className("adyen-checkout__input
		// adyen-checkout__input--large adyen-checkout__card__cardNumber__input
		// sMjS8HCbKiP5yR9Td9ZgQ")), DataDriven.CardNumber1, "Inserir Numero
		// Cartao 1");
		// pausar(2000);

		// get input field
		// driver.findElement(By.name("cardnumber")).sendKeys("4242424242424242");

		// fazTAB(By.xpath("//*[@id='component-container-cartao']/div/div/div[2]/div/div[1]/label/span[2]/span"));
		escreverTexto((By.xpath("//*[@id='encryptedCardNumber']")), DataDriven.ExpiryDate, "Inserir Dt Validade 1");
		pausar(2000);

		// fazTAB(By.id("encryptedExpiryDate"));
		escreverTexto((By.xpath("//*[@id='encryptedExpiryDate']")), DataDriven.SecurityCode, "Inserir Cvc Cvv 1");
		pausar(2000);

		escreverTexto((By.id("pagamento_cartao_credito_cpf_cnpj")), DataDriven.pagamentocartaocreditocpfcnpj,
				"Inserir Cpf");
		pausar(2000);

	}

	// Parcelamentos
	public void EscolhaParcelamento() throws Exception {

	}

	// Pagamento Debito em conta
	public void EscolhaDebitoConta() throws Exception {

		// selecionarElementoPorTexto(By by, String texto, String nomeElemento)
		driver.findElement(By.xpath("//fieldset[@id='pagamento']/ul/li[2]/p")).click();
		//selecionarElementoPorTexto(By.xpath("//fieldset[@id='pagamento']/ul/li[2]/p"), "Débito em conta", "Débito em conta");
		
		
		//aguardarPorElemento(By.id("janelaDC"));
		//pausar(2000);
//		driver.findElement(By.id("janelaDC")).click();

	}

	public void PreencheDebitoConta() throws Exception {

		// nometitular id = pagamento_debito_conta_nome
		escreverTexto((By.id("pagamento_debito_conta_nome")), DataDriven.identificacaonomecompleto,
				"Nome do titular da conta");
		pausar(2000);

		// combobox tipo banco
		// seleionar combobox
		 selecionarElementoPorTexto(By.id("pagamento_debito_conta_banco"),"Banco Itaú", "Banco Itaú");

		// cpf ou cnpj
		driver.findElement(By.id("pagamento_debito_conta_cpf_cnpj")).click();
		driver.findElement(By.id("pagamento_debito_conta_cpf_cnpj")).clear();
		driver.findElement(By.id("pagamento_debito_conta_cpf_cnpj")).sendKeys("476.794.400-77");
		//escreverTexto((By.id("pagamento_debito_conta_cpf_cnpj")), DataDriven.pagamentodebitocontacpfcnpj, "Cpf Cnpj");
		pausar(2000);
		// ag e digito
		escreverTexto((By.id("pagamento_debito_conta_agencia")), DataDriven.pagamentodebitocontaagencia, "Agencia");
		pausar(2000);
		//escreverTexto((By.id("pagamento_debito_conta_agencia_digito")), DataDriven.pagamentodebitocontaagenciadigito,
			//	"Digito");
		//pausar(2000);
		// conta e digito
		escreverTexto((By.id("pagamento_debito_conta_conta")), DataDriven.pagamentodebitocontaconta, "Conta");
		pausar(2000);
		//escreverTexto((By.id("pagamento_debito_conta_conta_digito")), DataDriven.pagamentodebitocontacontadigito,
				//"Digito Conta");
		//pausar(2000);

	}

	// Pagamento Boleto bancario
	public void EscolhaBoleto() throws Exception {

	}

	// Fechar Compra
	public void FecharCompra() throws Exception {
		aguardarPorElemento(By.id("validar_formulario"));
		pausar(2000);
		clicarElemento(By.xpath("//*[@id='validar_formulario']/div[3]/fieldset/button"), "Fechar compra");
		pausar(2000);
	}

	// Validações

	public void validarAcessoMateriaFechada() throws Exception {

		mouseScrollCimaParaBaixo();
		pausar(2000);
		textoPresentePorElemento(By.xpath("//*[@id='injected-paywall_info']/a"), ("Se já é assinante, entre aqui."));
		// mouseScrollBaixoParaCima();
		// pausar(2000);
		// textoPresentePorElemento(By.xpath("//*[@id='injected-paywall_info']/a"),
		// ("Se já é assinante, entre aqui."));

	}

}
