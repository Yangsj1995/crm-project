package com.shsxt.crm.dao;

import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.vo.CustomerLoss;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface CustomerLossDao {

    public Integer insertBatch(List<CustomerLoss> list);

    public List<CustomerLoss> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery);

    public Map<String,Object> queryById(Integer id);

    @Update("update t_customer_loss set state=1 , confirm_loss_time = now(), loss_reason = #{reason} where id = #{id} ")
    public Integer updateCustomerLossState(@Param("id") Integer id, @Param("reason") String reason);
}
