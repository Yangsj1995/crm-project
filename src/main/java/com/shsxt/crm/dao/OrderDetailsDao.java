package com.shsxt.crm.dao;

import com.shsxt.crm.vo.OrderDetails;

import java.util.List;

public interface OrderDetailsDao {

    public List<OrderDetails> queryOrderDetailsByOrderId(Integer orderId);
}
