package com.charm.simsoc;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.charm.simsoc.domain.Message;
import com.charm.simsoc.domain.User;
import com.charm.simsoc.service.MessageService;
import com.charm.simsoc.service.UserService;

@EnableJpaAuditing
@SpringBootApplication
public class SimpleSocialityApplication implements CommandLineRunner {
    
    private boolean setupTestData = true;

    @Autowired
    private UserService userService;
    
    @Autowired
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(SimpleSocialityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (setupTestData) {
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
