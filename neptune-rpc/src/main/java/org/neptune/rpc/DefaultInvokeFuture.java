/*
 * Copyright (c) 2022 The Neptune Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neptune.rpc;


import com.alibaba.fastjson2.JSON;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * org.neptune.rpc.core - DefaultInvokeFuture
 * 通过这个进行阶段通知
 *
 * @author tony-is-coding
 * @date 2021/12/20 17:56
 */
@Slf4j
public class DefaultInvokeFuture<V> extends CompletableFuture<V> implements InvokeFuture<V> {

    private static final ConcurrentHashMap<Long, DefaultInvokeFuture<?>> FUTURE_HOLDER = new ConcurrentHashMap<>(16);
    private static final byte SEND_FAILURE = -1;
    private static final byte SEND_SUCCESS = 1;
    private static final byte SENDING = 0;

    private final Channel channel;
    private final long invokeId;
    private final Class<V> returnType;

    private byte sendState = SENDING;

    public DefaultInvokeFuture(Channel channel, long invokeId, Class<V> returnType) {
        this.channel = channel;
        this.invokeId = invokeId;
        this.returnType = returnType;
        FUTURE_HOLDER.put(invokeId, this);
    }

    public void sentSuccess() {
        sendState = SEND_SUCCESS;
    }

    public void sentFailure() {
        sendState = SEND_FAILURE;
    }

    @Override
    public V result() throws Throwable {
        return get(1000, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("unchecked")
    private void doReceived(Response response) {
        final Object result = response.getResult();
        complete((V) result); // 完成
    }

    public static void received(Channel ch, Response response) {
        final long invokeId = response.getInvokeId();
        DefaultInvokeFuture<?> invokeFuture = FUTURE_HOLDER.remove(invokeId);
        if (invokeFuture == null) {
            return;
        }
        // 进行异步通知
        invokeFuture.doReceived(response);
    }

}
