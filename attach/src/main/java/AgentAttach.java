import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sun.tools.attach.VirtualMachine;

/**
 * 使用 java  -Djava.ext.dirs=./lib -jar spring-aop-1.0-SNAPSHOT.jar 命令启动服务
 *
 * 使用 lsof -t -s TCP:LISTEN -i TCP:8002 获取pid
 *
 * 调用 http://localhost:8002/agent 进行验证
 */
public class AgentAttach {

    private static String agentPath = "/Users/zhaohui/workspace/Projects/IDEA/spring-learning/agent/target/agent-1.0-SNAPSHOT.jar";

    public static void main(String[] args) {

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class vmClass = null;
        try {
            vmClass = classLoader.loadClass("com.sun.tools.attach.VirtualMachine");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object vmObject = null;
        try {
            //这里填写pid
            vmObject = vmClass.getMethod("attach", String.class).invoke(null, new String[]{"44297"});

            VirtualMachine virtualMachine = (VirtualMachine) vmObject;
            System.out.println(virtualMachine.id());

            Method loadAgentMethod = vmClass.getMethod("loadAgent", String.class, String.class);

            loadAgentMethod.invoke(vmObject, agentPath, "");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace(System.out);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            if (null != vmObject) {
                try {
                    vmClass.getMethod("detach", (Class<?>[]) null).invoke(vmObject,
                            (Object[]) null);
                    System.out.println("Detach Success");
                } catch (Exception e) {

                }
            }
        }

//        try {
//            while (true) {
//                TimeUnit.SECONDS.sleep(10);
//                System.out.println("");
//            }
//        } catch (InterruptedException e) {
//            if (null != vmObject) {
//                try {
//                    vmClass.getMethod("detach", (Class<?>[]) null).invoke(vmObject,
//                            (Object[]) null);
//                    System.out.println("Detach Success");
//                } catch (Exception exx) {
//
//                }
//            }
//            e.printStackTrace();
//        }

    }
}
