package com.shsxt.crm.vo;

import com.shsxt.crm.base.BaseVO;

public class DataDic extends BaseVO{

    private String id;
    private String dataDicName;
    private String dataDicValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataDicName() {
        return dataDicName;
    }

    public void setDataDicName(String dataDicName) {
        this.dataDicName = dataDicName;
    }

    public String getDataDicValue() {
        return dataDicValue;
    }

    public void setDataDicValue(String dataDicValue) {
        this.dataDicValue = dataDicValue;
    }
}
