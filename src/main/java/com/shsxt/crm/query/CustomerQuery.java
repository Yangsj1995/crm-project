package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

public class CustomerQuery extends BaseQuery{

    private String khno;
    private String customerName;

    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}