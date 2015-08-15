package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class TaxonData {

    private List<TaxaSet> taxaSets = new ArrayList<TaxaSet>();

    public List<TaxaSet> getTaxaSets() {
        return taxaSets;
    }

    public void setTaxaSets(List<TaxaSet> taxaSets) {
        this.taxaSets = taxaSets;
    }
}
