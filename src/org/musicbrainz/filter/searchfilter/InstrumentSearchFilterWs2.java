package org.musicbrainz.filter.searchfilter;

import java.util.Map;

/**
 * <p>A filter for the instrument collection.</p>
 * 
 * <p>Note that the <code>name</code> and <code>query</code> properties
 * may not be used together.</p>
 */
public class InstrumentSearchFilterWs2 extends SearchFilterWs2 {

    /**
    * The name of the instrument
    */
    private String name = null;

    public InstrumentSearchFilterWs2() {
       super();
    }

    @Override
    public Map<String, String> createParameters() 
    {
        Map<String, String> map = super.createParameters();
        if (this.name != null) 
        {
                if (map.containsKey(QUERY)) {
                        throw new IllegalArgumentException("The name and query properties may not be used together!");
                }

                map.put(QUERY, this.name);
        } 
        else {
                if (!map.containsKey(QUERY)) {
                        throw new IllegalArgumentException("This filter must specify a query or a name!");
                }
        }

        return map;
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
    public void setName (String name) {
        this.name = name;
    }

}