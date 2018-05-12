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

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(name = "user_follower", joinColumns = { @JoinColumn(name = "follower_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id") })
    // @JsonManagedReference
    @JsonIgnore
    private Set<User> followingUsers;

    @ManyToMany(mappedBy = "followingUsers", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @ManyToMany(mappedBy = "followingUsers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JsonBackReference
    @JsonIgnore
    private Set<User> followedByUsers;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @OrderBy("createdDate DESC")
    @JsonIgnore
    private Set<Message> messages;

    public User() {
        super();
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getFollowingUsers() {
        return followingUsers;
    }

    public void setFollowingUsers(Set<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public Set<User> getFollowedByUsers() {
        return followedByUsers;
    }

    public void setFollowedByUsers(Set<User> followedByUsers) {
        this.followedByUsers = followedByUsers;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void follow(User user) {
        this.getFollowingUsers().add(user);
    }

    public void unFollow(User user) {
        this.getFollowingUsers().remove(user);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

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
