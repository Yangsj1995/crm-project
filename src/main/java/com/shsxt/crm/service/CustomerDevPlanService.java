package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.CustomerDevPlanDao;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.util.AssertUtil;
import com.shsxt.crm.vo.CustomerDevPlan;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerDevPlanService {
    @Resource
    public CustomerDevPlanDao customerDevPlanDao;

    @Resource
    public SaleChanceDao saleChanceDao;

    /**
     * 先检测 营销机会是否存在
     *
     */
    public Map<String,Object> queryCusDevPlans(CusDevPlanQuery cusDevPlanQuery){

        AssertUtil.isTrue(null==saleChanceDao.querySaleChanceById(cusDevPlanQuery.saleChanceId+""),"营销机会不存在");

        PageHelper.startPage(cusDevPlanQuery.getPage(),cusDevPlanQuery.getRows());
        List<CustomerDevPlan> list = customerDevPlanDao.queryCusDevPlans(cusDevPlanQuery);
        PageInfo<CustomerDevPlan> pageInfo = new PageInfo<>(list);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;

    }

    /**
     * 检测：营销机会是否存在
     * 增加额外的数据
     * 1 。 创建时间
     * 2.   更新时间
     * 3.   isValid = 1
     * @param customerDevPlan
     */
    public void insert(CustomerDevPlan customerDevPlan){
        AssertUtil.isTrue(null==saleChanceDao.querySaleChanceById(customerDevPlan.getSaleChanceId()+""),"营销机会不存在");

        customerDevPlan.setCreateDate(new Date());
        customerDevPlan.setUpdateDate(new Date());
        customerDevPlan.setIsValid(1);

        AssertUtil.isTrue(customerDevPlanDao.insert(customerDevPlan)<1,"插入开发机会失败");

        AssertUtil.isTrue(saleChanceDao.updateSaleChanceDevResult(1,customerDevPlan.getSaleChanceId())<1,"修改开发结果失败");
    }

    /**
     * 检测：营销机会是否存在
     * 增加额外的数据
     *
     * 1.   更新时间
     *
     * @param customerDevPlan
     */
    public void update(CustomerDevPlan customerDevPlan){
        AssertUtil.isTrue(null==saleChanceDao.querySaleChanceById(customerDevPlan.getSaleChanceId()+""),"营销机会不存在");

        customerDevPlan.setUpdateDate(new Date());

        AssertUtil.isTrue(customerDevPlanDao.update(customerDevPlan)<1,"更新开发机会失败");
    }

    public void delete(Integer id){

        AssertUtil.isTrue(customerDevPlanDao.delete(id)<1,"删除开发机会失败");

    }

}
