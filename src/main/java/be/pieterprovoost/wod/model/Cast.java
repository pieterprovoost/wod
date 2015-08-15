package be.pieterprovoost.wod.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Cast {

    private PrimaryHeader primaryHeader;
    private SecondaryHeader secondaryHeader;
    private BiologicalHeader biologicalHeader;
    private TaxonData taxonData;

    public TaxonData getTaxonData() {
        return taxonData;
    }

    public void setTaxonData(TaxonData taxonData) {
        this.taxonData = taxonData;
    }

    public BiologicalHeader getBiologicalHeader() {
        return biologicalHeader;
    }

    public void setBiologicalHeader(BiologicalHeader biologicalHeader) {
        this.biologicalHeader = biologicalHeader;
    }

    public SecondaryHeader getSecondaryHeader() {
        return secondaryHeader;
    }

    public void setSecondaryHeader(SecondaryHeader secondaryHeader) {
        this.secondaryHeader = secondaryHeader;
    }

    private List<CharacterEntry> characterEntries = new ArrayList<CharacterEntry>();

    public List<CharacterEntry> getCharacterEntries() {
        return characterEntries;
    }

    public void setCharacterEntries(List<CharacterEntry> characterEntries) {
        this.characterEntries = characterEntries;
    }

    public PrimaryHeader getPrimaryHeader() {
        return primaryHeader;
    }

    public void setPrimaryHeader(PrimaryHeader primaryHeader) {
        this.primaryHeader = primaryHeader;
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
