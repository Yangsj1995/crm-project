package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.service.CustomerLossService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController {

    @Resource
    public CustomerLossService customerLossService;

    @RequestMapping("index")
    public String index() {
        return "customer_loss";
    }

    @RequestMapping("queryCustomerLossesByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery) {
        return customerLossService.queryCustomerLossesByParams(customerLossQuery);
    }

    @RequestMapping("toRepreivePage/{lossId}")
    public String toRepreivePage(@PathVariable("lossId") Integer lossId, Model model) {
        model.addAttribute("customerLoss", customerLossService.queryCusstomerLossById(lossId));
        return "customer_repri";
    }

    @RequestMapping("updateCustomerLossState")
    @ResponseBody
    public MessageModel updateCustomerLossState(Integer lossId, String lossReason){
        customerLossService.updateCustomerLossState(lossId,lossReason);
        return success("流失操作执行成功");
    }
}
