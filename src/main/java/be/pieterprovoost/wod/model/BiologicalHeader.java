package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class BiologicalHeader {

    private List<BiologicalHeaderEntry> entries = new ArrayList<BiologicalHeaderEntry>();

    public List<BiologicalHeaderEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BiologicalHeaderEntry> entries) {
        this.entries = entries;
    }
}
