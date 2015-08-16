package be.pieterprovoost.wod.model;

public class Value {

    private Double value;
    private Integer qualityControl;
    private Integer originator;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(Integer qualityControl) {
        this.qualityControl = qualityControl;
    }

    public Integer getOriginator() {
        return originator;
    }

    public void setOriginator(Integer originator) {
        this.originator = originator;
    }
}
