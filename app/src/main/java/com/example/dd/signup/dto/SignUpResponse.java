package com.example.dd.signup.dto;

import com.example.dd.login.dto.UsersData;

public class SignUpResponse {

    private String accessToken;

    private String refreshToken;

    private UsersData usersData;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UsersData getUsersData() {
        return usersData;
    }

    public void setUsersData(UsersData usersData) {
        this.usersData = usersData;
    }
}
