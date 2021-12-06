package ftor_testng;

/******************************************************************
 * Main -> Classe utilizada apenas no processo de exportação do JAR. 
 ******************************************************************/
public class Main {

	public static String cTeste = ""; 

	public static void main(String[] args) {

		if (args.length > 0 ) {
			
			cTeste = args [0];
			
			
		}
// testes para sair no console via cmd,  C:\workspace_ftor_testng_servidor3>java -jar SIFPP.jar
		// resultado = TEstye java
		 System.out.println("Teste java");
	}
	
}