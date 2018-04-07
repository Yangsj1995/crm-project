package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;
import org.springframework.web.servlet.DispatcherServlet;

public class CusDevPlanQuery extends BaseQuery {

   public Integer saleChanceId;

    public Integer getSaleChanceId() {
        return saleChanceId;
    }

    public void setSaleChanceId(Integer saleChanceId) {
        this.saleChanceId = saleChanceId;
    }
}
