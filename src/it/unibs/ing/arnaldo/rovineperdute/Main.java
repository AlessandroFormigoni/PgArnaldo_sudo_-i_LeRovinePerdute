package it.unibs.ing.arnaldo.rovineperdute;

public class Main {

	public static void main(String[] args) {
		
		long time = System.currentTimeMillis();
		ReadFile.initializeReader();
		ReadFile.extractCities();
		ReadFile.initializeReader();
		ReadFile.extractLink();
		
		//System.out.println(ReadFile.getGraph().toString());
		WriteFile.initializeWriter();
		WriteFile.printFile();
		System.out.println(System.currentTimeMillis() - time + " ms");
		//System.out.println(Dijkstra.display(ReadFile.getGraph().cityFromID(1999)));
	}

}
