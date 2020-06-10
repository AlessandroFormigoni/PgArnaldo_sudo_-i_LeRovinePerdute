package it.unibs.ing.arnaldo.rovineperdute;

public class Main {

	public static void main(String[] args) {
		
		ReadFile.initializeReader();
		ReadFile.extractCities();
		ReadFile.initializeReader();
		ReadFile.extractLink();
		
		System.out.println(ReadFile.getGraph().toString());
		
		Dijkstra.dijkstra(ReadFile.getGraph(), ReadFile.getGraph().cityFromID(0), Dijkstra.vehicle.PLANAR);
		
		System.out.println(Dijkstra.display(ReadFile.getGraph().cityFromID(1999)));
	}

}
