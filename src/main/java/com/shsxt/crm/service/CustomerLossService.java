package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.shsxt.crm.dao.CustomerDao;
import com.shsxt.crm.dao.CustomerLossDao;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.util.AssertUtil;
import com.shsxt.crm.vo.CustomerLoss;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerLossService {
    @Resource
    public CustomerLossDao customerLossDao;

    @Resource
    public CustomerDao customerDao;


    public Map<String,Object> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery){
        PageHelper.startPage(customerLossQuery.getPage(),customerLossQuery.getRows());
        List<CustomerLoss> list = customerLossDao.queryCustomerLossesByParams(customerLossQuery);
        PageInfo<CustomerLoss> pageInfo = new PageInfo<>(list);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());

        return map;

    }

    public void updateCustomerLossState(Integer id,String reason){
        AssertUtil.isTrue(StringUtils.isBlank(reason),"流失原因不能为空");
        Map<String,Object> map = queryCusstomerLossById(id);
        AssertUtil.isTrue(map==null||map.isEmpty(),"流失记录不存在");

        AssertUtil.isTrue(customerLossDao.updateCustomerLossState(id,reason) <1,"操作失败");


    }

    public Map<String,Object> queryCusstomerLossById(Integer id){
        return customerLossDao.queryById(id);
    }
}
