package grid_paralelo;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import ftor_testng.Browser;

/********************************************************************
 * OptionsManager: Classe responsável pela configuração dos browsers.
 ********************************************************************/
public class OptionsManager {

	// Para SELENIUM GRID
	public static DesiredCapabilities getFirefox() {
		DesiredCapabilities capFirefox = DesiredCapabilities.firefox();
		return capFirefox;
	}

	public static DesiredCapabilities getChrome() {
		DesiredCapabilities capChrome = DesiredCapabilities.chrome();
		return capChrome;
	}

	public static DesiredCapabilities getInternetExplorer() {
		DesiredCapabilities capInternetExplorer = DesiredCapabilities.internetExplorer();
		return capInternetExplorer;
	}
	
	// portable criado 30/04/2019
	public static DesiredCapabilities getFirefoxPortable() {
		DesiredCapabilities capFirefoxPortable = DesiredCapabilities.firefox();
		return capFirefoxPortable;
	}
	
                        //Criado pelo Edson	
//	public static DesiredCapabilities getInternetExplorer2() {
//
//		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
//		
//		//ie.forceCreateProcessApi com valor verdadeiro 
//       //ie.browserCommandLineSwitches com valor -private. 
//
//	//	cap.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
//     
//	//	cap.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
//		//cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//			 
//		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//
//		return cap;
//
//	}
	
	                        //Criado pelo Edson	

	// Get Firefox Options
//	public static DesiredCapabilities getFirefoxOptionsProfile() {
//		FirefoxOptions options = new FirefoxOptions();
//		FirefoxProfile profile = new FirefoxProfile();
//
//		options = new FirefoxOptions();
//		options.setLegacy(true);
//
//		FirefoxProfile firefoxProfile = new FirefoxProfile();
//		firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
//
//		// Set Firefox profile to capabilities
//		DesiredCapabilities cap = new DesiredCapabilities();
//		cap.setCapability(FirefoxDriver.PROFILE, "./3xmbvuwp.selenium");
//		return cap;
//	}

	
	                                    //Criado pelo Edson	
//	// Get Firefox Options
//	public static DesiredCapabilities getFirefoxOptionsProfileNOVO() {
//		FirefoxProfile profile = new FirefoxProfile(new File("./3xmbvuwp.selenium"));
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(profile);
//		// Set Firefox profile to capabilities
//		DesiredCapabilities cap = new DesiredCapabilities();
//		cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
//
//		return cap;
//	}
	
	
	                              //Criado pelo Edson	
//	public static DesiredCapabilities getFirefoxOptionsProfileNOVO2() {
//		FirefoxOptions fo = new FirefoxOptions();
//		fo.addArguments("-profile", "selenium");
//		DesiredCapabilities c = DesiredCapabilities.firefox();
//		c.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fo);
//		return c;
//	}
//	
	
	             //Criado pelo Edson	 n precisa descomentar 

	// DesiredCapabilities c = DesiredCapabilities.firefox();
	// c.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fo);
	// RemoteWebDriver wd = new RemoteWebDriver(hubUrl, c);

	
	
	
	
	// Get Chrome Options (TESTE LOCAL)
	public static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();

		System.setProperty("webdriver.chrome.driver", Browser.path_Chrome_Driver_2_40);

		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		return options;
	}

	// Get Firefox Options (TESTE LOCAL)
	public static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();

		/*****************************************
		 * Firefox Portable 44 + geckodriver 0.18
		 *****************************************/
		System.setProperty("webdriver.gecko.driver", Browser.path_Gecko_Driver_018);
		System.setProperty("webdriver.firefox.bin", Browser.path_Firefox_Portable_44);

		options = new FirefoxOptions();
		options.setLegacy(true);

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);

		// Set Firefox profile to capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(FirefoxDriver.PROFILE, profile);
		return options;
	}

	// //Get Firefox Options
	// public FirefoxOptions getFirefoxOptions () {
	// FirefoxOptions options = new FirefoxOptions();
	// FirefoxProfile profile = new FirefoxProfile();
	// //Accept Untrusted Certificates
	// profile.setAcceptUntrustedCertificates(true);
	// profile.setAssumeUntrustedCertificateIssuer(false);
	// //Use No Proxy Settings
	// profile.setPreference("network.proxy.type", 0);
	// //Set Firefox profile to capabilities
	// options.setCapability(FirefoxDriver.PROFILE, profile);
	// return options;
	// }

	// Get IE Options (TESTE LOCAL)

	public static InternetExplorerOptions getInternetExplorerOptions() {

		InternetExplorerOptions options = new InternetExplorerOptions();

		System.setProperty("webdriver.ie.driver", Browser.path_IE_Driver_Server_3_14);

		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return options;

	}
	
	
	


}