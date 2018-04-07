package com.shsxt.crm.dao;

import com.shsxt.crm.dto.CustomerDto;
import com.shsxt.crm.query.CustomerGCQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.vo.Customer;
import com.shsxt.crm.vo.CustomerLoss;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerDao {

    @Select("select id,name from t_customer where is_valid = 1 and state = 0")
    public List<Customer> queryAllCustomers();

    @Select("select id,khno,name from t_customer where is_valid = 1 and state = 0 and id = #{id}")
    public Customer queryCustomerById(String id);

    public Integer insert(Customer customer);

    public List<Customer> queryCustomersByParams(CustomerQuery customerQuery);

    public Integer delete(Integer[] ids);

    public Integer update(Customer customer);

    public List<CustomerLoss> queryCustomerLoss();

    public List<CustomerDto> queryCustomersContribution(CustomerGCQuery customerGCQuery);


    public List<CustomerDto> queryCustomerGC();
}
