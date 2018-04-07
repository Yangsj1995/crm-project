package com.shsxt.crm.dao;


import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.vo.CustomerDevPlan;

import java.util.List;

public interface CustomerDevPlanDao {


    public List<CustomerDevPlan> queryCusDevPlans(CusDevPlanQuery cusDevPlanQuery);

    public Integer insert(CustomerDevPlan customerDevPlan);

    public Integer update(CustomerDevPlan customerDevPlan);

    public Integer delete(Integer id);
}
