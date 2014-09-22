package org.musicbrainz.model.searchresult;

import org.musicbrainz.model.entity.AreaWs2;

/**
 * Represents an area result.
 */
public class AreaResultWs2 extends SearchResultWs2 {
	
    /**
     * @return the area
     */
    public AreaWs2 getArea() {
            return (AreaWs2)super.getEntity();
    }

    /**
     * @param area the area to set
     */
    public void setArea(AreaWs2 area) {
            super.setEntity(area);
    }
}
