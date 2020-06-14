package it.unibs.ing.arnaldo.rovineperdute;

import java.util.*;

/**
 * It simply contains a list of cities. Each {@linkplain City} has a {@linkplain Map} of
 * linked cities as keys and distances as values.
 * @author Simone, Alessandro, Francesca
 *
 */
public class Graph {
	
	
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
