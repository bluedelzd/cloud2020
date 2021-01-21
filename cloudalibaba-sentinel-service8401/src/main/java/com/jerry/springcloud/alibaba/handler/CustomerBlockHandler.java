package com.jerry.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import common.jerry.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(2020, "自定义限流处理信息....CustomerBlockHandler");

    }
}
