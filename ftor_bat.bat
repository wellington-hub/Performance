cd C:\Desenv\Projetos\Squad Performance TestNg\Performance\ProjetoPerformance\ProjetoGrupoAbril
set ProjectPath=C:\Desenv\Projetos\Squad Performance TestNg\Performance\ProjetoPerformance\ProjetoGrupoAbril
set ftor=C:\Desenv\Projetos\Squad Performance TestNg\Performance\ProjetoPerformance\ProjetoGrupoAbril\bin\grid_paralelo\
echo %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\libs\*
echo %classpath%
java org.testng.TestNG -log 100 %ftor%\testng_paralelo.xml
pause