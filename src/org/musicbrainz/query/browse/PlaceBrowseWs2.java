
package org.musicbrainz.query.browse;

import java.util.List;
import java.util.ArrayList;

import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.filter.browsefilter.PlaceBrowseFilterWs2;
import org.musicbrainz.includes.PlaceIncludesWs2;
import org.musicbrainz.model.entity.PlaceWs2;
import org.musicbrainz.model.entity.listelement.PlaceListWs2;
import org.musicbrainz.webservice.WebService;

public class PlaceBrowseWs2 extends BrowseWs2{

    PlaceListWs2 placeList = null;
  
    public PlaceBrowseWs2(PlaceBrowseFilterWs2 filter,
                                  PlaceIncludesWs2 include){
        
       super(filter, include);
    }
    public PlaceBrowseWs2( WebService ws,
                                    PlaceBrowseFilterWs2 filter,
                                    PlaceIncludesWs2 include){
        
       super(ws,filter, include);
    }

    public List <PlaceWs2> getFullList() {

        getFirstPage();
        while (hasMore())
        {
           getNextPage();
        }
        return placeList.getPlaces();

    }
    public List <PlaceWs2> getFirstPage() {

        placeList = new PlaceListWs2(); 
        getNextPage();

        return placeList.getPlaces();
    }
 public List <PlaceWs2> getNextPage() {
        
        if (placeList == null)
            return getFirstPage();
        
        List<PlaceWs2> results  = getOnePage();
        
        placeList.addAllPlaces(results); 
        filter.setOffset(filter.getOffset()+results.size());

        return results;
    }
    public List <PlaceWs2> getResults(){
        
        if (placeList.getPlaces() == null)
        return getFirstPage();
            
        return placeList.getPlaces();
        
    }
    private List <PlaceWs2> getOnePage() {

        List<PlaceWs2> results
                = new ArrayList<PlaceWs2>(0);
       
            try {
                    PlaceListWs2 temp = execQuery();
                    results.addAll(temp.getPlaces());
                    
            } catch (org.musicbrainz.MBWS2Exception ex) {

                    ex.printStackTrace();
            }

        return results;
    }
    
    
    private PlaceListWs2 execQuery() throws MBWS2Exception
    {

        PlaceListWs2 le = getMetadata(ARTIST).getPlaceListWs2();
        listElement = le;
        
        int sz  = le.getPlaces().size();
        return le;
    }
}
