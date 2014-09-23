/*
 * A controller for the Area Entity.
 * 
 */
package org.musicbrainz.controller;

import java.util.ArrayList;
import java.util.List;

import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.filter.browsefilter.ArtistBrowseFilterWs2;
import org.musicbrainz.filter.browsefilter.LabelBrowseFilterWs2;
import org.musicbrainz.filter.browsefilter.PlaceBrowseFilterWs2;
import org.musicbrainz.filter.browsefilter.ReleaseBrowseFilterWs2;
import org.musicbrainz.filter.searchfilter.AreaSearchFilterWs2;
import org.musicbrainz.includes.AreaIncludesWs2;
import org.musicbrainz.includes.ArtistIncludesWs2;
import org.musicbrainz.includes.LabelIncludesWs2;
import org.musicbrainz.includes.PlaceIncludesWs2;
import org.musicbrainz.includes.ReleaseIncludesWs2;
import org.musicbrainz.model.entity.AreaWs2;
import org.musicbrainz.model.entity.ArtistWs2;
import org.musicbrainz.model.entity.LabelWs2;
import org.musicbrainz.model.entity.PlaceWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.musicbrainz.model.searchresult.AreaResultWs2;
import org.musicbrainz.query.browse.ArtistBrowseWs2;
import org.musicbrainz.query.browse.LabelBrowseWs2;
import org.musicbrainz.query.browse.PlaceBrowseWs2;
import org.musicbrainz.query.browse.ReleaseBrowseWs2;
import org.musicbrainz.query.lookUp.LookUpWs2;
import org.musicbrainz.query.search.AreaSearchWs2;

public class Area extends Controller{
    
    private ArtistBrowseWs2  artistBrowse;
    private ArtistIncludesWs2 artistIncludes;
    private ArtistBrowseFilterWs2 artistBrowseFilter;
    
    private LabelBrowseWs2 labelBrowse;
    private LabelIncludesWs2 labelIncludes;
    private LabelBrowseFilterWs2 labelBrowseFilter;
    
    private ReleaseBrowseWs2 releaseBrowse;
    private ReleaseIncludesWs2 releaseIncludes;
    private ReleaseBrowseFilterWs2 releaseBrowseFilter;
    
    private PlaceBrowseWs2 placeBrowse;
    private PlaceIncludesWs2 placeIncludes;
    private PlaceBrowseFilterWs2 placeBrowseFilter;
    
    public Area(){
    
        super();
        setIncluded(new AreaIncludesWs2());
    }

    
// -------------- Search  -------------------------------------------------//
    
    @Override
    public AreaSearchFilterWs2 getSearchFilter(){
        
        return (AreaSearchFilterWs2)super.getSearchFilter();
    }
        
    @Override
    protected AreaSearchWs2 getSearch(){
        
        return (AreaSearchWs2)super.getSearch();
    }
    @Override
    protected AreaSearchFilterWs2 getDefaultSearchFilter(){
        
        AreaSearchFilterWs2 f = new AreaSearchFilterWs2();
        f.setLimit((long)100);
        f.setOffset((long)0);
        f.setMinScore((long)20);
        
        return f;

    }
    @Override
    public void search(String searchText){

        initSearch(searchText);
        setSearch(new AreaSearchWs2(getQueryWs(),getSearchFilter()));
    }
    
    public List <AreaResultWs2> getFullSearchResultList() {

        return getSearch().getFullList();

    }
    public List <AreaResultWs2> getFirstSearchResultPage() {

        return getSearch().getFirstPage();
    }
    public List <AreaResultWs2> getNextSearchResultPage() {

        return getSearch().getNextPage();
    }
    
   // -------------- LookUp -------------------------------------------------//
    
    @Override
    public AreaIncludesWs2 getIncludes(){
        return (AreaIncludesWs2)super.getIncludes();
    }
    
