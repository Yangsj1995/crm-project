package com.shsxt.crm.dao;

import com.shsxt.crm.vo.DataDic;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataDicDao {

    @Select("select data_dic_value as dataDicValue from t_dataDic where data_dic_name = #{dataDicName} and is_valid = 1")
    public List<DataDic> queryDataDicValueByDataDicName(String dataDicName);
}
