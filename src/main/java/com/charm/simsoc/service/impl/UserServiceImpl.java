package com.charm.simsoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charm.simsoc.domain.User;
import com.charm.simsoc.repository.UserRepository;
import com.charm.simsoc.service.UserService;

/**
 * The implementation of UserService
 * 
 * @author charm
 *
 */
@Service
public class UserServiceImpl implements UserService {
    
    /** logger **/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    /** userRepository **/
    private UserRepository userRepository;

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.UserService#listAllUsers()
     */
    @Override
    public List<User> listAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        logger.debug("{} users are retrieved", users.size());
        return users;
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.UserService#getUser(java.lang.Long)
     */
    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.UserService#addUser(com.charm.simsoc.domain.User)
     */
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.UserService#updateUser(com.charm.simsoc.domain.User)
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.UserService#deleteUser(java.lang.Long)
     */
    @Override
    public void deleteUser(Long id) {
//        User user = this.getUser(id);
//        user.setFollowingUsers(null);
//        user.setFollowedByUsers(null);
//        user.setMessages(null);
//        this.updateUser(user);
        userRepository.delete(id);
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.UserService#followUser(java.lang.Long, java.lang.Long)
     */
    @Override
    public void followUser(Long followerId, Long userId) {
        User follower = getUser(followerId);
        User user = getUser(userId);
        follower.follow(user);
        this.updateUser(follower);
        logger.debug("{}[{}] followed {}[{}]", follower.getName(), follower.getId(), user.getName(), user.getId());
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.UserService#unFollowUser(java.lang.Long, java.lang.Long)
     */
    @Override
    public void unFollowUser(Long followerId, Long userId) {
        User follower = getUser(followerId);
        User user = getUser(userId);
        follower.unFollow(user);
        this.updateUser(follower);
        logger.debug("{}[{}] unfollowed {}[{}]", follower.getName(), follower.getId(), user.getName(), user.getId());
    }

    /* (non-Javadoc)
     * @see com.charm.simsoc.service.UserService#findByName(java.lang.String)
     */
    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

}
