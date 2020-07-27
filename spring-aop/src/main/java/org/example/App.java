package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 * http://localhost:8002/test
 */
@SpringBootApplication(scanBasePackages = "org.example")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class App {
    public static void main(String[] args) {
        try {
            SpringApplication springApplication = new SpringApplication(App.class);
            springApplication.run(args);
            System.out.println("服务启动成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/**
 *   cglib 的测试用例
 */
//    public static void main(String[] args) {
//        try {
//
//            Enhancer en = new Enhancer();
//            en.setSuperclass(Chinese.class);
//            en.setCallback(new AroundAdvice());// 生成代理类的实例
//            Chinese proxy = (Chinese) en.create();
//            proxy.sayHello("饼");
//            System.out.println();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
