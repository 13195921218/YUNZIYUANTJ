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

    @RequestMapping(value = "/getEcsList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String EcsListApi() throws Exception {
        ziyuanService.httpRequestEcs();

        return "                  _ooOoo_\n" +
                "                 o8888888o\n" +
                "                 88\" . \"88\n" +
                "                 (| -_- |)\n" +
                "                 O\\ = /O\n" +
                "             ____/`---'\\____\n" +
                "           .' \\\\| |//      `.\n" +
                "          / \\\\||| : |||// \\ \n" +
                "         / _||||| -:- |||||- \\ \n" +
                "         | | \\\\\\ - /// | |\n" +
                "         | \\_| ''\\---/'' | |\n" +
                "         \\ .-\\__ `-` ___/-. /\n" +
                "        ___`. .' /--.--\\ `. . __\n" +
                "      .\"\" '< `.___\\__/___.' >'\"\".\n" +
                "    | | : `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "    \\ \\ `-. \\_ __\\ /__ _/ .-` / /\n" +
                "======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "                    `=---='\n" +
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                "    \t\t佛祖保佑 运行成功\n";
    }
    @RequestMapping(value = "/getDcsList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String DcsListApi() throws Exception {
        ziyuanService.httpRequestDcs();

        return "                  _ooOoo_\n" +
                "                 o8888888o\n" +
                "                 88\" . \"88\n" +
                "                 (| -_- |)\n" +
                "                 O\\ = /O\n" +
                "             ____/`---'\\____\n" +
                "           .' \\\\| |//      `.\n" +
                "          / \\\\||| : |||// \\ \n" +
                "         / _||||| -:- |||||- \\ \n" +
                "         | | \\\\\\ - /// | |\n" +
                "         | \\_| ''\\---/'' | |\n" +
                "         \\ .-\\__ `-` ___/-. /\n" +
                "        ___`. .' /--.--\\ `. . __\n" +
                "      .\"\" '< `.___\\__/___.' >'\"\".\n" +
                "    | | : `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "    \\ \\ `-. \\_ __\\ /__ _/ .-` / /\n" +
                "======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "                    `=---='\n" +
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                "    \t\t佛祖保佑 运行成功\n";
    }
}

