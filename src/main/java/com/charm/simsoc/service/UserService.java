package com.charm.simsoc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charm.simsoc.domain.User;
import com.charm.simsoc.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
//        User user = this.getUser(id);
//        user.setFollowingUsers(null);
//        user.setFollowedByUsers(null);
//        user.setMessages(null);
//        this.updateUser(user);
        userRepository.delete(id);
    }

    public void followUser(Long followerId, Long userId) {
        User follower = getUser(followerId);
        User user = getUser(userId);
        follower.follow(user);
        this.updateUser(follower);
    }

    public void unFollowUser(Long followerId, Long userId) {
        User follower = getUser(followerId);
        User user = getUser(userId);
        follower.unFollow(user);
        this.updateUser(follower);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

}