    @Override
    protected AreaIncludesWs2 getDefaultIncludes(){
        
        AreaIncludesWs2 inc =new AreaIncludesWs2();
        
        inc.setUrlRelations(true);
        inc.setLabelRelations(true);
        inc.setArtistRelations(true);
        inc.setReleaseGroupRelations(true);
        inc.setReleaseRelations(true);
        inc.setRecordingRelations(true);
        inc.setWorkRelations(true);
        inc.setAreaRelations(true);
        inc.setPlaceRelations(true);
        inc.setInstrumentRelations(true);
        inc.setSeriesRelations(true);
        
        inc.setTags(true);
        inc.setRatings(true);
        inc.setUserTags(true);
        inc.setUserRatings(true);
        
        inc.setAnnotation(true);
        inc.setAliases(false);
        inc.setArtists(false);
        inc.setLabels(false);
        inc.setReleases(false);
        inc.setPlaces(false);
        
        inc.setArtistCredits(true);
        inc.setDiscids(false);
        inc.setIsrcs(false);
        inc.setMedia(false);
        inc.setPuids(false);

        return inc;
    }
    /**
     * @return the Included
   */
    @Override
    protected AreaIncludesWs2 getIncluded() {
        return (AreaIncludesWs2)super.getIncluded();
    }
    private AreaWs2 getArea() {
        return (AreaWs2)getEntity();
    }
    
    public AreaWs2 getComplete(AreaWs2 area) throws MBWS2Exception {
        if (area == null) return null;
        if (area.getId() == null) return area;
                                                
        // save some field that come from search, but is missing in
        // lookUp http://tickets.musicbrainz.org/browse/MBS-3982
        setIncoming(area);
        
        return getComplete(area.getId());
    }
    public AreaWs2 getComplete(String id) throws MBWS2Exception{
        
        setEntity(lookUp(id));
        if (getIncludes().isArtists()) getFullArtistList();
        if (getIncludes().isLabels()) getFullLabelList();
        if (getIncludes().isReleases()) getFullReleaseList();
        if (getIncludes().isPlaces()) getFullPlaceList();
        
        return getArea();
    }
    
    public AreaWs2 lookUp(AreaWs2 area) throws MBWS2Exception{

        if (area == null) return null;
        if (area.getId() == null) return area;
                                                        
        // save some field that come from search, but is missing in
        // lookUp http://tickets.musicbrainz.org/browse/MBS-3982
        setIncoming(area);
        
        return lookUp(area.getId());
    }


    protected AreaIncludesWs2 getIncrementalInc(AreaIncludesWs2 inc){

        inc = (AreaIncludesWs2)super.getIncrementalInc(inc);
        if (getIncludes().isAliases() && !getIncluded().isAliases()) inc.setAliases(true);
        
        //if (getIncludes().isArtists() && !getIncluded().isArtists()) inc.setArtists(true);
       // if (getIncludes().isLabels() && !getIncluded().isLabels()) inc.setLabels(true);
      //  if (getIncludes().isReleases() && !getIncluded().isReleases()) inc.setReleases(true);
      //  if (getIncludes().isPlaces() && !getIncluded().isPlaces()) inc.setPlaces(true);
        
        
        return inc;
    }
    private boolean needsLookUp(AreaIncludesWs2 inc){
        
        return (  getArea() == null ||
                     super.needsLookUp(inc) ||
                     inc.isAliases());
    }

    public AreaWs2 lookUp(String id) throws MBWS2Exception{
 
        AreaIncludesWs2 inc = getIncrementalInc(new AreaIncludesWs2());

        // LookUp is limited by 25 linked entities, to be sure
        // is better perform a Browse (you could also get first 25
        // at lookUp time just hiitting areaInclude.setReleases(true), 
        // check if there could be  more releases left and in case perform 
        // the Browse... worry about sort order...).
        
         inc.setArtists(false); // handled via a browse.
         inc.setLabels(false); // handled via a browse.
         inc.setReleases(false); // handled via a browse.
         inc.setPlaces(false); // handled via a browse.
        
         // the following inc params are meaningless if not inc.isRelease() or
        // inc.isRecordings().
        
        inc.setMedia(false);
        inc.setDiscids(false);
        inc.setIsrcs(false);
        inc.setPuids(false);
        
        // Sanity check.
        //if(inc.isArtistCredits()) inc.setReleases(true); // to avoid the artist credits exceptions.
         
        inc.setArtistCredits(false); // invalid request
        
        inc.setRecordingLevelRelations(false);// invalid request
        inc.setWorkLevelRelations(false);// invalid request
  
        if (needsLookUp(inc))
        {    
            setLookUp(new LookUpWs2(getQueryWs()));

            AreaWs2 transit = null;
            transit = getLookUp().getAreaById(id, inc);
             

            if (transit ==null) return null;
            
            if (getArea() == null || !getArea().equals(transit)) // area is changed.
            {
                setEntity(transit);
                setIncluded(inc);
                
                artistBrowse = null;
                labelBrowse = null;
                releaseBrowse = null;
                placeBrowse = null;
              
            }
            else 
            {
                updateEntity(getArea(),transit,inc);
                if (inc.isAliases()) {
                    getArea().setAliases(transit.getAliases());
                    getIncluded().setAliases(true);
                }
            }
        }
        if (inc.isAnnotation()) loadAnnotation(getArea());
        
        initBrowses();
        
        return getArea();
    }
     // ------------- Browse -------------------------------------------------//
    
