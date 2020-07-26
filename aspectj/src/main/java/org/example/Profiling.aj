package org.example;
import java.util.Date;
aspect Profiling {


  pointcut ptc() : call(public * a(..)) || call(public * b(..)) || call(public * c(..));


  void around() :ptc() {
    String name =thisJoinPointStaticPart.getSignature().getName();
    Date start = new Date();
    proceed();
    Date end = new Date();
    System.out.println("方法" + name + "耗时[" + (end.getTime() - start.getTime()) + "]ms");
  }
}