package org.neptune.rpc.server;

import org.neptune.rpc.ServiceProvider;

import java.util.List;

/**
 * @desc TODO
 *
 * @author tony
 * @createDate 2024/4/22 9:25 下午
 */
public interface ServiceProviderContainer {

    /**
     * 添加服务提供器 (注意并不是发布服务到注册中心, 只是注册到本地容器)
     */
    void registryProvider(Object providerInstance);

    /**
     * 添加服务提供器 (注意并不是发布服务到注册中心, 只是注册到本地容器)
     */
    void registryProvider(Class<?> providerItf, Object providerInstance);

    /**
     * 本地容器查找服务
     */
    ServiceProvider lookupProvider(String uniqueKey);

    /**
     * 从本地容器移除服务
     */
    ServiceProvider removeProvider(String uniqueKey);

    /**
     * 获取本地容器中所有服务
     */
    List<ServiceProvider> listAllProviders();
}
