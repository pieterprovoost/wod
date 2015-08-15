package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class SecondaryHeader {

    private List<SecondaryHeaderEntry> entries = new ArrayList<SecondaryHeaderEntry>();

    public List<SecondaryHeaderEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<SecondaryHeaderEntry> entries) {
        this.entries = entries;
    }
}
