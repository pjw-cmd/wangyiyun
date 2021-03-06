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
        /*
            登录功能
         */
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Response transMessage = new Response();
        String userId = userService.login(jsonObject.getString("user_name"), jsonObject.getString("user_password"));
        if (StringUtils.isNotBlank(userId)) {
            transMessage.setCode(200);
            transMessage.setMessage("登录成功");
            transMessage.setData(userId);
        } else {
            transMessage.setCode(300);
            transMessage.setMessage("登录失败,请检查用户名和密码");
        }
        return JSON.toJSONString(transMessage);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String register(@RequestBody String jsonString) {
        /*
            注册功能
         */
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Response transMessage = new Response();
        String userName = jsonObject.getString("user_name");
        String userPassword = jsonObject.getString("user_password");
        String userPasswordSecond = jsonObject.getString("user_password_second");
        if (userPassword.equals(userPasswordSecond)) {
            String userId = userService.register(userName, userPassword);
            if (StringUtils.isNotBlank(userId)) {
                // 注册完后自动为其创建播放列表
                String playListId = playListService.createPlayList(userId);
                if (StringUtils.isNotBlank(playListId)) {
                    transMessage.setCode(200);
                    transMessage.setMessage("注册成功");
                } else {
                    transMessage.setCode(300);
                    transMessage.setMessage("注册失败，请输入其他的用户名");
                }
            } else {
                transMessage.setCode(300);
                transMessage.setMessage("注册失败，请输入其他的用户名");
            }
        } else {
            transMessage.setCode(300);
            transMessage.setMessage("两次密码输入有误");
        }

        return JSON.toJSONString(transMessage);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String logout() {
        /*
            登录功能
         */
        Response transMessage = new Response();
        transMessage.setCode(200);
        transMessage.setMessage("注销成功");

        return JSON.toJSONString(transMessage);
    }
}
