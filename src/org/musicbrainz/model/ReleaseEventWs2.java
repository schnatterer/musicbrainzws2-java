package org.musicbrainz.model;

import org.musicbrainz.model.entity.AreaWs2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * <p>A single Medium and relative TrackList.

 */
public class ReleaseEventWs2 
{
    private static Logger log = Logger.getLogger(ReleaseEventWs2.class.getName());

    /**
     * @return the log
     */
    public static Logger getLog() {
        return log;
    }

    /**
     * @param aLog the log to set
     */
    public static void setLog(Logger aLog) {
        log = aLog;
    }
    
    private String dateString;
    private AreaWs2 area;
    
    /**
   * Default Constructor
   */
    public ReleaseEventWs2()
    {

    }
/**
    * Parses the date string and returns a Date
    * @return A Date object
    */
    public Date getDate() {
        return dateFromdateString();
    }

    private Date dateFromdateString() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy");

        if (dateString == null) 
                return null;

        if (dateString.length() == 10) 
                f = new SimpleDateFormat("yyyy-MM-dd");

        if (dateString.length() == 7) 
                f = new SimpleDateFormat("yyyy-MM");

        try {
                return f.parse(dateString);
        } catch (ParseException e) {
                log.warning("Could not parse date string - returning null");
                return null;
        }
    }
    /**
     * @return the dateString
     */
    public String getDateString() {
        return dateString;
    }

    /**
     * @param dateString the dateString to set
     */
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    /**
     * @return the area
     */
    public AreaWs2 getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(AreaWs2 area) {
        this.area = area;
    }
    @Override
    public String toString(){
        return getArea().getName()+" "+getDateString();
    }

}