    private void initBrowses(){
        
        if (getIncludes().isArtists() && artistBrowse == null){
            
            ArtistIncludesWs2 relInc = getArtistIncludes();
            relInc.setRecordingLevelRelations(false);// invalid request
            relInc.setWorkLevelRelations(false);// invalid request

            ArtistBrowseFilterWs2 f = getArtistBrowseFilter();

            f.setRelatedEntity(AREA);
            f.setRelatedId(getArea().getId());

            artistBrowse = new ArtistBrowseWs2(getQueryWs(),f,relInc);

            getIncluded().setArtists(true);
        }
        if (getIncludes().isLabels() && labelBrowse == null){
            
            LabelIncludesWs2 relInc = getLabelIncludes();
            relInc.setRecordingLevelRelations(false);// invalid request
            relInc.setWorkLevelRelations(false);// invalid request

            LabelBrowseFilterWs2 f = getLabelBrowseFilter();

            f.setRelatedEntity(AREA);
            f.setRelatedId(getArea().getId());

            labelBrowse = new LabelBrowseWs2(getQueryWs(),f,relInc);

            getIncluded().setLabels(true);
        }
            
        if (getIncludes().isReleases() && releaseBrowse == null)
        {
            ReleaseIncludesWs2 relInc = getReleaseIncludes();
            relInc.setRecordingLevelRelations(false);// invalid request
            relInc.setWorkLevelRelations(false);// invalid request

            ReleaseBrowseFilterWs2 f = getReleaseBrowseFilter();

            f.setRelatedEntity(AREA);
            f.setRelatedId(getArea().getId());

            releaseBrowse = new ReleaseBrowseWs2(getQueryWs(),f,relInc);

            getIncluded().setReleases(true);
        }
           if (getIncludes().isPlaces() && placeBrowse == null) {
               
            PlaceIncludesWs2 relInc = getPlaceIncludes();
            relInc.setRecordingLevelRelations(false);// invalid request
            relInc.setWorkLevelRelations(false);// invalid request

            PlaceBrowseFilterWs2 f = getPlaceBrowseFilter();

            f.setRelatedEntity(AREA);
            f.setRelatedId(getArea().getId());

            placeBrowse = new PlaceBrowseWs2(getQueryWs(),f,relInc);

            getIncluded().setPlaces(true);
        }
           
    }
// Artists -------------------------------------------------------------------//
    public List <ArtistWs2> getFullArtistList() {
        
         if (getArea() == null) return null;
         getIncludes().setArtists(true);
         if (artistBrowse == null ) initBrowses();
         if (artistBrowse == null ) return null;
         if (!hasMoreArtists()) return getArea().getArtists();
         
         List <ArtistWs2> list = artistBrowse.getFullList();
        
        for (ArtistWs2 rel : list){
             getArea().addArtist(rel);
         }
        
        return list;
    }
    public List <ArtistWs2> getFirstArtistListPage() {
        
        if (getArea() == null) return null;
        getIncludes().setArtists(true);
        if (artistBrowse == null ) initBrowses();
        if (artistBrowse == null ) return null;
         
         List <ArtistWs2> list = artistBrowse.getFirstPage();
        
        for (ArtistWs2 rel : list) {
             getArea().addArtist(rel);
         }
        
        return list;
    }
    public List <ArtistWs2> getNextArtistListPage() {

        if (getArea() == null) return null;
        getIncludes().setArtists(true);
        if (artistBrowse == null ) initBrowses();
        if (artistBrowse == null ) return null;
        if (!hasMoreArtists()) return new ArrayList<ArtistWs2> ();
         
        List <ArtistWs2> list = artistBrowse.getNextPage();
        
        for (ArtistWs2 rel : list){
             getArea().addArtist(rel);
         }
        
        return list;
    }
    public boolean hasMoreArtists(){
        if (getArea() == null) return true;
        if (artistBrowse == null ) return true;
        return artistBrowse.hasMore();
    }
    /**
     * @return the artistIncludes
     */
    public ArtistIncludesWs2 getArtistIncludes() {
        if (artistIncludes == null)
            artistIncludes = getDefaultArtistInclude(getIncludes());
        return artistIncludes;
    }
    private ArtistIncludesWs2 getDefaultArtistInclude(AreaIncludesWs2 areainc){
        
        ArtistIncludesWs2 inc =new ArtistIncludesWs2();
        
        inc.setArtistCredits(false);
        inc.setAliases(false);
        inc.setReleaseGroups(false);
        inc.setReleases(false);
        inc.setVariousArtists(false);
        inc.setRecordings(false);
        inc.setWorks(false);
        inc.setMedia(false);
        inc.setDiscids(false);
       
        inc.setUrlRelations(false);
        inc.setLabelRelations(false);
        inc.setArtistRelations(false);
        inc.setReleaseGroupRelations(false);
        inc.setArtistRelations(false);
        inc.setRecordingRelations(false);
        inc.setWorkRelations(false);
        
        inc.setAreaRelations(false);
        inc.setPlaceRelations(false);
        inc.setInstrumentRelations(false);
        inc.setSeriesRelations(false);
        
        if (areainc == null) return inc;
        
        if (areainc.isDiscids()) inc.setDiscids(true);
        //if (areainc.isRecordingLevelRelations()) inc.setRecordingLevelRelations(true);
        //if (areainc.isWorkLevelRelations()) inc.setWorkLevelRelations(true);

        return inc;
    }
    /**
     * @return the artistBrowseFilter
     */
    public ArtistBrowseFilterWs2 getArtistBrowseFilter() {
         if (artistBrowseFilter == null)
            artistBrowseFilter = getDefaultArtistBrowseFilter();
         return artistBrowseFilter;
    }
    private ArtistBrowseFilterWs2 getDefaultArtistBrowseFilter(){
        
        ArtistBrowseFilterWs2 f = new ArtistBrowseFilterWs2();
        
        f.setLimit((long)getBrowseLimit());
        f.setOffset((long)0);
        
        return f;

    }
// Labels --------------------------------------------------------------------//    
    public List <LabelWs2> getFullLabelList() {
        
         if (getArea() == null) return null;
         getIncludes().setLabels(true);
         if (labelBrowse == null ) initBrowses();
         if (labelBrowse == null ) return null;
         if (!hasMoreLabels()) return getArea().getLabels();
         
         List <LabelWs2> list = labelBrowse.getFullList();
        
        for (LabelWs2 rel : list){
             getArea().addLabel(rel);
         }
        
        return list;
    }
    public List <LabelWs2> getFirstLabelListPage() {
        
        if (getArea() == null) return null;
        getIncludes().setLabels(true);
        if (labelBrowse == null ) initBrowses();
        if (labelBrowse == null ) return null;
         
         List <LabelWs2> list = labelBrowse.getFirstPage();
        
        for (LabelWs2 rel : list) {
             getArea().addLabel(rel);
         }
        
        return list;
    }
    public List <LabelWs2> getNextLabelListPage() {

        if (getArea() == null) return null;
        getIncludes().setLabels(true);
        if (labelBrowse == null ) initBrowses();
        if (labelBrowse == null ) return null;
        if (!hasMoreLabels()) return new ArrayList<LabelWs2> ();
         
        List <LabelWs2> list = labelBrowse.getNextPage();
        
        for (LabelWs2 rel : list){
             getArea().addLabel(rel);
         }
        
        return list;
    }
    public boolean hasMoreLabels(){
        if (getArea() == null) return true;
        if (labelBrowse == null ) return true;
        return labelBrowse.hasMore();
    }
    /**
     * @return the labelIncludes
     */
    public LabelIncludesWs2 getLabelIncludes() {
        if (labelIncludes == null)
            labelIncludes = getDefaultLabelInclude(getIncludes());
        return labelIncludes;
    }
    private LabelIncludesWs2 getDefaultLabelInclude(AreaIncludesWs2 areainc){
        
        LabelIncludesWs2 inc =new LabelIncludesWs2();
        
        inc.setAliases(false);
        inc.setReleases(false);
        inc.setMedia(false);

        inc.setUrlRelations(false);
        inc.setLabelRelations(false);
        inc.setLabelRelations(false);
        inc.setReleaseGroupRelations(false);
        inc.setLabelRelations(false);
        inc.setRecordingRelations(false);
        inc.setWorkRelations(false);
        
        inc.setAreaRelations(false);
        inc.setPlaceRelations(false);
        inc.setInstrumentRelations(false);
        inc.setSeriesRelations(false);
        
        if (areainc == null) return inc;
        
        if (areainc.isDiscids()) inc.setDiscids(true);
        //if (areainc.isRecordingLevelRelations()) inc.setRecordingLevelRelations(true);
        //if (areainc.isWorkLevelRelations()) inc.setWorkLevelRelations(true);

        return inc;
    }
    /**
     * @return the labelBrowseFilter
     */
    public LabelBrowseFilterWs2 getLabelBrowseFilter() {
         if (labelBrowseFilter == null)
            labelBrowseFilter = getDefaultLabelBrowseFilter();
         return labelBrowseFilter;
    }
    private LabelBrowseFilterWs2 getDefaultLabelBrowseFilter(){
        
        LabelBrowseFilterWs2 f = new LabelBrowseFilterWs2();
        
        f.setLimit((long)getBrowseLimit());
        f.setOffset((long)0);
        
        return f;

    }
// Releases ------------------------------------------------------------------//
    
