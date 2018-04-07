package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseQuery;
import com.shsxt.crm.dao.CustomerOrderDao;
import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.service.CustomerOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_order")
public class CustomerOrderController extends BaseController {

    @Resource
    public CustomerOrderService customerOrderService;

    @RequestMapping("queryOrdersByCid")
    @ResponseBody
    public Map<String,Object> queryOrdersByCid(CustomerOrderQuery customerOrderQuery){
        Map<String,Object> map = customerOrderService.queryOrdersByCid(customerOrderQuery);
        return map;
    }

    @RequestMapping("queryOrderInfoById")
    @ResponseBody
    public Map<String,Object> queryOrderInfoById(String orderId){
        Map<String,Object> map = customerOrderService.queryOrderInfoById(orderId);
        return  map;
    }

}
