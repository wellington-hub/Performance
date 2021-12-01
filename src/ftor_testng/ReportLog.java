package ftor_testng;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

/***************************************************************************************************
 * ReportLog -> Classe que contém os métodos e atributos responsáveis pela
 * manipulação do Extent Reports, Log4j, iText, ATUTestRecorder e arquivo config.properties.
 ***************************************************************************************************/

public class ReportLog extends TestUtils {

	public static ExtentTest logger;
	public static ExtentReports report;
	static ATUTestRecorder recorder;

	// Lê arquivo 'config.properties e cria objeto properties'
	InputStream input = getClass().getResourceAsStream("config.properties");
	Properties properties = new Properties();

	public void logSucesso(String mensagem) throws Exception {
		// Log4j
		Logger log = Logger.getLogger(getClass());
		log.info(mensagem);

		// Extent Reports
		logger.log(LogStatus.PASS, mensagem);
	}

	public void logErro(String mensagem, Exception e) throws Exception {
		// Log4j
		Logger log = Logger.getLogger(getClass());
		log.error(mensagem);
		log.info("***** End LOG4J *****" + "\r\n");

		// (Extent Reports) -> Adiciona screenshot ao relatório
		String imagem = Utils.screenShot(DataDriven.nomeCaso);
		logger.log(LogStatus.FAIL, e + logger.addScreenCapture(imagem));

		// Finaliza o teste
		report.endTest(logger);

		// Acrescenta o arquivo HTML com todos os testes finalizados
		report.flush();

		// Adiciona o relatório .pdf de erro
		gerarRelatorioPDFErro();

		throw new Exception();
	}

	public void apontarRelatorioHtml(String nomeCaso) throws IOException {

		/*************************************
		 * Configura o relatório ExtentReports
		 ***********************************/

		DataDriven.nomeCaso = nomeCaso;

		// Define o apontamento do relatório html
		report = new ExtentReports(
				Utils.userDir + "\\saidas\\ExtentReports\\" + DataDriven.nomeCaso + "_" + Utils.gerarDataHoraAtual() + "_"
						+ Utils.obterNomeBrowser() + "_" + Utils.obterVersaoBrowser() + "_" + Utils.osName + ".html");

		String informacoesCasoTeste = "Caso de Teste: " + DataDriven.nomeCaso + "  |" + "   Colaborador: "
				+ Utils.userName + "  |" + "   Browser: " + Utils.obterNomeBrowser() + "  |" + "   Versão: "
				+ Utils.obterVersaoBrowser() + "  |" + "   S.O: " + Utils.osName;

		// Define o cabeçalho do relatório
		logger = report.startTest(informacoesCasoTeste);

		/********************************************************************************
		 * Carrega config.properties e lê o arquivo xml de configuração do
		 * Extent Reports
		 ********************************************************************************/
		// Load do arquivo config.properties
		properties.load(input);

		// Path do arquivo xml de configuração do Extent Reports
		String extent_Config = properties.getProperty("extent_Config");
		String path_extent_config = (Utils.userDir + extent_Config);

		// Carrega o arquivo XML de configuração do Extent Reports
		report.loadConfig(new File(path_extent_config));

		/************
		 * LOG4J
		 ************/
		// Loga texto no Log4j
		Logger log = Logger.getLogger(getClass());
		log.info("***** Start LOG4J *****");

		/********************************************
		 * Imprime dados referentes ao Caso de Teste
		 ********************************************/
		log.info(informacoesCasoTeste);
	}

	public void adicionaImagemLogSucessoRelatorio(String mensagemSucesso) throws Exception {

		// Pula uma linha no Log4j (separação entre os casos de teste)
		Logger log = Logger.getLogger(getClass());
		log.info("***** End LOG4J *****" + "\r\n");

		// Adiciona screenshot ao relatório
		String imagem = Utils.screenShot(DataDriven.nomeCaso);
		logger.log(LogStatus.PASS, mensagemSucesso + logger.addScreenCapture(imagem));

		// Finaliza o teste
		report.endTest(logger);

		// Acrescenta o arquivo HTML com todos os testes finalizados
		report.flush();

		// Adiciona o relatório .pdf de sucesso
		gerarRelatorioPDFSucesso();
	}

	public void logStatusPassRelatorioHtml(String mensagemSucesso) {
		logger.log(LogStatus.PASS, mensagemSucesso);
	}

	public void logStatusFailRelatorioHtml(String mensagemFalha) {
		logger.log(LogStatus.FAIL, mensagemFalha);
	}

