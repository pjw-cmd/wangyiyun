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
            transMessage.setcode(200);
            transMessage.setMessage("登录成功");
            transMessage.setContent(userId);
        } else {
            transMessage.setcode(300);
            transMessage.setMessage("登录失败");
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
        transMessage.setcode(300);
        transMessage.setMessage("注册失败");
        String userName = jsonObject.getString("user_name");
        String userPassword = jsonObject.getString("user_password");
        String userId = userService.register(userName, userPassword);
        if (StringUtils.isNotBlank(userId)) {
            // 注册完后自动为其创建播放列表
            String playListId = playListService.createPlayList(userId);
            if (StringUtils.isNotBlank(playListId)) {
                transMessage.setcode(200);
                transMessage.setMessage("注册成功");
            }
        }
        return JSON.toJSONString(transMessage);
    }
}
