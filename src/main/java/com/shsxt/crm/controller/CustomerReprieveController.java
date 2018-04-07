package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerRepriQuery;
import com.shsxt.crm.service.CustomerReprieveService;
import com.shsxt.crm.vo.CustomerReprieve;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_repri")
public class CustomerReprieveController extends BaseController {

    @Resource
    public CustomerReprieveService customerReprieveService;

    @RequestMapping("customerReprieveByLossId")
    @ResponseBody
    public Map<String,Object> queryCustomerReprieveById(CustomerRepriQuery customerRepriQuery){

        return customerReprieveService.queryCustomerReprieveByLossId(customerRepriQuery);
    }



    @RequestMapping("insertReprive")
    @ResponseBody
    public MessageModel addCustomerReprive(CustomerReprieve customerReprieve){
        customerReprieveService.insert(customerReprieve);
        return success("添加暂缓措施成功");
    }

    @RequestMapping("updateReprive")
    @ResponseBody
    public MessageModel updateCustomerReprive(CustomerReprieve customerReprieve){
        customerReprieveService.update(customerReprieve);
        return success("添加暂缓更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer id){
        customerReprieveService.delete(id);
        return success("删除暂缓措施成功");
    }


}
