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
package org.neptune.registry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neptune.common.UnresolvedAddress;

/**
 * org.neptune.rpc.registry - RegistryMeta
 *
 * @author tony-is-coding
 * @date 2021/12/22 12:40
 */
@ToString
@Getter
@Setter
public class RegistryMeta {

    protected ServiceMetadata serviceMeta;
    protected UnresolvedAddress address;
    private int wight; // 主要是为了摘点流量 + 实现权重轮询

    public RegistryMeta() {
    }
    public String toUniqueInstanceId(){
        return "default";
    }
}
