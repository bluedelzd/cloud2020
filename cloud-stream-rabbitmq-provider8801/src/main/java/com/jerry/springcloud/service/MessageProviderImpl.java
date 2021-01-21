package com.jerry.springcloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilderFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import javax.annotation.Resource;
import org.springframework.cloud.stream.messaging.Source;
import java.util.UUID;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@EnableBinding(CustomSource.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider
{
    @Resource
    private MessageChannel outputjerry; // 消息发送管道

    @Override
    public String send()
    {
        HttpServletRequest request;
        
        String serial = UUID.randomUUID().toString();
        outputjerry.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "+serial);
        return null;
    }
}
