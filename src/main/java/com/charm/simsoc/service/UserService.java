package com.charm.simsoc.service;

import java.util.List;

import com.charm.simsoc.domain.User;

/**
 * The UserService interface contains corresponding operations on User instance(s)
 * 
 * @author charm
 *
 */
public interface UserService {

    /**
     * List all Users <br><br>
     * 
     * <I>Ideally, it is best to include pagination number as parameters to retrieve pieces of Users, it is not designed as this in order to make it as simple as it can, with considering not much volume in such simple application</I>
     * 
     * @return list of User
     */
    public List<User> listAllUsers();

    /**
     * Retrieve particular user by specifying User id
     * 
     * @param id
     * @return User
     */
    public User getUser(Long id);

    /**
     * Create a new User <br><b>User.id should be null and to be assigned by system.</b>
     * @param user
     * @return created User with User.id assigned
     */
    public User addUser(User user);

    /**
     * Update User
     * @param user with User.id
     * @return updated User
     */
    public User updateUser(User user);

    /**
     * Delete particular user by specifying User id
     * @param id
     */
    public void deleteUser(Long id);

    /**
     * Follow a User
     * @param followerId (The User to follow)
     * @param userId (The User to be followed)
     */
    public void followUser(Long followerId, Long userId);

    /**
     * Unfollow a User
     * @param followerId (The User to unfollow)
     * @param userId (The User to be unfollowed)
     */
    public void unFollowUser(Long followerId, Long userId);

    /**
     * Search Users by exact user name
     * @param name
     * @return list of Users
     */
    public List<User> findByName(String name);

}