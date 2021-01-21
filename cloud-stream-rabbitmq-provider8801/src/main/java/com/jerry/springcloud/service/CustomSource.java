package com.jerry.springcloud.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomSource {
    String OUTPUT_JERRY = "outputjerry";

    @Output(OUTPUT_JERRY)
    MessageChannel outputjerry();
}
