package ftor_testng;

import org.openqa.selenium.By;

/*********************************************************************************
 * Superclasse -> Classe respons�vel pela implementa��o dos m�todos referentes �
 * tela de resultado da consulta por pre�o e prazo de entrega que posteriormente
 * ser�o chamados dentro dos casos de teste.
 *********************************************************************************/
public class CalculoPrecoPrazoUtils extends Utils {

	public void acessarURL() throws Exception {

		acessarURL(DataDriven.url);
	}

	public void preencherUsuario() throws Exception {
		
		escreverTexto(By.name("usuario"), DataDriven.usuario, "Usuario");
	}

	public void preencherSenha() throws Exception {

		escreverTexto(By.name("senha"), DataDriven.senha, "Senha");
	}
	
	
	public void clicarBotao() throws Exception {

			clicarElemento(By.xpath("//button[@id='idBotao']"), "Acessar"); // funcionou
			pausar(3000);
		
		
	}
	
	
}
