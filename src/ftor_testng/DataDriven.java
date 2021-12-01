package ftor_testng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/***********************************************************************************************
 * DataDriven -> Classe responsável pela manipulação da massa de dados (CSV).
 ***********************************************************************************************/
public class DataDriven {

	public static String cCaso = null;
	public static String nomeCaso = null;
	public static String cLinha = null;
	public static int nLin = 0;
	public static int nLin2 = 0;
	public static String url = "https://preprod.veja.abril.com.br/";
	
	//Novas Urls	
	public static String url1 = "https://veja.abril.com.br/cultura/estados-ensaiam-a-volta-dos-shows-no-brasil-mas-variante-delta-preocupa/";
	public static String url2 = "https://veja.abril.com.br/cultura/com-rocha-lunar-e-itens-originais-exposicao-celebra-a-ida-do-homem-a-lua/";
	public static String url3 = "https://veja.abril.com.br/politica/movimento-de-bastidores-no-stj-tenta-protelar-escolha-de-ministros/";
	public static String urlFechada = "https://veja.abril.com.br/esporte/denarc-tem-as-fitas-contra-edinho/";
	public static String urlEnvio = "https://outlook.live.com/owa/";
	public static String urlAMP = "https://search.google.com/test/amp";	
	public static String urlpreProd = "https://preprod.abril.com.br/assinar-revista-veja-assine/";
	public static String urlProd = "https://veja.abril.com.br//";
	public static String nomeSistema = "Veja Abril";
	public static String nomeSuite = "Veja Abril";

	
	public static String idCaso = null;
	public static String areacod = null;	
	public static String senha = null;
	public static String usuario = null;	
	public static String senhagmail = null;
	public static String emailgmail = null;
	
	public static String emaildestino1 = null;
	public static String emaildestino2 = null;
	public static String assuntoemail = null;
	public static String mensagem = null;
	
	public static String identificacaonomecompleto = null;
	public static String identificacaoemail = null;
	public static String identificacaotelefone = null;
	
	public static String enderecocep = null;
	public static String enderecologradouro = null;
	public static String endereconumero = null;
	public static String enderecocomplemento = null;
	public static String enderecobairro = null;
	public static String enderecouf = null;
	public static String enderecocidade = null;	
	
	public static String pagamentocartaocreditonome	= null;
	public static String CardNumber1 = null;
	public static String ExpiryDate = null;
	public static String SecurityCode = null;
	public static String pagamentocartaocreditocpfcnpj = null;
	
	//boleto
	public static String pagamentodebitocontacpfcnpj = null;
	public static String pagamentodebitocontaagencia = null;
	public static String pagamentodebitocontaagenciadigito = null;
	public static String pagamentodebitocontaconta = null;
	public static String pagamentodebitocontacontadigito = null;
	

	public static ArrayList<String> aLinha = new ArrayList<String>();
	public static ArrayList<String> aidCaso = new ArrayList<String>();
	public static ArrayList<String> aUrl = new ArrayList<String>();
	public static ArrayList<String> aareacod = new ArrayList<String>();
	public static ArrayList<String> asenha = new ArrayList<String>();
	public static ArrayList<String> ausuario = new ArrayList<String>();	
	public static ArrayList<String> asenhagmail = new ArrayList<String>();
	public static ArrayList<String> aemailgmail = new ArrayList<String>();
	
	public static ArrayList<String> aemaildestino1 = new ArrayList<String>();
	public static ArrayList<String> aemaildestino2 = new ArrayList<String>();
	public static ArrayList<String> aassuntoemail = new ArrayList<String>();
	public static ArrayList<String> amensagem = new ArrayList<String>();
	
	
	public static ArrayList<String> aidentificacaonomecompleto = new ArrayList<String>();
	public static ArrayList<String> aidentificacaoemail = new ArrayList<String>();
	public static ArrayList<String> aidentificacaotelefone = new ArrayList<String>();
	
	public static ArrayList<String> aenderecocep = new ArrayList<String>();
	public static ArrayList<String> aenderecologradouro = new ArrayList<String>();
	public static ArrayList<String> aendereconumero = new ArrayList<String>();
	public static ArrayList<String> aenderecocomplemento = new ArrayList<String>();
	public static ArrayList<String> aenderecobairro = new ArrayList<String>();
	public static ArrayList<String> aenderecouf = new ArrayList<String>();
	public static ArrayList<String> aenderecocidade = new ArrayList<String>();	
	
	public static ArrayList<String> apagamentocartaocreditonome = new ArrayList<String>();
	public static ArrayList<String> aCardNumber1 = new ArrayList<String>();
	public static ArrayList<String> aExpiryDate = new ArrayList<String>();
	public static ArrayList<String> aSecurityCode = new ArrayList<String>();
	public static ArrayList<String> apagamentocartaocreditocpfcnpj = new ArrayList<String>();
	
