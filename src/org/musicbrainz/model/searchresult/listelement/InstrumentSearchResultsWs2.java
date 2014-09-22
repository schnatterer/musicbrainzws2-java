package org.musicbrainz.model.searchresult.listelement;

import java.util.ArrayList;
import java.util.List;

import org.musicbrainz.model.entity.listelement.InstrumentListWs2;
import org.musicbrainz.model.searchresult.InstrumentResultWs2;
import org.musicbrainz.wsxml.element.ListElement;

public class InstrumentSearchResultsWs2 extends ListElement{

    protected List<InstrumentResultWs2> instrumentResults = new ArrayList<InstrumentResultWs2>();
    private InstrumentListWs2 instrumentList = new InstrumentListWs2();

    public void addInstrumentResult(InstrumentResultWs2 instrumentResult) 
    {
        if (instrumentResults == null) {
                instrumentResults = new ArrayList<InstrumentResultWs2>();
        }
        if (getInstrumentList() == null) {
                instrumentList = new InstrumentListWs2();
        }
        instrumentResults.add(instrumentResult);
        instrumentList.addInstrument(instrumentResult.getInstrument());
        
        instrumentList.setCount(getCount());
        instrumentList.setOffset(getOffset());
    }

    public List<InstrumentResultWs2> getInstrumentResults() {
        return instrumentResults;
    }

    public InstrumentListWs2 getInstrumentList() {
        return instrumentList;
    }
}
