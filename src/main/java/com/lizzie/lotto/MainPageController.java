package com.lizzie.lotto;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPageController {

    @ResponseBody
    @GetMapping("/main")
    public String mainpage(){
        return "메인페이지";
    }

}
