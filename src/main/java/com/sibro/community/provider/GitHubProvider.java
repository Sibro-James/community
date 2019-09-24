package com.sibro.community.provider;

import com.alibaba.fastjson.JSON;
import com.sibro.community.dto.AccessTokenDTO;
import com.sibro.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    /*
    * 得到access_token
    * */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseStr = response.body().string();
            return (responseStr.split("&"))[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    * 得到登录用户信息
    * */
    public GitHubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?"+access_token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseStr = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(responseStr, GitHubUser.class);
            return gitHubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
