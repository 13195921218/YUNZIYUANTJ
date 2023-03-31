package com.example.yunziyuan.controller;

import com.example.yunziyuan.service.ZiYuanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
@Controller
public class ZiYuanController {

    @Autowired
    ZiYuanService ziyuanService;

    @RequestMapping(value = "/getEcsList")
    @ResponseBody
    public String EcsListApi() throws Exception {
        ziyuanService.httpRequestEcs();

        return "ECS SAVE SUCCESS!";
    }
    @RequestMapping(value = "/getDcsList")
    @ResponseBody
    public String DcsListApi() throws Exception {
        ziyuanService.httpRequestDcs();

        return "DCS SAVE SUCCESS!";
    }
}

