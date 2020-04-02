package com.tensquare.qa.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:Gao
 * @Date:2020-03-26 15:51
 */
@FeignClient("tensquare-base")
public interface BaseClient {
    @GetMapping(value = "/label/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId);
}
