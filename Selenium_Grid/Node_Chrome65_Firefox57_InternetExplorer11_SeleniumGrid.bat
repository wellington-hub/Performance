CALL java -Dwebdriver.ie.driver="C:\workspace_ftor_testng\ProjetoExemplo\utilitarios\drivers\IEDriverServer_3.14.exe" -Dwebdriver.gecko.driver="C:\workspace_ftor_testng\ProjetoExemplo\utilitarios\drivers\geckodriver_021.exe" -Dwebdriver.chrome.driver="C:\workspace_ftor_testng\ProjetoExemplo\utilitarios\drivers\chromedriver_2.25.exe" -jar C:\workspace_ftor_testng\ProjetoExemplo\utilitarios\libs\selenium-server-standalone-3.4.0.jar -port 5558 -role node -hub http://10.139.18.20:4444/grid/register -browser "browserName=firefox, maxInstances=5, platform=ANY, seleniumProtocol=WebDriver" -browser "browserName=internet explorer, version=11, platform=WINDOWS, maxInstances=5" -browser "browserName=chrome,version=ANY,maxInstances=5,platform=WINDOWS"