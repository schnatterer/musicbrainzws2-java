package org.musicbrainz.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.apache.commons.lang3.StringUtils;
import org.mc2.util.miscellaneous.CalendarUtils;
import org.musicbrainz.exception.MBWS2Exception;
import org.musicbrainz.controller.Place;
import org.musicbrainz.model.ArtistCreditWs2;
import org.musicbrainz.model.IsrcWs2;
import org.musicbrainz.model.PuidWs2;
import org.musicbrainz.model.RelationWs2;
import org.musicbrainz.model.entity.listelement.ReleaseListWs2;

/**
 <p> Represents a Recording.</p>
 */
public class RecordingWs2 extends EntityWs2 
{
    private String title;
    private Long durationInMillis;
    private String disambiguation;
    private Boolean video;

    private ArtistCreditWs2 artistCredit;

    private List<PuidWs2> puids;
    private List<IsrcWs2> isrcs;

    private ReleaseListWs2 releaseList = new ReleaseListWs2();
    
    public String getDuration(){
        return CalendarUtils.calcDurationString(this.getDurationInMillis());
    }
  
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    } 
    public Long getDurationInMillis() {
        return durationInMillis == null ?  0 : durationInMillis;
    }
    public void setDurationInMillis(Long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }
  
    public String getDisambiguation() {
        return disambiguation;
    }
    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }
  
    public ArtistCreditWs2 getArtistCredit() {
        return artistCredit;
    }
    public void setArtistCredit(ArtistCreditWs2 artistCredit) {
        this.artistCredit = artistCredit;
    }
     public String getArtistCreditString() {
            if (getArtistCredit()==null) return "";
            return artistCredit.getArtistCreditString();
    }

    public List<PuidWs2> getPuids() {
        return puids;
    }
    public void setPuids(List<PuidWs2> puids) {
        this.puids = puids;
    }
    public void addPuid(PuidWs2 puid) {
        if (puids == null)
                puids = new ArrayList<PuidWs2>();

        puids.add(puid);
    }
    public String getIsrcString() {
        return StringUtils.join(getIsrcs(), ", ");
    }
    public List<IsrcWs2> getIsrcs() {
        return isrcs;
    }
    public void setIsrcs(List<IsrcWs2> isrcs) {
        this.isrcs = isrcs;
    }
    public void addIsrc(IsrcWs2 isrc) {
        if (isrcs == null)
                isrcs = new ArrayList<IsrcWs2>();

        isrcs.add(isrc);
    }

    public List<ReleaseWs2> getReleases() {
        return (releaseList == null ? null : releaseList.getReleases());
    }
    public void setReleases(List<ReleaseWs2> releases) 
    {
        if (releaseList == null) {
                releaseList = new ReleaseListWs2();
        }
        this.releaseList.setReleases(releases);
    }
    public ReleaseListWs2 getReleaseList() {
        return releaseList;
    }
    public void setReleaseList(ReleaseListWs2 releaseList) {
        this.releaseList = releaseList;
    }
    public void addRelease(ReleaseWs2 release) 
    {
        if (releaseList == null) {
                releaseList = new ReleaseListWs2();
        } 
        releaseList.addRelease(release);
    }
    public String getUniqueTitle() {
        if (StringUtils.isNotBlank(disambiguation)) {
                return title + " (" + disambiguation + ")";
        }
        return title;
    }
    /**
     * @return the video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     * @param video the video to set
     */
    public void setVideo(Boolean video) {
        this.video = video;
    }
    public String getRecordingPlaceDisplay(){
        if (getRelationList() == null ) return "";
        if (getRelationList().getRelations().isEmpty()) return "";

        List<String> places = new ArrayList<String>();
        for (Iterator <RelationWs2> i = getRelationList().getRelations().iterator(); i.hasNext();)
        {
            RelationWs2 r = i.next();
            if (!r.getTargetType().equals(RelationWs2.TO_PLACE)) continue;
            if (!r.getType().equals(RelationWs2.RECORDEDAT)) continue;
            
            PlaceWs2 place = (PlaceWs2)r.getTarget();

            if (place.getArea()== null){

                Place p = new Place();
                p.setQueryWs(p.getQueryWs());

                p.getIncludes().excludeAll();
                p.getIncludes().setAreaRelations(true);
                try {
                    place = p.lookUp(place);
                    r.setTarget(place);
                } catch (MBWS2Exception ex) {
                    Logger.getLogger(RecordingWs2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String plstr = place.getFullAddress();
            String date ="";
            String end = "";
                       
            if (r.getBeginDate().isEmpty() && r.getEndDate().isEmpty()) date="";
            else if (r.getBeginDate().isEmpty()) date=r.getBeginDate();
            else if (r.getEndDate().isEmpty()) date=r.getEndDate();
            else if (r.getBeginDate().equals(r.getEndDate())) date=r.getEndDate();
            else  date =  r.getBeginDate()+" - "+r.getEndDate();
            if (!date.isEmpty()) date = "("+date+")";
            
            if (!plstr.isEmpty() && !date.isEmpty()) plstr = plstr+" "+date;
            else if (plstr.isEmpty()) plstr = date;
            
            places.add(plstr);
        }
        String out = Arrays.toString(places.toArray()).trim();
        out = out.substring(1);
        out = out.substring(0, out.length()-1).trim();
        
        return out;
    }
    public String getMixingPlaceDisplay(){
        if (getRelationList() == null ) return "";
        if (getRelationList().getRelations().isEmpty()) return "";

        List<String> places = new ArrayList<String>();
        for (Iterator <RelationWs2> i = getRelationList().getRelations().iterator(); i.hasNext();)
        {
            RelationWs2 r = i.next();
            if (!r.getTargetType().equals(RelationWs2.TO_PLACE)) continue;
            if (!r.getType().equals(RelationWs2.MIXEDAT)) continue;
            
            PlaceWs2 place = (PlaceWs2)r.getTarget();
            
            if (place.getArea()== null){

                Place p = new Place();
                p.setQueryWs(p.getQueryWs());

                p.getIncludes().excludeAll();
                p.getIncludes().setAreaRelations(true);
                try {
                    place = p.lookUp(place);
                    r.setTarget(place);
                } catch (MBWS2Exception ex) {
                    Logger.getLogger(RecordingWs2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            String plstr = place.getFullAddress();
            String date ="";
            String end = "";
                       
            if (r.getBeginDate().isEmpty() && r.getEndDate().isEmpty()) date="";
            else if (r.getBeginDate().isEmpty()) date=r.getBeginDate();
            else if (r.getEndDate().isEmpty()) date=r.getEndDate();
            else if (r.getBeginDate().equals(r.getEndDate())) date=r.getEndDate();
            else  date =  r.getBeginDate()+" - "+r.getEndDate();
            if (!date.isEmpty()) date = "("+date+")";
            
            if (!plstr.isEmpty() && !date.isEmpty()) plstr = plstr+" "+date;
            else if (plstr.isEmpty()) plstr = date;
            
            places.add(plstr);
        }
        String out = Arrays.toString(places.toArray()).trim();
        out = out.substring(1);
        out = out.substring(0, out.length()-1).trim();
        
        return out;
    }
    @Override
    public String toString() {
        return getUniqueTitle();
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RecordingWs2)) {
            return false;
        }
        RecordingWs2 other = (RecordingWs2) object;
        if (this.getIdUri().equals(other.getIdUri()))
        {
            return true;
        }
        return false;
    }
    
}
