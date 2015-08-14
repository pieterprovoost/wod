package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class Variable {

    private Integer code;
    private Integer qualityControl;
    private Integer metadataNumber;
    private List<Metadata> metadatas = new ArrayList<Metadata>();

    public List<Metadata> getMetadatas() {
        return metadatas;
    }

    public void setMetadatas(List<Metadata> metadatas) {
        this.metadatas = metadatas;
    }

    public Integer getMetadataNumber() {
        return metadataNumber;
    }

    public void setMetadataNumber(Integer metadataNumber) {
        this.metadataNumber = metadataNumber;
    }

    public Integer getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(Integer qualityControl) {
        this.qualityControl = qualityControl;
    }

    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
