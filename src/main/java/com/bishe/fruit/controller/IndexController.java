package com.bishe.fruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 跳转到首页
     * @param model
     * @return
     */
    @RequestMapping("/index.html")
    public String index(Model model) {

        return "index";
    }

}
