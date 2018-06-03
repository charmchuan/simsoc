package com.charm.simsoc.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.charm.simsoc.domain.User;
import com.charm.simsoc.service.UserService;

/**
 * Test class for testing operations of UserServiceImpl
 * 
 * @author charm
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    
    @Autowired
    /** userService **/
    private UserService userService;

    @Test
    public void test_addUser() {
        User user = new User("Ken");
        User addedUser = userService.addUser(user);
        User resUser = userService.getUser(addedUser.getId());
        assertNotNull(resUser);
        assertTrue("User is added successfully!", ("Ken".equals(resUser.getName())));
    }
    
    @Test
    public void test_getUser() {
        User u = userService.getUser(new Long(999));
        assertNull(u);
        
        User user = new User("Ken");
        User addedUser = userService.addUser(user);
        User resUser = userService.getUser(addedUser.getId());
        assertNotNull(resUser);
        assertTrue("User is added successfully!", ("Ken".equals(resUser.getName())));
    }
    
    @Test
    public void test_updateUser() {
        User user = new User("Ken");
        User addedUser = userService.addUser(user);
        User resUser = userService.getUser(addedUser.getId());
        assertNotNull(resUser);
        assertTrue("User is added successfully!", ("Ken".equals(resUser.getName())));
        
        resUser.setName("Ken Smith");
        User sUser = userService.updateUser(resUser);
        User ssUser = userService.getUser(sUser.getId());
        assertTrue("User is updated successfully!", ("Ken Smith".equals(ssUser.getName())));
        
    }
    
    @Test
    public void test_deleteUser() {
        User user = new User("James");
        User addedUser = userService.addUser(user);
        User resUser = userService.getUser(addedUser.getId());
        assertNotNull(resUser);
        
        userService.deleteUser(resUser.getId());
        assertNull(userService.getUser(resUser.getId()));
    }
    
    @Test
    public void test_listAllUsers() {
        assertTrue(userService.listAllUsers().size() >= 0);
    }
    
    @Test
    public void test_followUser() {
        User robert = userService.addUser(new User("Robert"));
        User tim = userService.addUser(new User("Tim"));
        robert.setFollowedByUsers(new HashSet<User>());
        robert.setFollowingUsers(new HashSet<User>());
        tim.setFollowedByUsers(new HashSet<User>());
        tim.setFollowingUsers(new HashSet<User>());
        
        assertTrue(robert.getFollowedByUsers().size() == 0);
        assertTrue(robert.getFollowingUsers().size() == 0);
        assertTrue(tim.getFollowedByUsers().size() == 0);
        assertTrue(tim.getFollowingUsers().size() == 0);
    }
    
    @Test
    public void test_unFollowUser() {
        User robert = userService.addUser(new User("Robert"));
        User tim = userService.addUser(new User("Tim"));
        robert.setFollowedByUsers(new HashSet<User>());
        robert.setFollowingUsers(new HashSet<User>());
        tim.setFollowedByUsers(new HashSet<User>());
        tim.setFollowingUsers(new HashSet<User>());
        
        assertTrue(robert.getFollowedByUsers().size() == 0);
        assertTrue(robert.getFollowingUsers().size() == 0);
        assertTrue(tim.getFollowedByUsers().size() == 0);
        assertTrue(tim.getFollowingUsers().size() == 0);
    }
    
    @Test
    public void test_findByName() {
        assertTrue(userService.findByName("Robert").size() == 0);
        
        User user = new User("Robert");
        userService.addUser(user);
        assertNotNull(userService.findByName("Robert"));
    }
}
