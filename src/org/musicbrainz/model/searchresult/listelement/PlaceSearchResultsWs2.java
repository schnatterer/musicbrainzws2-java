package org.musicbrainz.model.searchresult.listelement;

import java.util.ArrayList;
import java.util.List;

import org.musicbrainz.model.entity.listelement.PlaceListWs2;
import org.musicbrainz.model.searchresult.PlaceResultWs2;
import org.musicbrainz.wsxml.element.ListElement;

public class PlaceSearchResultsWs2 extends ListElement{

    protected List<PlaceResultWs2> placeResults = new ArrayList<PlaceResultWs2>();
    private PlaceListWs2 placeList = new PlaceListWs2();

    public void addPlaceResult(PlaceResultWs2 placeResult) 
    {
        if (placeResults == null) {
                placeResults = new ArrayList<PlaceResultWs2>();
        }
        if (getPlaceList() == null) {
                placeList = new PlaceListWs2();
        }
        placeResults.add(placeResult);
        placeList.addPlace(placeResult.getPlace());
        
        placeList.setCount(getCount());
        placeList.setOffset(getOffset());
    }

    public List<PlaceResultWs2> getPlaceResults() {
        return placeResults;
    }

    public PlaceListWs2 getPlaceList() {
        return placeList;
    }
}
