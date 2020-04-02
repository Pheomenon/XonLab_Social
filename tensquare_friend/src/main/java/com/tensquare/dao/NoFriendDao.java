package com.tensquare.dao;

import com.tensquare.pojo.Friend;
import com.tensquare.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author:Gao
 * @Date:2020-03-26 20:27
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    public NoFriend findByUseridAndFriendid(String userid, String friendid);

}
