package org.musicbrainz.model.searchresult;

import org.musicbrainz.model.entity.PlaceWs2;

/**
 * Represents a place result.
 */
public class PlaceResultWs2 extends SearchResultWs2 {
	
    /**
     * @return the area
     */
    public PlaceWs2 getPlace() {
            return (PlaceWs2)super.getEntity();
    }

    /**
     * @param place the place to set
     */
    public void setPlace(PlaceWs2 place) {
            super.setEntity(place);
    }
}
