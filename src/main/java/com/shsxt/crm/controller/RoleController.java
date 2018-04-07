package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.service.RoleService;
import com.shsxt.crm.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource
    public RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "role";
    }

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Role> queryAllRoles(){
        return roleService.queryAllRoles();
    }

    @RequestMapping("queryRolesByParams")
    @ResponseBody
    public Map<String,Object> queryRolesByParams(RoleQuery roleQuery){
        return roleService.queryRolesByParama(roleQuery);
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(Role role){
        roleService.insert(role);
        return success("角色记录添加成功!");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(Role role){
        roleService.update(role);
        return success("角色记录更新成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer id){
        roleService.delete(id);
        return success("角色记录删除成功!");
    }

    @RequestMapping("addPermission")
    @ResponseBody
    public MessageModel addPermission(Integer rid,Integer[] moduleIds){
        roleService.addPermission(rid,moduleIds);
        return success("角色授权成功");
    }

}
