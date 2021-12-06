cd C:\Desenv\Projetos\Squad Performance TestNg\Performance\ProjetoPerformance\ProjetoGrupoAbril
set suite=ProjetoGrupoAbril
echo %suite%
set ProjectPath=C:\Desenv\Projetos\Squad Performance TestNg\Performance\ProjetoPerformance\ProjetoGrupoAbril\%suite%
echo %ProjectPath%
set ftor=%ProjectPath%\bin\ftor_testng
echo %ftor%
set classpath=%ProjectPath%\bin;%ProjectPath%\utilitarios\libs\*
echo %classpath%
java org.testng.TestNG -log 100 %ftor%\testng.xml

