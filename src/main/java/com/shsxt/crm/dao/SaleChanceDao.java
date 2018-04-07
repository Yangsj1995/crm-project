package com.shsxt.crm.dao;

import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.vo.SaleChance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SaleChanceDao {

    public List<SaleChance> querySaleChancesByParams(SaleChanceQuery saleChanceQuery);

    @Insert("insert into t_sale_chance(chance_source,customer_name,cgjl,overview,link_man,link_phone,"
            + "description,create_man,assign_man,assign_time,state,dev_result,is_valid,create_date,update_date)"
            + " values(#{chanceSource},#{customerName},#{cgjl},#{overview},#{linkMan},#{linkPhone},"
            + " #{description},#{createMan},#{assignMan},#{assignTime},#{state},#{devResult},#{isValid},"
            + "#{createDate},#{updateDate})")
    public Integer insert(SaleChance saleChance);

    @Update("update t_sale_chance set chance_source=#{chanceSource},customer_name=#{customerName},"
            + "cgjl=#{cgjl},overview=#{overview},link_man=#{linkMan},link_phone=#{linkPhone},"
            + "description=#{description},assign_man=#{assignMan},assign_time=#{assignTime},state=#{state},"
            + "update_date=#{updateDate} where id=#{id} and is_valid=1")
    public Integer update(SaleChance saleChance);

    public Integer delete(Integer[] ids);

    @Select("select id,chance_source as chanceSource,customer_name as customerName,"
            + "cgjl,overview,link_man as linkMan,link_phone as linkPhone,"
            + " description,create_man as createMan,assign_man as  assignMan,"
            + " assign_time as assignTime,state,dev_result as devResult,create_date as createDate"
            + " from t_sale_chance where id=#{id} and is_valid=1")
    public SaleChance querySaleChanceById(String id);

    @Update("update t_sale_chance set dev_result = #{devResult} where id = #{saleChanceId} and is_valid = 1")
    public Integer updateSaleChanceDevResult(@Param("devResult") Integer devResult,@Param("saleChanceId") Integer saleChanceId);
}
