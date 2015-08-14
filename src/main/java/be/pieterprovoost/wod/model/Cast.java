package be.pieterprovoost.wod.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Cast {

    private PrimaryHeader primaryHeader;

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