    public List <ReleaseWs2> getFullReleaseList() {
        
         if (getArea() == null) return null;
         getIncludes().setReleases(true);
         if (releaseBrowse == null ) initBrowses();
         if (releaseBrowse == null ) return null;
         if (!hasMoreReleases()) return getArea().getReleases();
         
         List <ReleaseWs2> list = releaseBrowse.getFullList();
        
        for (ReleaseWs2 rel : list)
         {
             getArea().addRelease(rel);
         }
        
        return list;
    }
    public List <ReleaseWs2> getFirstReleaseListPage() {
        
        if (getArea() == null) return null;
        getIncludes().setReleases(true);
        if (releaseBrowse == null ) initBrowses();
        if (releaseBrowse == null ) return null;
         
         List <ReleaseWs2> list = releaseBrowse.getFirstPage();
        
        for (ReleaseWs2 rel : list)
         {
             getArea().addRelease(rel);
         }
        
        return list;
    }
    public List <ReleaseWs2> getNextReleaseListPage() {

        if (getArea() == null) return null;
        getIncludes().setReleases(true);
        if (releaseBrowse == null ) initBrowses();
        if (releaseBrowse == null ) return null;
        if (!hasMoreReleases()) return new ArrayList<ReleaseWs2> ();
         
        List <ReleaseWs2> list = releaseBrowse.getNextPage();
        
        for (ReleaseWs2 rel : list)
         {
             getArea().addRelease(rel);
         }
        
        return list;
    }
    public boolean hasMoreReleases(){
        if (getArea() == null) return true;
        if (releaseBrowse == null ) return true;
        return releaseBrowse.hasMore();
    }
    /**
     * @return the releaseIncludes
     */
    public ReleaseIncludesWs2 getReleaseIncludes() {
        if (releaseIncludes == null)
            releaseIncludes = getDefaultReleaseInclude(getIncludes());
        return releaseIncludes;
    }
    private ReleaseIncludesWs2 getDefaultReleaseInclude(AreaIncludesWs2 areainc){
        
        ReleaseIncludesWs2 inc =new ReleaseIncludesWs2();
        
        inc.setArtistCredits(true);
        inc.setLabel(false);
        inc.setMedia(false);
        inc.setReleaseGroups(true);
        inc.setRecordings(false);
        
        inc.setUrlRelations(false);
        inc.setLabelRelations(false);
        inc.setArtistRelations(false);
        inc.setReleaseGroupRelations(false);
        inc.setReleaseRelations(false);
        inc.setRecordingRelations(false);
        inc.setWorkRelations(false);
        
        inc.setAreaRelations(false);
        inc.setPlaceRelations(false);
        inc.setInstrumentRelations(false);
        inc.setSeriesRelations(false);
        
        if (areainc == null) return inc;
        
        if (areainc.isDiscids()) inc.setDiscids(true);
        //if (areainc.isRecordingLevelRelations()) inc.setRecordingLevelRelations(true);
        //if (areainc.isWorkLevelRelations()) inc.setWorkLevelRelations(true);

        return inc;
    }
    /**
     * @return the releaseBrowseFilter
     */
    public ReleaseBrowseFilterWs2 getReleaseBrowseFilter() {
         if (releaseBrowseFilter == null)
            releaseBrowseFilter = getDefaultReleaseBrowseFilter();
         return releaseBrowseFilter;
    }
    private ReleaseBrowseFilterWs2 getDefaultReleaseBrowseFilter(){
        
        ReleaseBrowseFilterWs2 f = new ReleaseBrowseFilterWs2();
        
        f.getReleaseTypeFilter().setTypeAll(true);
        f.getReleaseStatusFilter().setStatusAll(true);

        f.setLimit((long)getBrowseLimit());
        f.setOffset((long)0);
        
        return f;

    }
// Places --------------------------------------------------------------------//
     public List <PlaceWs2> getFullPlaceList() {
        
         if (getArea() == null) return null;
         getIncludes().setPlaces(true);
         if (placeBrowse == null ) initBrowses();
         if (placeBrowse == null ) return null;
         if (!hasMorePlaces()) return getArea().getPlaces();
         
         List <PlaceWs2> list = placeBrowse.getFullList();
        
        for (PlaceWs2 rel : list){
             getArea().addPlace(rel);
         }
        
        return list;
    }
    public List <PlaceWs2> getFirstPlaceListPage() {
        
        if (getArea() == null) return null;
        getIncludes().setPlaces(true);
        if (placeBrowse == null ) initBrowses();
        if (placeBrowse == null ) return null;
         
         List <PlaceWs2> list = placeBrowse.getFirstPage();
        
        for (PlaceWs2 rel : list) {
             getArea().addPlace(rel);
         }
        
        return list;
    }
    public List <PlaceWs2> getNextPlaceListPage() {

        if (getArea() == null) return null;
        getIncludes().setPlaces(true);
        if (placeBrowse == null ) initBrowses();
        if (placeBrowse == null ) return null;
        if (!hasMorePlaces()) return new ArrayList<PlaceWs2> ();
         
        List <PlaceWs2> list = placeBrowse.getNextPage();
        
        for (PlaceWs2 rel : list){
             getArea().addPlace(rel);
         }
        
        return list;
    }
    public boolean hasMorePlaces(){
        if (getArea() == null) return true;
        if (placeBrowse == null ) return true;
        return placeBrowse.hasMore();
    }
    /**
     * @return the placeIncludes
     */
    public PlaceIncludesWs2 getPlaceIncludes() {
        if (placeIncludes == null)
            placeIncludes = getDefaultPlaceInclude(getIncludes());
        return placeIncludes;
    }
    private PlaceIncludesWs2 getDefaultPlaceInclude(AreaIncludesWs2 areainc){
        
        PlaceIncludesWs2 inc =new PlaceIncludesWs2();
        
        inc.setAliases(false);

        inc.setUrlRelations(false);
        inc.setPlaceRelations(false);
        inc.setPlaceRelations(false);
        inc.setReleaseGroupRelations(false);
        inc.setPlaceRelations(false);
        inc.setRecordingRelations(false);
        inc.setWorkRelations(false);
        
        inc.setAreaRelations(false);
        inc.setPlaceRelations(false);
        inc.setInstrumentRelations(false);
        inc.setSeriesRelations(false);
        
        if (areainc == null) return inc;
        
        //if (areainc.isDiscids()) inc.setDiscids(true);
        //if (areainc.isRecordingLevelRelations()) inc.setRecordingLevelRelations(true);
        //if (areainc.isWorkLevelRelations()) inc.setWorkLevelRelations(true);

        return inc;
    }
    /**
     * @return the placeBrowseFilter
     */
    public PlaceBrowseFilterWs2 getPlaceBrowseFilter() {
         if (placeBrowseFilter == null)
            placeBrowseFilter = getDefaultPlaceBrowseFilter();
         return placeBrowseFilter;
    }
    private PlaceBrowseFilterWs2 getDefaultPlaceBrowseFilter(){
        
        PlaceBrowseFilterWs2 f = new PlaceBrowseFilterWs2();
        
        f.setLimit((long)getBrowseLimit());
        f.setOffset((long)0);
        
        return f;

    }
}
