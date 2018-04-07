package com.shsxt.crm.dao;

import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.vo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    public User queryUserByName(String userName);

    @Select("SELECT" +
            "\tu.true_name as trueName,u.id\n" +
            "FROM\n" +
            "\tt_user u\n" +
            "LEFT JOIN t_user_role ur ON u.id = ur.user_id\n" +
            "LEFT JOIN t_role r ON ur.role_id = r.id\n" +
            "WHERE\n" +
            "\trole_name = '客户经理'\n" +
            "AND u.is_valid = 1 and r.is_valid =1 and ur.is_valid =1")
    public List<User> queryAllCustomerManager();

    public User queryUserById(String id);

    public Integer updatePwd(@Param("id") String id,@Param("userPwd") String userPwd);

    public Integer insert(User user);

    public List<UserDto> queryUsersByParams(UserQuery userQuery);

    public Integer update(User user);

    @Delete("delete from t_user where id = #{id}")
    public Integer delete(Integer id);
}
