package com.zjgsu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjgsu.common.TransMessage;
import com.zjgsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/id", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(String user_id, String user_password) {
        TransMessage transMessage = new TransMessage();
        System.out.println("test");
        boolean login = userService.login(user_id, user_password);
        if (login) {
            transMessage.setCode(200);
            transMessage.setMsg("登录成功");
        } else {
            transMessage.setCode(300);
            transMessage.setMsg("登录失败");
        }
        System.out.println("asdas"+transMessage.getMsg());
        System.out.println("test2++" + JSON.toJSONString(transMessage));
        return JSON.toJSONString(transMessage);
    }
}
