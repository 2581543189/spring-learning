package org.example;

import org.example.service.AgentService;
import org.example.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaohui <zhaohui@kuaishou.com>
 * Created on 2020-07-27
 */
@RestController
@Component
public class DefaultController {

    @Autowired
    DefaultService defaultService;

    @Autowired
    AgentService agentService;


    //路径映射，对应浏览器访问的地址，访问该路径则执行下面函数
    @RequestMapping("/test")
    public String hello() {
        try {
            defaultService.test();
            return "hello!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //路径映射，对应浏览器访问的地址，访问该路径则执行下面函数
    @RequestMapping("/agent")
    public String agent() {
        try {
            agentService.test();
            return "hello!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
