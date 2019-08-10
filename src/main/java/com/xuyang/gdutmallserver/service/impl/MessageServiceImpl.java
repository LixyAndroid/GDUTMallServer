package com.xuyang.gdutmallserver.service.impl;

import com.xuyang.gdutmallserver.mapper.MessageInfoMapper;
import com.xuyang.gdutmallserver.model.MessageInfo;
import com.xuyang.gdutmallserver.service.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl  implements MessageService {

    @Autowired
    private MessageInfoMapper mapper;

    public int addMessage(MessageInfo model) {
        return this.mapper.insert(model);
    }

    public List<MessageInfo> getMessageList(Integer userId) {
        return this.mapper.selectMessageList(userId.intValue());
    }
}