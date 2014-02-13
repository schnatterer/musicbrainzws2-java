package org.musicbrainz.model;


import java.util.logging.Logger;

/**
 * <p>A  Cover Art Archive definition.
 *       
 * ... nothing found in MB documentation.
 * see http://tickets.musicbrainz.org/browse/MBS-4536
	 
 * </p>
 */
public class CoverArtArchiveWs2 
{
    private static Logger log = Logger.getLogger(CoverArtArchiveWs2.class.getName());

    /**
     * @return the log
     */
    public static Logger getLog() {
        return log;
    }


    private boolean artwork=false;
    private boolean front=false;
    private boolean back=false;
    private int count=0;
    
    public CoverArtArchiveWs2(boolean artwork, int count, boolean front, boolean back)
    {
        this.artwork= artwork;
        this.front = front;
        this.back = back;
        this.count = count;
    }

    /**
     * @return the artwork
     */
    public boolean hasArtwork() {
        return artwork;
    }

    /**
     * @return the front
     */
    public boolean hasFront() {
        return front;
    }

    /**
     * @return the back
     */
    public boolean hasBack() {
        return back;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }
   
}