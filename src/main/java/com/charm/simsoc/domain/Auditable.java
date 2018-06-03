package com.charm.simsoc.domain;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Abstract domain class that can automatically log down created date time and modified date time when persisting object
 * 
 * @author charm
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class Auditable {

    @CreatedDate
    @Temporal(TIMESTAMP)
    /** created date time **/
    protected Date createdDateTime;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    /** created date time **/
    protected Date lastModifiedDateTime;

    /**
     * get created date time
     * @return Date
     */
    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * set created date time
     * @param createdDateTime
     */
    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * get last modified date time
     * @return
     */
    public Date getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * set last modified date time
     * @param lastModifiedDateTime
     */
    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

}