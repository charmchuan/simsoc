package com.charm.simsoc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    /** logger **/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    /** userService **/
    private UserService userService;
    @Autowired
    /** messageService **/
    private MessageService messageService;
  
    // RESTful API
    // --------------------------------------------------------------------
    
    /**
     * list all users
     * @return list of User
     */
    @RequestMapping("/users")
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    /**
     * get user
     * 
     * @param id
     * @return User
     */
    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        logger.debug("[GET] get user[{}]", id);
        return userService.getUser(id);
    }

    /**
     * add user
     * 
     * @param user
     * @return User
     */
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public User addUser(@RequestBody User user) {
        logger.debug("[POST] add user");
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

    /**
     * list messages by user id
     * @param userId
     * @return list of message
     */
    @RequestMapping("/messages/{userId}")
    public List<Message> listMessagesByUserId(@PathVariable Long userId) {
        logger.debug("[GET] list messages by user[{}]", userId);
        return messageService.listMessagesByUserId(userId);
    }
    
    /**
     * list following users' messages
     * @param userId
     * @return list of Message
     */
    @RequestMapping("/messages/{userId}/following")
    public List<Message> listFollowingUsersMessages(@PathVariable Long userId) {
        logger.debug("[GET] list following users' messages for user[{}]", userId);
        return messageService.listFollowingMessagesByUserId(userId);
    }

    /**
     * add message
     * 
     * @param message
     * @param userId
     * @return Message
     */
    @RequestMapping(method = RequestMethod.POST, value = "/messages/{userId}")
    public Message addMessage(@RequestBody Message message, @PathVariable Long userId) {
        logger.debug("[POST] add message by user[{}]", userId);
        User user = userService.getUser(userId);
        message.setUser(user);
        return messageService.addMessage(message);
    }

    /**
     * update message
     * 
     * @param message
     * @return Message
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/messages")
    public Message updateMessage(@RequestBody Message message) {
        logger.debug("[PUT] update message by user[{}]", message.getUser().getId());
        return messageService.updateMessage(message);
    }

    /**
     * delete message
     * 
     * @param id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/messages/{id}")
    public void deleteMessage(@PathVariable Long id) {
        logger.debug("[DELETE] delete message[{}]", id);
        messageService.deleteMessage(id);
    }

    // --------------------------------------------------------------------

    /**
     * follow user
     * @param followerId
     * @param userId
     */
    @RequestMapping(method = RequestMethod.POST, value = "/following/{followerId}/{userId}")
    public void followUser(@PathVariable Long followerId, @PathVariable Long userId) {
        logger.debug("[POST] [{}] followUser [{}]", followerId, userId);
        userService.followUser(followerId, userId);
    }

    /**
     * unfollow user
     * 
     * @param followerId
     * @param userId
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/following/{followerId}/{userId}")
    public void unFollowUser(@PathVariable Long followerId, @PathVariable Long userId) {
        logger.debug("[POST] [{}] unFollowUser [{}]", followerId, userId);
        userService.unFollowUser(followerId, userId);
    }

}
