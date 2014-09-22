package org.musicbrainz.model.entity.listelement;

import java.util.ArrayList;
import java.util.List;

import org.musicbrainz.model.entity.SeriesWs2;
import org.musicbrainz.wsxml.element.ListElement;


/**
 * A list of Seriess
 */
public class SeriesListWs2 extends ListElement{

	private List<SeriesWs2> series = new ArrayList<SeriesWs2>();

	/**
	 * @return the seriess
	 */
	public List<SeriesWs2> getSeries() {
		return series;
	}

	/**
	 * @param seriess the seriess to set
	 */
	public void setSeries(List<SeriesWs2> series) {
		this.series = series;
	}

	public void addSeries(SeriesWs2 oneSeries) 
	{
		if (series == null) {
			series = new ArrayList<SeriesWs2>();
		}
		
		series.add(oneSeries);
	}
           public void addAllSeriess(List<SeriesWs2> seriesList) 
	{
                if (series == null) {
                        series = new ArrayList<SeriesWs2>();
                }

                series.addAll(seriesList);
	}
}
