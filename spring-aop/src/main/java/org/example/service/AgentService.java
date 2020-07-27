package org.example.service;

import org.springframework.stereotype.Service;

/**
 * @author zhaohui <zhaohui@kuaishou.com>
 * Created on 2020-07-28
 */
@Service
public class AgentService {


    public void test() {
        a();
        b();
        c();
    }

    public void a() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
