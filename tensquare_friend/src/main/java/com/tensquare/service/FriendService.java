package com.tensquare.service;

import com.tensquare.dao.FriendDao;
import com.tensquare.dao.NoFriendDao;
import com.tensquare.pojo.Friend;
import com.tensquare.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:Gao
 * @Date:2020-03-26 19:33
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid, String friendid) {
        //先判断userid到friendid是否有数据，来判断重复添加的情况
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if(friend != null){
            return 0;
        }
        //直接添加好友，让好友表中userid到friendid方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据，如果有，双发的状态都改为1
        if(friendDao.findByUseridAndFriendid(friendid, userid) != null){
            //把双方的IsLike都改成1
            friendDao.updateIsLike("1",userid,friendid);
            friendDao.updateIsLike("1",friendid,userid);
        }
        return 1;
    }

    public int addNoFriend(String userId, String friendid) {
        //先判断是不是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId,friendid);
        if(noFriend != null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        //先删除好友表中Userid到Friendid这条数据
        friendDao.deleteFriend(userid,friendid);
        //更新friendid到userid的islike为0
        friendDao.updateIsLike("0",friendid,userid);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend); 
    }
}
