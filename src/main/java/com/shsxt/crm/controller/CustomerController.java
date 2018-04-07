package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerGCQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerService;
import com.shsxt.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
    @Resource
    public CustomerService customerService;

    @RequestMapping("queryAllCustomers")
    @ResponseBody
    public List<Customer> queryAllCustomers() {
        return customerService.queryAllCustomers();
    }

    @RequestMapping("index")
    public String index() {

        return "customer";
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(Customer customer) {
        customerService.insert(customer);
        return success("客户信息添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(Customer customer) {
        customerService.update(customer);
        return success("客户信息修改成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] ids) {
        customerService.delete(ids);
        return success("客户信息删除成功");
    }

    @RequestMapping("queryCustomersByParams")
    @ResponseBody
    public Map<String,Object> queryCustomersByParams(CustomerQuery customerQuery){
        return customerService.queryCustomersByParams(customerQuery);
    }
    @RequestMapping("openCustomerOtherInfo/{type}/{id}")
    public String openCustomerOtherInfo(@PathVariable("type") String type,@PathVariable("id") String id,Model model){
        Customer customer = customerService.queryCustomerById(id);
        model.addAttribute("customer",customer);
        switch (type){
            case "1": return "customer_linkMan";
            case "2": return "customer_concat";
            case "3": return "customer_order";
            default:return "error";
        }
    }

    /**
     * 客户贡献分析
     * @param customerGCQuery
     * @return
     */
    @RequestMapping("queryCustomersContribution")
    @ResponseBody
    public Map<String,Object> queryCustomersContribution(CustomerGCQuery customerGCQuery){

        return  customerService.queryCustomersContribution(customerGCQuery);

    }

    /**
     * 客户构成分析
     * @return
     */
    @RequestMapping("queryCustomerGC")
    @ResponseBody
    public Map<String,Object> queryCustomerGC(){
        return  customerService.queryCustomerGC();
    }





}
