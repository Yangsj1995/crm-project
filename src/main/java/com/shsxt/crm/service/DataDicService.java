package com.shsxt.crm.service;

import com.shsxt.crm.dao.DataDicDao;
import com.shsxt.crm.vo.DataDic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataDicService {

    @Resource
    public DataDicDao dataDicDao;

    public List<DataDic> queryDataDicValueByDataDicName(String dataDicName){
       return dataDicDao.queryDataDicValueByDataDicName(dataDicName);
    }
}
