package com.shsxt.crm.dao;

import com.shsxt.crm.dto.ServeTypeDto;
import com.shsxt.crm.vo.CustomerServe;

import java.util.List;

public interface CustomerServeDao {

    public Integer insert(CustomerServe customerServe);

    public List<CustomerServe> queryCustomerServesByParams(String state);

    public Integer update(CustomerServe customerServe);

    public List<ServeTypeDto> queryCustomerServeType();
}
