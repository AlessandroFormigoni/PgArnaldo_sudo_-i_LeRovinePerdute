package it.unibs.ing.arnaldo.rovineperdute;

public class Main {

	public static void main(String[] args) {
		ReadFile.initializeReader();
		ReadFile.extractCities();
		ReadFile.initializeReader();
		ReadFile.extractLink();
		System.out.println(ReadFile.getGraph().toString());
		
		//tests
		System.out.println(ReadFile.getGraph().cityFromID(0).pointsTo(ReadFile.getGraph().cityFromID(1)));
		System.out.println(ReadFile.getGraph().cityFromID(1).pointsTo(ReadFile.getGraph().cityFromID(0)));
		System.out.println(ReadFile.getGraph().cityFromID(0).isPointedBy(ReadFile.getGraph().cityFromID(1)));
		System.out.println(ReadFile.getGraph().cityFromID(1).pointsTo(ReadFile.getGraph().cityFromID(1)));
		System.out.println(ReadFile.getGraph().cityFromID(1).isPointedBy(ReadFile.getGraph().cityFromID(0)));
		System.out.println(ReadFile.getGraph().cityFromID(3).pointsTo(ReadFile.getGraph().cityFromID(1)));
		System.out.println(ReadFile.getGraph().cityFromID(1).isPointedBy(ReadFile.getGraph().cityFromID(3)));
		System.out.println(ReadFile.getGraph().cityFromID(0).pointsTo(ReadFile.getGraph().cityFromID(4)));
	}

}
