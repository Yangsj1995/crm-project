package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

public class OrderDetailQuery extends BaseQuery {

    public Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
