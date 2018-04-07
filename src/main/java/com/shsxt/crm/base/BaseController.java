package com.shsxt.crm.base;

import com.shsxt.crm.model.MessageModel;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @ModelAttribute
    public void preMethod(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
    }

    public MessageModel success(String msg){
        MessageModel messageModel = new MessageModel();
        messageModel.setMsg(msg);
        return messageModel;
    }
}
