package org.musicbrainz.model.entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.Area;
import org.musicbrainz.model.LifeSpanWs2;
import org.musicbrainz.model.RelationWs2;
import org.musicbrainz.model.entity.listelement.ArtistListWs2;
import org.musicbrainz.model.entity.listelement.LabelListWs2;
import org.musicbrainz.model.entity.listelement.PlaceListWs2;
import org.musicbrainz.model.entity.listelement.ReleaseListWs2;

/**
 * <p>A Area definition.
 *      Areas are geographic regions or settlements.
 *      See Wikidata for details on how areas are added and updated. 
	 
 * </p>
 */
public class AreaWs2 extends EntityWs2 
{
    private static Logger log = Logger.getLogger(AreaWs2.class.getName());

    private String type;
    private String name;
    private String sortName;
    private String disambiguation;
    private LifeSpanWs2 lifespan;
    
    private List<String> iso_3166_1_codes= new ArrayList<String>();
    private List<String> iso_3166_2_codes= new ArrayList<String>();
    private List<String> iso_3166_3_codes= new ArrayList<String>();

    private ArtistListWs2 artistList = new ArtistListWs2();
    private LabelListWs2 labelList = new LabelListWs2();
    private ReleaseListWs2 releaseList = new ReleaseListWs2();
    private PlaceListWs2 placeList = new PlaceListWs2();
        
    private AreaWs2 superarea;
 /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sortName
     */
    public String getSortName() {
        return sortName;
    }

    /**
     * @param sortName the sortName to set
     */
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /**
     * @return the disambiguation
     */
    public String getDisambiguation() {
        return disambiguation;
    }

    /**
     * @param disambiguation the disambiguation to set
     */
    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    /**
     * @return the iso_3166_1_codes
     */
    public  List<String> getIso_3166_1_codes() {
        return iso_3166_1_codes;
    }

    /**
     * @param iso_3166_1_codes the iso_3166_1_codes to set
     */
    public void setIso_3166_1_codes(List<String> iso_3166_1_codes) {
        this.iso_3166_1_codes = iso_3166_1_codes;
    }

    /**
     * @return the iso_3166_2_codes
     */
    public List<String> getIso_3166_2_codes() {
        return iso_3166_2_codes;
    }

    /**
     * @param iso_3166_2_codes the iso_3166_2_codes to set
     */
    public void setIso_3166_2_codes(List<String> iso_3166_2_codes) {
        this.iso_3166_2_codes = iso_3166_2_codes;
    }
    /**
     * @return the iso_3166_3_codes
     */
    public List<String> getIso_3166_3_codes() {
        return iso_3166_3_codes;
    }

    /**
     * @param iso_3166_3_codes the iso_3166_3_codes to set
     */
    public void setIso_3166_3_codes(List<String> iso_3166_3_codes) {
        this.iso_3166_3_codes = iso_3166_3_codes;
    }
    
    public String getIso_3166_codes_Display() {
        
        List<String> codes = new ArrayList<String>(0);
        
        if (getIso_3166_1_codes()!= null) codes.addAll(getIso_3166_1_codes());
        if (getIso_3166_2_codes()!= null) codes.addAll(getIso_3166_2_codes());
        if (getIso_3166_3_codes()!= null) codes.addAll(getIso_3166_3_codes());

        String out = Arrays.toString(codes.toArray()).trim();
        out = out.substring(1);
        out = out.substring(0, out.length()-1).trim();

        return out;
    }
    /**
     * @return the lifespan
     */
    public LifeSpanWs2 getLifeSpan() {
        return lifespan;
    }
    /**
     * @param lifespan the lifespan to set
     */
    public void setLifeSpan(LifeSpanWs2 lifespan) {
        this.lifespan = lifespan;
    }
    /**
    * Gets the underlying <code>List</clode> of releases.
    * 
    * @return the releases
    */

