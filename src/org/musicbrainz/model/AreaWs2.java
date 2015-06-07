package org.musicbrainz.model;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>A Area definition.
 *      Areas are geographic regions or settlements.
 *      See Wikidata for details on how areas are added and updated. 
	 
 * </p>
 */
public class AreaWs2 
{
    private static Logger log = Logger.getLogger(AreaWs2.class.getName());

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

    private String id;
    private String type;
    private String name;
    private String sortName;
    private String disambiguation;
    private LifeSpanWs2 lifespan;
    
    private List<String> iso_3166_1_codes= new ArrayList<String>();
    private List<String> iso_3166_2_codes= new ArrayList<String>();
    private List<String> iso_3166_3_codes= new ArrayList<String>();
    
    private List<AliasWs2> aliases = new ArrayList<AliasWs2>();
    
    
    /**
    * Minimal Constructor
    */
    public AreaWs2(){

    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the aliases
     */
    public List<AliasWs2> getAliasList() {
        return aliases;
    }

    /**
     * @param aliases the aliases to set
     */
    public void setAliasList(List<AliasWs2> aliases) {
        this.aliases = aliases;
    }
    
}