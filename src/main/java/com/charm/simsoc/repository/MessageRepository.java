package com.charm.simsoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.charm.simsoc.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(nativeQuery = true,
            value = "select * from messages m left join user_follower uf on m.user_id = uf.user_id where uf.follower_id = :userId order by m.created_date desc")
    public List<Message> getFollowingMessagesByUserId(@Param("userId") Long userId);

}