    public String getCompleteString() {
       
        if (this.superarea != null) return  buildCompleteString(superarea, name);
 
        if (this.getRelationList()== null ||
             this.getRelationList().getRelations() == null ||
             this.getRelationList().getRelations().isEmpty()){
            
                AreaWs2 area =  loadArea(this);
                this.setRelationList(area.getRelationList());
        }
        this.superarea=getSuperArea(this);
        if (superarea != null) return  buildCompleteString(superarea, name);
        return name;
    }
    private String buildCompleteString(AreaWs2 superarea, String name){
        
        String out = name;
        String sup="";
        sup = superarea.getCompleteString();
        if (sup != null && !sup.isEmpty()) out = name+", "+sup;
        return out;
    }

    protected AreaWs2 loadArea(AreaWs2 area){

        if (area.getRelationList()== null ||
             area.getRelationList().getRelations() == null ||
             area.getRelationList().getRelations().isEmpty()){

            Area a = new Area();
            a.setQueryWs(a.getQueryWs());

            a.getIncludes().excludeAll();
            a.getIncludes().setAreaRelations(true);
            try {
                area = a.lookUp(area);
            } catch (MBWS2Exception ex) {
                Logger.getLogger(RecordingWs2.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         return area;
    }
    private AreaWs2 getSuperArea(AreaWs2 area){

         if (area.getRelationList()== null) return null;
         if (area.getRelationList().getRelations() == null) return null;
         if (area.getRelationList().getRelations().isEmpty()) return null;
         
         AreaWs2 sup = null;

         for (Iterator <RelationWs2> i = getRelationList().getRelations().iterator(); i.hasNext();) {
           
            RelationWs2 r = i.next();
            if (!r.getTargetType().equals(RelationWs2.TO_AREA)) continue;
            if (!r.getType().equals(RelationWs2.PARTOFAREA)) continue;
            if (r.getDirection() == null) continue;
            if (!r.getDirection().equals(RelationWs2.DIR_BACKWARD) &&
                 !r.getDirection().equals(RelationWs2.DIR_BACKWARD_PREF)) continue;
            
            sup = (AreaWs2)r.getTarget();
            return sup;
        }
        return  sup;
    }
    public String getUniqueName(){
        if (StringUtils.isNotBlank(disambiguation)) {
                return name + " (" + disambiguation + ")";
        }
        return name;
    }
    @Override
        public String toString() {
            return getUniqueName();
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AreaWs2)) {
            return false;
        }
        AreaWs2 other = (AreaWs2) object;
        if (this.getIdUri().equals(other.getIdUri()))
        {
            return true;
        }

        return false;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 61 * hash + (this.disambiguation != null ? this.disambiguation.hashCode() : 0);
        return hash;
    }
    // Artists -----------------------------------------------------------------//
    public List<ArtistWs2> getArtists() {
           return ( artistList == null ? null : artistList.getArtists());
    }
    /**
    * Sets the underlying <code>List</clode> of artists.
    * 
    * Note: This will implicitly create a new {@link #artistList}
    * if it is null.
    * 
    * @param artists the artists to set
    */
    public void setArtists(List<ArtistWs2> artists) 
    {
           if (artistList == null) {
                   artistList = new ArtistListWs2();
           }

           this.artistList.setArtists(artists);
    }
       /**
    * @return the artistList
    */
    public ArtistListWs2 getArtistList() {
           return artistList;
    }

    /**
    * @param artistList the artistList to set
    */
    public void setArtistList(ArtistListWs2 artistList) {
           this.artistList = artistList;
    }
       /**
    * <p>Adds a artist to the underlying <code>List</clode>
    * of artists.</p>
    * 
    * <p><em>Note: This will implicitly create a new {@link #artistList}
    * if it is null.</em></p>
    * 
    * @param artist The {@link ArtistWs1} to add.
    */
    public void addArtist(ArtistWs2 artist) 
    {
           if (artistList == null) {
                   artistList = new ArtistListWs2();
           } 
           artistList.addArtist(artist);
    }
    // Labels -------------------------------------------------------------------/
     public List<LabelWs2> getLabels() {
           return ( labelList == null ? null : labelList.getLabels());
    }
    /**
    * Sets the underlying <code>List</clode> of labels.
    * 
    * Note: This will implicitly create a new {@link #labelList}
    * if it is null.
    * 
    * @param labels the labels to set
    */
    public void setLabels(List<LabelWs2> labels) 
    {
           if (labelList == null) {
                   labelList = new LabelListWs2();
           }

           this.labelList.setLabels(labels);
    }
       /**
    * @return the labelList
    */
    public LabelListWs2 getLabelList() {
           return labelList;
    }

    /**
    * @param labelList the labelList to set
    */
    public void setLabelList(LabelListWs2 labelList) {
           this.labelList = labelList;
    }
       /**
    * <p>Adds a label to the underlying <code>List</clode>
    * of labels.</p>
    * 
    * <p><em>Note: This will implicitly create a new {@link #labelList}
    * if it is null.</em></p>
    * 
    * @param label The {@link LabelWs1} to add.
    */
    public void addLabel(LabelWs2 label) 
    {
           if (labelList == null) {
                   labelList = new LabelListWs2();
           } 
           labelList.addLabel(label);
    }
    // Releases ------------------------------------------------------------------//
    public List<ReleaseWs2> getReleases() {
           return ( releaseList == null ? null : releaseList.getReleases());
    }
    /**
    * Sets the underlying <code>List</clode> of releases.
    * 
    * Note: This will implicitly create a new {@link #releaseList}
    * if it is null.
    * 
    * @param releases the releases to set
    */
    public void setReleases(List<ReleaseWs2> releases) 
    {
           if (releaseList == null) {
                   releaseList = new ReleaseListWs2();
           }

           this.releaseList.setReleases(releases);
    }
       /**
    * @return the releaseList
    */
    public ReleaseListWs2 getReleaseList() {
           return releaseList;
    }

    /**
    * @param releaseList the releaseList to set
    */
    public void setReleaseList(ReleaseListWs2 releaseList) {
           this.releaseList = releaseList;
    }
       /**
    * <p>Adds a release to the underlying <code>List</clode>
    * of releases.</p>
    * 
    * <p><em>Note: This will implicitly create a new {@link #releaseList}
    * if it is null.</em></p>
    * 
    * @param release The {@link ReleaseWs1} to add.
    */
    public void addRelease(ReleaseWs2 release) 
    {
           if (releaseList == null) {
                   releaseList = new ReleaseListWs2();
           } 
           releaseList.addRelease(release);
    }
    // Places ------------------------------------------------------------------//
    public List<PlaceWs2> getPlaces() {
           return ( placeList == null ? null : placeList.getPlaces());
    }
    /**
    * Sets the underlying <code>List</clode> of places.
    * 
    * Note: This will implicitly create a new {@link #placeList}
    * if it is null.
    * 
    * @param places the places to set
    */
    public void setPlaces(List<PlaceWs2> places) 
    {
           if (placeList == null) {
                   placeList = new PlaceListWs2();
           }

           this.placeList.setPlaces(places);
    }
       /**
    * @return the placeList
    */
    public PlaceListWs2 getPlaceList() {
           return placeList;
    }

    /**
    * @param placeList the placeList to set
    */
    public void setPlaceList(PlaceListWs2 placeList) {
           this.placeList = placeList;
    }
       /**
    * <p>Adds a place to the underlying <code>List</clode>
    * of places.</p>
    * 
    * <p><em>Note: This will implicitly create a new {@link #placeList}
    * if it is null.</em></p>
    * 
    * @param place The {@link PlaceWs1} to add.
    */
    public void addPlace(PlaceWs2 place) 
    {
           if (placeList == null) {
                   placeList = new PlaceListWs2();
           } 
           placeList.addPlace(place);
    }
}