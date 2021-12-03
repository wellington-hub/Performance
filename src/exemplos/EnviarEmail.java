/****
 * Script Name	 : <b> ScriptEnviarEmail</b>
 * Generated	 : <b>18/11/2021 10:00</b>
 * Description	 : Functional Test Script
 * Original Host : Win10 version xx Build xxx (s)
 *  
 * @since  2021/11/18
 * @author Wellington Medeiros..
 * 
 * Baixar os jars
 *		
 * commons-email-1.5.jar
 *	mail-1.4.7.jar
 * 
 * 
 *	Habilitando email
 * 	No caso você deve configurar o e-mail que você está usando. 
 * 	Acesse o link: https://myaccount.google.com
 * 	Clique no menu chamado "Segurança"
 * 	Procure a função "Acesso a app menos seguro" e desative.
 * 
 * 	Sendo possível o envio de e-mail! 
 * 	Espero ter ajudado, abrçs!
 *
 * 
 */

package exemplos;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.relevantcodes.extentreports.LogStatus;

import ftor_testng.ReportLog;

public class EnviarEmail extends ReportLog{
	
	public static void main(String[] args) {
		
		//Declaração de duas variaveis
		
		String meuEmail = "wellington.medeiros@abril.com.br";
		//String EmailTester = "thiago.silva@abril.com.br";
		String minhaSenha = "@grupoabril2022@*";
		
		
		//Objeto SimpleEmail  usado para email simples sem anexo
		
		//SimpleEmail email = new SimpleEmail();
		
		
		//Para o exemplo do Anexo arquivos  MultiPartEmail
		
		MultiPartEmail email = new MultiPartEmail();
		
		//config host, porta
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		
		//Autenticação do e-mail , passando email e senha
		email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
		
		//Ativação do protocolo SSL
		email.setSSLOnConnect(true);
		
		//Bloco de excessao
		
		try {
			//De quem esse email?
			email.setFrom(meuEmail);
			
			//Assunto
			email.setSubject("Relatorios Grupo Abril com anexo");
			
			//Mensagem corpo email
			
			// Extent Reports
//			Throwable mensagem = null;
//			ReportLog.logger.log(LogStatus.PASS, mensagem);
			//email.setMsg("Testando envio de email relatorios pdf , anexos abaixo...");
						
			email.setMsg("mensagem");
			//como pego isso ->  document.add(p);  que é RESUMO DA EXECUÇÃO DE CASO DE TESTE, que veio da classe Reports ,  dentro da string acima 
			
			//Para quem é o envio do email
			email.addTo(meuEmail);
			//email.addTo(EmailTester);			
			
			//codigo que vai anexar 1 arq nesse email algum arquivo
			
//			EmailAttachment anexo = new EmailAttachment();
//			anexo.setPath("C:/Desenv/Projetos/Performance/GrupoAbril/Desafios/GrupoAbril/saidas/PDFReport/_005LoginOrgBlockFechadoWEB_2021111712_chrome_95.0.4638.69_Windows 7.pdf");
//			anexo.setName("Arquivo LoginOrgBlockFechadoWEB.pdf");
//			email.attach(anexo);
			//email.send();
			
			// criando vetor de anexo para varios arquivos
			
			EmailAttachment [] anexos = new EmailAttachment[2];
			anexos[0] = new EmailAttachment();			
			anexos[0].setPath("C:/Desenv/Projetos/BKP/workspace_ftor_testng_servidor3/ProjetoExemplo/saidas/PDFReport/_005LoginOrgBlockFechadoWEB_2021113013_chrome_96.0.4664.45_Windows 7.pdf");
			anexos[0].setName("Arquivo LoginOrgBlockFechadoWEB.pdf");
			
			anexos[1] = new EmailAttachment();			
			anexos[1].setPath("C:/Desenv/Projetos/BKP/workspace_ftor_testng_servidor3/ProjetoExemplo/saidas/PDFReport/_005LoginOrgBlockFechadoWEB_2021113013_chrome_96.0.4664.45_Windows 7.pdf");
			anexos[1].setName("Arquivo LoginOrgBlockFechadoWEB2.pdf");
			
			
			
			
			email.attach(anexos[0]);
			email.attach(anexos[1]);			
			
			
			//Metodo send para confirmar o envio
			email.send();
			System.out.println("Email foi enviado com sucesso !");
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	

}
