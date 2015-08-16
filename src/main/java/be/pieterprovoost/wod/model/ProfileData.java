package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class ProfileData {

    private List<Level> levels = new ArrayList<Level>();

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
}