	//boletos
	public static ArrayList<String> apagamentodebitocontacpfcnpj = new ArrayList<String>();
	public static ArrayList<String> apagamentodebitocontaagencia = new ArrayList<String>();
	public static ArrayList<String> apagamentodebitocontaagenciadigito = new ArrayList<String>();
	public static ArrayList<String> apagamentodebitocontaconta = new ArrayList<String>();
	public static ArrayList<String> apagamentodebitocontacontadigito = new ArrayList<String>();
	

	
	public static void lerCSV() throws Exception {

		// Leitura do CSV
		File arquivoCSV = new File("dados/MASSA_ABRIL.csv");
		FileInputStream arquivoStream = new FileInputStream(arquivoCSV);
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(arquivoStream, "ISO-8859-1"));
			while (in.ready()) {
				cLinha = in.readLine();

				if (cLinha != null) {
					String[] campos = cLinha.split(";");

					aLinha.add(cLinha);
					aareacod.add(campos[0]);
					asenhagmail.add(campos[1]);
					asenha.add(campos[2]);
					ausuario.add(campos[3]);
					aemailgmail.add(campos[4]);
					aemaildestino1.add(campos[5]);
					aemaildestino2.add(campos[6]);
					aassuntoemail.add(campos[7]);
					amensagem.add(campos[8]);
					
					aidentificacaonomecompleto.add(campos[9]);
					aidentificacaoemail.add(campos[10]);
					aidentificacaotelefone.add(campos[11]);
					
					
					apagamentocartaocreditonome.add(campos[12]);
					aCardNumber1.add(campos[13]);
					aExpiryDate.add(campos[14]);
					aSecurityCode.add(campos[15]);
					apagamentocartaocreditocpfcnpj.add(campos[16]);
					
					aenderecocep.add(campos[17]);
					aenderecologradouro.add(campos[18]);
					aendereconumero.add(campos[19]);
					aenderecocomplemento.add(campos[20]);
					aenderecobairro.add(campos[21]);
					aenderecouf.add(campos[22]);
					aenderecocidade.add(campos[23]);
					
					apagamentodebitocontacpfcnpj.add(campos[24]);
					apagamentodebitocontaagencia.add(campos[25]);
					apagamentodebitocontaagenciadigito.add(campos[26]);
					apagamentodebitocontaconta.add(campos[27]);
					apagamentodebitocontacontadigito.add(campos[28]);
					
					aidCaso.add(campos[29]);

				}
			}

			in.close();
		}

		catch (final Exception e) {

			System.out.println("Não foi possível ler a planilha .csv");
			throw new Exception();
		}
	}

	public static void lerRegistro() {
		// LER REGISTRO (get do array)
		idCaso = aidCaso.get(nLin);
		areacod = aareacod.get(nLin);
		senhagmail = asenhagmail.get(nLin);
		senha = asenha.get(nLin);
		usuario = ausuario.get(nLin);
		emailgmail = aemailgmail.get(nLin);
		emaildestino1 = aemaildestino1.get(nLin);
		emaildestino2 = aemaildestino2.get(nLin);
		assuntoemail = aassuntoemail.get(nLin);
		mensagem = amensagem.get(nLin);
		
		identificacaonomecompleto = aidentificacaonomecompleto.get(nLin);
		identificacaoemail = aidentificacaoemail.get(nLin);
		identificacaotelefone = aidentificacaotelefone.get(nLin);
		
		enderecocep = aenderecocep.get(nLin);
		enderecologradouro = aenderecologradouro.get(nLin);
		endereconumero = aendereconumero.get(nLin);
		enderecocomplemento = aenderecocomplemento.get(nLin);
		enderecobairro = aenderecobairro.get(nLin);
		enderecouf = aenderecouf.get(nLin);
		enderecocidade = aenderecocidade.get(nLin);
		
		pagamentocartaocreditonome = apagamentocartaocreditonome.get(nLin);
		CardNumber1 = aCardNumber1.get(nLin);
		ExpiryDate = aExpiryDate.get(nLin);
		SecurityCode = aSecurityCode.get(nLin);
		pagamentocartaocreditocpfcnpj = apagamentocartaocreditocpfcnpj.get(nLin);
		
		
		pagamentodebitocontacpfcnpj = apagamentodebitocontacpfcnpj.get(nLin);
		pagamentodebitocontaagencia = apagamentodebitocontaagencia.get(nLin);
		pagamentodebitocontaagenciadigito = apagamentodebitocontaagenciadigito.get(nLin);
		pagamentodebitocontaconta = apagamentodebitocontaconta.get(nLin);
		pagamentodebitocontacontadigito = apagamentodebitocontacontadigito.get(nLin);

	}
}
