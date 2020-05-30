package com.zjgsu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjgsu.service.PlayListService;
import com.zjgsu.service.UserService;
import com.zjgsu.utils.Response;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    PlayListService playListService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Response transMessage = new Response();
        boolean login = userService.login(jsonObject.getString("user_name"), jsonObject.getString("user_password"));

        if (login) {
            transMessage.setStatus(200);
            transMessage.setMessage("登录成功");
        } else {
            transMessage.setStatus(300);
            transMessage.setMessage("登录失败");
        }
        return JSON.toJSONString(transMessage);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String register(@RequestBody String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Response transMessage = new Response();
        String userName = jsonObject.getString("user_name");
        String userPassword = jsonObject.getString("user_password");
        String userId = userService.register(userName, userPassword);
        if (StringUtils.isNotBlank(userId)) {
            transMessage.setStatus(200);
            transMessage.setMessage("注册成功");
        } else {
            transMessage.setStatus(300);
            transMessage.setMessage("注册失败");
        }
        return JSON.toJSONString(transMessage);
    }
}
