package grid_paralelo;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

 
/**********************************************************************************************************
 *InvokedMethodListener: Classe respons�vel por controlar a inicia��o e t�rmino das inst�ncias dos browsers 
 *durante a execu��o dos casos de teste.
 **********************************************************************************************************/
public class InvokedMethodListener implements IInvokedMethodListener {
	public String browserName;
	public String versao;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            System.out.println("A thread " + Thread.currentThread().getId() + " iniciou");
             browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
            TLDriverFactory.setDriver(browserName);
        }
    }
 
    @Override
   public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
        	System.out.println("A thread " + Thread.currentThread().getId() + " terminou");
            WebDriver driver = TLDriverFactory.getDriver();
            if (driver != null) {
              //driver.quit();
                
            }
        }
    } 
    
}