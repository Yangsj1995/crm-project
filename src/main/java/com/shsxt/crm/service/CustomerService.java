package com.shsxt.crm.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.CustomerDao;
import com.shsxt.crm.dao.CustomerLossDao;
import com.shsxt.crm.dto.CustomerDto;
import com.shsxt.crm.query.CustomerGCQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.util.AssertUtil;
import com.shsxt.crm.util.JsonUtil;
import com.shsxt.crm.vo.Customer;
import com.shsxt.crm.vo.CustomerLoss;
import com.shsxt.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    @Resource
    public CustomerDao customerDao;
    @Resource
    public CustomerLossDao customerLossDao;


    public List<Customer> queryAllCustomers(){
        return  customerDao.queryAllCustomers();
    }

    /**
     * 插入前的检查
     * 1 客户名字不能为空，2 联系电话不能为空
     * 3 法人不能为空
     * 额外数据的补录：
     * 1.创建时间
     * 2.更新时间
     * 3.流失状态 为 0 ，未流失
     * 4.有效状态   1
     * @param customer
     */
    public void insert(Customer customer){
        checkParams(customer.getName(),customer.getFr(),customer.getPhone());
        customer.setState(0);
        customer.setIsValid(1);
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(customerDao.insert(customer)<1,"客户信息添加失败");

    }

    public Map<String,Object> queryCustomersContribution(CustomerGCQuery customerGCQuery){
        PageHelper.startPage(customerGCQuery.getPage(),customerGCQuery.getRows());
        List<CustomerDto> list = customerDao.queryCustomersContribution(customerGCQuery);
        PageInfo<CustomerDto> pageInfo = new PageInfo<>(list);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return  map;
    }



    /**
     * 插入前的检查
     * 1 客户名字不能为空，2 联系电话不能为空
     * 3 法人不能为空
     * 额外数据的补录：
     *
     * 1.更新时间
     *
     *
     * @param customer
     */
    public void update(Customer customer){

        checkParams(customer.getName(),customer.getFr(),customer.getPhone());
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(customerDao.update(customer)<1,"客户信息修改失败");

    }

    public Customer queryCustomerById(String id){
       return customerDao.queryCustomerById(id);
    }

    public void delete(Integer[] ids){

        AssertUtil.isTrue(customerDao.delete(ids)<1,"客户信息删除失败");

    }


    public void updateCustomerLossState(){

        List<CustomerLoss> list = customerDao.queryCustomerLoss();

        if(list!=null&&list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                CustomerLoss customerLoss = list.get(i);
                customerLoss.setState(0);
                customerLoss.setIsValid(1);
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
            }
        }
        AssertUtil.isTrue( customerLossDao.insertBatch(list)<1,"客户流失数据添加失败");

    }

    public Map<String,Object> queryCustomersByParams(CustomerQuery customerQuery){
        PageHelper.startPage(customerQuery.getPage(),customerQuery.getRows());
        List<Customer> list = customerDao.queryCustomersByParams(customerQuery);
        PageInfo<Customer> pageInfo = new PageInfo<>(list);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return  map;
    }

    public Map<String,Object> queryCustomerGC(){
       List<CustomerDto>  customerDtos = customerDao.queryCustomerGC();

        /**
         * 准备 X坐标的数组 和 Y坐标的数组
         *
         */
        String[] levels = null;

        Integer[] counts = null;

        Map<String,Object> map  = new HashMap<>();

        map.put("code",300);

        if(customerDtos!=null&&customerDtos.size()>0){

            levels=new String[customerDtos.size()];
            counts=new Integer[customerDtos.size()];


            for (int i = 0; i <customerDtos.size() ; i++) {

                CustomerDto customerDto  = customerDtos.get(i);

                levels[i] = customerDto.getLevel();

                counts[i] = customerDto.getCount();
            }
            map.put("code",200);
        }

        map.put("levels",levels);
        map.put("counts",counts);

        return map;

    }

    public void checkParams(String customerName,String fr ,String phone){
        AssertUtil.isTrue(StringUtils.isBlank(customerName),"客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(fr),"法人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone),"联系电话不能为空");
    }
}
