/*
 * A controller for the Instrument Entity.
 * 
 */
package org.musicbrainz.controller;

import java.util.List;

import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.filter.searchfilter.InstrumentSearchFilterWs2;
import org.musicbrainz.includes.InstrumentIncludesWs2;
import org.musicbrainz.model.entity.InstrumentWs2;
import org.musicbrainz.model.searchresult.InstrumentResultWs2;
import org.musicbrainz.query.lookUp.LookUpWs2;
import org.musicbrainz.query.search.InstrumentSearchWs2;

public class Instrument extends Controller{
    
    public Instrument(){
    
        super();
        setIncluded(new InstrumentIncludesWs2());
    }

    
// -------------- Search  -------------------------------------------------//
    
    @Override
    public InstrumentSearchFilterWs2 getSearchFilter(){
        
        return (InstrumentSearchFilterWs2)super.getSearchFilter();
    }
        
    @Override
    protected InstrumentSearchWs2 getSearch(){
        
        return (InstrumentSearchWs2)super.getSearch();
    }
    @Override
    protected InstrumentSearchFilterWs2 getDefaultSearchFilter(){
        
        InstrumentSearchFilterWs2 f = new InstrumentSearchFilterWs2();
        f.setLimit((long)100);
        f.setOffset((long)0);
        f.setMinScore((long)20);
        
        return f;

    }
    @Override
    public void search(String searchText){

        initSearch(searchText);
        setSearch(new InstrumentSearchWs2(getQueryWs(),getSearchFilter()));
    }
    
    public List <InstrumentResultWs2> getFullSearchResultList() {

        return getSearch().getFullList();

    }
    public List <InstrumentResultWs2> getFirstSearchResultPage() {

        return getSearch().getFirstPage();
    }
    public List <InstrumentResultWs2> getNextSearchResultPage() {

        return getSearch().getNextPage();
    }
    
   // -------------- LookUp -------------------------------------------------//
    
    @Override
    public InstrumentIncludesWs2 getIncludes(){
        return (InstrumentIncludesWs2)super.getIncludes();
    }
    
    @Override
    protected InstrumentIncludesWs2 getDefaultIncludes(){
        
        InstrumentIncludesWs2 inc =new InstrumentIncludesWs2();
        
        inc.setUrlRelations(true);
        inc.setLabelRelations(true);
        inc.setArtistRelations(true);
        inc.setReleaseGroupRelations(true);
        inc.setReleaseRelations(true);
        inc.setRecordingRelations(true);
        inc.setWorkRelations(true);
        inc.setAreaRelations(true);
        inc.setInstrumentRelations(true);
        inc.setInstrumentRelations(true);
        inc.setSeriesRelations(true);
        
        inc.setTags(false);
        inc.setRatings(false);
        inc.setUserTags(false);
        inc.setUserRatings(false);
        
        inc.setAnnotation(true);
        inc.setAliases(true);

        return inc;
    }
    /**
     * @return the Included
   */
    @Override
    protected InstrumentIncludesWs2 getIncluded() {
        return (InstrumentIncludesWs2)super.getIncluded();
    }
    private InstrumentWs2 getInstrument() {
        return (InstrumentWs2)getEntity();
    }
    
    public InstrumentWs2 getComplete(InstrumentWs2 instrument) throws MBWS2Exception {
        if (instrument == null) return null;
        if (instrument.getId() == null) return instrument;
                                                
        // save some field that come from search, but is missing in
        // lookUp http://tickets.musicbrainz.org/browse/MBS-3982
        setIncoming(instrument);
        
        return getComplete(instrument.getId());
    }
    public InstrumentWs2 getComplete(String id) throws MBWS2Exception{
        
        setEntity(lookUp(id));
        
        return getInstrument();
    }
    
    public InstrumentWs2 lookUp(InstrumentWs2 instrument) throws MBWS2Exception{

        if (instrument == null) return null;
        if (instrument.getId() == null) return instrument;
                                                        
        // save some field that come from search, but is missing in
        // lookUp http://tickets.musicbrainz.org/browse/MBS-3982
        setIncoming(instrument);
        
        return lookUp(instrument.getId());
    }


    protected InstrumentIncludesWs2 getIncrementalInc(InstrumentIncludesWs2 inc){

        inc = (InstrumentIncludesWs2)super.getIncrementalInc(inc);
        if (getIncludes().isAliases() && !getIncluded().isAliases()) inc.setAliases(true);
        
        return inc;
    }
    private boolean needsLookUp(InstrumentIncludesWs2 inc){
        
        return (  getInstrument() == null ||
                     super.needsLookUp(inc) ||
                     inc.isAliases());
    }

    public InstrumentWs2 lookUp(String id) throws MBWS2Exception{
 
        InstrumentIncludesWs2 inc = getIncrementalInc(new InstrumentIncludesWs2());

        // LookUp is limited by 25 linked entities, to be sure
        // is better perform a Browse (you could also get first 25
        // at lookUp time just hiitting instrumentInclude.setReleases(true), 
        // check if there could be  more releases left and in case perform 
        // the Browse... worry about sort order...).
        
        // Sanity check.
        inc.setArtistCredits(false); // invalid request
        inc.setRecordingLevelRelations(false);// invalid request
        inc.setWorkLevelRelations(false);// invalid request
        //
        inc.setTags(false);// invalid request
        inc.setRatings(false);// invalid request
        inc.setUserTags(false);// invalid request
        inc.setUserRatings(false);// invalid request
  
        if (needsLookUp(inc))
        {    
            setLookUp(new LookUpWs2(getQueryWs()));

            InstrumentWs2 transit = null;
            transit = getLookUp().getInstrumentById(id, inc);
             

            if (transit ==null) return null;
            
            if (getInstrument() == null || !getInstrument().equals(transit)) // instrument is changed.
            {
                setEntity(transit);
                setIncluded(inc);
            }
            else 
            {
                updateEntity(getInstrument(),transit,inc);
                if (inc.isAliases()) {
                    getInstrument().setAliases(transit.getAliases());
                    getIncluded().setAliases(true);
                }
            }
        }
        if (inc.isAnnotation()) loadAnnotation(getInstrument());

        return getInstrument();
    }
}
