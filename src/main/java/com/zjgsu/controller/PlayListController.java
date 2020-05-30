package com.zjgsu.controller;

import com.zjgsu.service.PlayListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("playlist")
public class PlayListController {
    @Resource
    PlayListService playListService;
}
