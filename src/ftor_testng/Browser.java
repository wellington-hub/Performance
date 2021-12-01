package ftor_testng;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/*****************************************************************************************
 * Browser -> Classe que contém atributos e métodos referentes à utilização dos
 * browsers.
 *****************************************************************************************/
public class Browser extends TestUtils {

	public static FirefoxOptions options;
	public static String path_Firefox_Portable_44 = (Utils.userDir + "\\utilitarios\\FirefoxPortable\\FirefoxPortable.exe");
	public static String path_Gecko_Driver = "utilitarios/drivers/geckodriver.exe";
	public static String path_Gecko_Driver_018 = "utilitarios/drivers/geckodriver_018.exe";
	public static String path_Gecko_Driver_021 = "utilitarios/drivers/geckodriver_021.exe";
	public static String path_Chrome_Portable_67 = (Utils.userDir + "\\utilitarios\\GoogleChromePortable\\GoogleChromePortable.exe");
	public static String path_Chrome_Driver_2_25 = "utilitarios/drivers/chromedriver_2.25.exe";
	public static String path_Chrome_Driver_2_40 = "utilitarios/drivers/chromedriver_2.40.exe";
	public static String path_IE_Driver_Server_3_14 = "utilitarios/drivers/IEDriverServer_3.14.exe";
	public static String path_Microsoft_WebDriver_v6 = "utilitarios/drivers/MicrosoftWebDriver_v6.exe";
	public static String path_PhantomJS_2_11 = (Utils.userDir + "\\utilitarios\\PhantomJS\\bin\\phantomjs.exe");

	public static void executarComFirefoxPortable44() {
		/*****************************************
		 * Firefox Portable 44 + geckodriver 0.18
		 *****************************************/
		System.setProperty("webdriver.gecko.driver", path_Gecko_Driver_018);
		System.setProperty("webdriver.firefox.bin", path_Firefox_Portable_44);

		options = new FirefoxOptions();
		options.setLegacy(true);

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);

		driver = new FirefoxDriver(options);
		configurarNovaInstanciaBrowser();
	}

	public static void executarComFirefox52() {
		/********************************
		 * Firefox 52 + geckodriver 0.18
		 ********************************/
		System.setProperty("webdriver.gecko.driver", path_Gecko_Driver_018);

		options = new FirefoxOptions();
		options.setLegacy(true);

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("default");
		options.setProfile(myprofile);

		driver = new FirefoxDriver(options);
		configurarNovaInstanciaBrowser();
	}

	public static void executarComFirefox57() {
		/*******************************************
		 * Firefox 57 ou superior + geckodriver 0.21
		 *******************************************/
		//System.setProperty("webdriver.gecko.driver", path_Gecko_Driver_021);
		System.setProperty("webdriver.gecko.driver", path_Gecko_Driver);

		options = new FirefoxOptions();
		options.setLegacy(true);

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("default");
		options.setProfile(myprofile);

		driver = new FirefoxDriver(options);
		configurarNovaInstanciaBrowser();
	}

	public static void executarComChrome54() {
		/********************************
		 * Chrome 54 + chromedriver 2.25
		 ********************************/
		System.setProperty("webdriver.chrome.driver", path_Chrome_Driver_2_25);

		driver = new ChromeDriver();
		configurarNovaInstanciaBrowser();
	}

	public static void executarComChrome70() {
		/********************************
		 * Chrome 70 + chromedriver 2.40
		 ********************************/
		System.setProperty("webdriver.chrome.driver", path_Chrome_Driver_2_40);

		driver = new ChromeDriver();
		configurarNovaInstanciaBrowser();
	}

	public static void executarComIE11() {
		/**********************************************
		 * Internet Explorer 11 + IEDriver Server 3.14.0
		 **********************************************/
		System.setProperty("webdriver.ie.driver", path_IE_Driver_Server_3_14);		

		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		driver = new InternetExplorerDriver();
		configurarNovaInstanciaBrowser();
	}

	public static void executarComEdge17() {
		/****************************************
		 * Edge 17 + Microsoft WebDriver versão 6
		 ****************************************/
		System.setProperty("webdriver.edge.driver", path_Microsoft_WebDriver_v6);

		driver = new EdgeDriver();
		configurarNovaInstanciaBrowser();
	}

	public static void executarComPhantomJS_2_11() {
		/******************
		 * PhantomJS 2.11
		 ******************/
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path_PhantomJS_2_11);
		driver = new PhantomJSDriver(caps);
	}

	public static void executarComChrome70_Headless() {
		/******************************************
		 * Chrome 70 (HEADLESS) + chromedriver 2.40
		 ******************************************/
		System.setProperty("webdriver.chrome.driver", path_Chrome_Driver_2_40);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		driver = new ChromeDriver(options);
		configurarNovaInstanciaBrowser();
	}

	public static void executarComChromePortable() {
		/****************************************
		 * Chrome Portable + chromedriver 2.40
		 ****************************************/
		String proxyHost = null;
		System.setProperty("webdriver.chrome.driver", path_Chrome_Driver_2_40);

		DesiredCapabilities extraCapabilities = new DesiredCapabilities();
		extraCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		extraCapabilities.setPlatform(Platform.WINDOWS);

		Proxy proxy = new Proxy();
		proxy.setHttpProxy(proxyHost).setFtpProxy(proxyHost).setSslProxy(proxyHost);
		extraCapabilities.setCapability(CapabilityType.PROXY, proxy);

		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("binary", path_Chrome_Portable_67);
		extraCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

		driver = new ChromeDriver();
		configurarNovaInstanciaBrowser();
	}

	public static void fecharInstancia() {

		//driver.quit();
	}

	public static void configurarNovaInstanciaBrowser() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
