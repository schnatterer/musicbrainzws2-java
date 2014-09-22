package org.musicbrainz.includes;

import java.util.List;

/**
 * A specification on how much data to return with a work.
 */

public class AreaIncludesWs2 extends IncludesWs2
{

      // Misc inc= arguments 

      private boolean aliases = false;
      
      /* Subqueries
    * The inc= is parameter allows you to request more information to be 
    * included about the entity. Any of the entities directly linked to 
    * the entity can be included. 
   */
       private boolean artists = false;
       private boolean labels = false;
       private boolean releases = false;
       private boolean places = false;
       
     /* inc= arguments which affect subqueries
     * Some additional inc= parameters are supported to specify how 
     * much of the data about the linked entities should be included: 
    */
      private boolean discids = false;
      private boolean media = false;
      private boolean puids = false;
      private boolean isrcs = false;
     
    /**
     * Default constructor
     */
    public AreaIncludesWs2(){

    }
   
    @Override
    public List<String> createIncludeTags() 
    {
            List<String> includeTags = super.createIncludeTags();

            // not that elegant but straight forward :)
            if (isAliases()) includeTags.add(ALIASES_INC);
            if (isArtists()) includeTags.add(ARTISTS_INC);
            if (isLabels()) includeTags.add(LABELS_INC);
            if (isReleases()) includeTags.add(RELEASES_INC);
            if (isPlaces()) includeTags.add(PLACES_INC);
            
            if (isDiscids()) includeTags.add(DISCIDS_INC);
            if (isMedia()) includeTags.add(MEDIA_INC);
            if (isPuids()) includeTags.add(PUIDS_INC);
            if (isIsrcs()) includeTags.add(ISRCS_INC);
            
            return includeTags;
    }
 /**
   * set all the parameters to false.
   */
    @Override
    public void excludeAll(){
        
        super.excludeAll();
        setAliases(false);
        setArtists(false);
        setLabels(false);
        setReleases(false);
        setPlaces(false);
        
        setMedia(false);
        setDiscids(false);
        setPuids(false);
        setIsrcs(false);

    }
    /**
   * set all the parameters to true.
   */
    @Override
    public void includeAll(){
        
       super.includeAll();
        setAliases(true);
        setArtists(true);
        setLabels(true);
        setReleases(true);
        setPlaces(true);
        
        setMedia(true);
        setDiscids(true);
        setPuids(true);
        setIsrcs(true);

    }
    /**
   *  clone the current status of IncludesWs2 to a new one.
   */
    @Override
    public AreaIncludesWs2 clone(){
        
        AreaIncludesWs2 target = new AreaIncludesWs2();
        copyTo(target);
        target.setAliases(isAliases());
        target.setArtists(isArtists());
        target.setLabels(isLabels());
        target.setReleases(isReleases());
        target.setPlaces(isPlaces());
        
        target.setMedia(isMedia());
        target.setDiscids(isDiscids());
        target.setPuids(isPuids());
        target.setIsrcs(isIsrcs());
        
        return target;
    }
 /**
     * @return the aliases
     */
    public boolean isAliases() {
        return aliases;
    }
    /**
     * @param aliases the aliases to set
     */
    public void setAliases(boolean aliases) {
        this.aliases = aliases;
    }

    /**
     * @return the artists
     */
    public boolean isArtists() {
        return artists;
    }

    /**
     * @return the lables
     */
    public boolean isLabels() {
        return labels;
    }

    /**
     * @return the releases
     */
    public boolean isReleases() {
        return releases;
    }

    /**
     * @return the places
     */
    public boolean isPlaces() {
        return places;
    }

    /**
     * @param artists the artists to set
     */
    public void setArtists(boolean artists) {
        this.artists = artists;
    }

    /**
     * @param lables the lables to set
     */
    public void setLabels(boolean labels) {
        this.labels = labels;
    }

    /**
     * @param release the releases to set
     */
    public void setReleases(boolean releases) {
        this.releases = releases;
    }

    /**
     * @param places the places to set
     */
    public void setPlaces(boolean places) {
        this.places = places;
    }
    /**
     * @return the discids
     */
    public boolean isDiscids() {
            return discids;
    }
    /**
     * @param discids the discids to set
     */
    public void setDiscids(boolean discids) {
            this.discids = discids;
    }
       /**
     * @return the media
     */
    public boolean isMedia() {
            return media;
    }
    /**
     * @param media the media to set
     */
    public void setMedia(boolean media) {
            this.media = media;
    }
       /**
     * @return the puids
     */
    public boolean isPuids() {
            return puids;
    }
    /**
     * @param puids the puids to set
     */
    public void setPuids(boolean puids) {
            this.puids = puids;
    }
       /**
     * @return the isrcs
     */
    public boolean isIsrcs() {
            return isrcs;
    }
    /**
     * @param isrcs the isrcs to set
     */
    public void setIsrcs(boolean isrcs) {
            this.isrcs = isrcs;
    }
}
