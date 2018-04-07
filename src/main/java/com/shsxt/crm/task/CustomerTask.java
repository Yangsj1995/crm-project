package com.shsxt.crm.task;

import com.shsxt.crm.service.CustomerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomerTask {

    @Resource
    public CustomerService customerService;

//    //每天的12点12分0秒触发这个任务
//    @Scheduled(cron = "40 55 14 * * ?")
//    public void lossTask(){
//        customerService.updateCustomerLossState();
//        System.out.println("你好，我被触发了--");
//    }

}
