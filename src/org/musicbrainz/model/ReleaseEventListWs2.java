package org.musicbrainz.model;

import org.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Logger;

/**
 * <p>A List of Media (Medium) referred by a release</p>

 */
public class ReleaseEventListWs2 extends ListElement
{
    private static Logger log = Logger.getLogger(ReleaseEventListWs2.class.getName());

    private List<ReleaseEventWs2> events
               = new ArrayList<ReleaseEventWs2>();


    public ReleaseEventListWs2(List<ReleaseEventWs2> events)
    {
        if (events!=null)
        {       
            for (ReleaseEventWs2 element : events)
            {
                addReleaseEvent(element);
            }
        }
    }
    
    public List<ReleaseEventWs2>  getReleaseEvents(){
        return events;
    }
    
    private void addReleaseEvent(ReleaseEventWs2 element){

        events.add(element);

    }
    @Override
    public String toString(){
       
        if (getReleaseEvents() == null || 
             getReleaseEvents().isEmpty()) return "";
       
       String out="";
       String separator="";
       Iterator itr = getReleaseEvents().iterator();
       while (itr.hasNext()) {
              
                ReleaseEventWs2 ev = (ReleaseEventWs2)itr.next();
                out=out+separator+ ev.toString();
                separator=", ";
       }
       return out;
    }
}