package com.shsxt.crm.dto;

import java.util.List;

public class ServeTypeDto {


    private String name;
    private Integer value;

    private List<String>  listType;

    public List<String> getListType() {
        return listType;
    }

    public void setListType(List<String> listType) {
        this.listType = listType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
