package be.pieterprovoost.wod.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({ "originatorsCruise", "originatorsStationCode", "primaryHeader", "secondaryHeader", "biologicalHeader", "taxonData", "principalInvestigators" })
public class Cast {

    private PrimaryHeader primaryHeader;
    private SecondaryHeader secondaryHeader;
    private BiologicalHeader biologicalHeader;
    private TaxonData taxonData;
    private String originatorsCruise;
    private String originatorsStationCode;
    private List<PrincipalInvestigator> principalInvestigators = new ArrayList<PrincipalInvestigator>();

    public String getOriginatorsCruise() {
        return originatorsCruise;
    }

    public void setOriginatorsCruise(String originatorsCruise) {
        this.originatorsCruise = originatorsCruise;
    }

    public String getOriginatorsStationCode() {
        return originatorsStationCode;
    }

    public void setOriginatorsStationCode(String originatorsStationCode) {
        this.originatorsStationCode = originatorsStationCode;
    }

    public List<PrincipalInvestigator> getPrincipalInvestigators() {
        return principalInvestigators;
    }

    public void setPrincipalInvestigators(List<PrincipalInvestigator> principalInvestigators) {
        this.principalInvestigators = principalInvestigators;
    }

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

    public PrimaryHeader getPrimaryHeader() {
        return primaryHeader;
    }

    public void setPrimaryHeader(PrimaryHeader primaryHeader) {
        this.primaryHeader = primaryHeader;
    }

}
