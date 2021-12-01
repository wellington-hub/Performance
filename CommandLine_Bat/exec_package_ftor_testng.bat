cd C:\workspace_ftor_testng\ProjetoExemplo
set suite=ProjetoExemplo
echo %suite%
set ProjectPath=C:\workspace_ftor_testng\%suite%
echo %ProjectPath%
set ftor=%ProjectPath%\bin\ftor_testng
echo %ftor%
set classpath=%ProjectPath%\bin;%ProjectPath%\utilitarios\libs\*
echo %classpath%
java org.testng.TestNG -log 100 %ftor%\testng.xml

