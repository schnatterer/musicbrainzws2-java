package org.musicbrainz.model.searchresult;

import org.musicbrainz.model.entity.InstrumentWs2;

/**
 * Represents an Instrument result.
 */
public class InstrumentResultWs2 extends SearchResultWs2 {
	
    /**
     * @return the Instrument
     */
    public InstrumentWs2 getInstrument() {
            return (InstrumentWs2)super.getEntity();
    }

    /**
     * @param instrument the Instrument to set
     */
    public void setInstrument(InstrumentWs2 instrument) {
            super.setEntity(instrument);
    }
}
