package it.unibs.ing.arnaldo.rovineperdute;

public class Main {

	public static void main(String[] args) {
		ReadFile.initializeReader();
		ReadFile.extractCities();
		ReadFile.extractLink();
		System.out.println(ReadFile.getGraph().toString());

	}

}
