
package org.musicbrainz.query.browse;

import java.util.List;
import java.util.ArrayList;

import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.filter.browsefilter.InstrumentBrowseFilterWs2;
import org.musicbrainz.includes.InstrumentIncludesWs2;
import org.musicbrainz.model.entity.InstrumentWs2;
import org.musicbrainz.model.entity.listelement.InstrumentListWs2;
import org.musicbrainz.webservice.WebService;

public class InstrumentBrowseWs2 extends BrowseWs2{

    InstrumentListWs2 instrumentList = null;
  
    public InstrumentBrowseWs2(InstrumentBrowseFilterWs2 filter,
                                  InstrumentIncludesWs2 include){
        
       super(filter, include);
    }
    public InstrumentBrowseWs2( WebService ws,
                                    InstrumentBrowseFilterWs2 filter,
                                    InstrumentIncludesWs2 include){
        
       super(ws,filter, include);
    }

    public List <InstrumentWs2> getFullList() {

        getFirstPage();
        while (hasMore())
        {
           getNextPage();
        }
        return instrumentList.getInstruments();

    }
    public List <InstrumentWs2> getFirstPage() {

        instrumentList = new InstrumentListWs2(); 
        getNextPage();

        return instrumentList.getInstruments();
    }
 public List <InstrumentWs2> getNextPage() {
        
        if (instrumentList == null)
            return getFirstPage();
        
        List<InstrumentWs2> results  = getOnePage();
        
        instrumentList.addAllInstruments(results); 
        filter.setOffset(filter.getOffset()+results.size());

        return results;
    }
    public List <InstrumentWs2> getResults(){
        
        if (instrumentList.getInstruments() == null)
        return getFirstPage();
            
        return instrumentList.getInstruments();
        
    }
    private List <InstrumentWs2> getOnePage() {

        List<InstrumentWs2> results
                = new ArrayList<InstrumentWs2>(0);
       
            try {
                    InstrumentListWs2 temp = execQuery();
                    results.addAll(temp.getInstruments());
                    
            } catch (org.musicbrainz.MBWS2Exception ex) {

                    ex.printStackTrace();
            }

        return results;
    }
    
    
    private InstrumentListWs2 execQuery() throws MBWS2Exception
    {

        InstrumentListWs2 le = getMetadata(INSTRUMENT).getInstrumentListWs2();
        listElement = le;
        
        int sz  = le.getInstruments().size();
        return le;
    }
}
