package org.musicbrainz.model.entity.listelement;

import java.util.ArrayList;
import java.util.List;

import org.musicbrainz.model.entity.AreaWs2;
import org.musicbrainz.wsxml.element.ListElement;


/**
 * A list of Areas
 */
public class AreaListWs2 extends ListElement{

	private List<AreaWs2> areas = new ArrayList<AreaWs2>();

	/**
	 * @return the areas
	 */
	public List<AreaWs2> getAreas() {
		return areas;
	}

	/**
	 * @param areas the areas to set
	 */
	public void setAreas(List<AreaWs2> areas) {
		this.areas = areas;
	}

	public void addArea(AreaWs2 area) 
	{
		if (areas == null) {
			areas = new ArrayList<AreaWs2>();
		}
		
		areas.add(area);
	}
           public void addAllAreas(List<AreaWs2> areaList) 
	{
                if (areas == null) {
                        areas = new ArrayList<AreaWs2>();
                }

                areas.addAll(areaList);
	}
}
