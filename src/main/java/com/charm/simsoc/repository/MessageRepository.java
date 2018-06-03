package com.charm.simsoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.charm.simsoc.domain.Message;

/**
 * The data access object who provides operations on Message
 * 
 * @author charm
 *
 */
public interface MessageRepository extends CrudRepository<Message, Long> {

    /**
     * Native SQL query to list all Messages that particular User is following
     * 
     * @param userId
     * @return list of Message
     */
    @Query(nativeQuery = true,
            value = "select * from message m left join user_follower uf on m.user_id = uf.user_id where uf.follower_id = :userId order by m.created_date_time desc")
    public List<Message> getFollowingMessagesByUserId(@Param("userId") Long userId);

}
