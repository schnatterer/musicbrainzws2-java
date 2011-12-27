package org.musicbrainz.model;
import java.util.Calendar;
/**
 * A DiscTrack consists of tracknum, offset and length of the drack IN the disc.
 * Primary used to rebuild TOC.
 *
 */

public class DiscTrackWs2 {
     /** track number on disc */
    private int tracknum;
    /** length in sectors of this track */
    private int length;
     /** offset position in sectors of this track */
    private int offset;

    public DiscTrackWs2(Integer tracknum, Integer offset, Integer length) {
        this.tracknum = tracknum;
        this.offset = offset;
        this.length = length;
    }

    public DiscTrackWs2() {
        
    }

     /**
     * @param tracknum the tracknum to set
     */
    public void setTracknum(int tracknum) {
        this.tracknum = tracknum;
    }
    /**
     * @param offset the offset to set in sectors
     */
    public void setOffset(int sectors) {
        this.offset = sectors;
    }
    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }
    /**
     * @param offset the offset to set in milliseconds
     */
    public void setOffsetInMillis(long millis) {
        this.offset = (int)millis*75/1000;
    }
    /**
     * @param length the length to set in milliseconds
     */
    public void setLengthInMillis(long millis) {
        this.length = (int)millis*75/1000;;
    }
    /**
     * @return the tracknum
     */
    public int getTracknum() {
        return tracknum;
    }
    /**
     * @return the offset in sector
     */
    public int getOffset() {
        return offset;
    }
    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }
    
    public Long getOffsetInMillis(){
        return new Long(offset)*1000/75;
    }
    public Long getLengthInMillis(){
        return new Long(length)*1000/75;
    }
    public String getOffsetString(){
        
        return getTimeString(getOffsetInMillis());
    }
     public String getLengthString(){
        
        return getTimeString(getLengthInMillis());
    }
    public int getRealLength(){
        return getLength()-getOffset();
    }
     public Long getRealLengthInMillis(){
        return new Long(getRealLength())*1000/75;
    }
    public String getRealLengthString(){
        
        return getTimeString(getRealLengthInMillis());
    }
    private String getTimeString(Long millis){

        if (millis==null) return "";
        
        Calendar cal = Calendar.getInstance();

        long durms = millis;
        String dur;

        cal.setTimeInMillis(durms);
        dur = String.format("%1$tM:%1$tS", cal);
        
        return dur;
    }
    
}
