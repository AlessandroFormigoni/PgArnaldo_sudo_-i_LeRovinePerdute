package it.unibs.ing.arnaldo.rovineperdute;

import java.util.ArrayList;


public class Dijkstra {

	private static City[] prec = new City[ReadFile.size];
	private static double[] dist = new double[ReadFile.size];
	//public static enum vehicle {PLANAR, VERTICAL};
	private static ArrayList<City> route = new ArrayList<City>();
	
	private static int minDistIndex(ArrayList<City> group) {
		int ind = 0;
		double min = Double.POSITIVE_INFINITY;
		for (City city : group) { // cities in group are ordered by increasing id
			int i = city.getId();
			if (dist[i] <= min) { // <= ensures to get max id possible with min distance
				min = dist[i];
				ind = i;
			}
		} return ind;
	}
	
	public static void dijkstra(Graph nodes, City source, int ind) {
		
		ArrayList<City> toVisit = new ArrayList<City>();
		prec = new City[ReadFile.size];
		dist = new double[ReadFile.size];
		for (City city : nodes.getList()) {
			prec[city.getId()] = null;
			dist[city.getId()] = Double.POSITIVE_INFINITY;
			toVisit.add(city);
		}
		
		dist[source.getId()] = 0;
		
		while (!toVisit.isEmpty()) {
			City T = nodes.cityFromID(minDistIndex(toVisit));
			toVisit.remove(T);
			
			for (City city : T.getLinkedCities().keySet()) {
				
				if (!toVisit.contains(city)) continue;
				
				double calcDist = dist[T.getId()] + T.getLinkedCities().get(city)[ind];
				
				if (calcDist < dist[city.getId()]) {
					dist[city.getId()] = calcDist;
					prec[city.getId()] = T;
				}
			}
		}
		
		calculateRoute();
	}
	
	private static void calculateRoute() {
		
		City destination = ReadFile.getGraph().cityFromID(ReadFile.size - 1);
		int index = destination.getId();
		route.clear();
		route.add(destination);
		
		while (prec[index] != null) {
			City temp = prec[index];
			route.add(0, temp);
			index = temp.getId();
		}
		
	}

	public static String display(City destination) {
		
		double totalDistance = dist[destination.getId()];
		int index = destination.getId();
		ArrayList<City> route = new ArrayList<City>();
		route.add(destination);
		
		while (prec[index] != null) {
			City temp = prec[index];
			route.add(0, temp);
			index = temp.getId();
		}
		
		StringBuffer str = new StringBuffer();
		str.append("From campo base to " + destination.getName());
		str.append("\n\nTotal distance: " + totalDistance + "\n\n");
		
		for (City city : route) {
			str.append(city.getName() + "\n");
		}
		
		return str.toString();
	}

	public static City[] getPrec() {
		return prec;
	}

	public static double[] getDist() {
		return dist;
	}

	public static ArrayList<City> getRoute() {
		return route;
	}
	
	
}
