package com.charm.simsoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charm.simsoc.domain.Message;
import com.charm.simsoc.domain.User;
import com.charm.simsoc.repository.MessageRepository;
import com.charm.simsoc.service.MessageService;
import com.charm.simsoc.service.UserService;

/**
 * The implementation of MessageService
 * 
 * @author charm
 *
 */
@Service
public class MessageServiceImpl implements MessageService {
    
    /** logger **/
    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    /** messageRepository **/
    private MessageRepository messageRepository;
    
    @Autowired
    /** userService **/
    private UserService userService;

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.MessageService#getAllMessages()
     */
    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<Message>();
        messageRepository.findAll().forEach(messages::add);
        logger.debug("{} messages are retrieved", messages.size());
        return messages;
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.MessageService#getMessage(java.lang.Long)
     */
    @Override
    public Message getMessage(Long id) {
        return messageRepository.findOne(id);
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.MessageService#addMessage(com.charm.simsoc.domain.Message)
     */
    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.MessageService#updateMessage(com.charm.simsoc.domain.Message)
     */
    @Override
    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.MessageService#deleteMessage(java.lang.Long)
     */
    @Override
    public void deleteMessage(Long id) {
        messageRepository.delete(id);
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.MessageService#listMessagesByUserId(java.lang.Long)
     */
    @Override
    public List<Message> listMessagesByUserId(Long userId) {
        List<Message> messages = new ArrayList<Message>();
        User user = userService.getUser(userId);
        if (user != null) {
            user.getMessages().forEach(messages::add);
        } else {
            logger.warn("Can not get any user with user id [{}]", userId);
        }
        return messages;
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.MessageService#listFollowingMessagesByUserId(java.lang.Long)
     */
    @Override
    public List<Message> listFollowingMessagesByUserId(Long userId) {
        List<Message> messages = messageRepository.getFollowingMessagesByUserId(userId);
        return messages;
    }
}
