package com.shsxt.crm.controller;

import com.shsxt.crm.RequestPermission;
import com.shsxt.crm.SystemLog;
import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {

    @Resource
    public SaleChanceService saleChanceService;


    @RequestMapping("index/{type}")
    public String index(@PathVariable("type") String type){
        switch (type){
            case "1":return "sale_chance";
            case "2":return "cus_dev_plan";
            default:return "error";
        }
    }

    @RequestMapping("updateSaleChanceDevResult")
    @ResponseBody
    public MessageModel updateSaleChanceDevResult(Integer devResult,Integer saleChanceId){
        saleChanceService.updateSaleChanceDevResult(devResult,saleChanceId);
        return success("操作成功");
    }

    @RequestMapping("querySaleChancesByParams")
    @ResponseBody
    public Map<String,Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
       Map<String,Object> map =  saleChanceService.querySaleChancesByParams(saleChanceQuery);
       return map;
    }

    @RequestPermission(aclVal = "101001")
    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(SaleChance saleChance){
        saleChanceService.insert(saleChance);
        MessageModel messageModel = new MessageModel();
        messageModel.setMsg("营销机会添加成功");
        return  messageModel;
    }

    @RequestPermission(aclVal = "101002")
    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(SaleChance saleChance){
        saleChanceService.update(saleChance);
        MessageModel messageModel = new MessageModel();
        messageModel.setMsg("营销机会修改成功");
        return  messageModel;
    }

    @SystemLog(desc = "营销机会删除方法")
    @RequestPermission(aclVal = "101003")
    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] ids){

        saleChanceService.delete(ids);
        MessageModel messageModel = new MessageModel();
        messageModel.setMsg("营销机会删除成功");
        return  messageModel;
    }
}
