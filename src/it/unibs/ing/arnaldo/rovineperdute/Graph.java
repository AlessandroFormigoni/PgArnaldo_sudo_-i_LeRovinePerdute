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

	public City cityFromID(int id) { // we know cities are stored in map ordered by id so that id == index
		/*for (City city : map) {
			if(city.getId()==id)
				return city;
		}
		return null;*/
		return map.get(id);
	}

	@Override
	public String toString() {
		return "Graph [map= \n" + map + "]";
	}
	
	
}
