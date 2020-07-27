package org.example;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;

/**
 * Created by liuruisen on 2018/11/18.
 */
public class MyTransformer implements ClassFileTransformer {

    private ClassPool classPool = ClassPool.getDefault();

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        CtClass ctClass = null;
        byte[] returnByte = null;
        String classNameJava = jvmnameToJavaname(className);
        if (!matchClazz(classNameJava)) {
            return null;
        }
        try {
            ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

            if (!ctClass.isInterface()) {
                for (CtBehavior method : ctClass.getDeclaredBehaviors()) {
                    if (matchMethod(method.getLongName())) {
                        System.out.println("开始修改class: " + ctClass.getName() + " 的 " + method.getLongName() + " 方法");
                        method.insertBefore(insertBeforStr(method));
                        method.insertAfter(insertAfterStr(method));
                        System.out.println("CtClass.toString() -1 \n" + ctClass.toString());
                    }

                }
                System.out.println("CtClass.toString() -2 \n " + ctClass.toBytecode().toString());
                returnByte = ctClass.toBytecode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } finally {
            if (ctClass != null) {
                ctClass.detach();
            }
        }

        System.out.println("Transforming end");
        return returnByte;
    }


    private String insertBeforStr(CtBehavior method) throws CannotCompileException {
        System.out.println("方法执行前追加下列语句:");
        method.addLocalVariable("startTime", CtClass.longType);
        StringBuilder stringBuilder = new StringBuilder();
        String tmp = "startTime = System.currentTimeMillis();";
        System.out.println(tmp);
        stringBuilder.append(tmp);
        return stringBuilder.toString();
    }

    private String insertAfterStr(CtBehavior method) {
        System.out.println("方法执行后追加下列语句:");
        StringBuilder stringBuilder = new StringBuilder();
        String tmp = "System.out.println(" +
                "\"" + method.getLongName() + "耗时: \" + " +
                "(System.currentTimeMillis() - startTime)" +
                "+\"ms\");";
        System.out.println(tmp);
        stringBuilder.append(tmp);
        return stringBuilder.toString();
    }

    /**
     * 判断是否目标类
     * @param clazzName
     * @return
     */
    private static boolean matchClazz(String clazzName) {
        return clazzName.equalsIgnoreCase(AgentMain.TARGET_CLASS);
    }

    /**
     * 判断是否目标方法
     * @param methodName
     * @return
     */
    private boolean matchMethod(String methodName) {
        return AgentMain.TARGET_METHOD_SET.contains(methodName.toLowerCase());
    }

    /**
     * 将 斜杠 转成 点
     * @param jvmName
     * @return
     */
    public static String jvmnameToJavaname(String jvmName) {
        if (jvmName == null) {
            throw new NullPointerException("类名不能为空");
        }
        return jvmName.replace('/', '.');
    }


}
