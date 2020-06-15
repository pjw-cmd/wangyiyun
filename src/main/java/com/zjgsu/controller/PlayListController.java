package com.zjgsu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjgsu.entity.MusicItemEntity;
import com.zjgsu.entity.PlayListItemEntity;
import com.zjgsu.service.MusicItemService;
import com.zjgsu.service.PlayListService;
import com.zjgsu.utils.MyStringUtils;
import com.zjgsu.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.util.IOUtils.UTF8;
import static com.alibaba.fastjson.util.IOUtils.getStringProperty;

@Controller
@RequestMapping("playlist")
public class PlayListController {
    @Resource
    PlayListService playListService;

    @Resource
    MusicItemService musicItemService;
    @Autowired
    CloseableHttpClient httpClient;

    @Autowired
    RequestConfig requestConfig;


    /**
     * 更新用户的播放列表
     *
     * @param jsonString
     * @return
     */
    @RequestMapping(value = "/updateUserPlayList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUserPlayList(@RequestBody String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Response transMessage = new Response();
        String userId = jsonObject.getString("user_id");
        JSONArray playList = jsonObject.getJSONArray("play_list");
        playListService.updateUserPlayList(userId, playList);
        transMessage.setCode(200);
        transMessage.setMessage("更新成功");
        return JSON.toJSONString(transMessage);
    }

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

        if (StringUtils.isNotBlank(playListId)) {
            transMessage.setCode(200);
            transMessage.setMessage("添加成功");
        } else {
            transMessage.setCode(300);
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
        transMessage.setCode(200);
        transMessage.setMessage("删除成功");
        return JSON.toJSONString(transMessage);
    }

    /*
        获取用户的播放列表里面的歌曲
     */
    @RequestMapping(value = "/getListById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getListById(String user_id) {
        Response transMessage = new Response();
        List<JSONObject> userPlayList = playListService.getUserPlayList(user_id);
        if (userPlayList != null) {
//            List<String> ids = new ArrayList<>();
//            for (int i = 0; i < userPlayList.size(); i++) {
//                PlayListItemEntity playListItemEntity = userPlayList.get(i);
//                ids.add(playListItemEntity.getMusicId());
//            }
//            String string_ids = MyStringUtils.listToString(ids, ',');
//            String music_string = musicItemService.listMusicByIds(string_ids);
            transMessage.setData(userPlayList);
        }
        transMessage.setCode(200);
        transMessage.setMessage("查询成功");
        return JSON.toJSONString(transMessage);
    }


    /**
     * 根据用户的
     *
     * @param user_id
     * @return 返回歌单
     */
    @RequestMapping(value = "/getUserSimilarityPlayList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUserSimilarityPlayList(String user_id) {
        Response transMessage = new Response();
        List<JSONObject> userPlayList = playListService.getUserSimilarityPlayList(user_id);
        if (userPlayList != null) {

            transMessage.setData(userPlayList);
        }
        transMessage.setCode(200);
        transMessage.setMessage("查询成功");
        return JSON.toJSONString(transMessage);
    }

    /*
      用来测试的
    */
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMusicItem(String ids) {
        Response transMessage = new Response();
        if (ids == null) {

            ids = "1437405183,518066367";
        }
        musicItemService.saveMusicItemToDataSet(ids);
//        System.out.println(s);
        return JSON.toJSONString(transMessage);
    }

}
