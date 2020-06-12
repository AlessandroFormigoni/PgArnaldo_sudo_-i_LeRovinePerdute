package it.unibs.ing.arnaldo.rovineperdute;

import java.util.*;

public class City {

	private String name;
	private int id;
	private Point coordinates;
	private Map<City,Double[]> linkedCities = new HashMap<City,Double[]>();
	/**
	 * constructor
	 * @param name
	 * @param id
	 * @param coordinates
	 */
	public City(String name, int id, Point coordinates) {
		this.name = name;
		this.id = id;
		this.coordinates = coordinates;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Point getCoordinates() {
		return coordinates;
	}

	public Map<City, Double[]> getLinkedCities() {
		return linkedCities;
	}
    /**
     * set the cities linked on the map (key City value/weight distance)
     * the array of Double contains the 2 values ​​of the weight of the arc
     * for linear distance and altitude difference
     * @param linkedCities 
     * see public addCity() 
     */
	public void setLinkedCities(Map<City, Double[]> linkedCities) {
		this.linkedCities = linkedCities;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}
	/**
	 * adds to the map of connected cities
     * key City value/weight distance
	 * @param city
	 */
	public void addCity(City city) {
		linkedCities.put(city, calculateDistances(city));
	}
	/**
	 * set distance value :
	 * calculate linear distance and altitude difference for  
     * graph arc weight
	 * @param city
	 * @return distance
	 */
	public Double[] calculateDistances(City city) {
		Double[] distances = new Double[2];
		distances[0] = coordinates.distanceOnPlane(city.getCoordinates());
		distances[1] = coordinates.altitudeDifference(city.getCoordinates());
		return distances;
	}

	@Override
	public String toString() {
		String str =  "\nCity [name=" + name + ", id=" + id + ", coordinates=" + coordinates + "]\n";
		StringBuffer buff = new StringBuffer();
		buff.append(str);
		for (City city : linkedCities.keySet()) {
			buff.append("\t" + city.getName() + ", " + linkedCities.get(city)[0] + ", " + linkedCities.get(city)[1] + "\n");
		}
		return buff.toString();
	}
	/**
	 * Checks if you can cross the arc from this city to query city
	 */
	public boolean pointsTo(City city) {
		return linkedCities.containsKey(city);
	}
	
	/**
	 * Checks if you can cross from the query city to this city
	 */
	public boolean isPointedBy(City city) {
		
		return city.pointsTo(this);
	}
	
	/**
	 * Checks if the arc can be crossed both ways
	 */
	public boolean reciprocalPointing(City city) {
		return (pointsTo(city) && isPointedBy(city));
	}
	
	

}
