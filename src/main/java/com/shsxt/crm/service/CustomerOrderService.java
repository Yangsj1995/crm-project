package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.CustomerOrderDao;
import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.vo.CustomerOrder;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerOrderService {

    @Resource
    public CustomerOrderDao customerOrderDao;

    public Map<String,Object> queryOrdersByCid(CustomerOrderQuery customerOrderQuery){
        PageHelper.startPage(customerOrderQuery.getPage(),customerOrderQuery.getRows());
        List<CustomerOrder> list = customerOrderDao.queryOrdersByCid(customerOrderQuery);
        PageInfo<CustomerOrder> pageInfo = new PageInfo<>(list);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return  map;
    }

    public Map<String,Object> queryOrderInfoById(String orderId){
        return customerOrderDao.queryOrderInfoById(orderId);
    }
}
