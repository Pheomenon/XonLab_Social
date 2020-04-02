package com.tensquare.controller;

import com.tensquare.client.UserClient;
import com.tensquare.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:Gao
 * @Date:2020-03-26 19:19
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;

    /**
     * 添加好友或者非好友
     * @return
     */
    @PutMapping(value = "/like/{friendid}/{type}")
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        //验证是否登录,并拿到登录的用户ID
        Claims claims = (Claims) request.getAttribute("claims_user");
        if(claims == null){
            //说明当前用户没有User角色
            return new Result(false,StatusCode.LOGINERROR,"权限不足");
        }
        //得到当前用户的Id
        String userId = claims.getId();
        //判断是添加好友还是添加非好友
        if(type != null){
            if(type.equals("1")){
                //添加好友
                int flag = friendService.addFriend(userId,friendid);
                if(flag == 0){
                    return new Result(false, StatusCode.ERROR,"重复添加");
                }
                if(flag == 1){
                    userClient.updateFansAndFallow(userId,friendid,1);
                    return new Result(true, StatusCode.OK,"添加成功");
                }
            }
            else if(type.equals("2")){
                //添加非好友
                int flag = friendService.addNoFriend(userId,friendid);
                if(flag == 0){
                    return new Result(false,StatusCode.ERROR,"不能重复添加非好友");
                }
                if(flag == 1){
                    return new Result(true, StatusCode.OK,"添加成功");
                }
            }
            return new Result(false, StatusCode.ERROR,"参数异常");
        }else {
            return new Result(false, StatusCode.ERROR,"参数异常");
        }
    }
    @DeleteMapping(value = "/{friendid}")
    public Result deleteFriend(@PathVariable String friendid){
        //验证是否登录,并拿到登录的用户ID
        Claims claims = (Claims) request.getAttribute("claims_user");
        if(claims == null){
            //说明当前用户没有User角色
            return new Result(false,StatusCode.LOGINERROR,"权限不足");
        }
        //得到当前用户的Id
        String userid = claims.getId();
        friendService.deleteFriend(userid,friendid);
        userClient.updateFansAndFallow(userid,friendid,-1);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}