package it.unibs.ing.arnaldo.rovineperdute;

import java.util.*;

public class Graph {
	
	/**
	 * class containing list cities as (nodes)
	 *  and represents the map 
	 */
	private ArrayList<City> map = new ArrayList<City>();

	public ArrayList<City> getList() {
		return map;
	}

	public void setList(ArrayList<City> map) {
		this.map = map;
	}

	public City cityFromID(int id) {
		for (City city : map) {
			if(city.getId()==id)
				return city;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Graph [map= \n" + map + "]";
	}
	
	
}
