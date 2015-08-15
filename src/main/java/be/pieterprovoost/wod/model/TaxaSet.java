package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class TaxaSet {

    List<TaxaSetEntry> entries = new ArrayList<TaxaSetEntry>();

    public List<TaxaSetEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TaxaSetEntry> entries) {
        this.entries = entries;
    }
}
