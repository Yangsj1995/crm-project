package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.shsxt.crm.dao.CustomerReprieveDao;
import com.shsxt.crm.query.CustomerRepriQuery;
import com.shsxt.crm.util.AssertUtil;
import com.shsxt.crm.vo.CustomerReprieve;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerReprieveService {
    @Resource
    public CustomerReprieveDao customerReprieveDao;

    @Resource
    public CustomerLossService customerLossService;

    public Map<String,Object> queryCustomerReprieveByLossId(CustomerRepriQuery customerRepriQuery){
        PageHelper.startPage(customerRepriQuery.getPage(),customerRepriQuery.getRows());
        List<CustomerReprieve> orderList = customerReprieveDao.queryRepriByLossId(customerRepriQuery.lossId);
        PageInfo<CustomerReprieve> pageInfo = new PageInfo<>(orderList);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    /**
     * 1.流失ID 暂缓措施 必须存在
     * 2.添加 额外参数
     * @param customerReprieve
     */

    public void insert(CustomerReprieve customerReprieve){
        checkParams(customerReprieve.getLossId(),customerReprieve.getMeasure());
        customerReprieve.setIsValid(1);
        customerReprieve.setCreateDate(new Date());
        customerReprieve.setUpdateDate(new Date());
        AssertUtil.isTrue(customerReprieveDao.insert(customerReprieve)<1,"暂缓措施添加失败");

    }

    public void delete(Integer id){
        AssertUtil.isTrue(customerReprieveDao.delete(id)<1,"删除暂缓措施失败");
    }

    public void update(CustomerReprieve customerReprieve){
        checkParams(customerReprieve.getId(),customerReprieve.getLossId(),customerReprieve.getMeasure());
        customerReprieve.setUpdateDate(new Date());
        AssertUtil.isTrue(customerReprieveDao.update(customerReprieve)<1,"暂缓措施修改失败");

    }

    public void checkParams(Integer id,Integer lossId,String measure){
        checkParams(lossId,measure);

        AssertUtil.isTrue(customerReprieveDao.queryRepriById(id)==null,"暂缓措施不存在");

    }

    public void checkParams(Integer lossId,String measure){
        AssertUtil.isTrue(StringUtils.isBlank(measure),"暂缓措施不能为空");
        Map<String,Object> map = customerLossService.queryCusstomerLossById(lossId);
        AssertUtil.isTrue(lossId==null||map==null||map.isEmpty(),"流失记录不存在");

    }
}
