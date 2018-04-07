package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.base.CrmConstant;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.dao.UserRoleDao;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.util.AssertUtil;
import com.shsxt.crm.util.Base64Util;
import com.shsxt.crm.util.Md5Util;
import com.shsxt.crm.vo.SaleChance;
import com.shsxt.crm.vo.User;
import com.shsxt.crm.vo.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private PermissionDao permissionDao;
    @Resource
    private HttpSession session;


    public List<User> queryAllCustomerManager() {
        return userDao.queryAllCustomerManager();
    }

    /**
     * 用户合法性的检测
     *
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel userLogin(String userName, String userPwd) {

        User user = userDao.queryUserByName(userName);

        AssertUtil.isTrue(user == null, "用户不存在");
        AssertUtil.isTrue("0".equals(user.getIsValid()), "用户已注销");
        String pwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!pwd.equals(user.getUserPwd()), "密码错误");

        /**
         * 获取用户权限  根据用户拥有的角色
         */
        List<String> permissions=permissionDao.queryPermissionsByUserId(user.getId() + "");
        if(null!=permissions&&permissions.size()>0){

            session.setAttribute(CrmConstant.USER_PERMISSIONS, permissions);
        }
        return createUserModel(user);
    }

    /**
     * 修改密码之前进行验证。
     * 1.先看userId存在与否
     * 2.新密码不能为空
     * 3.两次密码必须输入一致。
     * 4.通过userId能正确查找到记录。
     * 5.原始密码必须输入正确
     * 6.如果update方法返回的结果小于1，说明更新失败
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */

    public void updatePwd(String userId, String oldPassword, String newPassword, String confirmPassword) {

        AssertUtil.isTrue(userId == null, "非法用户");

        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "新密码不能为空");

        AssertUtil.isTrue(!newPassword.equals(confirmPassword), "两次密码输入不一致");

        User user = userDao.queryUserById(userId);

        AssertUtil.isTrue(user == null, "用户被冻结，不允许修改密码");

        oldPassword = Md5Util.encode(oldPassword);
        AssertUtil.isTrue(!oldPassword.equals(user.getUserPwd()), "原始密码错误");

        AssertUtil.isTrue(userDao.updatePwd(userId, Md5Util.encode(newPassword)) < 1, "操作失败");

    }

    /**
     * 生成userModel,但是userId需要加密后，再返回给前端。
     *
     * @param user
     * @return
     */
    public UserModel createUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        String id = Base64Util.encode(user.getId());
        userModel.setUserId(id);
        return userModel;
    }


    /**
     * 数据验证
     * 用户名不能为空，且不能已存在
     * 真实姓名不能为空
     * 电话不能为空
     * 额外数据的补录
     * 密码 ： 123456
     * isvalid 1
     * createDate
     * updateDate
     *
     * @param user
     */

    public void insert(User user) {

        checkParams(user.getUserName(), user.getTrueName(), user.getPhone());
        //判断userName的唯一性
        AssertUtil.isTrue(userDao.queryUserByName(user.getUserName()) != null, "用户名已经存在");
        user.setUserPwd(Md5Util.encode("123456"));
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());

        AssertUtil.isTrue(userDao.insert(user) < 1, "用户数据添加失败");

        String userId = user.getId();
        List<Integer> roleIds = user.getRoleIds();
        if (roleIds != null && roleIds.size() > 0) {
            //级联操作,添加用户角色。
            relateRoles(userId, roleIds);
        }

        int a = 1/0;

    }

    /**
     * 数据验证
     *    用户名不能为空，且不能已存在
     *   真实姓名不能为空
     *   电话不能为空
     * 额外数据的补录
     * updateDate
     *
     * @param user
     */

    public void update(User user) {

        checkParams(user.getUserName(), user.getTrueName(), user.getPhone());

        user.setUpdateDate(new Date());

        User tmpUser = userDao.queryUserByName(user.getUserName());

        AssertUtil.isTrue(tmpUser!=null&&!user.getId().equals(tmpUser.getId()),"用户名称已存在");

        AssertUtil.isTrue(userDao.update(user) < 1, "用户数据添加失败");

        String userId = user.getId();

        userRoleDao.deleteUserRolesByUserId(Integer.parseInt(userId));

        List<Integer> roleIds = user.getRoleIds();
        if (roleIds != null && roleIds.size() > 0) {
            //级联操作,添加用户角色。
            relateRoles(userId, roleIds);
        }
    }

    public void delete(Integer id){
        AssertUtil.isTrue(userDao.delete(id)<1,"用户数据删除失败");

        int count = userRoleDao.queryUserRoleCountsByUserId(id);

        if(count>0){
            AssertUtil.isTrue(userRoleDao.deleteUserRolesByUserId(id)<count,"用户角色级联删除失败");
        }

    }

    public void relateRoles(String userId, List<Integer> roleIds) {


        List<UserRole> list = new ArrayList<>();

        for (Integer roleId : roleIds) {

            UserRole userRole = new UserRole();

            userRole.setIsValid(1);
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            userRole.setUserId(Integer.parseInt(userId));
            userRole.setRoleId(roleId);
            list.add(userRole);
        }

        AssertUtil.isTrue(userRoleDao.insertBatch(list) < list.size(), "用户角色添加失败");

    }

    public void checkParams(String userName, String trueName, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(trueName), "真实姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号不能为空");

    }

    public Map<String, Object> queryUsersByParams(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPage(), userQuery.getRows());
        List<UserDto> list = userDao.queryUsersByParams(userQuery);

        if (list != null && list.size() > 0) {
            for (UserDto userDto : list) {
                String roleIdstr = userDto.getRoleIdsStr();
                if (roleIdstr != null) {
                   String[] roleIdArray =  roleIdstr.split(",");
                    for (int i = 0; i <roleIdArray.length ; i++) {
                        List<Integer> roleIds =  userDto.getRoleIds();
                        roleIds.add(Integer.parseInt(roleIdArray[i]));
                    }
                }
            }
        }

        PageInfo<UserDto> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    public User queryUserById(String id) {

        return userDao.queryUserById(id);
    }


}
