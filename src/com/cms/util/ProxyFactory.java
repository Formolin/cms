package com.cms.util;

import com.cms.service.INewsfileService;
import com.cms.service.NewsfileServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static INewsfileService getProxyNewfileService() {
        final INewsfileService newsfileService = new NewsfileServiceImpl();

        INewsfileService proxy = (INewsfileService) Proxy.newProxyInstance(newsfileService.getClass().getClassLoader(), newsfileService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //切事务
                System.out.println("method.getName()===" + method.getName());//getAddNewsLastId
                Object invoke = null;
                if ("getAddNewsLastId".equals(method.getName())) {
                   ManagerThreadLocal.startTransaction();
                   invoke = method.invoke(newsfileService,args);
                    return invoke;
                }
                if ("addNewsfile".equals(method.getName())){
                    try {
                        invoke = method.invoke(newsfileService,args);
                        ManagerThreadLocal.commit();
                    } catch (Exception e) {
                        System.out.println("在两个方法前后添加事务");
                        ManagerThreadLocal.rollback();
                    }
                    return invoke;
                }
                return invoke;
            }
        });

        return proxy;
    }
}
