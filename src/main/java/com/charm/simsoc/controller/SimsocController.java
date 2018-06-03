package com.charm.simsoc.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.charm.simsoc.domain.Message;
import com.charm.simsoc.domain.User;
import com.charm.simsoc.service.UserService;

/**
 * This class is for convenient purpose to interact with F/E requests, it
 * delegate to SimsocRestController for processing and it interprets the data
 * before returning to F/E
 * 
 * @author charm
 *
 */
@Controller
public class SimsocController {
    
    /** logger **/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    /** simsocRestController **/
    private SimsocRestController simsocRestController;
    
    @Autowired
    /** userService **/
    private UserService userService;

    /**
     * redirect to login page
     * 
     * @return login path
     */
    @RequestMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    /**
     * login
     * 
     * @param model
     * @return login path
     */
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("users", simsocRestController.listAllUsers());
        return "login";
    }
    
    /**
     * create user
     * 
     * @param userName
     * @return my messages path
     */
    @RequestMapping("/usercreate")
    public String createUser(@RequestParam(value = "username") String userName) {
        User user = new User(userName);
        User persistUser = simsocRestController.addUser(user);
        Long userId = persistUser.getId();
        logger.debug("User {}[{}] is successfully created", userName, userId);
        return "redirect:/mymessages/" + userId;
    }
    
    /**
     * logout
     * 
     * @return login path
     */
    @RequestMapping("/logout")
    public String logout() {
        // No HTTP Session is set in this simple application, otherwise, it should clear the Session and then return to login page.
        return "redirect:/login";
    }
    
    /**
     * login with specified user
     * 
     * @param userId
     * @return my messages path
     */
    @RequestMapping("/userlogin")
    public String loginUser(@RequestParam(value = "userid") String userId) {
        logger.debug("Login user[{}]", userId);
        return "redirect:/mymessages/" + userId;
    }

