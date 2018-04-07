package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.ModuleQuery;
import com.shsxt.crm.service.ModuleService;
import com.shsxt.crm.vo.Module;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.pkcs11.Secmod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Resource
    public ModuleService moduleService;



    @RequestMapping("queryAllsModuleDtos")
    @ResponseBody
    public List<ModuleDto> queryAllsModuleDtos(Integer rid) {

        return moduleService.queryAllsModuleDtos(rid);
    }


    @RequestMapping("index")
    public String index(){
        return "module";
    }


    @RequestMapping("queryModulesByParams")
    @ResponseBody
    public Map<String, Object> queryModulesByParams(ModuleQuery moduleQuery){
        return moduleService.queryModulesByParams(moduleQuery);
    }


    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(Module module){
        moduleService.insert(module);
        return success("模块记录添加成功!");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(Module module){
        moduleService.update(module);
        return success("模块记录更新成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] ids){
        moduleService.delete(ids[0]);
        return success("模块记录删除成功!");
    }


    @RequestMapping("queryModulesByGrade")
    @ResponseBody
    public List<Module> queryModulesByGrade(Integer grade){
        return moduleService.queryModulesByGrade(grade);

    }
}
