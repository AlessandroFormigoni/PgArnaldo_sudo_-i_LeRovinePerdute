package it.unibs.ing.arnaldo.rovineperdute;

import java.util.*;

public class City {

	private String name;
	private int id;
	private Point coordinates;
	private Map<City,Double[]> linkedCities = new HashMap<City,Double[]>();
	
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
	
	public void addCity(City city) {
		linkedCities.put(city, calculateDistances(city));
	}
	
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

	

}
