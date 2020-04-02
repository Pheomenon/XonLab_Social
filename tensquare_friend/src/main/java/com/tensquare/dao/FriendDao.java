package com.tensquare.dao;

import com.tensquare.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author:Gao
 * @Date:2020-03-26 19:43
 */

public interface FriendDao extends JpaRepository<Friend,String> {
    public Friend findByUseridAndFriendid(String userid,String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike = ? WHERE userid = ? AND friendid = ?",nativeQuery = true)
    public void updateIsLike(String islike, String userid,String friendid);

    @Modifying
    @Query(value = "DELETE FROM tb_friend WHERE userid = ? AND friendid = ?",nativeQuery = true)
    void deleteFriend(String userid, String friendid);
}
