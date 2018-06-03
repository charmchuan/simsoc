package com.charm.simsoc;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.charm.simsoc.domain.Message;
import com.charm.simsoc.domain.User;
import com.charm.simsoc.service.UserService;
import com.charm.simsoc.service.MessageService;

/**
 * The entrance of the application
 * 
 * @author charm
 *
 */
@EnableJpaAuditing
@SpringBootApplication
public class SimpleSocialityApplication implements CommandLineRunner {
    
    /** logger **/
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** whether to pre setup test data **/
    private boolean setupTestData = true;

    @Autowired
    /** userService **/
    private UserService userService;
    
    @Autowired
    /** messageService **/
    private MessageService messageService;

    /**
     * entry point for SpringBoot
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SimpleSocialityApplication.class, args);
    }

    /* (non-Javadoc)
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     */
    @Override
    public void run(String... args) throws Exception {

        if (setupTestData) {
            logger.info("--- pre setup users ---");
            User adrian = new User("Adrian");
            User dillon = new User("Dillon");
            User lik = new User("Lik");
    
            Set<User> adrianFollowingUsers = new HashSet<User>();
            adrianFollowingUsers.add(dillon);
            adrianFollowingUsers.add(lik);
            adrian.setFollowingUsers(adrianFollowingUsers);
    
            Set<User> dillonFollowingUsers = new HashSet<User>();
            dillonFollowingUsers.add(adrian);
            dillonFollowingUsers.add(lik);
            dillon.setFollowingUsers(dillonFollowingUsers);
    
            Set<User> likFollowingUsers = new HashSet<User>();
            likFollowingUsers.add(adrian);
            likFollowingUsers.add(dillon);
            lik.setFollowingUsers(likFollowingUsers);
    
            // Set<User> adrianFollowedByUsers = new HashSet<User>();
            // adrianFollowedByUsers.add(dillon);
            // adrianFollowedByUsers.add(lik);
            // adrian.setFollowedByUsers(adrianFollowedByUsers);
            //
            // Set<User> dillonFollowedByUsers = new HashSet<User>();
            // dillonFollowedByUsers.add(adrian);
            // dillonFollowedByUsers.add(lik);
            // dillon.setFollowedByUsers(dillonFollowedByUsers);
            //
            // Set<User> likFollowedByUsers = new HashSet<User>();
            // likFollowedByUsers.add(dillon);
            // likFollowedByUsers.add(adrian);
            // lik.setFollowedByUsers(likFollowedByUsers);
            
            Message adrian1stMsg = new Message("Adrian's first post");
            adrian1stMsg.setUser(adrian);
            Message dillon1stMsg = new Message("Dillon's first post");
            dillon1stMsg.setUser(dillon);
            Message lik1stMsg = new Message("Lik's first post");
            lik1stMsg.setUser(lik);
    
            userService.addUser(adrian);
            userService.addUser(dillon);
            userService.addUser(lik);
            
            messageService.addMessage(adrian1stMsg);
            messageService.addMessage(dillon1stMsg);
            messageService.addMessage(lik1stMsg);
        }
    }
}
