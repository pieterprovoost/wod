package be.pieterprovoost.wod.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Cast {

    private PrimaryHeader primaryHeader;
    private SecondaryHeader secondaryHeader;

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
