package org.musicbrainz.model.entity.listelement;

import java.util.ArrayList;
import java.util.List;

import org.musicbrainz.model.entity.PlaceWs2;
import org.musicbrainz.wsxml.element.ListElement;


/**
 * A list of Places
 */
public class PlaceListWs2 extends ListElement{

	private List<PlaceWs2> places = new ArrayList<PlaceWs2>();

	/**
	 * @return the places
	 */
	public List<PlaceWs2> getPlaces() {
		return places;
	}

	/**
	 * @param places the places to set
	 */
	public void setPlaces(List<PlaceWs2> places) {
		this.places = places;
	}

	public void addPlace(PlaceWs2 place) 
	{
		if (places == null) {
			places = new ArrayList<PlaceWs2>();
		}
		
		places.add(place);
	}
           public void addAllPlaces(List<PlaceWs2> placeList) 
	{
                if (places == null) {
                        places = new ArrayList<PlaceWs2>();
                }

                places.addAll(placeList);
	}
}
