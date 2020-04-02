package com.tensquare.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @Author:Gao
 * @Date:2020-03-26 21:41
 */

@FeignClient("tensquare-user")
public interface UserClient {

    @PutMapping("/user/{userid}/{friendid}/{x}")
    public void updateFansAndFallow(@PathVariable("userid") String userid,
                                    @PathVariable("friendid") String friendid,
                                    @PathVariable("x") int x);
}
