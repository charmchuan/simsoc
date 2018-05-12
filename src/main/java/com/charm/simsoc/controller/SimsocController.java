package com.charm.simsoc.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.charm.simsoc.domain.Message;
import com.charm.simsoc.domain.User;
import com.charm.simsoc.service.UserService;

@Controller
public class SimsocController {

    @Autowired
    private SimsocRestController simsocRestController;
    
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String redirToLogin() {
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("users", simsocRestController.listAllUsers());
        return "login";
    }
    
    @RequestMapping("/usercreate")
    public String createUser(@RequestParam(value = "username") String userName) {
        User user = new User(userName);
        User persistUser = simsocRestController.addUser(user);
        Long userId = persistUser.getId();
        return "redirect:/mymessages/" + userId;
    }
    
    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
    
    @RequestMapping("/userlogin")
    public String loginUser(@RequestParam(value = "userid") String userId) {
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
    
    @RequestMapping("/postmessage/{userId}")
    public String postMessage(@RequestParam(value = "message") String text, @PathVariable Long userId, Model model) {
        Message message = new Message(text);
        simsocRestController.addMessage(message, userId);
        return "redirect:/mymessages/" + userId;
    }
    
    @RequestMapping("/myfollowingmessages/{userId}")
    public String listMyFollowingMessages(@PathVariable Long userId, Model model) {
        List<Message> myFollowingMessages = simsocRestController.listFollowingUsersMessages(userId);
        model.addAttribute("myFollowingMessages", myFollowingMessages);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", simsocRestController.getUser(userId).getName());
        return "myfollowingmessagelist";
    }
    
    @RequestMapping("/mymessages/{userId}")
    public String listMyMessages(@PathVariable Long userId, Model model) {
        List<Message> myMessages = simsocRestController.listMessagesByUserId(userId);
        model.addAttribute("myMessages", myMessages);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", simsocRestController.getUser(userId).getName());
        return "mymessagelist";
    }
    
    @RequestMapping("/myusers/{userId}")
    public String listUsers(@PathVariable Long userId, Model model) {
        User user = simsocRestController.getUser(userId);
        Set<User>followingUsers = user.getFollowingUsers();
        Set<User>followedByUsers = user.getFollowedByUsers();
        model.addAttribute("followingUsers", followingUsers);
        model.addAttribute("followedByUsers", followedByUsers);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", simsocRestController.getUser(userId).getName());
        return "userlist";
    }
    
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
        return "userlist";
    }
    
    @RequestMapping("/userinfo/{targetUserId}")
    public String showUserInfo(@PathVariable Long targetUserId, @RequestParam(value = "userid") Long userId, Model model) {
        User user = simsocRestController.getUser(userId);
        User targetUser = simsocRestController.getUser(targetUserId);
        Set<User>followedByUsers = targetUser.getFollowedByUsers();
        
        if (followedByUsers.contains(user)) {
            model.addAttribute("hasFollowed", true);
        } else {
            model.addAttribute("hasFollowed", false);
        }
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", simsocRestController.getUser(userId).getName());
        return "userinfo";
    }
    
    @RequestMapping("/followuser/{userId}")
    public String followUser(@RequestParam(value = "targetuserid") Long targetUserId, @PathVariable Long userId, Model model) {
        simsocRestController.followUser(userId, targetUserId);
        User targetUser = simsocRestController.getUser(targetUserId);
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("hasFollowed", true);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", simsocRestController.getUser(userId).getName());
        return "userinfo";
    }
    
    @RequestMapping("/unfollowuser/{userId}")
    public String unFollowUser(@RequestParam(value = "targetuserid") Long targetUserId, @PathVariable Long userId, Model model) {
        simsocRestController.unFollowUser(userId, targetUserId);
        User targetUser = simsocRestController.getUser(targetUserId);
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("hasFollowed", false);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", simsocRestController.getUser(userId).getName());
        return "userinfo";
    }
    
}
