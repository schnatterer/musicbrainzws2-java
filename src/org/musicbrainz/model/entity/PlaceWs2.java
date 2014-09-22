package org.musicbrainz.model.entity;


import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.musicbrainz.model.LifeSpanWs2;

/**
 * <p>A Place definition.
 *      A place is a building or outdoor area used for performing or 
 *      producing music.  
	 
 * </p>
 */
public class PlaceWs2 extends EntityWs2 
{
    private static Logger log = Logger.getLogger(PlaceWs2.class.getName());
    
    private String type;
    private String name;
    private String disambiguation;
    private String address;
    private String latitude;
    private String longitude;
    private AreaWs2 area;
    private LifeSpanWs2 lifespan;

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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @return the area
     */
    public AreaWs2 getArea() {
        return area;
    }

    /**
     * @return the lifespan
     */
    public LifeSpanWs2 getLifespan() {
        return lifespan;
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
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @param area the area to set
     */
    public void setArea(AreaWs2 area) {
        this.area = area;
    }

    /**
     * @param lifespan the lifespan to set
     */
    public void setLifespan(LifeSpanWs2 lifespan) {
        this.lifespan = lifespan;
    }

    public String getFullAddress() {
        String out = name;
        if (getAddress() != null && !getAddress().isEmpty()) 
            out = out+" ("+getAddress();

        if (getArea() == null) return out+")";

        String complete = getArea().getCompleteString();
        
        if (complete.isEmpty()) return out+")";
        out = out+", "+complete+")";
        
        return out;
    } 
     public String getUniqueName(){
        if (StringUtils.isNotBlank(disambiguation)) {
                return name + " (" + disambiguation + ")";
        }
        return name;
    }
    @Override
    public String toString() {
        return getFullAddress();
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlaceWs2)) {
            return false;
        }
        PlaceWs2 other = (PlaceWs2) object;
        if (this.getIdUri().equals(other.getIdUri()))
        {
            return true;
        }
        return false;
    }
}