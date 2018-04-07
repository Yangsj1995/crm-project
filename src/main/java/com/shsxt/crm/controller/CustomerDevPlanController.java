package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.service.CustomerDevPlanService;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.CustomerDevPlan;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("cus_dev_plan")
public class CustomerDevPlanController extends BaseController {
    @Resource
    public SaleChanceService saleChanceService;

    @Resource
    public CustomerDevPlanService customerDevPlanService;


    @RequestMapping("index")
    public String index( String id,Model model){
        SaleChance saleChance = saleChanceService.querySaleChanceById(id);
        model.addAttribute("saleChance",saleChance);
        return "cus_dev_plan_detail";

    }

    @RequestMapping("queryCusDevPlans")
    @ResponseBody
    public Map<String,Object> queryCusDevPlans(CusDevPlanQuery cusDevPlanQuery){
        return customerDevPlanService.queryCusDevPlans(cusDevPlanQuery);
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(CustomerDevPlan customerDevPlan){
        customerDevPlanService.insert(customerDevPlan);
        return success("添加开发计划成功");
    }


    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(CustomerDevPlan customerDevPlan){
        customerDevPlanService.update(customerDevPlan);
        return success("修改开发计划成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer  id){
        customerDevPlanService.delete(id);
        return success("删除开发计划成功");
    }


}
