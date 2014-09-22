package org.musicbrainz.model.entity;


import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>A Instrument definition.
 *      Instruments are devices created or adapted to make musical sounds. 
 *      Instruments are primarily used in relationships between two other 
 *      entities and for that, each instrument entity has a parallel relationship 
 *      attribute with the same MBID. Instruments, like relationship attributes, 
 *      can only be edited by relationship editors. 	 
 * </p>
 */
public class InstrumentWs2 extends EntityWs2 
{
    private static Logger log = Logger.getLogger(InstrumentWs2.class.getName());

    //Actualy valid values for Type:
    
    public static final String TYPE_WINDINSTRUMENT ="Wind instrument";
    public static final String TYPE_STRINGINSTRUMENT ="String instrument";
    public static final String TYPE_PERCUSSIONSTRUMENT ="Percussion instrument";
    public static final String TYPE_ELECTRONICINSTRUMENT ="Electronic instrument";
    public static final String TYPE_OTHERINSTRUMENT ="Other instrument";
    
    private String type;
    private String name;
    private String disambiguation;
    private String description;

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
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof InstrumentWs2)) {
            return false;
        }
        InstrumentWs2 other = (InstrumentWs2) object;
        if (this.getIdUri().equals(other.getIdUri()))
        {
            return true;
        }

        return false;
    }
}