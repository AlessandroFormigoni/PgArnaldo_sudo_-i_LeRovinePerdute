package it.unibs.ing.arnaldo.rovineperdute;

import java.util.ArrayList;

/**
 * Contains methods to perform calculations on {@linkplain Graph} using 
 * Dijkstra algorithm
 * @author Simone, Alessandro, Francesca
 *
 */
public class Dijkstra {

	private static City[] prec = new City[ReadFile.size]; // Example: prec[5] stores the previous city of city with id = 5
	private static double[] dist = new double[ReadFile.size]; // Example: dist[5] stores ditance from start city to city with id = 5
	private static ArrayList<City> route = new ArrayList<City>(); // Ready to store any sequence of cities
	
	/**
	 * Returns the index (id) of the city with the minimum distance value.
	 * If more cities have the same distance, the one with highest index is returned.
	 * @param group the list of cities of which to check the index
	 * @return the index found
	 */
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
	
	/**
	 * Performs Dijkstra algorithm on a {@linkplain Graph} object.
	 * It also calculates the route from source city to the city with max index.
	 * @param nodes the Graph object
	 * @param source the starting node
	 * @param ind ONLY ACCEPTED VALUES ARE 0 OR 1. (Refers to two possible distances between two cities: 0 for planar, 1 for vertical).
	 */
	public static void dijkstra(Graph nodes, City source, int ind) {
		
		ArrayList<City> toVisit = new ArrayList<City>();
		prec = new City[ReadFile.size];
		dist = new double[ReadFile.size];
		int pathLenght[] = new int[ReadFile.size]; // stores the distance in terms of edges
		for (City city : nodes.getList()) { // initialization of data structures
			prec[city.getId()] = null;
			dist[city.getId()] = Double.POSITIVE_INFINITY;
			toVisit.add(city);
		}
		
		dist[source.getId()] = 0; // distance from source to itself is 0.
		
		while (!toVisit.isEmpty()) {
			
			City T = nodes.cityFromID(minDistIndex(toVisit)); // get the city with minimum distance
			toVisit.remove(T); // remove it from unvisited nodes
			
			for (City city : T.getLinkedCities().keySet()) { // for every city linked to it...
				
				if (!toVisit.contains(city)) continue; // skip iteration if city is already visited
				
				double calcDist = dist[T.getId()] + T.getLinkedCities().get(city)[ind];	// calculate distance of city from source
				
				if (calcDist < dist[city.getId()]) { // if calculated distance is less than currently stored distance...
					
					dist[city.getId()] = calcDist; // update distance
					prec[city.getId()] = T; // update previous city
					pathLenght[city.getId()] = pathLenght[T.getId()] + 1; // update path length 
					
				} else if (calcDist == dist[city.getId()]) { // if we found two paths with the same distance...
					
					int previousDistanceInEdges = pathLenght[city.getId()];
					int newDistanceInEdges = pathLenght[T.getId()] + 1;
					
					if (previousDistanceInEdges > newDistanceInEdges) { // and the new path has fewer edges...
						prec[city.getId()] = T; // we update the previous node
						pathLenght[city.getId()] = newDistanceInEdges; // update path length
					} 	
					
				}

			}
			
		}
		// to use Test_min_citta file use -2
		calculateRoute(ReadFile.size - 1); // calculate route from source to max id city
	}
	
	/**
	 * Calculates the route to a given destination and stores it in a static array
	 * @param cityId the id of destination city
	 */
	public static void calculateRoute(int cityId) {
		
		City destination = ReadFile.getGraph().cityFromID(cityId); 
		int index = destination.getId();
		route.clear();
		route.add(destination);
		
		while (prec[index] != null) {
			City temp = prec[index];
			route.add(0, temp);
			index = temp.getId();
		}
		
	}

	/**
	 * Returns a string containing the sequence of cities to reach a destination
	 * @param destination
	 * @return string sequence
	 */
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
