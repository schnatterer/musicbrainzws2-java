package org.musicbrainz.query.search;

import java.util.List;
import java.util.ArrayList;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.filter.searchfilter.PlaceSearchFilterWs2;
import org.musicbrainz.model.searchresult.listelement.PlaceSearchResultsWs2;
import org.musicbrainz.model.searchresult.PlaceResultWs2;
import org.musicbrainz.webservice.WebService;


public class PlaceSearchWs2 extends SearchWs2{

    private PlaceSearchResultsWs2 placeSearchResults = null;
  
    public PlaceSearchWs2(PlaceSearchFilterWs2 filter){
       super(filter);
    }
    
    public PlaceSearchWs2(WebService ws, PlaceSearchFilterWs2 filter){
       super(ws, filter);
    }

    public List <PlaceResultWs2> getFullList() {

        getFirstPage();
        while (hasMore())
        {
           getNextPage();
        }
        return placeSearchResults.getPlaceResults();

    }
    public List <PlaceResultWs2> getFirstPage() {

        placeSearchResults = new PlaceSearchResultsWs2(); 
        setLastScore(100);
        getNextPage();

        return placeSearchResults.getPlaceResults();
    }

    public List <PlaceResultWs2> getNextPage() {
       
        List<PlaceResultWs2> results  = getOnePage();
        
        placeSearchResults.getPlaceResults().addAll(results); 
        getFilter().setOffset(getFilter().getOffset()+results.size());

        return results;
    }
    public List <PlaceResultWs2> getResults(){
        
        if (placeSearchResults.getPlaceResults() == null)
        return getFirstPage();
            
        return placeSearchResults.getPlaceResults();
        
    }
     private List <PlaceResultWs2> getOnePage(){

        List <PlaceResultWs2> results 
                = new ArrayList <PlaceResultWs2>();

        try {
                    PlaceSearchResultsWs2 temp = execQuery();
                    results.addAll(temp.getPlaceResults());


            } catch (org.musicbrainz.MBWS2Exception ex) {
                
                    ex.printStackTrace();
            }

        return results;
    }
    
    private PlaceSearchResultsWs2 execQuery() throws MBWS2Exception
    {

        PlaceSearchResultsWs2 le = getMetadata(PLACE).getPlaceResultsWs2();
        setListElement(le);
        
        int sz  = le.getPlaceResults().size();
        if (sz>0)
        {
            setLastScore((int) le.getPlaceResults().get(sz-1).getScore());
        }
        return le;
    }
}
