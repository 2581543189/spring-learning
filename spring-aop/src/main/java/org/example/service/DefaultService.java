package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaohui <zhaohui@kuaishou.com>
 * Created on 2020-07-27
 */
@Service
public class DefaultService {

    @Autowired
    DefaultService defaultService;

    public void test() {
        defaultService.a();
        defaultService.b();
        defaultService.c();
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
