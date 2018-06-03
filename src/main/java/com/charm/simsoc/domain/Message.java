package com.charm.simsoc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The domain class reflecting to MESSAGE table
 * 
 * @author charm
 *
 */
@Entity
@Table(name = "message")
public class Message extends Auditable {
    @Id
    @GeneratedValue
    /** id **/
    private Long id;
    /** content **/
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    /** the owner of the message **/
    private User user;
    
    /**
     * Default constructor
     */
    public Message() {
        super();
    }
    
    /**
     * Constructor
     * 
     * @param content
     */
    public Message(String content) {
        this.content = content;
    }

    /**
     * get message id
     * 
     * @return message id
     */
    public Long getId() {
        return id;
    }

    /**
     * set message id
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get content
     * 
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * set content
     * 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get user
     * 
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * set user
     * 
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
