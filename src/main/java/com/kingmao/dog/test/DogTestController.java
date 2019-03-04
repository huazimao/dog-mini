package com.kingmao.dog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Paceage:com.kingmao.dog.test
 * Description:
 * Date:2019/2/20
 * Author: KingMao
 **/
@Controller
public class DogTestController {
    @ResponseBody
    @RequestMapping("/dog/test1.do")
    public String getDog(){
        System.out.println("进入到dog-mini项目！");
        return "进入到dog-mini项目！";
    }
}
