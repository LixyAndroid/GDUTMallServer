package com.xuyang.gdutmallserver.service;

import com.xuyang.gdutmallserver.model.MessageInfo;

import java.util.List;

public interface MessageService {
    int addMessage(MessageInfo paramMessageInfo);

    List<MessageInfo> getMessageList(Integer paramInteger);
}
