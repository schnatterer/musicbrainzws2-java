package org.musicbrainz.model;

import java.util.logging.Logger;

/**
 * <p>Represents an alias for Artist, Works or Labels </p>
 * The alias locale is interesting mostly for transliterations and indicates
 * which locale is used for the alias value. To represent the locale,
 * iso-3166-2 codes like 'ja', 'en', or 'it' are used.</p>
 */
public class AliasWs2 
{
    
     private static Logger log = Logger.getLogger(AliasWs2.class.getName());
    /**
     * The alias
     */
    private String value;
    private String sortName;
    private String type;
    private String primary;
    private String beginDate;
    private String endDate;
    private boolean ended;

    /**
     * ISO-15924 script code
     */
    private String locale;

    public String getDisplayValue(){
        
        String out="";
        
        if (!(primary== null) && !(primary.isEmpty())) out = out+primary;
        if (!(type== null) && !(type.isEmpty())) {
            
            if (!out.isEmpty())out= out+" ";
            out = out+type;
        }
         if (!out.isEmpty())out= out+": "; out = out+value;
         
         if (!(getLocale()== null) && (getLocale().isEmpty())) out = out+" ("+getLocale()+")";
         
        return out;
    }

    /**
     * @return the getScript
     */
    public String getLocale() {
            return locale;
    }

    /**
     * @param getScript the getScript to set
     */
    public void setLocale(String locale) {
            this.locale = locale;
    }

    /**
     * @return the value
     */
    public String getValue() {
            return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
            this.value = value;
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
     * @return the primary
     */
    public String getPrimary() {
        return primary;
    }

    /**
     * @param primary the primary to set
     */
    public void setPrimary(String primary) {
        this.primary = primary;
    }
  /**
     * @return the beginDate
     */
    public String getBeginDate() {
            return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
    }
    /**
     * @return the endDate
     */
    public String getEndDate() {
            return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
            this.endDate = endDate;
    }
    /**
     * @return the ended
     */
    public boolean isEnded() {
        return ended;
    }

    /**
     * @param ended the ended to set
     */
    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
