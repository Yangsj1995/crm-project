package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

public class CustomerGCQuery extends BaseQuery{

    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
