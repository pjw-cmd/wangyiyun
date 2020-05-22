package com.zjgsu.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String test() {
//        JSONObject jsonObject = JSONObject.parseObject(parms);
//        System.out.println(jsonObject);

        return "123";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String test2(String id) {
//        JSONObject jsonObject = JSONObject.parseObject(parms);
        System.out.println(id);

        return id;
    }
}
