package com.tensquare.user.intercept;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:Gao
 * @Date:2020-03-23 21:33
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器起效");
        //拦截器只负责把请求头中的Token进行解析验证
        String header = request.getHeader("Authorization");
        if(header == null || "".equals(header)){
            throw new RuntimeException("权限不足");
        }
        if(!header.startsWith("Bearer ")){
            throw new RuntimeException("权限不足");
        }
        if(header != null && !"".equals(header)){
            //如果包含Authorization就进行解析
            if(header.startsWith("Bearer ")){
                //得到Token
                String token = header.substring(7);
                //进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String)claims.get("roles");
                    if(roles != null && roles.equals("admin")){
                        request.setAttribute("claims_admin",token);
                    }
                    if(roles != null && roles.equals("user")){
                        request.setAttribute("claims_user",token);
                    }
                }catch (Exception e){
                    throw new RuntimeException("令牌不正确");
                }
            }
        }
        return true;
    }
}