//    @RequestMapping("/newuserpost")
//    public String postMessageAsNewUser(@RequestParam(value = "username") String userName, @RequestParam(value = "message") String text, Model model) {
//        User user = new User(userName);
//        User persistUser = simsocRestController.addUser(user);
//        Long userId = persistUser.getId();
//        Message message = new Message(text);
//        simsocRestController.addMessage(message, userId);
////        List<Message> myMessages = simsocRestController.listMessagesByUserId(userId);
////        model.addAttribute("myMessages", myMessages);
////        model.addAttribute("userId", userId);
//        return "redirect:/mymessages/" + userId;
//    }
    
    /**
     * post message
     * 
     * @param text
     * @param userId
     * @param model
     * @return my messages path
     */
    @RequestMapping("/postmessage/{userId}")
    public String postMessage(@RequestParam(value = "message") String text, @PathVariable Long userId, Model model) {
        Message message = new Message(text);
        simsocRestController.addMessage(message, userId);
        logger.debug("[{}] has post a message", userId);
        return "redirect:/mymessages/" + userId;
    }
    
    /**
     * list my following messages
     * @param userId
     * @param model
     * @return my following message list
     */
    @RequestMapping("/myfollowingmessages/{userId}")
    public String listMyFollowingMessages(@PathVariable Long userId, Model model) {
        List<Message> myFollowingMessages = simsocRestController.listFollowingUsersMessages(userId);
        String userName = simsocRestController.getUser(userId).getName();
        model.addAttribute("myFollowingMessages", myFollowingMessages);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        logger.debug("Forward to myfollowingmessagelist of {}[{}]", userName, userId);
        return "myfollowingmessagelist";
    }
    
    /**
     * list my messages
     * 
     * @param userId
     * @param model
     * @return my message list path
     */
    @RequestMapping("/mymessages/{userId}")
    public String listMyMessages(@PathVariable Long userId, Model model) {
        List<Message> myMessages = simsocRestController.listMessagesByUserId(userId);
        String userName = simsocRestController.getUser(userId).getName();
        model.addAttribute("myMessages", myMessages);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        logger.debug("Forward to mymessagelist of {}[{}]", userName, userId);
        return "mymessagelist";
    }
    
    /**
     * list users
     * 
     * @param userId
     * @param model
     * @return user list path
     */
    @RequestMapping("/myusers/{userId}")
    public String listUsers(@PathVariable Long userId, Model model) {
        User user = simsocRestController.getUser(userId);
        Set<User>followingUsers = user.getFollowingUsers();
        Set<User>followedByUsers = user.getFollowedByUsers();
        String userName = simsocRestController.getUser(userId).getName();
        model.addAttribute("followingUsers", followingUsers);
        model.addAttribute("followedByUsers", followedByUsers);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        logger.debug("Forward to userlist of {}[{}]", userName, userId);
        return "userlist";
    }
    
    /**
     * list users by name
     * 
     * @param userName
     * @param userId
     * @param model
     * @return user list path
     */
    @RequestMapping("/searchuser")
    public String listUsersByName(@RequestParam(value = "searchusername") String userName, @RequestParam(value = "userid") Long userId, Model model) {
        List<User> users = userService.findByName(userName);
        model.addAttribute("searchUserResult", users);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", simsocRestController.getUser(userId).getName());
        
        User user = simsocRestController.getUser(userId);
        Set<User>followingUsers = user.getFollowingUsers();
        Set<User>followedByUsers = user.getFollowedByUsers();
        model.addAttribute("followingUsers", followingUsers);
        model.addAttribute("followedByUsers", followedByUsers);
        logger.debug("Forward to searched userlist of {}[{}]", simsocRestController.getUser(userId).getName(), userId);
        return "userlist";
    }
    
    /**
     * show user info
     * 
     * @param targetUserId
     * @param userId
     * @param model
     * @return user info path
     */
    @RequestMapping("/userinfo/{targetUserId}")
    public String showUserInfo(@PathVariable Long targetUserId, @RequestParam(value = "userid") Long userId, Model model) {
        User user = simsocRestController.getUser(userId);
        User targetUser = simsocRestController.getUser(targetUserId);
        String userName = simsocRestController.getUser(userId).getName();
        Set<User>followedByUsers = targetUser.getFollowedByUsers();
        
        if (followedByUsers.contains(user)) {
            model.addAttribute("hasFollowed", true);
        } else {
            model.addAttribute("hasFollowed", false);
        }
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        
        logger.debug("{}[{}] views {}[{}]'s information", userName, userId, targetUser.getName(), targetUserId);
        return "userinfo";
    }
    
    /**
     * follow user
     * 
     * @param targetUserId
     * @param userId
     * @param model
     * @return user info path
     */
    @RequestMapping("/followuser/{userId}")
    public String followUser(@RequestParam(value = "targetuserid") Long targetUserId, @PathVariable Long userId, Model model) {
        String userName = simsocRestController.getUser(userId).getName();
        simsocRestController.followUser(userId, targetUserId);
        User targetUser = simsocRestController.getUser(targetUserId);
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("hasFollowed", true);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        logger.debug("{}[{}] follows and views {}[{}]'s information", userName, userId, targetUser.getName(), targetUserId);
        return "userinfo";
    }
    
    /**
     * unfollow user
     * 
     * @param targetUserId
     * @param userId
     * @param model
     * @return user info path
     */
    @RequestMapping("/unfollowuser/{userId}")
    public String unFollowUser(@RequestParam(value = "targetuserid") Long targetUserId, @PathVariable Long userId, Model model) {
        String userName = simsocRestController.getUser(userId).getName();
        simsocRestController.unFollowUser(userId, targetUserId);
        User targetUser = simsocRestController.getUser(targetUserId);
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("hasFollowed", false);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        logger.debug("{}[{}] unfollows and views {}[{}]'s information", userName, userId, targetUser.getName(), targetUserId);
        return "userinfo";
    }
    
}
