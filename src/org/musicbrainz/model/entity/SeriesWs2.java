package org.musicbrainz.model.entity;


import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;


/**
 * <p>A Instrument definition.
 *      A series is a sequence of separate release groups, releases, recordings
 *      or works with a common theme. The theme is usually prominent in the 
 *      branding of the entities in the series and the individual entities will 
 *      often have been given a number indicating the position in the series.
 * 
 *      Examples
 * 
 *        Now That's What I Call Music (UK edition)
 *        Blue Note Commemorative 75th Anniversary Series
 *        Bach-Werke-Verzeichnis
 * 
 * </p>
 */
public class SeriesWs2 extends EntityWs2 
{
    private static Logger log = Logger.getLogger(SeriesWs2.class.getName());

    //Actualy valid values for Type:
    
    public static final String TYPE_RELEASEGROUP ="Release group";
    public static final String TYPE_RELEASE ="Release";
    public static final String TYPE_RECORDING ="Recording";
    public static final String TYPE_WORK ="Work";
    public static final String TYPE_CATALOGUE ="Catalogue";
    
    private String type;
    private String name;
    private String disambiguation;
    private String orderingAttribute;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the disambiguation
     */
    public String getDisambiguation() {
        return disambiguation;
    }

    /**
     * @return the orderingAttribute
     */
    public String getOrderingAttribute() {
        return orderingAttribute;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param disambiguation the disambiguation to set
     */
    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }
    /**
     * @param orderingAttribute the orderingAttribute to set
     */
    public void setOrderingAttribute(String orderingAttribute) {
        this.orderingAttribute = orderingAttribute;
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
        if (!(object instanceof SeriesWs2)) {
            return false;
        }
        SeriesWs2 other = (SeriesWs2) object;
        if (this.getIdUri().equals(other.getIdUri()))
        {
            return true;
        }
        return false;
    }
}