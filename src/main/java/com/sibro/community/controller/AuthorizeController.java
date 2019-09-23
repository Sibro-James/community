package com.sibro.community.controller;

import com.sibro.community.dto.AccessTokenDTO;
import com.sibro.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,@RequestParam("state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("d14b35c68273cd81ca22");
        accessTokenDTO.setClient_secret("09593255ffb569886396ef8514fe1563be1584e6");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/calback");
        gitHubProvider.getAccessToken(accessTokenDTO);
        return "index";
    }
}
