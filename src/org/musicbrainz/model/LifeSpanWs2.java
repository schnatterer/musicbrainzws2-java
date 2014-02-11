package org.musicbrainz.model;


import java.util.logging.Logger;

/**
 * <p>A Life Span definition.
 *       * The definition of the begin date depends on the Entity
	 * type. For persons, this is the day of birth, for groups 
         * and Labels is the day was founded
	 * 
	 * The returned date has the format 'YYYY', 'YYYY-MM', or 
	 * 'YYYY-MM-DD', depending on how much detail is known.
 * 
 *       * The definition of the end date depends on the artist's
	 * type. For persons, this is the day of death, for groups 
         * and Labels it is the day was dissolved.
	 * 
	 * The returned date has the format 'YYYY', 'YYYY-MM', or 
	 * 'YYYY-MM-DD', depending on how much detail is known.
	 
 * </p>
 */
public class LifeSpanWs2 
{
    private static Logger log = Logger.getLogger(LifeSpanWs2.class.getName());

    private String begin;
    private String end;
    
    /**
    * Minimal Constructor
    * @param nameCredits A list of  NameCreditWs2
    * describing the Artist Credit.
    */
    public LifeSpanWs2(String begin, String end)
    {
        this.begin= begin;
        this.end = end;
    }
    public String getBegin(){
        return begin;
    }
    public String getEnd(){
        return end;
    }
}