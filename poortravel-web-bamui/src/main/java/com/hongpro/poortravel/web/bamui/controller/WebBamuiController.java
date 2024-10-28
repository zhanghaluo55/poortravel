package com.hongpro.poortravel.web.bamui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebBamuiController {

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "index_v3.html", method = RequestMethod.GET)
    public String indexv3() {
        return "index_v3";
    }

    @RequestMapping(value = {"userlist"}, method = RequestMethod.GET)
    public String userlist() {
        return "userlist";
    }

    @RequestMapping(value = {"postlist"}, method = RequestMethod.GET)
    public String postlist() {
        return "postlist";
    }

    @RequestMapping(value = {"taglist"}, method = RequestMethod.GET)
    public String taglist() {
        return "taglist";
    }

    @RequestMapping(value = {"fileslist"}, method = RequestMethod.GET)
    public String fileslist() {
        return "fileslist";
    }

    @RequestMapping(value = {"attractionlist"}, method = RequestMethod.GET)
    public String attractionlist() {
        return "attractionlist";
    }

    @RequestMapping(value = {"attrfileslist"}, method = RequestMethod.GET)
    public String attrfileslist() {
        return "attrfileslist";
    }

    @RequestMapping(value = {"attrtaglist"}, method = RequestMethod.GET)
    public String attrtaglist() {
        return "attrtaglist";
    }

}