	public void gerarRelatorioPDFSucesso() throws Exception {

		// Cria o objeto 'document' que é o arquivo .pdf
		Document document = new Document(PageSize.A4.rotate());

		try {
			// Configura o path do relatório .pdf e da evidência de teste
			// (concatenando com o nome do caso de teste e o objeto do tipo Date
			// criado anteriormente),logo da empresa e fonte utilizada no checkbox
			// do relatório.
			String File = Utils.userDir + "\\saidas\\PDFReport\\" + DataDriven.nomeCaso + "_" + Utils.gerarDataHoraAtual() + "_"
					+ Utils.obterNomeBrowser() + "_" + Utils.obterVersaoBrowser() + "_" + Utils.osName + ".pdf";
			String IMG = Utils.userDir + "\\saidas\\ExtentReports\\" + DataDriven.nomeCaso + "_" + Utils.gerarDataHoraAtual()
					+ "_" + Utils.obterNomeBrowser() + "_" + Utils.obterVersaoBrowser() + "_" + Utils.osName + ".jpg";
			String IMGEmpresa = Utils.userDir + "\\logo-empresa\\grupoabril.jpg";
			String fonte = Utils.userDir + "\\fonts\\wingding.ttf";

			// Define a fonte que será utilizada na criação do checkbox de
			// validação do resultado do caso de teste
			BaseFont base = BaseFont.createFont(fonte, BaseFont.IDENTITY_H, false);
			Font font = new Font(base, 16f, Font.BOLD, BaseColor.GREEN);
			char checked = '\u00FE';

			// Realiza open no arquivo .pdf
			PdfWriter.getInstance(document, new FileOutputStream(File));
			document.open();

			// Configura e adiciona a imagem (evidência de teste), gerada na
			// execução do caso de teste
			Image img = Image.getInstance(IMG);
			img.setAlignment(Image.MIDDLE);
			img.scaleToFit(350, 315);
			float offsetX = (350 - img.getScaledWidth()) / 2;
			float offsetY = (315 - img.getScaledHeight()) / 2;
			img.setAbsolutePosition(36 + offsetX, 36 + offsetY);
			document.add(img);

			// Configura e adiciona o logo da CAIXA
			Image imagem = Image.getInstance(IMGEmpresa);
			imagem.setAlignment(Image.RIGHT);
			document.add(imagem);

			// Configura e adiciona as bordas do relatório (seta a cor verde,
			// pois, o caso de teste passou)
			Rectangle rect = new Rectangle(840, 594);
			rect.enableBorderSide(1);
			rect.enableBorderSide(2);
			rect.enableBorderSide(4);
			rect.enableBorderSide(8);
			rect.setBorder(Rectangle.BOX);
			rect.setBorderWidthBottom(4);
			rect.setBorderWidthLeft(4);
			rect.setBorderWidthRight(4);
			rect.setBorderWidthTop(4);
			rect.setBorderColor(BaseColor.GREEN);
			document.add(rect);

			// Configura a Data e Hora para posteriormente adicioná-la ao
			// relatório
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			// Adiciona o texto em questão centralizado
			Paragraph p = new Paragraph("RESUMO DA EXECUÇÃO DE CASO DE TESTE");
			p.setAlignment(1);
			document.add(p);

			// Pula linha
			document.add(Chunk.NEWLINE);

			// Adiciona as informações do relatório
			document.add(new Paragraph("Sistema: " + DataDriven.nomeSistema));
			document.add(new Paragraph("Suíte de Teste: " + DataDriven.nomeSuite));
			document.add(new Paragraph("Caso de Teste: " + DataDriven.nomeCaso));
			document.add(new Paragraph("Colaborador: " + Utils.userName));
			document.add(new Paragraph("Status do Teste: Passou"));
			document.add(new Paragraph("Data/Hora: " + dateFormat.format(cal.getTime())));
			document.add(new Paragraph(String.valueOf(checked), font));

			// Pula linhas para adicionar a Data/Hora na parte inferior do
			// arquivo .pdf
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			// Adiciona a label 'FTOR - Framework de Testes Orientado ao Reuso'
			Paragraph paragraph = new Paragraph("FTOR - Framework de Testes Orientado ao Reuso");
			paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
			document.add(paragraph);

		} catch (Exception e) {
			logErro("Erro ao gerar relatório .pdf", e);
		} finally {

			// Finaliza o relatório .pdf
			document.close();
		}
	}

