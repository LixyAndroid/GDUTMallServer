package com.xuyang.gdutmallserver.mapper;

import com.xuyang.gdutmallserver.model.MessageInfo;

import java.util.List;

public interface MessageInfoMapper {
    int deleteByPrimaryKey(Integer paramInteger);

    int insert(MessageInfo paramMessageInfo);

    int insertSelective(MessageInfo paramMessageInfo);

    MessageInfo selectByPrimaryKey(Integer paramInteger);

    int updateByPrimaryKeySelective(MessageInfo paramMessageInfo);

    int updateByPrimaryKey(MessageInfo paramMessageInfo);

    List<MessageInfo> selectMessageList(int paramInt);
}
