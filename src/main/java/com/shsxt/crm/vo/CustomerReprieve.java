package com.shsxt.crm.vo;

import com.shsxt.crm.base.BaseVO;

public class CustomerReprieve extends BaseVO {
    private Integer id;

    private Integer lossId;

    private String measure;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure == null ? null : measure.trim();
    }


}