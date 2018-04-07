package com.shsxt.crm.base.exceptions;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.base.CrmConstant;
import com.shsxt.crm.model.MessageModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class GlobalExcpetionResolver implements HandlerExceptionResolver  {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView modelAndView = createDefaultModelAndView(request);

        /**
         * view试图
         * json异常
         */
        if(handler instanceof HandlerMethod){

            if(ex instanceof ParamsException ){

                ParamsException exception = (ParamsException) ex;
                /**
                 * 处理用户未登录
                 */
                if(exception.getCode().equals(CrmConstant.LOGIN_FAILED_CODE)){

                    modelAndView.addObject("msg","用户未登录");
                    modelAndView.addObject("code",CrmConstant.LOGIN_FAILED_CODE);
                    return modelAndView;
                }
            }

            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);

            if(responseBody!=null){
                MessageModel messageModel = new MessageModel();
                messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
                messageModel.setCode(CrmConstant.OPS_FAILED_CODE);

                if(ex instanceof ParamsException){
                    ParamsException exception = (ParamsException)ex;
                    messageModel.setMsg(exception.getMsg());
                    messageModel.setCode(exception.getCode());
                }
                response.setContentType("application/json;charset=uft-8");
                response.setCharacterEncoding("utf-8");
                PrintWriter printWriter = null;
                try {
                    printWriter = response.getWriter();

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(printWriter!=null){
                        printWriter.write(JSON.toJSONString(messageModel));
                        printWriter.flush();
                        printWriter.close();
                    }
                }
                return  null;
            }else{
                if(ex instanceof ParamsException ){
                    ParamsException exception = (ParamsException)ex;
                    modelAndView.addObject("msg",exception.getMsg());
                    modelAndView.addObject("code",exception.getCode());
                }
            }


        }
        return modelAndView;
    }

    private ModelAndView createDefaultModelAndView(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code",CrmConstant.OPS_FAILED_CODE);

        modelAndView.addObject("msg", CrmConstant.OPS_FAILED_MSG);
        modelAndView.addObject("uri",request.getRequestURI());
        modelAndView.addObject("ctx",request.getContextPath());
        return  modelAndView;
    }


}
