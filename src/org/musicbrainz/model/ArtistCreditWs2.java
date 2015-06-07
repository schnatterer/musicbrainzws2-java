package org.musicbrainz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>An Artist Credit used instead of Artist for collaborations or 
 * performance name variations.</p>

 */
public class ArtistCreditWs2
{
    private static Logger log = Logger.getLogger(ArtistCreditWs2.class.getName());

    /**
     * A string containing the complete credit as join 
     * of credit names in the list.
     */
    private String artistCreditString="";
       private List<NameCreditWs2> nameCredits
               = new ArrayList<NameCreditWs2>();

    /**
    * Minimal Constructor
    * @param nameCredits A list of  NameCreditWs2
    * describing the Artist Credit.
    */
    public ArtistCreditWs2(List<NameCreditWs2> nameCredits)
    {
        if (nameCredits!=null)
        {       
            for (NameCreditWs2 nameCredit : nameCredits)
            {
                addNameCredit(nameCredit);
            }
        }
    }
    public String getArtistCreditString(){
        return artistCreditString;
    }
    
    public List<NameCreditWs2>  getNameCredits(){
        return nameCredits;
    }
    
    private void addNameCredit(NameCreditWs2 nameCredit){

        nameCredits.add(nameCredit);
        
        if (nameCredit.getNameCreditString() != null && !nameCredit.getNameCreditString().equals(""))
        {
            artistCreditString = 
                 artistCreditString+nameCredit.getNameCreditString();
        }
    }

    @Override
    public String toString() {
            return artistCreditString;
    }
	
	
}