package org.musicbrainz.model.searchresult.listelement;

import java.util.ArrayList;
import java.util.List;

import org.musicbrainz.model.entity.listelement.SeriesListWs2;
import org.musicbrainz.model.searchresult.SeriesResultWs2;
import org.musicbrainz.wsxml.element.ListElement;

public class SeriesSearchResultsWs2 extends ListElement{

    protected List<SeriesResultWs2> seriesResults = new ArrayList<SeriesResultWs2>();
    private SeriesListWs2 seriesList = new SeriesListWs2();

    public void addSeriesResult(SeriesResultWs2 seriesResult) 
    {
        if (seriesResults == null) {
                seriesResults = new ArrayList<SeriesResultWs2>();
        }
        if (getSeriesList() == null) {
                seriesList = new SeriesListWs2();
        }
        seriesResults.add(seriesResult);
        seriesList.addSeries(seriesResult.getSeries());
        
        seriesList.setCount(getCount());
        seriesList.setOffset(getOffset());
    }

    public List<SeriesResultWs2> getSeriesResults() {
        return seriesResults;
    }

    public SeriesListWs2 getSeriesList() {
        return seriesList;
    }
}
