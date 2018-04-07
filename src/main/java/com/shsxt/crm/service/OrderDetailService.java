package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.OrderDetailsDao;
import com.shsxt.crm.query.OrderDetailQuery;
import com.shsxt.crm.vo.OrderDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderDetailService {
    @Resource
    public OrderDetailsDao orderDetailsDao;

    public Map<String,Object> queryOrderDetailsByOrderId(OrderDetailQuery orderDetailQuery){
        PageHelper.startPage(orderDetailQuery.getPage(),orderDetailQuery.getRows());
        List<OrderDetails> list = orderDetailsDao.queryOrderDetailsByOrderId(orderDetailQuery.orderId);
        PageInfo<OrderDetails> pageInfo = new PageInfo<>(list);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
