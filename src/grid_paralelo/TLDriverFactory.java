package grid_paralelo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
 
 /**************************************************************
  *TLDriverFactory: Classe responsável pela criação dos drivers.
  **************************************************************/
public class TLDriverFactory {
    //private static OptionsManager optionsManager = new OptionsManager();
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    
 
    public static synchronized void setDriver(String browser) {
    	
        if (browser.equals("firefox")) {      
            //Para teste local
          // tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver(OptionsManager.getFirefoxOptions()));
        	
 
            /*************************************************************************************************
             *              Descomentar o try e catch abaixo para executar com o Selenium GRID 
             *                Comentar o try e catch abaixo caso queira executar localmente 
             *                  e descomentar a funcao .getFirefoxOptions() acima 
             *                  
             **************************************************************************************************/
            try {

            	  //local e esta funcionando
               tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), OptionsManager.getFirefox()));  //local
            	
            	 //servidor de teste automatizado
            	//tlDriver.set(new RemoteWebDriver(new URL("http://10.116.85.16:4444/wd/hub"), OptionsManager.getFirefox()));  // funcionou    servidor                     
                
                
            	//tlDriver.set(new RemoteWebDriver(new URL("http://10.116.85.16:4444/wd/hub"), OptionsManager.getFirefoxOptionsProfile()));   
            	//tlDriver.set(new RemoteWebDriver(new URL("http://10.116.85.16:4444/wd/hub"), OptionsManager.getFirefoxOptionsProfileNOVO()));  
            	//tlDriver.set(new RemoteWebDriver(new URL("http://10.116.85.16:4444/wd/hub"), OptionsManager.getFirefoxOptionsProfileNOVO2()));
            	
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            
            /**************************************************************************************************/
            
        } else if (browser.equals("chrome")) {
            //Para teste local
           // tlDriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
 
            /*************************************************************************************************
             *              Descomentar o try e catch abaixo para executar com o Selenium GRID 
             *                Comentar o try e catch abaixo caso queira executar localmente 
             *                  e descomentar a funcao .getChromeOptions() acima 
             *                  
             **************************************************************************************************/
            try {
                tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), OptionsManager.getChrome())); //local
                
                //tlDriver.set(new RemoteWebDriver(new URL("http://10.116.85.16:4444/wd/hub"), OptionsManager.getChrome()));  // funcionou    servidor 
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            
            /**************************************************************************************************/
            
        }
        else if (browser.equals("ie")) {
            //Para teste local
        	//tlDriver.set(new InternetExplorerDriver(OptionsManager.getInternetExplorerOptions()));
            		
  
        	 
            /*************************************************************************************************
             *              Descomentar o try e catch  abaixo para executar com o Selenium GRID 
             *                Comentar o try e catch abaixo caso queira executar localmente 
             *                  e descomentar a funcao .getInternetExplorerOptions() acima 
             *                  
             **************************************************************************************************/
            try {
                tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), OptionsManager.getInternetExplorer())); // local
                
                //tlDriver.set(new RemoteWebDriver(new URL("http://10.116.85.16:4444/wd/hub"), OptionsManager.getInternetExplorer()));  // funcionou    servidor 
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            
            /*************************************************************************************************/
        }
    }
 
    public static synchronized WebDriverWait getWait (WebDriver driver) {
        return new WebDriverWait(driver,20);
    }
 
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}