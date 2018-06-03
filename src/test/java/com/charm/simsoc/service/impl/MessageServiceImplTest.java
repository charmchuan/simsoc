package com.charm.simsoc.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.charm.simsoc.domain.Message;
import com.charm.simsoc.service.MessageService;

/**
 * Test class for testing operations of MessageServiceImpl
 * 
 * @author charm
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageServiceImplTest {
    
    @Autowired
    /** messageService **/
    private MessageService messageService;
    
    @Test
    public void test_addMessage() {
        Message message = new Message("This is a test content!");
        Message addedMsg = messageService.addMessage(message);
        Message resMsg = messageService.getMessage(addedMsg.getId());
        String resContent = resMsg.getContent();
        assertTrue("Message is added successfully!", ("This is a test content!".equals(resContent)));
    }
    
    @Test
    public void test_getMessage() {
        Message msg = messageService.getMessage(new Long("999"));
        assertNull(msg);
        
        Message newMsg = new Message("new message");
        Message addedMsg = messageService.addMessage(newMsg);
        assertNotNull("Success", messageService.getMessage(addedMsg.getId()));
    }
    
    @Test
    public void test_updateMessage() {
        Message message = new Message("This is a test content!");
        Message addedMsg = messageService.addMessage(message);
        Message resMsg = messageService.getMessage(addedMsg.getId());
        String resContent = resMsg.getContent();
        assertTrue("Message is added successfully!", ("This is a test content!".equals(resContent)));
        
        resMsg.setContent("Updated content!");
        messageService.updateMessage(resMsg);
        Message updatedMsg = messageService.getMessage(resMsg.getId());
        assertTrue("Message is added successfully!", ("Updated content!".equals(updatedMsg.getContent())));
    }
    
    @Test
    public void test_deleteMessage() {
        Message newMsg = new Message("new message");
        Message addedMsg = messageService.addMessage(newMsg);
        Message msg = messageService.getMessage(addedMsg.getId());
        assertNotNull(msg);
        
        messageService.deleteMessage(addedMsg.getId());
        assertNull(messageService.getMessage(addedMsg.getId()));
    }
    
    @Test
    public void teset_getAllMessages() {
        assertTrue(messageService.getAllMessages().size() >= 0);
    }
    
    @Test
    public void test_listMessagesByUserId() {
        List<Message> messages = messageService.listMessagesByUserId(new Long("999"));
        assertTrue(0 == messages.size());
    }
    
    @Test
    public void test_listFollowingMessagesByUserId() {
        List<Message> messages = messageService.listFollowingMessagesByUserId(new Long("999"));
        assertTrue(0 == messages.size());
    }
    
}
