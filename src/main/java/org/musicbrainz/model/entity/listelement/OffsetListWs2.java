package org.musicbrainz.model.entity.listelement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.musicbrainz.model.OffsetWs2;
import org.musicbrainz.wsxml.element.ListElement;

/**
 * <p>A List of disc offsets referred by a disc</p>

 */
public class OffsetListWs2 extends ListElement
{
    private static Logger log = Logger.getLogger(OffsetListWs2.class.getName());
    
    private List<OffsetWs2> offsets
               = new ArrayList<OffsetWs2>();
    
    public OffsetListWs2() {
    }
    
    public OffsetListWs2(List<OffsetWs2> offsets)
    {
        if (offsets !=null)
        {       
            for (OffsetWs2 element : offsets)
            {
                addOffset(element);
            }
        }
    }
    
    public List<OffsetWs2> getOffsets(){
        return offsets;
    }
    
    private void addOffset(OffsetWs2 element){

        offsets.add(element);

    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
}
