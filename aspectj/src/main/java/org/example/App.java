package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Hello world!
 * 调试命令：java -cp "aspectj-1.0-SNAPSHOT.jar:lib/*"   org.example.App
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
            server.createContext("/test", new TestHandler());
            server.start();
            System.out.println("服务已经启动");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
