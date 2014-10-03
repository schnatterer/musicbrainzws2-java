package org.musicbrainz.query.search;

import java.util.List;
import java.util.ArrayList;
import org.musicbrainz.exception.MBWS2Exception;
import org.musicbrainz.filter.searchfilter.SeriesSearchFilterWs2;
import org.musicbrainz.model.searchresult.listelement.SeriesSearchResultsWs2;
import org.musicbrainz.model.searchresult.SeriesResultWs2;
import org.musicbrainz.webservice.WebService;


public class SeriesSearchWs2 extends SearchWs2{

    private SeriesSearchResultsWs2 seriesSearchResults = null;
  
    public SeriesSearchWs2(SeriesSearchFilterWs2 filter){
       super(filter);
    }
    
    public SeriesSearchWs2(WebService ws, SeriesSearchFilterWs2 filter){
       super(ws, filter);
    }

    public List <SeriesResultWs2> getFullList() {

        getFirstPage();
        while (hasMore())
        {
           getNextPage();
        }
        return seriesSearchResults.getSeriesResults();

    }
    public List <SeriesResultWs2> getFirstPage() {

        seriesSearchResults = new SeriesSearchResultsWs2(); 
        setLastScore(100);
        getNextPage();

        return seriesSearchResults.getSeriesResults();
    }

    public List <SeriesResultWs2> getNextPage() {
       
        List<SeriesResultWs2> results  = getOnePage();
        
        seriesSearchResults.getSeriesResults().addAll(results); 
        getFilter().setOffset(getFilter().getOffset()+results.size());

        return results;
    }
    public List <SeriesResultWs2> getResults(){
        
        if (seriesSearchResults.getSeriesResults() == null)
        return getFirstPage();
            
        return seriesSearchResults.getSeriesResults();
        
    }
     private List <SeriesResultWs2> getOnePage(){

        List <SeriesResultWs2> results 
                = new ArrayList <SeriesResultWs2>();

        try {
                    SeriesSearchResultsWs2 temp = execQuery();
                    results.addAll(temp.getSeriesResults());


            } catch (org.musicbrainz.exception.MBWS2Exception ex) {
                
                    ex.printStackTrace();
            }

        return results;
    }
    
    private SeriesSearchResultsWs2 execQuery() throws MBWS2Exception
    {

        SeriesSearchResultsWs2 le = getMetadata(SERIES).getSeriesResultsWs2();
        setListElement(le);
        
        int sz  = le.getSeriesResults().size();
        if (sz>0)
        {
            setLastScore((int) le.getSeriesResults().get(sz-1).getScore());
        }
        return le;
    }
}
