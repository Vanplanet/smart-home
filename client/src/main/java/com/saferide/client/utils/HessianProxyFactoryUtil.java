package com.saferide.client.utils;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

public class HessianProxyFactoryUtil {

    /**
     *  获取调用端对象
     * @param clazz 实体对象泛型
     * @param url 客户端url地址
     * @param <T>
     * @return 业务对象
     */
    public static <T> T getHessianClientBean(Class<T> clazz,String url)
    {
        // 客户端连接工厂,这里只是做了最简单的实例化，还可以设置超时时间，密码等安全参数
        HessianProxyFactory factory = new HessianProxyFactory();
        T t = null;
        try {
            t = (T)factory.create(clazz,url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return t;
    }
}
