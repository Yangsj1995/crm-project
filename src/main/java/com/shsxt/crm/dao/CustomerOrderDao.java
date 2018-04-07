package com.shsxt.crm.dao;

import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.vo.CustomerOrder;

import java.util.List;
import java.util.Map;

public interface CustomerOrderDao {

    public List<CustomerOrder> queryOrdersByCid(CustomerOrderQuery customerOrderQuery);

    public Map<String,Object> queryOrderInfoById(String orderId);
}
