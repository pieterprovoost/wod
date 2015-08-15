package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterEntry {

    private Integer dataType;
    private String data;
    private Integer nameNumber;

    private List<PrincipalInvestigator> principalInvestigators = new ArrayList<PrincipalInvestigator>();

    public List<PrincipalInvestigator> getPrincipalInvestigators() {
        return principalInvestigators;
    }

    public void setPrincipalInvestigators(List<PrincipalInvestigator> principalInvestigators) {
        this.principalInvestigators = principalInvestigators;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getNameNumber() {
        return nameNumber;
    }

    public void setNameNumber(Integer nameNumber) {
        this.nameNumber = nameNumber;
    }

}
