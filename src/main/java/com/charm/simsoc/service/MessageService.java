package com.charm.simsoc.service;

import java.util.List;

import com.charm.simsoc.domain.Message;

/**
 * The MessageService interface contains corresponding operations on Message instance(s)
 * 
 * @author charm
 *
 */
public interface MessageService {

    /**
     * List all Messages <br><br>
     * 
     * <I>Ideally, it is best to include pagination number as parameters to retrieve pieces of Messages, it is not designed as this in order to make it as simple as it can, with considering not much volume in such simple application</I>
     * 
     * @return list of Message
     */
    public List<Message> getAllMessages();

    /**
     * Retrieve particular message by specifying Message id
     * 
     * @param id
     * @return Message
     */
    public Message getMessage(Long id);

    /**
     * Create a new message <br><b>Message.id should be null and to be assigned by system. User.id should be assigned to identify the ownership of the message</b>
     * @param Message
     * @return created Message with Message.id assigned
     */
    public Message addMessage(Message message);

    /**
     * Update Message
     * @param message with Message.id
     * @return updated Message
     */
    public Message updateMessage(Message message);

    /**
     * Delete particular message by specifying Message id
     * @param id
     */
    public void deleteMessage(Long id);

    /**
     * List all Messages from particular User<br><br>
     * 
     * <I>Ideally, it is best to include pagination number as parameters to retrieve pieces of Messages, it is not designed as this in order to make it as simple as it can, with considering not much volume in such simple application</I>
     * 
     * @return list of Message
     */
    public List<Message> listMessagesByUserId(Long userId);

    /**
     * List all Messages that particular User is following<br><br>
     * 
     * <I>Ideally, it is best to include pagination number as parameters to retrieve pieces of Messages, it is not designed as this in order to make it as simple as it can, with considering not much volume in such simple application</I>
     * 
     * @return list of Message
     */
    public List<Message> listFollowingMessagesByUserId(Long userId);

}