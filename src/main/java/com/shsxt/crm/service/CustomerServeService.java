package com.shsxt.crm.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.base.ServeType;
import com.shsxt.crm.dao.CustomerServeDao;
import com.shsxt.crm.dto.ServeTypeDto;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.util.AssertUtil;
import com.shsxt.crm.util.CookieUtil;
import com.shsxt.crm.util.JsonUtil;
import com.shsxt.crm.vo.CustomerReprieve;
import com.shsxt.crm.vo.CustomerServe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class CustomerServeService {

    @Resource
    public CustomerServeDao customerServeDao;


    /**
     * 必要性的检测
     * 1.服务类型
     * 2.客户
     * 3.服务请求
     * 额外数据的补录
     *  1.创建时间
     *  2.更新时间
     *  3.isvalid = 1
     *  4.state = 1   1- 5
     *
     * @param customerServe
     */
    public void insert(CustomerServe customerServe){
        cheakCustomerServeParams(customerServe.getServeType(),customerServe.getCustomer(),customerServe.getServiceRequest());
        customerServe.setState(ServeType.CREATE.getType());
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());
        customerServe.setIsValid(1);
//        customerServe.setCreatePeople();

        AssertUtil.isTrue(customerServeDao.insert(customerServe)<1,"服务创建失败");

    }

    public Map<String,Object> queryCustomerServesByParams(CustomerServeQuery customerServeQuery){
        PageHelper.startPage(customerServeQuery.getPage(),customerServeQuery.getRows());
        List<CustomerServe> orderList = customerServeDao.queryCustomerServesByParams(customerServeQuery.getState());
        PageInfo<CustomerServe> pageInfo = new PageInfo<>(orderList);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;

    }

    public void update(CustomerServe customerServe , HttpServletRequest request){

        customerServe.setUpdateDate(new Date());
        if(customerServe.getState().equals(ServeType.ASSIGN.getType())){

            customerServe.setAssigner(CookieUtil.getCookieValue(request,"trueName"));
            customerServe.setAssignTime(new Date());


        }else if(customerServe.getState().equals(ServeType.PROCEED.getType())){
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProce()),"处理内容不能为空");
            customerServe.setServiceProceTime(new Date());

        }else if(customerServe.getState().equals(ServeType.FEEDBACK.getType())) {


            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProceResult()),"处理结果不能为空");

            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getMyd()),"满意度不能为空");

            customerServe.setState(ServeType.ARCHIVE.getType());
        }


        AssertUtil.isTrue(customerServeDao.update(customerServe)<1,"操作失败");


    }

    public Map<String,Object> queryCustomerServeType(){
        List<ServeTypeDto> list = customerServeDao.queryCustomerServeType();

        String[] types = null;
        ServeTypeDto[] serveTypeDtos = null;

        Map<String,Object> map = new HashMap<>();
        map.put("code",300);

        if(list!=null&& list.size()>0){
            types = new String[list.size()];
            serveTypeDtos = new ServeTypeDto[list.size()];
            for (int i = 0; i <list.size() ; i++) {

                ServeTypeDto serveTypeDto = list.get(i);

                types[i] = serveTypeDto.getName();


                List<String> mylist = new ArrayList<>();

                mylist.add("1");
                mylist.add("2");
                mylist.add("3");
                mylist.add("4");

                serveTypeDto.setListType(mylist);

                serveTypeDtos[i] = serveTypeDto;

            }

            map.put("code",200);
        }

        map.put("types",types);
        map.put("datas",serveTypeDtos);
        System.out.println(JSON.toJSON(map));

        System.out.println(JsonUtil.toJson(map));

        return  map;
    }

    private void cheakCustomerServeParams(String serveType, String customer,
                                          String serviceRequest) {
        AssertUtil.isTrue(StringUtils.isBlank(serveType), "服务类型非空!");
        AssertUtil.isTrue(StringUtils.isBlank(customer), "客户名称非空!");
        AssertUtil.isTrue(StringUtils.isBlank(serviceRequest), "内容非空!");
    }



}
