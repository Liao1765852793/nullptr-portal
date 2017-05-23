package com.nullptr.portal.controller;

import com.nullptr.portal.service.ContentServicePortal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Nullptr on 2017/5/13.
 * 首页访问controller
 */
@Controller
public class IndexController {

    @Autowired
    private ContentServicePortal contentServicePortal;

    @RequestMapping("/index")
    public String showIndex(Model model) {

        //取大广告位内容
        String json = contentServicePortal.getAd1List();
        System.out.println("json = " + json);
        model.addAttribute("ad1", json);
        return "index";
    }


    @RequestMapping(value = "posttest", method = RequestMethod.POST)
    @ResponseBody
    public String postTest(String name, String pass) {
        System.out.println("name = " + name);
        System.out.println("pass = " + pass);
        return "ok";
    }
}
