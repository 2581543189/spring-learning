package org.example.cglib;

/**
 * @author zhaohui <zhaohui@kuaishou.com>
 * Created on 2020-07-27
 */
public class Chinese {

    public String sayHello(String name) {
        System.out.println("-- 正在执行 sayHello 方法 --");
        // 返回简单的字符串

        eat("包子");
        return name + " Hello , Spring AOP";
    }

    // 定义一个 eat() 方法
    public void eat(String food) {
        System.out.println("我正在吃 :" + food);
    }
}
