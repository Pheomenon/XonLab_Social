package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Gao
 * @Date:2020-03-21 10:25
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @GetMapping(value = "/{spitId}")
    public Result findById(@PathVariable String spitId){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
    }

    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    @PutMapping(value = "/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @PostMapping(value = "{/spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @GetMapping(value = "/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentid,
                                 @PathVariable int page,
                                 @PathVariable int size){
        Page<Spit> pageData = spitService.findByParentId(parentid, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageData.getTotalElements(),pageData.getContent()));
    }

    @PutMapping(value = "/thumbup/{spitId}")
    public Result thumbUp(@PathVariable String spitId){
        //判断当前用户是否已经点赞，但当前没做认证，暂时把userid写死
        String userid = "111";
        //判断当前用户是否已经点赞
        if(redisTemplate.opsForValue().get("thumbup_"+userid) != null){
            return new Result(true,StatusCode.REPERROR,"重复点赞");
        }
        spitService.thumbUp(spitId);
        redisTemplate.opsForValue().set("thumbup_" + userid, 1);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

}
