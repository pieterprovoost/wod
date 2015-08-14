package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class PrimaryHeader {

    private String versionIdentifier;
    private Integer castNumber;
    private String countryCode;
    private Integer cruiseNumber;
    private Integer year;
    private Integer month;
    private Integer day;
    private Double time;
    private Double latitude;
    private Double longitude;
    private Integer levelNumber;
    private Integer profileType;
    private Integer variableNumber;

    private List<Variable> variables = new ArrayList<Variable>();

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public Integer getVariableNumber() {
        return variableNumber;
    }

    public void setVariableNumber(Integer variableNumber) {
        this.variableNumber = variableNumber;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Integer getProfileType() {
        return profileType;
    }

    public void setProfileType(Integer profileType) {
        this.profileType = profileType;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getCruiseNumber() {
        return cruiseNumber;
    }

    public void setCruiseNumber(Integer cruiseNumber) {
        this.cruiseNumber = cruiseNumber;
    }

    public String getVersionIdentifier() {
        return versionIdentifier;
    }

    public void setVersionIdentifier(String versionIdentifier) {
        this.versionIdentifier = versionIdentifier;
    }

    public Integer getCastNumber() {
        return castNumber;
    }

    public void setCastNumber(Integer castNumber) {
        this.castNumber = castNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
