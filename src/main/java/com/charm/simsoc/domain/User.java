package com.charm.simsoc.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The domain class reflecting to USER table
 * 
 * @author charm
 *
 */
@Entity
@Table(name = "users") // 'user' is a reserved word for derby, so named it 'users'
public class User {
    @Id
    @GeneratedValue
    /** id **/
    private Long id;
    /** name **/
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(name = "user_follower", joinColumns = { @JoinColumn(name = "follower_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id") })
    // @JsonManagedReference
    @JsonIgnore
    /** users this user is following **/
    private Set<User> followingUsers;

    @ManyToMany(mappedBy = "followingUsers", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @ManyToMany(mappedBy = "followingUsers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JsonBackReference
    @JsonIgnore
    /** users who are following this user **/
    private Set<User> followedByUsers;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @OrderBy("createdDateTime DESC")
    @JsonIgnore
    /** messages of this user **/
    private Set<Message> messages;

    /**
     * Default constructor
     */
    public User() {
        super();
    }

    /**
     * Constructor
     * 
     * @param name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * get id
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set id
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get users that this user is following
     * 
     * @return set of Users
     */
    public Set<User> getFollowingUsers() {
        return followingUsers;
    }

    /**
     * set following users
     * 
     * @param followingUsers
     */
    public void setFollowingUsers(Set<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    /**
     * get users who are following this user
     * @return set of User
     */
    public Set<User> getFollowedByUsers() {
        return followedByUsers;
    }

    /**
     * set followed by users
     * @param followedByUsers
     */
    public void setFollowedByUsers(Set<User> followedByUsers) {
        this.followedByUsers = followedByUsers;
    }

    /**
     * get messages
     * 
     * @return set of Messages
     */
    public Set<Message> getMessages() {
        return messages;
    }

    /**
     * set messages
     * 
     * @param messages
     */
    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    /**
     * follow the user
     * 
     * @param user
     */
    public void follow(User user) {
        this.getFollowingUsers().add(user);
    }

    /**
     * unfollow the user
     * 
     * @param user
     */
    public void unFollow(User user) {
        this.getFollowingUsers().remove(user);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
}
