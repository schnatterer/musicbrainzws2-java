package org.musicbrainz.model;

import java.util.logging.Logger;

/**

 */
public class RatingsWs2 
{
    
   private static Logger log = Logger.getLogger(RatingsWs2.class.getName());

    /**
     * the numebr of people who votes
     */
    private Long votesCount=0L;
    /**
     * the average rating
     */
    private Float averageRating=0F;

    /**
     * @return the votesCount
     */
    public Long getVotesCount() {
        return votesCount;
    }

    /**
     * @param votesCount the votesCount to set
     */
    public void setVotesCount(Long votesCount) {
        this.votesCount = votesCount;
    }

    /**
     * @return the averageRating
     */
    public Float getAverageRating() {
        return averageRating;
    }

    /**
     * @param averageRating the averageRating to set
     */
    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }
    
}
