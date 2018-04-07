package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.CrmConstant;
import com.shsxt.crm.base.exceptions.ParamsException;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.util.UserLoginUtil;
import com.shsxt.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @RequestMapping("userLogin")
    @ResponseBody
    public MessageModel userLogin(String userName, String userPwd) {

        MessageModel messageModel = new MessageModel();
        UserModel userModel = userService.userLogin(userName, userPwd);
        messageModel.setResult(userModel);
        return messageModel;
    }
    @RequestMapping("index")
    public String index(){
        return "user";
    }

    @RequestMapping("updatePwd")
    @ResponseBody
    public MessageModel updatePwd(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        MessageModel messageModel = new MessageModel();
        //先获取userId,通过userId来做下一步的验证工作。
        String userId = UserLoginUtil.realseUserId(request);

        userService.updatePwd(userId, oldPassword, newPassword, confirmPassword);

        return messageModel;
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(User user) {
        userService.insert(user);
        return success("用户记录添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(User user) {
        userService.update(user);
        return success("用户记录修改成功");
    }
    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer id){

        userService.delete(id);
        return success("用户记录删除成功");
    }



    @RequestMapping("queryUsersByParams")
    @ResponseBody
    public Map<String,Object> queryUsersByParams(UserQuery userQuery){
        return userService.queryUsersByParams(userQuery);
    }

    @RequestMapping("queryAllCustomerManager")
    @ResponseBody
    public List<User> queryAllCustomerManager(){
        return  userService.queryAllCustomerManager();
    }


}
