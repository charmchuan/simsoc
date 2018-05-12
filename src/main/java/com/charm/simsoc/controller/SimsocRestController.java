package com.charm.simsoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charm.simsoc.domain.Message;
import com.charm.simsoc.domain.User;
import com.charm.simsoc.service.MessageService;
import com.charm.simsoc.service.UserService;

/**
 * This RestController implements all RESTful API (only JSON is accepted in request/response)
 *
 */
@RestController
public class SimsocRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
  
    // RESTful API
    // --------------------------------------------------------------------
    
    @RequestMapping("/users")
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/users")
//    public User updateUser(@RequestBody User user) {
//        return userService.updateUser(user);
//    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }

    // --------------------------------------------------------------------

    @RequestMapping("/messages/{userId}")
    public List<Message> listMessagesByUserId(@PathVariable Long userId) {
        return messageService.listMessagesByUserId(userId);
    }
    
    @RequestMapping("/messages/{userId}/following")
    public List<Message> listFollowingUsersMessages(@PathVariable Long userId) {
        return messageService.listFollowingMessagesByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/messages/{userId}")
    public void addMessage(@RequestBody Message message, @PathVariable Long userId) {
        User user = userService.getUser(userId);
        message.setUser(user);
        messageService.addMessage(message);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/messages")
    public void updateMessage(@RequestBody Message message) {
        messageService.updateMessage(message);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/messages/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }

    // --------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST, value = "/following/{followerId}/{userId}")
    public void followUser(@PathVariable Long followerId, @PathVariable Long userId) {
        userService.followUser(followerId, userId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/following/{followerId}/{userId}")
    public void unFollowUser(@PathVariable Long followerId, @PathVariable Long userId) {
        userService.unFollowUser(followerId, userId);
    }

}
