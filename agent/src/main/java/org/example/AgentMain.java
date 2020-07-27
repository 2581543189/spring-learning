package org.example;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaohui <zhaohui@kuaishou.com>
 * Created on 2020-07-27
 */
public class AgentMain {


    public static final String TARGET_CLASS = "org.example.service.AgentService";

    public static final Set<String> TARGET_METHOD_SET = new HashSet<String>();

    static {
        TARGET_METHOD_SET.add("org.example.service.agentservice.a()");
        TARGET_METHOD_SET.add("org.example.service.agentservice.b()");
        TARGET_METHOD_SET.add("org.example.service.agentservice.c()");
    }


    /**
     * agent方法
     * @param args
     * @param instr
     * @throws UnmodifiableClassException
     */
    public static void agentmain(String args, Instrumentation instr) throws UnmodifiableClassException {

        System.out.println("agent开始进行动态织入");
        try {
            instr.addTransformer(new MyTransformer(), true);
            Class[] classes = instr.getAllLoadedClasses();
            for (Class clazz : classes) {
                // 对指定的类进行transform
                if (matchClazz(clazz)) {
                    System.out.println("Class matched " + clazz.getName());
                    instr.retransformClasses(clazz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("织入完毕");
    }


    private static boolean matchClazz(Class clazz) {
        return clazz.getName().equalsIgnoreCase(TARGET_CLASS);
    }

}
