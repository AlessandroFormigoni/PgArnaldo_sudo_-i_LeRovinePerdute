package it.unibs.ing.arnaldo.rovineperdute;

public class Main {

	public static void main(String[] args) {
		
		long time = System.currentTimeMillis();
		ReadFile.initializeReader();
		ReadFile.extractCities();
		System.out.println("Tempo lettura elenco citta': " + (System.currentTimeMillis() - time) + " ms");
		long time2 = System.currentTimeMillis();
		ReadFile.initializeReader();
		ReadFile.extractLink();
		System.out.println("Tempo lettura citta' collegate: " + (System.currentTimeMillis() - time2) + " ms");
		System.out.println("Tempo lettura totale: " + (System.currentTimeMillis() - time) + " ms");
		WriteFile.initializeWriter();
		WriteFile.printFile();
		System.out.println("Tempo totale: " + (System.currentTimeMillis() - time) + " ms");
	}

}
