package com.zjgsu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjgsu.service.PlayListService;
import com.zjgsu.utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("playlist")
public class PlayListController {
    @Resource
    PlayListService playListService;

    /*
        添加播放歌曲
     */
    @RequestMapping(value = "/addMusicItem", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addMusicItem(@RequestBody String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Response transMessage = new Response();
        String playlistId = jsonObject.getString("playlist_id");
        String musicId = jsonObject.getString("music_id");
        String playListId = playListService.addMusicToPlayList(playlistId, musicId);

        if (playListId != null) {
            transMessage.setStatus(200);
            transMessage.setMessage("添加成功");
        } else {
            transMessage.setStatus(300);
            transMessage.setMessage("添加失败");
        }
        return JSON.toJSONString(transMessage);
    }

    /*
        删除歌曲
     */
    @RequestMapping(value = "/deleteMusicItem", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteMusicItem(@RequestBody String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Response transMessage = new Response();
        String playlistId = jsonObject.getString("playlist_id");
        String musicId = jsonObject.getString("music_id");

        playListService.deleteMusicItem(playlistId, musicId);
        return "";
//        boolean success = userService.register(userName, userPassword);
//        if (success) {
//            transMessage.setStatus(200);
//            transMessage.setMessage("注册成功");
//        } else {
//            transMessage.setStatus(300);
//            transMessage.setMessage("注册失败");
//        }
//        return JSON.toJSONString(transMessage);
    }
}
