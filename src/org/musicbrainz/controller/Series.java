/*
 * A controller for the Series Entity.
 * 
 */
package org.musicbrainz.controller;

import java.util.List;

import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.filter.searchfilter.SeriesSearchFilterWs2;
import org.musicbrainz.includes.SeriesIncludesWs2;
import org.musicbrainz.model.entity.SeriesWs2;
import org.musicbrainz.model.searchresult.SeriesResultWs2;
import org.musicbrainz.query.lookUp.LookUpWs2;
import org.musicbrainz.query.search.SeriesSearchWs2;

public class Series extends Controller{
    
    public Series(){
    
        super();
        setIncluded(new SeriesIncludesWs2());
    }

    
// -------------- Search  -------------------------------------------------//
    
    @Override
    public SeriesSearchFilterWs2 getSearchFilter(){
        
        return (SeriesSearchFilterWs2)super.getSearchFilter();
    }
        
    @Override
    protected SeriesSearchWs2 getSearch(){
        
        return (SeriesSearchWs2)super.getSearch();
    }
    @Override
    protected SeriesSearchFilterWs2 getDefaultSearchFilter(){
        
        SeriesSearchFilterWs2 f = new SeriesSearchFilterWs2();
        f.setLimit((long)100);
        f.setOffset((long)0);
        f.setMinScore((long)20);
        
        return f;

    }
    @Override
    public void search(String searchText){

        initSearch(searchText);
        setSearch(new SeriesSearchWs2(getQueryWs(),getSearchFilter()));
    }
    
    public List <SeriesResultWs2> getFullSearchResultList() {

        return getSearch().getFullList();

    }
    public List <SeriesResultWs2> getFirstSearchResultPage() {

        return getSearch().getFirstPage();
    }
    public List <SeriesResultWs2> getNextSearchResultPage() {

        return getSearch().getNextPage();
    }
    
   // -------------- LookUp -------------------------------------------------//
    
    @Override
    public SeriesIncludesWs2 getIncludes(){
        return (SeriesIncludesWs2)super.getIncludes();
    }
    
    @Override
    protected SeriesIncludesWs2 getDefaultIncludes(){
        
        SeriesIncludesWs2 inc =new SeriesIncludesWs2();
        
        inc.setUrlRelations(true);
        inc.setLabelRelations(true);
        inc.setArtistRelations(true);
        inc.setReleaseGroupRelations(true);
        inc.setReleaseRelations(true);
        inc.setRecordingRelations(true);
        inc.setWorkRelations(true);
        inc.setAreaRelations(true);
        inc.setSeriesRelations(true);
        inc.setInstrumentRelations(true);
        inc.setSeriesRelations(true);
        
        inc.setTags(true);
        inc.setRatings(true);
        inc.setUserTags(true);
        inc.setUserRatings(true);
        
        inc.setAnnotation(true);
        inc.setAliases(true);

        return inc;
    }
    /**
     * @return the Included
   */
    @Override
    protected SeriesIncludesWs2 getIncluded() {
        return (SeriesIncludesWs2)super.getIncluded();
    }
    private SeriesWs2 getSeries() {
        return (SeriesWs2)getEntity();
    }
    
    public SeriesWs2 getComplete(SeriesWs2 series) throws MBWS2Exception {
        if (series == null) return null;
        if (series.getId() == null) return series;
                                                
        // save some field that come from search, but is missing in
        // lookUp http://tickets.musicbrainz.org/browse/MBS-3982
        setIncoming(series);
        
        return getComplete(series.getId());
    }
    public SeriesWs2 getComplete(String id) throws MBWS2Exception{
        
        setEntity(lookUp(id));
        
        return getSeries();
    }
    
    public SeriesWs2 lookUp(SeriesWs2 series) throws MBWS2Exception{

        if (series == null) return null;
        if (series.getId() == null) return series;
                                                        
        // save some field that come from search, but is missing in
        // lookUp http://tickets.musicbrainz.org/browse/MBS-3982
        setIncoming(series);
        
        return lookUp(series.getId());
    }


    protected SeriesIncludesWs2 getIncrementalInc(SeriesIncludesWs2 inc){

        inc = (SeriesIncludesWs2)super.getIncrementalInc(inc);
        if (getIncludes().isAliases() && !getIncluded().isAliases()) inc.setAliases(true);
        
        return inc;
    }
    private boolean needsLookUp(SeriesIncludesWs2 inc){
        
        return (  getSeries() == null ||
                     super.needsLookUp(inc) ||
                     inc.isAliases());
    }

    public SeriesWs2 lookUp(String id) throws MBWS2Exception{
 
        SeriesIncludesWs2 inc = getIncrementalInc(new SeriesIncludesWs2());

        // LookUp is limited by 25 linked entities, to be sure
        // is better perform a Browse (you could also get first 25
        // at lookUp time just hiitting seriesInclude.setReleases(true), 
        // check if there could be  more releases left and in case perform 
        // the Browse... worry about sort order...).
        
        // Sanity check.
        inc.setArtistCredits(false); // invalid request
        inc.setRecordingLevelRelations(false);// invalid request
        inc.setWorkLevelRelations(false);// invalid request
  
        if (needsLookUp(inc))
        {    
            setLookUp(new LookUpWs2(getQueryWs()));

            SeriesWs2 transit = null;
            transit = getLookUp().getSeriesById(id, inc);
             

            if (transit ==null) return null;
            
            if (getSeries() == null || !getSeries().equals(transit)) // series is changed.
            {
                setEntity(transit);
                setIncluded(inc);
            }
            else 
            {
                updateEntity(getSeries(),transit,inc);
                if (inc.isAliases()) {
                    getSeries().setAliases(transit.getAliases());
                    getIncluded().setAliases(true);
                }
            }
        }
        if (inc.isAnnotation()) loadAnnotation(getSeries());

        return getSeries();
    }
}
