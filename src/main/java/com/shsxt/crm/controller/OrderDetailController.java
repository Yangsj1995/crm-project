package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseQuery;
import com.shsxt.crm.query.OrderDetailQuery;
import com.shsxt.crm.service.OrderDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("order_detail")
public class OrderDetailController extends BaseController{

    @Resource
    public OrderDetailService orderDetailService;

    @RequestMapping("queryOrderDetailsByOrderId")
    @ResponseBody
    public Map<String,Object> queryOrderDetailsByOrderId(OrderDetailQuery orderDetailQuery){
        return orderDetailService.queryOrderDetailsByOrderId(orderDetailQuery);

    }
}
