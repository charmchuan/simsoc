package com.charm.simsoc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charm.simsoc.domain.Message;
import com.charm.simsoc.domain.User;
import com.charm.simsoc.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<Message>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    public Message getMessage(Long id) {
        return messageRepository.findOne(id);
    }

    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.delete(id);
    }

    public List<Message> listMessagesByUserId(Long userId) {
        List<Message> messages = new ArrayList<Message>();
        User user = userService.getUser(userId);
        user.getMessages().forEach(messages::add);
        return messages;
    }

    public List<Message> listFollowingMessagesByUserId(Long userId) {
        List<Message> messages = messageRepository.getFollowingMessagesByUserId(userId);
        return messages;
    }
}
