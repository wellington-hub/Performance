package grid_paralelo;

import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ftor_testng.DataDriven;

/*********************************************************************************
 * SuperClass -> Classe respons�vel pela implementa��o dos m�todos referentes �
 * tela de resultado da consulta por pre�o e prazo de entrega que posteriormente
 * ser�o chamados dentro dos casos de teste.
 *********************************************************************************/
public class SuperClass extends Utils {

	// L� arquivo 'config.properties e cria objeto properties'
	InputStream input = getClass().getResourceAsStream("config.properties");
	Properties properties = new Properties();

	public void acessarURL(WebDriver driver) throws Exception {

		// Load do arquivo config.properties
		properties.load(input);

		// Seta para cada par�metro necess�rio do arquivo config uma vari�vel
		String url = properties.getProperty("url");

		// Acessa a URL
		acessarURL(driver, url);
		printaLogThread("Acesso � URL ");
	}
	
	
//USUARIO1
	public void preencherUsuario(WebDriver driver) throws Exception {

		// Load do arquivo config.properties
		properties.load(input);

		// Seta para cada par�metro necess�rio do arquivo config uma vari�vel
		String usuario = properties.getProperty("usuario");

		escreverTexto(driver, By.id("codigoCredencial"), usuario, "Usuario");

		// Preenche o USER
		// escreverTexto(driver, By.name("usuario"), usuario, "Usuario");
		printaLogThread("Preenche o Usuario ");

	}

	//USUARIO2

	public void preencherUsuario2(WebDriver driver) throws Exception {

		// Load do arquivo config.properties
		properties.load(input);

		// Seta para cada par�metro necess�rio do arquivo config uma vari�vel
		String usuario2 = properties.getProperty("usuario2");

		escreverTexto(driver, By.id("codigoCredencial"), usuario2, "Usuario2");

		// Preenche o USER
		// escreverTexto(driver, By.name("usuario"), usuario, "Usuario");
		printaLogThread("Preenche o Usuario2 ");

	}

	public void preencherSenha(WebDriver driver) throws Exception {

		// Load do arquivo config.properties
		properties.load(input);

		// Seta para cada par�metro necess�rio do arquivo config uma vari�vel
		String senha = properties.getProperty("senha");

		// Preenche SENHA
		escreverTexto(driver, By.name("senha"), senha, " Senha");

		printaLogThread("Preenche o campo Senha ");

	}

	public void preencherSenha2(WebDriver driver) throws Exception {

		// Load do arquivo config.properties
		properties.load(input);

		// Seta para cada par�metro necess�rio do arquivo config uma vari�vel
		String senha2 = properties.getProperty("senha2");

		// Preenche SENHA
		escreverTexto(driver, By.name("senha"), senha2, " Senha2");

		printaLogThread("Preenche o campo Senha2 ");

	}

	public void clicarBotao(WebDriver driver) throws Exception {

		clicarElemento(driver, By.id("idBotao"), "clica botao  Acessar");
		pausar(3000);
		printaLogThread("Clicar botao  ");
		
		

	}
	
	
	
	
	
	

}
