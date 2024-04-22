package org.neptune.example;

import org.neptune.registry.nacos.NacosServicePublisher;
import org.neptune.rpc.server.DefaultServer;
import org.neptune.rpc.server.Server;

/**
 * @desc TODO
 *
 * @author tony
 * @createDate 2024/4/21 10:10 下午
 */
public class MultiSimpleServer {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> server(8001));
        Thread t2 = new Thread(() -> server(8002));
        Thread t3 = new Thread(() -> server(8003));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    public static void server(int port){


        Server server = null;
        try {

            NacosServicePublisher nacosServicePublisher = new NacosServicePublisher(
                    "127.0.0.1", "8848"
            );
            server = DefaultServer.builder()
                    .serverName("demo-service")
                    .group("test")
                    .port(port)
                    .servicePublisher(nacosServicePublisher)
                    .build();
            server.start();
        } catch (Exception e) {
        } finally {
            server.shutdownGracefully();
        }
    }
}
