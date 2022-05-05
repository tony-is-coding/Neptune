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
package org.neptune.core.core.consumer.handler;

import org.neptune.core.core.Client;
import org.neptune.core.core.ServiceMeta;
import org.neptune.core.core.consumer.Dispatcher;
import org.neptune.core.core.consumer.cluster.ClusterInvoker;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;

/**
 * org.neptune.core.core.consumer - SyncInvocationHandler
 *
 * @author tony-is-coding
 * @date 2021/12/17 20:10
 */
public class SyncInvocationHandler extends AbstractInvocationHandler {


    public SyncInvocationHandler(ClusterInvoker clusterInvoker, ServiceMeta meta, Client client, Dispatcher dispatcher) {
        this.clusterInvoker = clusterInvoker;
        this.serviceMeta = meta;
        this.client = client;
        this.dispatcher = dispatcher;
    }

    @RuntimeType
    public Object invoke(@Origin Method method, @AllArguments @RuntimeType Object[] args) throws Throwable {
        return doInvoke(method.getName(), args, method.getReturnType(), true);
    }

}
