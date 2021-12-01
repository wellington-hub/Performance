cd C:\workspace_ftor_testng_servidor3\ProjetoExemplo
set ProjectPath=C:\workspace_ftor_testng_servidor3\ProjetoExemplo
set ftor=C:\workspace_ftor_testng_servidor3\ProjetoExemplo\bin\grid_paralelo
echo %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\utilitarios\libs\*
echo %classpath%
java org.testng.TestNG -log 100 %ftor%\testng_paralelo.xml

