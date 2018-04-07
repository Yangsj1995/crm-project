package com.shsxt.crm.dao;

import com.shsxt.crm.vo.CustomerReprieve;

import java.util.List;

public interface CustomerReprieveDao {

    public List<CustomerReprieve> queryRepriByLossId(Integer lossId);

    public Integer insert(CustomerReprieve customerReprieve);

     public Integer update(CustomerReprieve customerReprieve);

    public Integer delete(Integer id);

    public CustomerReprieve queryRepriById(Integer id);
}
