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

    @RequestMapping(value = "/getJoke",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String jokeApi() throws Exception {
        //return "getjoke";
        ziyuanService.httpRequest();
        StringBuffer sb = new StringBuffer();
        sb.append("                  _ooOoo_\n");
        sb.append("                 o8888888o\n");
        sb.append("                 88\" . \"88\n");
        sb.append("                 (| -_- |)\n");
        sb.append("                 O\\ = /O\n");
        sb.append("             ____/`---'\\____\n");
        sb.append("           .' \\\\| |//      `.\n");
        sb.append("          / \\\\||| : |||// \\ \n");
        sb.append("         / _||||| -:- |||||- \\ \n");
        sb.append("         | | \\\\\\ - /// | |\n");
        sb.append("         | \\_| ''\\---/'' | |\n");
        sb.append("         \\ .-\\__ `-` ___/-. /\n");
        sb.append("        ___`. .' /--.--\\ `. . __\n");
        sb.append("      .\"\" '< `.___\\__/___.' >'\"\".\n");
        sb.append("    | | : `- \\`.;`\\ _ /`;.`/ - ` : | |\n");
        sb.append("    \\ \\ `-. \\_ __\\ /__ _/ .-` / /\n");
        sb.append("======`-.____`-.___\\_____/___.-`____.-'======\n");
        sb.append("                    `=---='\n");
        sb.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
        sb.append("    \t\t佛祖保佑 运行成功\n");

        return sb.toString();
    }
}

