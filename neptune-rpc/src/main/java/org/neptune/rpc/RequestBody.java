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

import lombok.Getter;
import lombok.Setter;
import org.neptune.registry.ServiceMetadata;

import java.io.Serializable;

/**
 * org.neptune.rpc.core - RequestBody
 *
 * @author tony-is-coding
 * @date 2021/12/24 14:19
 */
@Getter
@Setter
public class RequestBody implements Serializable {

    private static final long serialVersionUID = 1009813828866652852L;

    private String srcAppName;                   // 当前应用 - 应用名称
    private final ServiceMetadata metadata;      // 目标服务元数据
    private String methodName;                   // 目标方法名称
    private Object[] args;                       // 目标方法参数

    public RequestBody(ServiceMetadata metadata) {
        this.metadata = metadata;
    }
    public static int searchInsert(int[] nums, int target) {
        // 2分查找
        int left = 0;
        int right = nums.length - 1;
        if(target < nums[0]){
            return -1;
        }else if(target > nums[nums.length - 1]){
            return nums.length;
        }else if(nums.length == 1){
            return 0;
        }

        while (left < right) {
            if(target == nums[left]){
                return left;
            }else if(target == nums[right]){
                return right;
            }
            if(left + 1 == right){
                return left + 1;
            }
            int mid = left + (right - left) / 2;
            if(target == nums[mid]){
                return mid;
            }
            if(target > nums[mid]){
                left = mid;
            }else{
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        searchInsert(new int[]{1,3,5,6}, 2);
    }
}
