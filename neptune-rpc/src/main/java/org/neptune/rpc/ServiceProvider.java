package org.neptune.rpc;

import java.lang.reflect.Method;

/**
 * @desc 服务抽象后, 可以执行调用的的方法
 * 秉承着RPC: 方法即服务 的特性
 *
 * @author tony
 * @createDate 2024/4/22 8:35 下午
 */
public class ServiceProvider {
    Object instance;
    Method method;
    Object[] params;

    public Object invoke(Object... args){
        return null;
    }
}