	public void gerarRelatorioPDFErro() throws Exception {

		// Cria o objeto 'document' que é o arquivo .pdf
		Document document = new Document(PageSize.A4.rotate());

		try {
			// Configura o path do relatório .pdf e da evidência de teste
			// (concatenando com o nome do caso de teste e o objeto do tipo Date
			// criado anteriormente),logo da Empresa e fonte utilizada no checkbox
			// do relatório.
			String File = Utils.userDir + "\\saidas\\PDFReport\\" + DataDriven.nomeCaso + "_" + Utils.gerarDataHoraAtual() + "_"
					+ Utils.obterNomeBrowser() + "_" + Utils.obterVersaoBrowser() + "_" + Utils.osName + ".pdf";
			String IMG = Utils.userDir + "\\saidas\\ExtentReports\\" + DataDriven.nomeCaso + "_" + Utils.gerarDataHoraAtual()
					+ "_" + Utils.obterNomeBrowser() + "_" + Utils.obterVersaoBrowser() + "_" + Utils.osName + ".jpg";
			String IMGEmpresa = Utils.userDir + "\\logo-empresa\\grupoabril.jpg";
			String fonte = Utils.userDir + "\\fonts\\wingding.ttf";

			// Define a fonte que será utilizada na criação do checkbox de
			// validação do resultado do caso de teste
			BaseFont base = BaseFont.createFont(fonte, BaseFont.IDENTITY_H, false);
			Font font = new Font(base, 16f, Font.BOLD, BaseColor.RED);
			char checked = '\u00FE';

			// Realiza open no arquivo .pdf
			PdfWriter.getInstance(document, new FileOutputStream(File));
			document.open();

			// Configura e adiciona a imagem (evidência de teste), gerada na
			// execução do caso de teste
			Image img = Image.getInstance(IMG);
			img.setAlignment(Image.MIDDLE);
			img.scaleToFit(350, 315);
			float offsetX = (350 - img.getScaledWidth()) / 2;
			float offsetY = (315 - img.getScaledHeight()) / 2;
			img.setAbsolutePosition(36 + offsetX, 36 + offsetY);
			document.add(img);

			// Configura e adiciona o logo da CAIXA
			Image imagem = Image.getInstance(IMGEmpresa);
			imagem.setAlignment(Image.RIGHT);
			document.add(imagem);

			// Configura e adiciona as bordas do relatório (seta a cor verde,
			// pois, o caso de teste passou)
			Rectangle rect = new Rectangle(840, 594);
			rect.enableBorderSide(1);
			rect.enableBorderSide(2);
			rect.enableBorderSide(4);
			rect.enableBorderSide(8);
			rect.setBorder(Rectangle.BOX);
			rect.setBorderWidthBottom(4);
			rect.setBorderWidthLeft(4);
			rect.setBorderWidthRight(4);
			rect.setBorderWidthTop(4);
			rect.setBorderColor(BaseColor.RED);
			document.add(rect);

			// Configura a Data e Hora para posteriormente adicioná-la ao
			// relatório
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			// Adiciona o texto em questão centralizado
			Paragraph p = new Paragraph("RESUMO DA EXECUÇÃO DE CASO DE TESTE");
			p.setAlignment(1);
			document.add(p);

			// Pula linha
			document.add(Chunk.NEWLINE);

			// Adiciona as informações do relatório
			document.add(new Paragraph("Sistema: " + DataDriven.nomeSistema));
			document.add(new Paragraph("Suíte de Teste: " + DataDriven.nomeSuite));
			document.add(new Paragraph("Caso de Teste: " + DataDriven.nomeCaso));
			document.add(new Paragraph("Colaborador: " + Utils.userName));
			document.add(new Paragraph("Status do Teste: Falhou"));
			document.add(new Paragraph("Data/Hora: " + dateFormat.format(cal.getTime())));
			document.add(new Paragraph(String.valueOf(checked), font));

			// Pula linhas para adicionar a Data/Hora na parte inferior do
			// arquivo .pdf
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			// Adiciona a label 'FTOR - Framework de Testes Orientado ao Reuso'
			Paragraph paragraph = new Paragraph("FTOR - Framework de Testes Orientado ao Reuso");
			paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
			document.add(paragraph);

		} catch (Exception e) {
			logErro("Erro ao gerar relatório .pdf", e);
		} finally {

			// Finaliza o relatório .pdf
			document.close();
		}
	}

	public static void iniciarGravacaoVideoExecucaoCasoTeste() throws ATUTestRecorderException {
		recorder = new ATUTestRecorder(Utils.userDir + "\\saidas\\Video\\",
				DataDriven.nomeCaso + "_" + Utils.gerarDataHoraAtual() + "_" + Utils.obterNomeBrowser() + "_"
						+ Utils.obterVersaoBrowser() + "_" + Utils.osName,
				false);
		recorder.start();
	}

	public static void finalizarGravacaoVideoExecucaoCasoTeste() throws ATUTestRecorderException {
		recorder.stop();
	}
}
