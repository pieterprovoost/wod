package be.pieterprovoost.wod.model;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private Double depth;
    private Integer depthErrorCode;
    private Integer originatorDepthErrorFlag;
    private List<Value> values = new ArrayList<Value>();

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public Integer getOriginatorDepthErrorFlag() {
        return originatorDepthErrorFlag;
    }

    public void setOriginatorDepthErrorFlag(Integer originatorDepthErrorFlag) {
        this.originatorDepthErrorFlag = originatorDepthErrorFlag;
    }

    public Integer getDepthErrorCode() {
        return depthErrorCode;
    }

    public void setDepthErrorCode(Integer depthErrorCode) {
        this.depthErrorCode = depthErrorCode;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }
}
