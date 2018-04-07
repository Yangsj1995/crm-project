package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.service.CustomerServeService;
import com.shsxt.crm.vo.CustomerServe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("customer_serve")
public class CustomerServerController extends BaseController {

    @Resource
    public CustomerServeService customerServeService;

    @RequestMapping("index/{type}")
    public String index(@PathVariable("type") Integer type) {

        switch (type) {
            case 1:
                return "customer_serve_create";
            case 2:
                return "customer_serve_assign";
            case 3:
                return "customer_serve_proceed";
            case 4:
                return "customer_serve_feed_back";
            case 5:
                return "customer_serve_archive";
            default:
                return "error";
        }

    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(CustomerServe customerServe) {

        customerServeService.insert(customerServe);

        return success("服务创建成功");
    }
    @RequestMapping("queryCustomerServesByParams")
    @ResponseBody
    public Map<String,Object> queryCustomerServesByParams(CustomerServeQuery customerServeQuery){

        return  customerServeService.queryCustomerServesByParams(customerServeQuery);
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(CustomerServe customerServe,HttpServletRequest httpServletRequest){
        customerServeService.update(customerServe,httpServletRequest);
        return success("操作成功");
    }

    @ResponseBody
    @RequestMapping("queryCustomerServeType")
    public Map<String,Object> queryCustomerServeType(){
        return customerServeService.queryCustomerServeType();
    }

}
