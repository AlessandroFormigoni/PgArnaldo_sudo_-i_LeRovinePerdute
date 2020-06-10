package it.unibs.ing.arnaldo.rovineperdute;

import java.util.ArrayList;
import java.util.HashSet;


public class Dijkstra {

	private static City[] prec = new City[ReadFile.size];
	private static double[] dist = new double[ReadFile.size];
	public static enum vehicle {PLANAR, VERTICAL};
	
	private static int minDistIndex(HashSet<City> group) {
		int ind = 0;
		double min = Double.POSITIVE_INFINITY;
		for (City city : group) {
			int i = city.getId();
			if (dist[i] < min) {
				min = dist[i];
				ind = i;
			}
		} return ind;
	}
	
	public static void dijkstra(Graph nodes, City source, vehicle ind) {
		
		HashSet<City> toVisit = new HashSet<City>();
		
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
				
				double calcDist = dist[T.getId()] + T.getLinkedCities().get(city)[ind.ordinal()];
				
				if (calcDist < dist[city.getId()]) {
					dist[city.getId()] = calcDist;
					prec[city.getId()] = T;
				}
			}
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
}
